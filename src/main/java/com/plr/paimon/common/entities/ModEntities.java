package com.plr.paimon.common.entities;

import com.plr.paimon.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(BuiltInRegistries.ENTITY_TYPE, Constants.MOD_ID);

    public static final DeferredHolder<EntityType<?>, EntityType<EntityPaimon>> PAIMON = ENTITIES.register("paimon", () -> EntityType.Builder.<EntityPaimon>of(EntityPaimon::new, MobCategory.MISC)
            .sized(0.6F, 0.8F)
            .setUpdateInterval(10)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build("paimon"));

}