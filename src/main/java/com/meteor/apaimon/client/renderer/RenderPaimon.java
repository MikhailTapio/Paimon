package com.meteor.apaimon.client.renderer;

import com.meteor.apaimon.client.ClientTickHandler;
import com.meteor.apaimon.client.model.ModelPaimon;
import com.meteor.apaimon.common.entities.EntityPaimon;
import com.meteor.apaimon.common.libs.LibMisc;
import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.mojang.math.Axis;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.renderer.MultiBufferSource;
import net.minecraft.client.renderer.entity.EntityRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;
import net.minecraft.client.renderer.texture.OverlayTexture;
import net.minecraft.resources.ResourceLocation;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

@OnlyIn(Dist.CLIENT)
public class RenderPaimon extends EntityRenderer<EntityPaimon> {
    private final EntityModel<EntityPaimon> paimonModel;

    public RenderPaimon(EntityRendererProvider.Context renderManager) {
        super(renderManager);
        this.paimonModel = new ModelPaimon(renderManager.bakeLayer(ModelPaimon.MODEL_LAYER_LOCATION));
    }

    @Override
    public void render(EntityPaimon entityIn, float entityYaw, float partialTicks, PoseStack matrixStackIn, MultiBufferSource bufferIn, int packedLightIn) {
        matrixStackIn.pushPose();
        matrixStackIn.translate(0.0D, 1.0D, 0.0D);
        if (!entityIn.getFollowing())
            matrixStackIn.translate(0.0D, 0.06D * Math.sin((ClientTickHandler.ticksInGame * 0.1F)), 0.0D);
        matrixStackIn.mulPose(Axis.YP.rotationDegrees(180.0F - entityYaw));
        matrixStackIn.mulPose(Axis.ZP.rotationDegrees(entityIn.getRotation()));
        matrixStackIn.mulPose(Axis.XP.rotationDegrees(entityIn.getPitch()));

        float s = 0.65F;
        this.paimonModel.setupAnim(entityIn, partialTicks, 0.0F, -0.1F, 0.0F, 0.0F);
        VertexConsumer vertexConsumer = bufferIn.getBuffer(this.paimonModel.renderType(getTextureLocation(entityIn)));
        matrixStackIn.scale(-1.0F, -1.0F, 1.0F);
        matrixStackIn.scale(s, s, s);
        this.paimonModel.renderToBuffer(matrixStackIn, vertexConsumer, packedLightIn, OverlayTexture.NO_OVERLAY, 1.0F, 1.0F, 1.0F, 1.0F);
        matrixStackIn.scale(1.0F / s, 1.0F / s, 1.0F / s);
        matrixStackIn.popPose();
        super.render(entityIn, entityYaw, partialTicks, matrixStackIn, bufferIn, packedLightIn);
    }

    @Override
    public ResourceLocation getTextureLocation(EntityPaimon entity) {
        return new ResourceLocation(LibMisc.MOD_ID, "textures/entity/paimon.png");
    }
}