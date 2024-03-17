package com.plr.paimon.common.core;

import com.google.common.collect.Multimap;
import com.plr.paimon.common.items.ItemBauble;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.fml.InterModComms;
import net.minecraftforge.fml.event.lifecycle.InterModEnqueueEvent;
import top.theillusivec4.curios.api.CuriosApi;
import top.theillusivec4.curios.api.CuriosCapability;
import top.theillusivec4.curios.api.SlotContext;
import top.theillusivec4.curios.api.SlotTypeMessage;
import top.theillusivec4.curios.api.type.capability.ICurio;

import java.util.UUID;
import java.util.concurrent.atomic.AtomicReference;
import java.util.function.Predicate;


public class CurioIntegration
        extends EquipmentHandler {
    public static void sendImc(InterModEnqueueEvent evt) {
        InterModComms.sendTo("curios", "register_type", () -> new SlotTypeMessage.Builder("paimon_medal")
                .size(1)
                .priority(210)
                .icon(new ResourceLocation("paimon", "slot/empty_paimon_slot"))
                .build());
    }


    protected ItemStack findItem(Item item, LivingEntity living) {
        final AtomicReference<ItemStack> r = new AtomicReference<>(ItemStack.EMPTY);
        CuriosApi.getCuriosHelper().findFirstCurio(living, item).ifPresent(s -> r.set(s.stack()));
        return r.get();
    }


    protected ItemStack findItem(Predicate<ItemStack> pred, LivingEntity living) {
        final AtomicReference<ItemStack> r = new AtomicReference<>(ItemStack.EMPTY);
        CuriosApi.getCuriosHelper().findFirstCurio(living, pred).ifPresent(s -> r.set(s.stack()));
        return r.get();
    }


    protected ICapabilityProvider initCap(ItemStack stack) {
        return new SimpleCapProvider<>(CuriosCapability.ITEM, new Wrapper(stack));
    }

    public static class Wrapper implements ICurio {
        private final ItemStack stack;

        Wrapper(ItemStack stack) {
            this.stack = stack;
        }

        private ItemBauble getItem() {
            return (ItemBauble) this.stack.getItem();
        }


        @Override
        public ItemStack getStack() {
            return stack;
        }

        @Override
        public void curioTick(SlotContext slotContext) {
            getItem().onWornTick(this.stack, slotContext.entity());
        }

        @Override
        public void onEquip(SlotContext slotContext, ItemStack prevStack) {
            getItem().onEquipped(this.stack, slotContext.entity());
        }

        @Override
        public void onUnequip(SlotContext slotContext, ItemStack newStack) {
            getItem().onUnequipped(this.stack, slotContext.entity());
        }

        @Override
        public boolean canEquip(SlotContext slotContext) {
            return getItem().canEquip(this.stack, slotContext.entity());
        }

        @Override
        public Multimap<Attribute, AttributeModifier> getAttributeModifiers(SlotContext slotContext, UUID uuid) {
            return getItem().getEquippedAttributeModifiers(this.stack);
        }

        @Override
        public boolean canSync(SlotContext slotContext) {
            return true;
        }

        @Override
        public void onEquipFromUse(SlotContext slotContext) {
            // Won't play any equip sound
        }

        @Override
        public boolean canEquipFromUse(SlotContext slotContext) {
            return true;
        }
    }
}