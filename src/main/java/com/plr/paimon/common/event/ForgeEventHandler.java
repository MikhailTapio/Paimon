package com.plr.paimon.common.event;

import com.plr.paimon.common.items.ModItems;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.level.Level;
import net.minecraftforge.event.entity.player.ItemFishedEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.concurrent.ThreadLocalRandom;

@Mod.EventBusSubscriber
public class ForgeEventHandler {
    private static final String TAG_PAIMONREWARD = "paimonreward";

    @SubscribeEvent
    public static void onFished(ItemFishedEvent event) {
        final Player player = event.getEntity();
        final CompoundTag tag = player.getPersistentData();
        final CompoundTag data = tag.getCompound("PlayerPersisted");
        if (data.getBoolean(TAG_PAIMONREWARD)) return;
        event.setCanceled(true);
        final FishingHook hook = event.getHookEntity();
        final ItemEntity itementity = new ItemEntity(
                player.level(),
                hook.getX(), hook.getY(), hook.getZ(),
                ModItems.paimonmedal.get().getDefaultInstance()
        );
        final double d0 = player.getX() - hook.getX();
        final double d1 = player.getY() - hook.getY();
        final double d2 = player.getZ() - hook.getZ();
        final Level level = player.level();
        itementity.setDeltaMovement(
                d0 * .1,
                d1 * .1 + Math.sqrt(Math.sqrt(d0 * d0 + d1 * d1 + d2 * d2)) * .08,
                d2 * .1
        );
        level.addFreshEntity(itementity);
        level.addFreshEntity(new ExperienceOrb(
                level, player.getX(), player.getY() + 0.5D, player.getZ() + 0.5D,
                ThreadLocalRandom.current().nextInt(6) + 1));
        data.putBoolean(TAG_PAIMONREWARD, true);
        tag.put("PlayerPersisted", data);
    }
}
