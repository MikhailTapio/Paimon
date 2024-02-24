package com.plr.paimon.mixin;

import com.mojang.authlib.GameProfile;
import com.plr.paimon.common.api.IPaimonOwner;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player {
    public MixinServerPlayer(Level level, BlockPos blockPos, float f, GameProfile gameProfile) {
        super(level, blockPos, f, gameProfile);
    }

    @Inject(method = "restoreFrom", at = @At("TAIL"))
    private void inject$restoreFrom(ServerPlayer serverPlayer, boolean bl, CallbackInfo ci) {
        final IPaimonOwner extendedThis = (IPaimonOwner) this;
        final IPaimonOwner extendedThat = (IPaimonOwner) serverPlayer;
        extendedThis.paimon$setMedalAcquired(extendedThat.paimon$medalAcquired());
    }
}
