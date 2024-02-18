package com.plr.paimon.common.core;

import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import top.theillusivec4.curios.api.CuriosApi;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;


public class CurioIntegration
        extends EquipmentHandler {
    protected ItemStack findItem(Item item, LivingEntity living) {
        final AtomicReference<ItemStack> r = new AtomicReference<>(ItemStack.EMPTY);
        CuriosApi.getCuriosInventory(living).flatMap(c -> c.findFirstCurio(item)).ifPresent(s -> r.set(s.stack()));
        return r.get();
    }


    protected ItemStack findItem(Predicate<ItemStack> pred, LivingEntity living) {
        final AtomicReference<ItemStack> r = new AtomicReference<>(ItemStack.EMPTY);
        CuriosApi.getCuriosInventory(living).flatMap(c -> c.findFirstCurio(pred)).ifPresent(s -> r.set(s.stack()));
        return r.get();
    }

}