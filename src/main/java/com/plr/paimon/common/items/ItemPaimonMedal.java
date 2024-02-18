package com.plr.paimon.common.items;

import com.plr.paimon.common.api.IPaimonOwner;
import com.plr.paimon.common.core.ModSounds;
import com.plr.paimon.common.entities.EntityPaimon;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketEnums;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class ItemPaimonMedal extends TrinketItem {
    public ItemPaimonMedal() {
        super(new Properties().rarity(Rarity.EPIC).stacksTo(1).fireResistant());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(Component.translatable("paimon.paimonmedalinfo").withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.tick(stack, slot, entity);
        if (entity.level().isClientSide() || entity.level().getGameTime() % 20 != 0) return;
        if (entity instanceof Player player) {
            final IPaimonOwner owner = (IPaimonOwner) player;
            Entity e = player.level().getEntity(owner.paimon$getPaimonEntityId());
            if (!player.getCooldowns().isOnCooldown(this) && (!(e instanceof EntityPaimon))) {
                Vec3 lookVec = player.getLookAngle().normalize().scale(1.5D);
                Vec3 spawnPoint = player.position().add(lookVec.x, 1.0D, lookVec.z);
                EntityPaimon paimon = new EntityPaimon(player.level(), spawnPoint.x, spawnPoint.y, spawnPoint.z);
                paimon.setOwnerID(player.getId());
                paimon.faceEntity(player, 360.0F, 360.0F);
                player.level().addFreshEntity(paimon);
                randomSpawnSound(paimon, player.level().random.nextInt(2));
                player.getCooldowns().addCooldown(this, 100);
                owner.paimon$setPaimonEntityId(paimon.getId());
            }
        }
    }

    @Override
    public TrinketEnums.DropRule getDropRule(ItemStack stack, SlotReference slot, LivingEntity entity) {
        return TrinketEnums.DropRule.KEEP;
    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onUnequip(stack, slot, entity);
        if (!(entity instanceof Player player)) return;
        final Level level = player.level();
        if (level.isClientSide()) return;
        final IPaimonOwner owner = (IPaimonOwner) player;
        if (!(level.getEntity(owner.paimon$getPaimonEntityId()) instanceof EntityPaimon paimon)) return;
        paimon.vanish();
        owner.paimon$setPaimonEntityId(-1);
    }

    public float getSoundVolume() {
        return 0.25F;
    }

    public void randomSpawnSound(Entity entity, int i) {
        switch (i) {
            case 0 -> entity.playSound(ModSounds.paimon_spawn_0, getSoundVolume(), 1.0F);
            case 1 -> entity.playSound(ModSounds.paimon_0, getSoundVolume(), 1.0F);
        }
    }
}