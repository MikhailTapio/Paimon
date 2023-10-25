package com.plr.paimon.client.model;

import com.mojang.blaze3d.vertex.PoseStack;
import com.mojang.blaze3d.vertex.VertexConsumer;
import com.plr.paimon.Constants;
import com.plr.paimon.client.event.ClientTickHandler;
import com.plr.paimon.common.entities.EntityPaimon;
import net.minecraft.client.model.EntityModel;
import net.minecraft.client.model.geom.ModelLayerLocation;
import net.minecraft.client.model.geom.ModelPart;
import net.minecraft.client.model.geom.PartPose;
import net.minecraft.client.model.geom.builders.*;
import net.minecraft.resources.ResourceLocation;


public class ModelPaimon extends EntityModel<EntityPaimon> {
    public static final ModelLayerLocation MODEL_LAYER_LOCATION = new ModelLayerLocation(new ResourceLocation(Constants.MOD_ID, "paimon"), "main");

    public final ModelPart bone10;
    public final ModelPart body;
    public final ModelPart arms;
    public final ModelPart bone8;
    public final ModelPart bone9;
    public final ModelPart legs;
    public final ModelPart bone7;
    public final ModelPart cube_r1;
    public final ModelPart cube_r2;
    public final ModelPart cube_r3;
    public final ModelPart cube_r4;
    public final ModelPart head;
    public final ModelPart cube_r5;
    public final ModelPart blink;
    public final ModelPart bone6;
    public final ModelPart bone5;

    public ModelPaimon(ModelPart root) {
        this.bone10 = root.getChild("bone10");
        this.body = bone10.getChild("body");
        this.arms = body.getChild("arms");
        this.bone8 = arms.getChild("bone8");
        this.bone9 = arms.getChild("bone9");
        this.legs = body.getChild("legs");
        this.bone7 = body.getChild("bone7");
        this.cube_r1 = bone7.getChild("cube_r1");
        this.cube_r2 = bone7.getChild("cube_r2");
        this.cube_r3 = bone7.getChild("cube_r3");
        this.cube_r4 = bone7.getChild("cube_r4");
        this.head = bone10.getChild("head");
        this.cube_r5 = head.getChild("cube_r5");
        this.blink = head.getChild("blink");
        this.bone6 = head.getChild("bone6");
        this.bone5 = head.getChild("bone5");
    }

