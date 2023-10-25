package com.plr.paimon.common.items;

import com.plr.paimon.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.Item;


public class ModItems {
    public static final Item paimonmedal = Registry.register(BuiltInRegistries.ITEM, new ResourceLocation(Constants.MOD_ID, "paimonmedal"),
            new ItemPaimonMedal());

    public static void register() {
    }
}