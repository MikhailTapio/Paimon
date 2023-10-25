package com.plr.paimon.common.tab;

import com.plr.paimon.Constants;
import com.plr.paimon.common.items.ModItems;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.CreativeModeTab;

public class ModCreativeTabs {
    public static final CreativeModeTab PAIMON = Registry.register(BuiltInRegistries.CREATIVE_MODE_TAB, new ResourceLocation(Constants.MOD_ID, Constants.MOD_ID), CreativeModeTab.builder(CreativeModeTab.Row.TOP, 0)
            .title(Component.translatable("itemGroup.paimon"))
            .icon(ModItems.paimonmedal::getDefaultInstance)
            .displayItems((p, o) -> o.accept(ModItems.paimonmedal))
            .build());

    public static void register() {
    }
}
