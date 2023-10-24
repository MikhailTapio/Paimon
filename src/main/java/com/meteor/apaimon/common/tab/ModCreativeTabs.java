package com.meteor.apaimon.common.tab;

import com.meteor.apaimon.common.items.ModItems;
import com.meteor.apaimon.common.libs.LibMisc;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeTabs {
    public static final DeferredRegister<CreativeModeTab> TABS = DeferredRegister.create(Registries.CREATIVE_MODE_TAB, LibMisc.MOD_ID);

    public static final RegistryObject<CreativeModeTab> PAIMON = TABS.register("paimon", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup.paimon"))
            .icon(() -> ModItems.paimonmedal.get().getDefaultInstance())
            .displayItems((p, o) -> o.accept(ModItems.paimonmedal.get()))
            .build());
}
