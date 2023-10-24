package com.meteor.apaimon.common.items;

import com.meteor.apaimon.common.libs.LibMisc;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;


public class ModItems {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, LibMisc.MOD_ID);

    public static final RegistryObject<Item> paimonmedal = ITEMS.register("paimonmedal", ItemPaimonMedal::new);
}