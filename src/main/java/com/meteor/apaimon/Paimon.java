package com.meteor.apaimon;

import com.meteor.apaimon.common.core.ConfigHandler;
import com.meteor.apaimon.common.core.EquipmentHandler;
import com.meteor.apaimon.common.core.IProxy;
import com.meteor.apaimon.common.core.ModSounds;
import com.meteor.apaimon.common.entities.ModEntities;
import com.meteor.apaimon.common.items.ModItems;
import com.meteor.apaimon.common.libs.LibMisc;
import com.meteor.apaimon.common.tab.ModCreativeTabs;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.DistExecutor;
import net.minecraftforge.fml.ModList;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;


@Mod(LibMisc.MOD_ID)
public class Paimon {
    public static IProxy proxy;

    public static boolean curiosLoaded = false;

    public Paimon() {
        proxy = DistExecutor.unsafeRunForDist(() -> com.meteor.apaimon.client.ClientProxy::new, () -> com.meteor.apaimon.common.ServerProxy::new);
        proxy.registerHandlers();

        ModLoadingContext.get().registerConfig(ModConfig.Type.CLIENT, ConfigHandler.CLIENT_SPEC);
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, ConfigHandler.COMMON_SPEC);

        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::commonSetup);
        ModSounds.SOUNDS.register(modBus);
        ModItems.ITEMS.register(modBus);
        ModEntities.ENTITIES.register(modBus);
        ModCreativeTabs.TABS.register(modBus);
    }

    private void commonSetup(FMLCommonSetupEvent event) {
        curiosLoaded = ModList.get().isLoaded("curios");
        EquipmentHandler.init();
    }
}