package com.plr.paimon.common.core;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.function.Predicate;


public abstract class EquipmentHandler {
    public static EquipmentHandler instance;

    public static void init() {
        instance = new TrinketsIntegration();
    }

    public static ItemStack findOrEmpty(Item item, LivingEntity living) {
        return instance.findItem(item, living);
    }

    public static ItemStack findOrEmpty(Predicate<ItemStack> pred, LivingEntity living) {
        return instance.findItem(pred, living);
    }

    protected abstract ItemStack findItem(Item paramItem, LivingEntity paramLivingEntity);

    protected abstract ItemStack findItem(Predicate<ItemStack> paramPredicate, LivingEntity paramLivingEntity);
}