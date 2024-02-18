package com.plr.paimon.common.tab;

import com.plr.paimon.Constants;
import com.plr.paimon.common.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final DeferredHolder<CreativeModeTab, CreativeModeTab> PAIMON = TABS.register("paimon", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.paimon"))
            .icon(() -> ModItems.paimonMedal.get().getDefaultInstance())
            .displayItems((p, o) -> o.accept(ModItems.paimonMedal.get()))
            .build());
}
