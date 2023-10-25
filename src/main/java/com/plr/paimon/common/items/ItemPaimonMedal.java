package com.plr.paimon.common.items;

import com.plr.paimon.common.core.ConfigHandler;
import com.plr.paimon.common.core.ModSounds;
import com.plr.paimon.common.entities.EntityPaimon;
import com.plr.paimon.common.tab.ModCreativeTabs;
import net.minecraft.ChatFormatting;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.network.chat.TranslatableComponent;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.Vec3;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.entity.player.PlayerEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.items.ItemHandlerHelper;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.event.DropRulesEvent;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.List;

public class ItemPaimonMedal extends ItemBauble {
    public static String TAG_PAIMONID = "paimon_id";
    public static String TAG_PAIMONREWARD = "paimonreward";

    public ItemPaimonMedal() {
        super(new Properties().rarity(Rarity.EPIC).stacksTo(1).setNoRepair().tab(ModCreativeTabs.PAIMON));
        MinecraftForge.EVENT_BUS.addListener(this::onPlayerJoinWorld);
        MinecraftForge.EVENT_BUS.addListener(this::keepCurioDrops);
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(new TranslatableComponent("paimon.paimonmedalinfo").withStyle(ChatFormatting.ITALIC));
    }

    public void onWornTick(ItemStack stack, LivingEntity entity) {
        super.onWornTick(stack, entity);
        if (entity instanceof Player player) {

            CompoundTag nbtData = player.getPersistentData();
            CompoundTag data = nbtData.getCompound("PlayerPersisted");
            if (!data.contains(TAG_PAIMONID)) {
                data.putInt(TAG_PAIMONID, -1);
            }
            boolean checkPaimonExistence = false;
            int RANGE = 5;
            AABB axis = new AABB(player.blockPosition().offset(-RANGE, -RANGE, -RANGE), player.blockPosition().offset(RANGE, RANGE, RANGE));
            List<EntityPaimon> paimons = player.level.getEntitiesOfClass(EntityPaimon.class, axis);
            for (EntityPaimon paimon : paimons) {
                if (paimon.getOwnerID() == player.getId()) {
                    checkPaimonExistence = true;

                    break;
                }
            }
            int id = data.getInt(TAG_PAIMONID);
            Entity e = player.level.getEntity(id);
            if (!player.getCooldowns().isOnCooldown(this) && !checkPaimonExistence && (!(e instanceof EntityPaimon))) {
                Vec3 lookVec = player.getLookAngle().normalize().scale(1.5D);
                Vec3 spawnPoint = player.position().add(lookVec.x, 1.0D, lookVec.z);
                EntityPaimon paimon = new EntityPaimon(player.level, spawnPoint.x, spawnPoint.y, spawnPoint.z);
                paimon.setOwnerID(player.getId());
                paimon.faceEntity(player, 360.0F, 360.0F);
                if (!player.level.isClientSide) {
                    player.level.addFreshEntity(paimon);
                    randomSpawnSound(paimon, player.level.random.nextInt(2));
                    player.getCooldowns().addCooldown(this, 100);
                }
                data.putInt(TAG_PAIMONID, paimon.getId());
            }
        }
    }


    public float getSoundVolume() {
        return 0.25F;
    }

    public void randomSpawnSound(Entity entity, int i) {
        switch (i) {
            case 0 -> entity.playSound(ModSounds.paimon_spawn_0.get(), getSoundVolume(), 1.0F);
            case 1 -> entity.playSound(ModSounds.paimon_0.get(), getSoundVolume(), 1.0F);
        }
    }

    @SubscribeEvent
    public void keepCurioDrops(DropRulesEvent event) {
        event.addOverride(stack -> (stack.getItem() == this), ICurio.DropRule.ALWAYS_KEEP);
    }


    @SubscribeEvent
    public void onPlayerJoinWorld(PlayerEvent.PlayerLoggedInEvent event) {
        CompoundTag nbtData = event.getEntity().getPersistentData();
        CompoundTag data = nbtData.getCompound("PlayerPersisted");
        if (ConfigHandler.COMMON.spawnWithMedal.get() &&
                !data.getBoolean(TAG_PAIMONREWARD)) {
            ItemHandlerHelper.giveItemToPlayer(event.getPlayer(), new ItemStack(ModItems.paimonmedal.get()));
            data.putBoolean(TAG_PAIMONREWARD, true);
            nbtData.put("PlayerPersisted", data);
        }
    }
}