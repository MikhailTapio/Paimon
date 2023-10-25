package com.plr.paimon.common.tab;

import com.plr.paimon.common.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class ModCreativeTabs {
    public static final CreativeModeTab PAIMON = new CreativeModeTab("paimon") {
        @Override
        public ItemStack makeIcon() {
            return ModItems.paimonmedal.get().getDefaultInstance();
        }
    };
}
