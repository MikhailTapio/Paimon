package com.plr.paimon.common.items;

import com.plr.paimon.common.api.IPaimonOwner;
import com.plr.paimon.common.core.ModSounds;
import com.plr.paimon.common.entities.EntityPaimon;
import net.minecraft.ChatFormatting;
import net.minecraft.network.chat.Component;
import net.minecraft.server.level.ServerPlayer;
import net.minecraft.world.damagesource.DamageSource;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Rarity;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.Vec3;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.type.capability.ICurio;
import top.theillusivec4.curios.api.type.capability.ICurioItem;

import java.util.List;

public class ItemPaimonMedal extends Item implements ICurioItem {
    public static String TAG_PAIMONID = "paimon_id";

    public ItemPaimonMedal() {
        super(new Properties().rarity(Rarity.EPIC).stacksTo(1).setNoRepair());
    }

    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level world, List<Component> tooltip, TooltipFlag flags) {
        super.appendHoverText(stack, world, tooltip, flags);
        tooltip.add(Component.translatable("paimon.info.paimon_medal").withStyle(ChatFormatting.ITALIC));
    }

    @Override
    public void curioTick(SlotContext slotContext, ItemStack stack) {
        if (!(slotContext.entity() instanceof ServerPlayer player)) return;
        if (player.level().isClientSide() || player.level().getGameTime() % 20 != 0) return;
        final IPaimonOwner owner = (IPaimonOwner) player;
        int id = owner.paimon$getPaimonId();
        Entity e = player.level().getEntity(id);
        if (!player.getCooldowns().isOnCooldown(this) && (!(e instanceof EntityPaimon))) {
            Vec3 lookVec = player.getLookAngle().normalize().scale(1.5D);
            Vec3 spawnPoint = player.position().add(lookVec.x, 1.0D, lookVec.z);
            EntityPaimon paimon = new EntityPaimon(player.level(), spawnPoint.x, spawnPoint.y, spawnPoint.z);
            paimon.setOwnerID(player.getId());
            paimon.faceEntity(player, 360.0F, 360.0F);
            player.level().addFreshEntity(paimon);
            randomSpawnSound(paimon, player.level().random.nextInt(2));
            player.getCooldowns().addCooldown(this, 100);
            owner.paimon$setPaimonId(paimon.getId());
        }
    }

    @Override
    public void onUnequip(SlotContext slotContext, ItemStack newStack, ItemStack stack) {
        if (!(slotContext.entity() instanceof ServerPlayer player)) return;
        final Level level = player.level();
        if (level.isClientSide()) return;
        final IPaimonOwner owner = (IPaimonOwner) player;
        if (!(level.getEntity(owner.paimon$getPaimonId()) instanceof EntityPaimon paimon)) return;
        paimon.vanish();
        owner.paimon$setPaimonId(-1);
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

    @NotNull
    @Override
    public ICurio.DropRule getDropRule(SlotContext slotContext, DamageSource source, int lootingLevel, boolean recentlyHit, ItemStack stack) {
        return ICurio.DropRule.ALWAYS_KEEP;
    }
}