    public static LayerDefinition createBodyLayer() {
        MeshDefinition meshdefinition = new MeshDefinition();
        PartDefinition partdefinition = meshdefinition.getRoot();

        PartDefinition bone10 = partdefinition.addOrReplaceChild("bone10", CubeListBuilder.create(), PartPose.offset(0.0F, 24.0F, 0.0F));

        PartDefinition body = bone10.addOrReplaceChild("body", CubeListBuilder.create().texOffs(16, 29).addBox(-2.5F, -1.5659F, -2.7651F, 5.0F, 7.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(51, 43).addBox(1.0F, 5.0341F, -3.2651F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(27, 50).addBox(-3.0F, 5.0341F, -3.2651F, 2.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -7.3F, 0.9F));

        PartDefinition arms = body.addOrReplaceChild("arms", CubeListBuilder.create(), PartPose.offset(0.0F, 7.3F, -1.9F));

        PartDefinition bone8 = arms.addOrReplaceChild("bone8", CubeListBuilder.create().texOffs(8, 56).addBox(-1.5273F, 0.1303F, -0.75F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(59, 15).addBox(-1.0273F, 4.1303F, -1.25F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(3.5F, -7.75F, 0.75F));

        PartDefinition bone9 = arms.addOrReplaceChild("bone9", CubeListBuilder.create().texOffs(32, 6).addBox(-1.1745F, 4.3203F, -1.5F, 2.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(16, 56).addBox(-0.6745F, 0.3203F, -1.0F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.25F, -8.0F, 1.0F));

        PartDefinition legs = body.addOrReplaceChild("legs", CubeListBuilder.create().texOffs(0, 56).addBox(-2.25F, -1.25F, -0.75F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(41, 56).addBox(-2.75F, 2.25F, -1.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(13, 24).addBox(1.75F, 2.25F, -1.25F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(26, 55).addBox(0.25F, -1.25F, -0.75F, 2.0F, 6.0F, 2.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.0841F, -1.0151F));

        PartDefinition bone7 = body.addOrReplaceChild("bone7", CubeListBuilder.create().texOffs(56, 40).addBox(-2.0F, -8.0F, 2.4F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(53, 28).addBox(-3.0F, -7.0F, 1.9F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 37).addBox(-2.0F, -4.6F, 3.6F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(35, 52).addBox(-3.0F, -3.0F, 3.7F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 59).addBox(-1.5F, -2.5F, 4.5F, 3.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 4).addBox(-0.7F, -1.6F, 5.0F, 2.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 56).addBox(-1.3F, 1.0F, 4.3F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 21).addBox(-1.1F, 0.0F, 4.7F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(34, 56).addBox(-1.75F, -3.6F, 4.2F, 4.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(51, 48).addBox(-3.0F, -5.0F, 3.0F, 6.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(3.0F, -5.0F, 2.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 21).addBox(-4.0F, -5.0F, 2.75F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 56).addBox(2.5F, -6.0F, 2.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 51).addBox(-3.5F, -6.0F, 2.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 30).addBox(-2.5F, -6.0F, 3.0F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 52).addBox(-3.0F, -7.0F, 2.9F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 24).addBox(-3.0F, -7.0F, -1.1F, 6.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(56, 34).addBox(-2.0F, -8.0F, -2.0F, 4.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(55, 0).addBox(2.0F, -8.0F, -1.3F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F))
                .texOffs(14, 48).addBox(-3.0F, -8.0F, -1.3F, 1.0F, 1.0F, 4.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 6.8341F, -1.5151F));

        PartDefinition cube_r1 = bone7.addOrReplaceChild("cube_r1", CubeListBuilder.create().texOffs(24, 43).addBox(-4.3F, -3.0F, 3.25F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 4).addBox(-4.9F, -1.0F, 3.35F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 2).addBox(-4.9F, -2.9F, 2.85F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 37).addBox(-5.1F, -2.0F, 3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(6, 56).addBox(-4.3F, -2.0F, 3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 45).addBox(-3.3F, -2.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 0).addBox(-5.5F, 0.5F, 3.05F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(22, 56).addBox(-4.5F, 0.0F, 3.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(55, 2).addBox(-4.1F, -1.0F, 3.85F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 56).addBox(-3.1F, -1.0F, 4.35F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r2 = bone7.addOrReplaceChild("cube_r2", CubeListBuilder.create().texOffs(58, 13).addBox(1.4F, -3.0F, 3.25F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 58).addBox(2.4F, 0.0F, 4.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 5).addBox(3.4F, -1.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 50).addBox(3.2F, 0.0F, 3.55F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(7, 50).addBox(3.9F, -1.0F, 3.25F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 39).addBox(4.2F, -2.0F, 2.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(35, 50).addBox(3.8F, -4.0F, 2.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(35, 50).addBox(4.0F, -3.0F, 2.45F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(55, 0).addBox(3.4F, -2.0F, 3.75F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 7).addBox(2.4F, -2.0F, 4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r3 = bone7.addOrReplaceChild("cube_r3", CubeListBuilder.create().texOffs(38, 18).addBox(-0.5F, -1.0F, -0.5F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(-3.8F, -4.2F, 2.85F));

        PartDefinition cube_r4 = bone7.addOrReplaceChild("cube_r4", CubeListBuilder.create().texOffs(51, 44).addBox(1.9F, 1.0F, 4.4F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition head = bone10.addOrReplaceChild("head", CubeListBuilder.create().texOffs(21, 19).addBox(-3.0F, -13.0F, -2.5F, 6.0F, 5.0F, 5.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition cube_r5 = head.addOrReplaceChild("cube_r5", CubeListBuilder.create().texOffs(0, 0).addBox(-2.6F, -0.35F, -0.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 1).addBox(-0.6F, -0.35F, -0.3F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(13, 24).addBox(-1.6F, -1.1F, -0.3F, 1.0F, 3.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(4.5F, -15.5F, -4.0F));

        PartDefinition blink = head.addOrReplaceChild("blink", CubeListBuilder.create().texOffs(18, 45).addBox(3.0F, -11.0F, -2.6F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(38, 6).addBox(-4.0F, -11.0F, -2.6F, 1.0F, 1.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(48, 7).addBox(-3.0F, -13.0F, -2.6F, 6.0F, 5.0F, 0.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        PartDefinition bone6 = head.addOrReplaceChild("bone6", CubeListBuilder.create().texOffs(0, 16).addBox(-0.5F, -1.5833F, -2.5F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(33, 62).addBox(2.5F, -0.5833F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 18).addBox(-3.5F, -0.5833F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 60).addBox(-3.5F, -0.5833F, -1.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(46, 60).addBox(2.5F, -0.5833F, 2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(50, 60).addBox(1.5F, -0.5833F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(56, 32).addBox(-2.5F, -0.5833F, 3.5F, 5.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 9).addBox(-2.5F, -0.5833F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(57, 9).addBox(3.5F, -0.5833F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(49, 56).addBox(-4.5F, -0.5833F, -0.5F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, -19.3167F, 0.05F));

        PartDefinition bone5 = head.addOrReplaceChild("bone5", CubeListBuilder.create().texOffs(0, 8).addBox(-4.0F, -16.0F, -3.0F, 8.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 16).addBox(-3.0F, -17.0F, -3.0F, 6.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(63, 6).addBox(-5.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(6.0F, -13.0F, -2.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(61, 60).addBox(7.0F, -12.0F, -2.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 44).addBox(5.0F, -11.0F, -3.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(-6.0F, -14.0F, -3.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(15, 40).addBox(-6.0F, -11.0F, -3.0F, 1.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(0, 38).addBox(3.0F, -12.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(18, 45).addBox(3.0F, -11.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 6).addBox(-4.0F, -11.0F, -2.5F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 9).addBox(-5.0F, -12.0F, -3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 0).addBox(-5.0F, -15.0F, -3.0F, 10.0F, 1.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(31, 44).addBox(-4.0F, -13.0F, 3.0F, 8.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(42, 17).addBox(-4.0F, -15.0F, 4.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 29).addBox(-4.0F, -14.0F, 5.0F, 8.0F, 6.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 54).addBox(-3.0F, -15.0F, 5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 54).addBox(-3.0F, -8.0F, 5.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(38, 54).addBox(-3.0F, -16.0F, 4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(23, 8).addBox(4.0F, -13.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 36).addBox(3.0F, -10.0F, -4.0F, 1.0F, 3.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(7, 47).addBox(-4.0F, -10.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(-5.0F, -13.0F, -4.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 51).addBox(-4.0F, -13.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(49, 62).addBox(3.0F, -13.0F, -4.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(52, 54).addBox(-3.0F, -16.0F, -4.0F, 6.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(43, 15).addBox(-4.0F, -15.0F, -4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(14, 48).addBox(0.0F, -12.0F, -4.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(4, 24).addBox(4.0F, -13.0F, 4.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(4, 16).addBox(-5.0F, -13.0F, 4.0F, 1.0F, 4.0F, 0.0F, new CubeDeformation(0.0F))
                .texOffs(0, 24).addBox(2.0F, -14.0F, -3.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(23, 9).addBox(-5.0F, -14.0F, -3.0F, 3.0F, 2.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(49, 31).addBox(4.0F, -16.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(27, 33).addBox(5.0F, -14.0F, -3.0F, 1.0F, 3.0F, 7.0F, new CubeDeformation(0.0F))
                .texOffs(48, 1).addBox(5.0F, -15.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(13, 28).addBox(-5.0F, -7.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 25).addBox(-6.0F, -8.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(54, 57).addBox(-6.0F, -9.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(0, 12).addBox(-6.0F, -8.0F, 1.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(61, 2).addBox(-6.0F, -7.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 8).addBox(-7.0F, -13.0F, 0.0F, 1.0F, 2.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(57, 61).addBox(-8.0F, -12.0F, 0.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(53, 61).addBox(-7.0F, -8.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 47).addBox(-6.0F, -15.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(49, 37).addBox(3.0F, -17.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(7, 48).addBox(-5.0F, -16.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(19, 48).addBox(-4.0F, -17.0F, -2.0F, 1.0F, 1.0F, 5.0F, new CubeDeformation(0.0F))
                .texOffs(9, 33).addBox(4.0F, -9.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(63, 21).addBox(2.0F, -4.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(24, 45).addBox(-1.0F, -13.0F, -4.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 13).addBox(-5.0F, -14.0F, -4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 33).addBox(3.0F, -8.0F, -3.0F, 1.0F, 4.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(60, 62).addBox(4.0F, -10.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(62, 57).addBox(4.0F, -7.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(9, 38).addBox(3.0F, -8.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(16, 40).addBox(3.0F, -9.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(58, 21).addBox(5.0F, -9.0F, 1.0F, 1.0F, 1.0F, 3.0F, new CubeDeformation(0.0F))
                .texOffs(61, 0).addBox(5.0F, -8.0F, 1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(19, 19).addBox(5.0F, -9.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(19, 16).addBox(-6.0F, -9.0F, -2.0F, 1.0F, 1.0F, 2.0F, new CubeDeformation(0.0F))
                .texOffs(45, 62).addBox(3.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(37, 62).addBox(-4.0F, -6.0F, 3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 31).addBox(3.0F, -7.0F, 3.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 45).addBox(-4.0F, -7.0F, 4.0F, 8.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(59, 43).addBox(3.0F, -8.0F, 3.0F, 3.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 43).addBox(-5.0F, -8.0F, 4.0F, 10.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(27, 0).addBox(-5.0F, -13.0F, 4.0F, 10.0F, 5.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(37, 37).addBox(3.0F, -10.0F, -2.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(41, 62).addBox(-5.0F, -7.0F, -1.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(0, 29).addBox(-5.0F, -9.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(30, 29).addBox(-5.0F, -8.0F, -1.0F, 2.0F, 1.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(36, 6).addBox(-6.0F, -10.0F, -2.0F, 3.0F, 1.0F, 6.0F, new CubeDeformation(0.0F))
                .texOffs(20, 48).addBox(-4.0F, -8.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(26, 48).addBox(-5.0F, -9.0F, -3.0F, 1.0F, 2.0F, 1.0F, new CubeDeformation(0.0F))
                .texOffs(63, 19).addBox(-3.0F, -6.0F, -3.0F, 1.0F, 1.0F, 1.0F, new CubeDeformation(0.0F)), PartPose.offset(0.0F, 0.0F, 0.0F));

        return LayerDefinition.create(meshdefinition, 80, 80);
    }


    public void renderToBuffer(PoseStack matrixStack, VertexConsumer buffer, int packedLight, int packedOverlay, float red, float green, float blue, float alpha) {
        this.bone10.render(matrixStack, buffer, packedLight, packedOverlay);
    }

    public void setRotationAngle(ModelPart modelRenderer, float x, float y, float z) {
        modelRenderer.xRot = x;
        modelRenderer.yRot = y;
        modelRenderer.zRot = z;
    }

    @Override
    public void setupAnim(EntityPaimon entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch) {
        if (entity.getFollowing()) {
            setRotationAngle(this.bone8, 0.0F, 0.0F, (float) Math.toRadians(-42.5D + 4.0D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))));
            setRotationAngle(this.bone9, 0.0F, 0.0F, (float) Math.toRadians(42.5D - 4.0D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))));
            setRotationAngle(this.body, (float) Math.toRadians(12.5D), 0.0F, 0.0F);
            setRotationAngle(this.legs, (float) Math.toRadians(22.5D + 5.0D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))), 0.0F, 0.0F);
            setRotationAngle(this.bone7, (float) Math.toRadians(-2.5D + 2.5D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))), 0.0F, 0.0F);
        } else {
            setRotationAngle(this.bone8, 0.0F, 0.0F, (float) Math.toRadians(-27.5D + 4.0D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))));
            setRotationAngle(this.bone9, 0.0F, 0.0F, (float) Math.toRadians(27.5D - 4.0D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))));
            setRotationAngle(this.body, (float) Math.toRadians(2.5D), 0.0F, 0.0F);
            setRotationAngle(this.legs, (float) Math.toRadians(-2.5D + 5.0D * Math.sin((ClientTickHandler.ticksInGame * 0.1F))), 0.0F, 0.0F);
        }
        this.blink.visible = (ClientTickHandler.ticksInGame % 40 >= 2 && ClientTickHandler.ticksInGame % 40 <= 5);
    }
}