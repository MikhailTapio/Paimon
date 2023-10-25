package com.plr.paimon.common.entities;

import com.plr.paimon.common.core.ConfigHandler;
import com.plr.paimon.common.core.EquipmentHandler;
import com.plr.paimon.common.core.ModSounds;
import com.plr.paimon.common.items.ModItems;
import net.minecraft.core.particles.ParticleTypes;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.syncher.EntityDataAccessor;
import net.minecraft.network.syncher.EntityDataSerializers;
import net.minecraft.network.syncher.SynchedEntityData;
import net.minecraft.util.Mth;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.ThrowableProjectile;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;

public class EntityPaimon extends ThrowableProjectile {
    private static final int TP_SOUNDS = 4;
    private static final int RANDOM_SOUNDS = 14;
    private static final int VANISH_SOUNDS = 7;
    private static final int THANK_SOUNDS = 3;
    private static final String TAG_PITCH = "pitch";
    private static final String TAG_ROTATION = "rotation";
    private static final String TAG_OWNER_ID = "owner_id";
    private static final String TAG_FOLLOWING = "following";
    private static final String TAG_ANIMATION = "animation";
    private static final String TAG_VOICECD = "voicecd";
    private static final String TAG_TPCD = "tpcd";
    private static final EntityDataAccessor<Integer> OWNER_ID = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Float> PITCH = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Float> ROTATION = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.FLOAT);

    private static final EntityDataAccessor<Boolean> FOLLOWING = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.BOOLEAN);

    private static final EntityDataAccessor<Integer> ANIMATION = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> VOICECD = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.INT);

    private static final EntityDataAccessor<Integer> TPCD = SynchedEntityData.defineId(EntityPaimon.class, EntityDataSerializers.INT);
    private int changeTicks;
    private final int MAX_CHANGE_TICKS;
    private int stayTicks;
    private int tooFarTicks;
    private int i;
    private final int MAX_ANIMATION_TICKS;

    public EntityPaimon(EntityType<? extends EntityPaimon> entityTypeIn, Level worldIn) {
        super(entityTypeIn, worldIn);

        this.changeTicks = 0;
        this.MAX_CHANGE_TICKS = 8;
        this.stayTicks = 0;
        this.tooFarTicks = 0;
        this.i = 0;
        this.MAX_ANIMATION_TICKS = 20;
    }

    public EntityPaimon(Level worldIn, double x, double y, double z) {
        super(ModEntities.PAIMON, worldIn);
        this.changeTicks = 0;
        this.MAX_CHANGE_TICKS = 8;
        this.stayTicks = 0;
        this.tooFarTicks = 0;
        this.i = 0;
        this.MAX_ANIMATION_TICKS = 20;
        setPos(x, y, z);
        this.xo = x;
        this.yo = y;
        this.zo = z;
        setDeltaMovement(Vec3.ZERO);
        setNoGravity(true);
        setAnimation(this.MAX_ANIMATION_TICKS);
        setVoiceCD(400);
        setTPCD(200);
        setDeltaMovement(Vec3.ZERO);
    }

    public void tick() {
        Player player = null;
        super.tick();
        clearFire();
        if (getAnimation() > 0) {
            setAnimation(getAnimation() - 1);
            if (getAnimation() <= this.MAX_ANIMATION_TICKS) {
                setDeltaMovement(0.0D, 0.08D, 0.0D);
                if (this.level().isClientSide) {
                    this.i += 30;
                    float r = 1.0F;
                    double x = getX() + r * Math.cos(Math.toRadians(this.i));
                    double y = getY() + ((this.i / 24.0) * 0.05);
                    double z = getZ() + r * Math.sin(Math.toRadians(this.i));
                    this.level().addParticle(ParticleTypes.END_ROD, x, y, z, 0.0D, -0.04D, 0.0D);
                }
                Vec3 v = position().add(getLookAngle().yRot((float) Math.toRadians(60.0D)));
                facePos(v.x, v.y, v.z);
            }

            return;
        }
        if (getVoiceCD() > 0)
            setVoiceCD(getVoiceCD() - 1);
        if (getTPCD() > 0) {
            setTPCD(getTPCD() - 1);
        }
        if (getOwnerID() != -1) {
            Entity owner = this.level().getEntity(getOwnerID());
            if (owner instanceof Player) {
                player = (Player) owner;
            }
        }

        if (player == null || EquipmentHandler.findOrEmpty(ModItems.paimonmedal, player).isEmpty()) {
            if (this.level().isClientSide)
                for (this.i = 0; this.i < 720; this.i += 24) {
                    float r = 0.6F;
                    double x = getX() + r * Math.cos(Math.toRadians(this.i));
                    double y = getY() - 0.25D + ((this.i / 15.0) * 0.05);
                    double z = getZ() + r * Math.sin(Math.toRadians(this.i));
                    this.level().addParticle(ParticleTypes.END_ROD, x, y, z, 0.0D, -0.04D, 0.0D);
                }
            if (!this.level().isClientSide) {
                setVoiceCD(200);
                setTPCD(200);
                randomVanishSound(this.level().random.nextInt(7));
            }
            discard();

            return;
        }
        Vec3 playerPos = player.position();
        Vec3 lookVec = (new Vec3((player.getLookAngle()).x, 0.0D, (player.getLookAngle()).z)).normalize().yRot((float) Math.toRadians(30.0D)).reverse().scale(1.1D);
        Vec3 targetPos = playerPos.add(lookVec.x, lookVec.y, lookVec.z).add(0.0D, 1.2D, 0.0D);

        if (player.isCrouching() && player.getMainHandItem().getItem().isEdible()) {
            targetPos = playerPos.add((player.getLookAngle()).x, 1.2D, (player.getLookAngle()).z);
        } else if (player.getOffhandItem().getItem().isEdible()) {
            lookVec = lookVec.reverse();
            targetPos = playerPos.add(lookVec.x, lookVec.y, lookVec.z).add(0.0D, 1.2D, 0.0D);
        }

        if (position().distanceTo(targetPos) >= 16.0D) {
            this.tooFarTicks++;
        } else {
            this.tooFarTicks = 0;
        }
        if (this.tooFarTicks >= 20) {
            this.tooFarTicks = 0;
            teleportTo(targetPos.x, targetPos.y, targetPos.z);
            setDeltaMovement(Vec3.ZERO);
            if (getTPCD() == 0) {
                if (!this.level().isClientSide)
                    randomTPSound(this.level().random.nextInt(4));
                setTPCD(400);
                setVoiceCD(getTPCD() + 300);
            }
            setAnimation(this.MAX_ANIMATION_TICKS);

            return;
        }
        this.i = 0;

        if (getVoiceCD() == 0) {
            if (!this.level().isClientSide)
                randomSound(this.level().random.nextInt(14));
            setVoiceCD((int) (ConfigHandler.COMMON.soundInterval.get() + Math.random() * 400.0D));
            setTPCD(getTPCD() + 200);
        }

        if (posEqual(position(), targetPos)) {
            this.stayTicks++;
        } else {
            this.stayTicks = 0;
        }
        if (this.changeTicks >= this.MAX_CHANGE_TICKS) {
            setFollowing(this.stayTicks < 8 || !posEqual(position(), targetPos));
            this.changeTicks = 0;
        }
        this.changeTicks++;

        if (getFollowing()) {
            Vec3 motion = (new Vec3(targetPos.x - getX(), targetPos.y - getY(), targetPos.z - getZ())).normalize().scale(0.2199999988079071D);
            if (!posEqual(position(), targetPos)) {
                setDeltaMovement(motion);
                faceEntity(player, 360.0F, 360.0F);
                if (this.tickCount % 12 == 0 &&
                        this.level().isClientSide) {
                    this.level().addParticle(ParticleTypes.END_ROD, getX() - motion.x, getY(), getZ() - motion.z, -motion.x, -0.05D, -motion.z);
                }
            } else {
                setDeltaMovement(Vec3.ZERO);
            }
        } else {
            setDeltaMovement(Vec3.ZERO);
        }
    }

    public void defineSynchedData() {
        this.entityData.define(ROTATION, 0.0F);
        this.entityData.define(PITCH, 0.0F);
        this.entityData.define(OWNER_ID, -1);
        this.entityData.define(FOLLOWING, false);
        this.entityData.define(ANIMATION, 0);
        this.entityData.define(VOICECD, 0);
        this.entityData.define(TPCD, 0);
    }

    public boolean isPickable() {
        return !this.isRemoved();
    }


    public InteractionResult interact(Player player, InteractionHand hand) {
        if (player.isSecondaryUseActive()) {
            ItemStack stack = player.getItemInHand(hand);

            if (stack.getItem().isEdible()) {

                if (!this.level().isClientSide) {
                    if (getVoiceCD() <= ConfigHandler.COMMON.soundInterval.get()) {
                        randomThankSound(this.level().random.nextInt(3));
                        setVoiceCD((int) (getVoiceCD() + ConfigHandler.COMMON.soundInterval.get() * 0.5D));
                    }
                    if (!player.getAbilities().instabuild) {
                        stack.shrink(1);
                    }
                } else {
                    for (int i = 0; i < 5; i++) {
                        this.level().addParticle(ParticleTypes.HEART, getX() - 0.25D + 0.5D * Math.random(), getY() + 0.5D + 0.30000001192092896D * Math.random(), getZ() - 0.25D + 0.5D * Math.random(), 0.0D, 0.029999999329447746D, 0.0D);
                    }
                }
                return InteractionResult.SUCCESS;
            }
        }
        return InteractionResult.PASS;
    }

    public float getSoundVolume() {
        return 0.3F;
    }

    public void randomThankSound(int i) {
        switch (i) {
            case 0 -> playSound(ModSounds.paimon_thank_0, getSoundVolume(), 1.0F);
            case 1 -> playSound(ModSounds.paimon_thank_1, getSoundVolume(), 1.0F);
            case 2 -> playSound(ModSounds.paimon_thank_2, getSoundVolume(), 1.0F);
        }
    }

    public void randomVanishSound(int i) {
        switch (i) {
            case 0 -> playSound(ModSounds.paimon_vanish_0, getSoundVolume(), 1.0F);
            case 1 -> playSound(ModSounds.paimon_vanish_1, getSoundVolume(), 1.0F);
            case 2 -> playSound(ModSounds.paimon_vanish_2, getSoundVolume(), 1.0F);
            case 3 -> playSound(ModSounds.paimon_vanish_3, getSoundVolume(), 1.0F);
            case 4 -> playSound(ModSounds.paimon_vanish_4, getSoundVolume(), 1.0F);
            case 5 -> playSound(ModSounds.paimon_vanish_5, getSoundVolume(), 1.0F);
            case 6 -> playSound(ModSounds.paimon_vanish_6, getSoundVolume(), 1.0F);
        }
    }

    public void randomTPSound(int i) {
        switch (i) {
            case 0 -> playSound(ModSounds.paimon_tp_0, getSoundVolume(), 1.0F);
            case 1 -> playSound(ModSounds.paimon_tp_1, getSoundVolume(), 1.0F);
            case 2 -> playSound(ModSounds.paimon_tp_2, getSoundVolume(), 1.0F);
            case 3 -> playSound(ModSounds.paimon_tp_3, getSoundVolume(), 1.0F);
        }
    }

    public void randomSound(int i) {
        switch (i) {
            case 1 -> playSound(ModSounds.paimon_1, getSoundVolume(), 1.0F);
            case 2 -> playSound(ModSounds.paimon_2, getSoundVolume(), 1.0F);
            case 3 -> playSound(ModSounds.paimon_3, getSoundVolume(), 1.0F);
            case 4 -> playSound(ModSounds.paimon_4, getSoundVolume(), 1.0F);
            case 5 -> playSound(ModSounds.paimon_5, getSoundVolume(), 1.0F);
            case 6 -> playSound(ModSounds.paimon_6, getSoundVolume(), 1.0F);
            case 7 -> playSound(ModSounds.paimon_7, getSoundVolume(), 1.0F);
            case 8 -> playSound(ModSounds.paimon_8, getSoundVolume(), 1.0F);
            case 9 -> playSound(ModSounds.paimon_9, getSoundVolume(), 1.0F);
            case 10 -> playSound(ModSounds.paimon_10, getSoundVolume(), 1.0F);
            case 11 -> playSound(ModSounds.paimon_11, getSoundVolume(), 1.0F);
            case 12 -> playSound(ModSounds.paimon_12, getSoundVolume(), 1.0F);
            case 13 -> playSound(ModSounds.paimon_13, getSoundVolume(), 1.0F);
        }
    }


    protected void updateRotation() {
    }


    public void facePos(double x, double y, double z) {
        double d0 = x - getX();
        double d2 = z - getZ();

        double d3 = Mth.sqrt((float) (d0 * d0 + d2 * d2));
        float f = (float) (Mth.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
        float f1 = (float) -(Mth.atan2(y, d3) * 57.2957763671875D);
        this.setXRot(updateRotation(this.getXRot(), f1, 360.0F));
        this.setYRot(updateRotation(this.getYRot(), f, 360.0F));
    }

    public void faceEntity(Entity entityIn, float maxYawIncrease, float maxPitchIncrease) {
        double d1, d0 = entityIn.getX() - getX();
        double d2 = entityIn.getZ() - getZ();

        if (entityIn instanceof LivingEntity livingentity) {
            d1 = livingentity.getEyeY() - getEyeY();
        } else {
            d1 = ((entityIn.getBoundingBox()).minY + (entityIn.getBoundingBox()).maxY) / 2.0D - getEyeY();
        }

        double d3 = Mth.sqrt((float) (d0 * d0 + d2 * d2));
        float f = (float) (Mth.atan2(d2, d0) * 57.2957763671875D) - 90.0F;
        float f1 = (float) -(Mth.atan2(d1, d3) * 57.2957763671875D);
        this.setXRot(updateRotation(this.getXRot(), f1, maxPitchIncrease));
        this.setYRot(updateRotation(this.getYRot(), f, maxYawIncrease));
    }

    private float updateRotation(float angle, float targetAngle, float maxIncrease) {
        float f = Mth.wrapDegrees(targetAngle - angle);
        if (f > maxIncrease) {
            f = maxIncrease;
        }

        if (f < -maxIncrease) {
            f = -maxIncrease;
        }

        return angle + f;
    }

    public boolean posEqual(Vec3 v1, Vec3 v2) {
        return (v1.distanceTo(v2) <= 0.25D);
    }


    public void readAdditionalSaveData(CompoundTag compound) {
        setOwnerID(compound.getInt("owner_id"));
        setRotation(compound.getFloat("rotation"));
        setPitch(compound.getFloat("pitch"));
        setFollowing(compound.getBoolean("following"));
        setAnimation(compound.getInt("animation"));
        setVoiceCD(compound.getInt("voicecd"));
        setTPCD(compound.getInt("tpcd"));
    }


    public void addAdditionalSaveData(CompoundTag compound) {
        compound.putInt("owner_id", getOwnerID());
        compound.putFloat("rotation", getRotation());
        compound.putFloat("pitch", getPitch());
        compound.putBoolean("following", getFollowing());
        compound.putInt("animation", getAnimation());
        compound.putInt("voicecd", getVoiceCD());
        compound.putInt("tpcd", getTPCD());
    }

    public void setAnimation(int i) {
        this.entityData.set(ANIMATION, i);
    }

    public int getAnimation() {
        return this.entityData.get(ANIMATION);
    }

    public void setVoiceCD(int i) {
        this.entityData.set(VOICECD, i);
    }

    public int getVoiceCD() {
        return this.entityData.get(VOICECD);
    }

    public void setTPCD(int i) {
        this.entityData.set(TPCD, i);
    }

    public int getTPCD() {
        return this.entityData.get(TPCD);
    }

    public void setOwnerID(int i) {
        this.entityData.set(OWNER_ID, i);
    }

    public int getOwnerID() {
        return this.entityData.get(OWNER_ID);
    }

    public float getRotation() {
        return this.entityData.get(ROTATION);
    }

    public void setRotation(float rot) {
        this.entityData.set(ROTATION, rot);
    }

    public float getPitch() {
        return this.entityData.get(PITCH);
    }

    public void setPitch(float rot) {
        this.entityData.set(PITCH, rot);
    }

    public boolean getFollowing() {
        return this.entityData.get(FOLLOWING);
    }

    public void setFollowing(boolean following) {
        this.entityData.set(FOLLOWING, following);
    }
}