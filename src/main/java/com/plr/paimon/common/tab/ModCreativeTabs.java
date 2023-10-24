package com.plr.paimon.common.tab;

import com.plr.paimon.Constants;
import com.plr.paimon.common.items.ModItems;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, Constants.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PAIMON = TABS.register("paimon", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.paimon"))
            .icon(() -> ModItems.paimonmedal.get().getDefaultInstance())
            .displayItems((p, o) -> o.accept(ModItems.paimonmedal.get()))
            .build());
}
