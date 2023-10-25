package com.plr.paimon.common.core;

import dev.emi.trinkets.api.TrinketsApi;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;


public class TrinketsIntegration extends EquipmentHandler {

    protected ItemStack findItem(Item item, LivingEntity living) {
        final AtomicReference<ItemStack> r = new AtomicReference<>(ItemStack.EMPTY);
        TrinketsApi.getTrinketComponent(living).ifPresent(c -> {
            final var equipped = c.getEquipped(item);
            if (!equipped.isEmpty()) r.set(equipped.get(0).getB());
        });
        return r.get();
    }


    protected ItemStack findItem(Predicate<ItemStack> pred, LivingEntity living) {
        final AtomicReference<ItemStack> r = new AtomicReference<>(ItemStack.EMPTY);
        TrinketsApi.getTrinketComponent(living).ifPresent(c -> {
            final var equipped = c.getEquipped(pred);
            if (!equipped.isEmpty()) r.set(equipped.get(0).getB());
        });
        return r.get();
    }
}