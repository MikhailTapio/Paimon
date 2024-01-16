package com.plr.paimon.mixin;

import com.plr.paimon.common.api.IPaimonOwner;
import com.plr.paimon.common.core.ConfigHandler;
import com.plr.paimon.common.items.ModItems;
import net.minecraft.network.Connection;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.server.network.CommonListenerCookie;
import net.minecraft.server.players.PlayerList;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PlayerList.class)
public abstract class MixinPlayerList {
    @Inject(method = "placeNewPlayer", at = @At("TAIL"))
    private void inject$placeNewPlayer(Connection connection, ServerPlayer player, CommonListenerCookie commonListenerCookie, CallbackInfo ci) {
        if (!ConfigHandler.COMMON.spawnWithMedal.get()) return;
        final IPaimonOwner owner = (IPaimonOwner) player;
        if (owner.paimon$medalAcquired()) return;
        final ItemStack medal = new ItemStack(ModItems.paimonmedal);
        final ItemEntity i = new ItemEntity(player.level(), player.getX(), player.getY(), player.getZ(), medal);
        player.serverLevel().addFreshEntity(i);
        owner.paimon$setMedalAcquired(true);
    }
}
