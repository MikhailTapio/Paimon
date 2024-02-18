package com.plr.paimon;

import com.plr.paimon.common.core.ConfigHandler;
import com.plr.paimon.common.core.EquipmentHandler;
import com.plr.paimon.common.core.ModSounds;
import com.plr.paimon.common.entities.ModEntities;
import com.plr.paimon.common.items.ModItems;
import com.plr.paimon.common.tab.ModCreativeTabs;
import fuzs.forgeconfigapiport.fabric.api.neoforge.v4.NeoForgeConfigRegistry;
import net.fabricmc.api.ModInitializer;
import net.neoforged.fml.config.ModConfig;

public class Paimon implements ModInitializer {
    @Override
    public void onInitialize() {
        NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        NeoForgeConfigRegistry.INSTANCE.register(Constants.MOD_ID, ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);
        ModSounds.register();
        ModItems.register();
        ModEntities.register();
        ModCreativeTabs.register();
        EquipmentHandler.init();
    }
}