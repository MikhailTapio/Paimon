package com.plr.paimon.mixin;

import com.mojang.authlib.GameProfile;
import com.plr.paimon.common.api.IPaimonOwner;
import net.minecraft.core.BlockPos;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ServerPlayer.class)
public abstract class MixinServerPlayer extends Player implements IPaimonOwner {
    @Unique
    private int paimon$paimonId = -1;
    @Unique
    private boolean paimon$rewardGained = false;

    public MixinServerPlayer(Level pLevel, BlockPos pPos, float pYRot, GameProfile pGameProfile) {
        super(pLevel, pPos, pYRot, pGameProfile);
    }

    @Override
    public void paimon$setPaimonId(int id) {
        this.paimon$paimonId = id;
    }

    @Override
    public int paimon$getPaimonId() {
        return paimon$paimonId;
    }

    @Override
    public void paimon$setRewardGained(boolean gained) {
        this.paimon$rewardGained = gained;
    }

    @Override
    public boolean paimon$rewardGained() {
        return paimon$rewardGained;
    }

    @Inject(method = "addAdditionalSaveData", at = @At("TAIL"))
    private void inject$addAdditionalSaveData(CompoundTag pCompound, CallbackInfo ci) {
        pCompound.putInt("paimon_id", paimon$paimonId);
    }

    @Inject(method = "readAdditionalSaveData", at = @At("TAIL"))
    private void inject$readAdditionalSaveData(CompoundTag pCompound, CallbackInfo ci) {
        paimon$paimonId = pCompound.getInt("paimon_id");
    }

}
