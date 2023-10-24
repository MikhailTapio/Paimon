package com.plr.paimon.client.event;

import com.plr.paimon.client.model.ModelPaimon;
import com.plr.paimon.client.renderer.RenderPaimon;
import com.plr.paimon.common.entities.ModEntities;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.EntityRenderersEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

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