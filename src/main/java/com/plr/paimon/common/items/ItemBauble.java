package com.plr.paimon.common.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import com.plr.paimon.common.core.EquipmentHandler;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;

import javax.annotation.Nullable;

public abstract class ItemBauble
        extends Item {
    private static final String TAG_BAUBLE_UUID_MOST = "baubleUUIDMost";
    private static final String TAG_BAUBLE_UUID_LEAST = "baubleUUIDLeast";

    public ItemBauble(Item.Properties props) {
        super(props);
    }


    @Nullable
    public ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return EquipmentHandler.initBaubleCap(stack);
    }


    public void onWornTick(ItemStack stack, LivingEntity entity) {
    }


    public void onEquipped(ItemStack stack, LivingEntity entity) {
    }

    public void onUnequipped(ItemStack stack, LivingEntity entity) {
    }

    public boolean canEquip(ItemStack stack, LivingEntity entity) {
        return true;
    }

    public Multimap<Attribute, AttributeModifier> getEquippedAttributeModifiers(ItemStack stack) {
        return HashMultimap.create();
    }
}