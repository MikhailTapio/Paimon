package com.plr.paimon.common.entities;

import com.plr.paimon.Constants;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;


public class ModEntities {
    public static final EntityType<EntityPaimon> PAIMON = Registry.register(BuiltInRegistries.ENTITY_TYPE,
            new ResourceLocation(Constants.MOD_ID, Constants.MOD_ID), EntityType.Builder.<EntityPaimon>of(EntityPaimon::new, MobCategory.MISC)
                    .sized(0.6F, 0.8F)
                    .updateInterval(10)
                    .clientTrackingRange(64)
                    .build("paimon"));

    public static void register() {
    }
}