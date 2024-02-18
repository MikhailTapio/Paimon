package com.plr.paimon.common.items;

import com.plr.paimon.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(BuiltInRegistries.ITEM, Constants.MOD_ID);

    public static final DeferredHolder<Item, Item> paimonMedal = ITEMS.register("paimon_medal", ItemPaimonMedal::new);
}