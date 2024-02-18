package com.plr.paimon.client.event;

import com.plr.paimon.client.model.ModelPaimon;
import com.plr.paimon.client.renderer.RenderPaimon;
import com.plr.paimon.common.entities.ModEntities;
import net.neoforged.api.distmarker.Dist;
import net.neoforged.bus.api.SubscribeEvent;
import net.neoforged.fml.common.Mod;
import net.neoforged.neoforge.client.event.EntityRenderersEvent;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientModEventHandler {
    @SubscribeEvent
    public static void registerModels(EntityRenderersEvent.RegisterRenderers evt) {
        evt.registerEntityRenderer(ModEntities.PAIMON.get(), RenderPaimon::new);
    }

    @SubscribeEvent
    public static void registerLayerDefs(EntityRenderersEvent.RegisterLayerDefinitions evt) {
        evt.registerLayerDefinition(ModelPaimon.MODEL_LAYER_LOCATION, ModelPaimon::createBodyLayer);
    }
}