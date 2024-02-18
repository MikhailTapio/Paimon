package com.plr.paimon;

import com.plr.paimon.common.core.ConfigHandler;
import com.plr.paimon.common.core.EquipmentHandler;
import com.plr.paimon.common.core.ModSounds;
import com.plr.paimon.common.entities.ModEntities;
import com.plr.paimon.common.items.ModItems;
import com.plr.paimon.common.tab.ModCreativeTabs;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModList;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.fml.common.Mod;
import net.neoforged.fml.config.ModConfig;
import net.neoforged.fml.event.lifecycle.FMLCommonSetupEvent;


@Mod(Constants.MOD_ID)
public class Paimon {
    public static boolean curiosLoaded = false;

    public Paimon(IEventBus bus) {
        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
        bus.addListener(this::commonSetup);
        ModSounds.SOUNDS.register(bus);
        ModItems.ITEMS.register(bus);
        ModEntities.ENTITIES.register(bus);
        ModCreativeTabs.TABS.register(bus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        curiosLoaded = ModList.get().isLoaded("curios");
        EquipmentHandler.init();
    }
}