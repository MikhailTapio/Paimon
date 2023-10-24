package com.plr.paimon.common.core;

import com.plr.paimon.Paimon;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.items.IItemHandlerModifiable;

import java.util.function.Predicate;


public abstract class EquipmentHandler {
    public static EquipmentHandler instance;

    public static void init() {
        if (Paimon.curiosLoaded) {
            instance = new CurioIntegration();
            FMLJavaModLoadingContext.get().getModEventBus().addListener(CurioIntegration::sendImc);
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

    public static ICapabilityProvider initBaubleCap(ItemStack stack) {
        if (instance != null) {
            return instance.initCap(stack);
        }
        return null;
    }

    protected abstract ItemStack findItem(Item paramItem, LivingEntity paramLivingEntity);

    protected abstract ItemStack findItem(Predicate<ItemStack> paramPredicate, LivingEntity paramLivingEntity);

    protected abstract ICapabilityProvider initCap(ItemStack paramItemStack);

    static class InventoryEquipmentHandler
            extends EquipmentHandler {
        protected LazyOptional<IItemHandlerModifiable> getAllWornItems(LivingEntity living) {
            return LazyOptional.empty();
        }


        protected ItemStack findItem(Item item, LivingEntity living) {
            return ItemStack.EMPTY;
        }


        protected ItemStack findItem(Predicate<ItemStack> pred, LivingEntity living) {
            return ItemStack.EMPTY;
        }


        protected ICapabilityProvider initCap(ItemStack stack) {
            return null;
        }
    }
}