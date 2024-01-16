package com.plr.paimon.mixin;

import com.plr.paimon.common.api.IPaimonOwner;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Player.class)
public abstract class MixinPlayer extends LivingEntity implements IPaimonOwner {
    @Unique
    private int paimon$paimonEntityId = -1;
    @Unique
    private boolean paimon$medalAcquired = false;

    protected MixinPlayer(EntityType<? extends LivingEntity> entityType, Level level) {
        super(entityType, level);
    }

    @Override
    public void paimon$setPaimonEntityId(int paimonEntityId) {
        this.paimon$paimonEntityId = paimonEntityId;
    }

    @Override
    public int paimon$getPaimonEntityId() {
        return paimon$paimonEntityId;
    }

    @Override
    public void paimon$setMedalAcquired(boolean medalAcquired) {
        this.paimon$medalAcquired = medalAcquired;
    }

    @Override
    public boolean paimon$medalAcquired() {
        return paimon$medalAcquired;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("HEAD"))
    private void inject$writeCustomDataToNbt(CompoundTag nbt, CallbackInfo ci) {
        final CompoundTag tag = new CompoundTag();
        tag.putInt("paimon_id", paimon$paimonEntityId);
        tag.putBoolean("medal_acquired", paimon$medalAcquired);
        nbt.put("paimon_tag", tag);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("HEAD"))
    private void inject$readCustomDataFromNbt(CompoundTag nbt, CallbackInfo ci) {
        if (!nbt.contains("paimon_tag")) return;
        final CompoundTag tag = nbt.getCompound("paimon_tag");
        paimon$paimonEntityId = tag.getInt("paimon_id");
        paimon$medalAcquired = tag.getBoolean("medal_acquired");
    }
}
