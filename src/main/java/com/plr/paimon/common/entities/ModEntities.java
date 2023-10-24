package com.plr.paimon.common.entities;

import com.plr.paimon.Constants;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.MobCategory;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModEntities {
    public static final DeferredRegister<EntityType<?>> ENTITIES = DeferredRegister.create(ForgeRegistries.ENTITY_TYPES, Constants.MOD_ID);

    public static final RegistryObject<EntityType<EntityPaimon>> PAIMON = ENTITIES.register("paimon", () -> EntityType.Builder.<EntityPaimon>of(EntityPaimon::new, MobCategory.MISC)
            .sized(0.6F, 0.8F)
            .setUpdateInterval(10)
            .setTrackingRange(64)
            .setShouldReceiveVelocityUpdates(true)
            .build("paimon"));

}