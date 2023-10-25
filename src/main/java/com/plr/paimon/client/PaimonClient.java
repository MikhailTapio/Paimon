package com.plr.paimon.client;

import com.plr.paimon.client.model.ModelPaimon;
import com.plr.paimon.client.renderer.RenderPaimon;
import com.plr.paimon.common.entities.ModEntities;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.rendering.v1.EntityModelLayerRegistry;
import net.fabricmc.fabric.api.client.rendering.v1.EntityRendererRegistry;

public class PaimonClient implements ClientModInitializer {
    @Override
    public void onInitializeClient() {
        EntityModelLayerRegistry.registerModelLayer(ModelPaimon.MODEL_LAYER_LOCATION, ModelPaimon::createBodyLayer);
        EntityRendererRegistry.register(ModEntities.PAIMON, RenderPaimon::new);
    }
}
