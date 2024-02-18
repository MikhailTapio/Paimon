package com.plr.paimon.mixin;

import com.plr.paimon.common.api.IPaimonOwner;
import com.plr.paimon.common.core.ConfigHandler;
import com.plr.paimon.common.items.ModItems;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.ExperienceOrb;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.entity.projectile.FishingHook;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.concurrent.ThreadLocalRandom;

@Mixin(FishingHook.class)
public abstract class MixinFishingHook extends Entity {
    public MixinFishingHook(EntityType<?> entityType, Level level) {
        super(entityType, level);
    }

    @Shadow
    @Nullable
    public abstract Player getPlayerOwner();

    @Inject(
            method = "retrieve",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/level/storage/loot/LootParams$Builder;withLuck(F)Lnet/minecraft/world/level/storage/loot/LootParams$Builder;"
            ),
            cancellable = true
    )
    private void inject$retrieve(ItemStack itemStack, CallbackInfoReturnable<Integer> cir) {
        if (!ConfigHandler.COMMON.getMedalByFishing.get()) return;
        final Player player = getPlayerOwner();
        if (player == null) return;
        final IPaimonOwner owner = (IPaimonOwner) player;
        if (owner.paimon$medalAcquired()) return;
        cir.setReturnValue(0);
        final ItemEntity itementity = new ItemEntity(
                player.level(),
                this.getX(), this.getY(), this.getZ(),
                ModItems.paimonmedal.getDefaultInstance()
        );
        final double d0 = player.getX() - this.getX();
        final double d1 = player.getY() - this.getY();
        final double d2 = player.getZ() - this.getZ();
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
        owner.paimon$setMedalAcquired(true);
        this.discard();
    }
}