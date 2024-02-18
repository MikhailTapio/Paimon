package com.plr.paimon.common.core;

import com.plr.paimon.Paimon;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.neoforged.neoforge.items.IItemHandlerModifiable;

import java.util.Optional;
import java.util.function.Predicate;


public abstract class EquipmentHandler {
    public static EquipmentHandler instance;

    public static void init() {
        if (Paimon.curiosLoaded) {
            instance = new CurioIntegration();
        } else {
            instance = new InventoryEquipmentHandler();
        }
    }

    public static ItemStack findOrEmpty(Item item, LivingEntity living) {
        return instance.findItem(item, living);
    }

    public static ItemStack findOrEmpty(Predicate<ItemStack> pred, LivingEntity living) {
        return instance.findItem(pred, living);
    }

    protected abstract ItemStack findItem(Item paramItem, LivingEntity paramLivingEntity);

    protected abstract ItemStack findItem(Predicate<ItemStack> paramPredicate, LivingEntity paramLivingEntity);

    static class InventoryEquipmentHandler
            extends EquipmentHandler {
        protected Optional<IItemHandlerModifiable> getAllWornItems(LivingEntity living) {
            return Optional.empty();
        }


        protected ItemStack findItem(Item item, LivingEntity living) {
            return ItemStack.EMPTY;
        }


        protected ItemStack findItem(Predicate<ItemStack> pred, LivingEntity living) {
            return ItemStack.EMPTY;
        }
    }
}