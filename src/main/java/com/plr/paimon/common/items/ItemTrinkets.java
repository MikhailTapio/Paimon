package com.plr.paimon.common.items;

import com.google.common.collect.HashMultimap;
import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.TrinketItem;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

public abstract class ItemTrinkets
        extends TrinketItem {

    public ItemTrinkets(Item.Properties props) {
        super(props);
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