package com.meteor.apaimon.client;

import com.meteor.apaimon.client.model.ModelPaimon;
import com.meteor.apaimon.client.renderer.RenderPaimon;
import com.meteor.apaimon.common.core.IProxy;
import com.meteor.apaimon.common.entities.ModEntities;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;

public class ClientProxy
        implements IProxy {
    public void registerModels(EntityRenderersEvent.RegisterRenderers evt) {
        evt.registerEntityRenderer(ModEntities.PAIMON.get(), RenderPaimon::new);
    }

    public void registerLayerDefs(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(ModelPaimon.MODEL_LAYER_LOCATION, ModelPaimon::createBodyLayer);
    }


    public void onClientSetUpEvent(FMLClientSetupEvent event) {
    }


    public void registerHandlers() {
        IEventBus modBus = FMLJavaModLoadingContext.get().getModEventBus();
        modBus.addListener(this::onClientSetUpEvent);
        modBus.addListener(this::registerModels);
        modBus.addListener(this::registerLayerDefs);

        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
        forgeBus.addListener(ClientTickHandler::clientTickEnd);
    }
}