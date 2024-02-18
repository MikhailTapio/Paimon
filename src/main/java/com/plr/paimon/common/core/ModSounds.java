package com.plr.paimon.common.core;

import com.plr.paimon.Constants;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.neoforged.neoforge.registries.DeferredHolder;
import net.neoforged.neoforge.registries.DeferredRegister;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUNDS = DeferredRegister.create(BuiltInRegistries.SOUND_EVENT, Constants.MOD_ID);

    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_0 = makeSoundEvent("paimon_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_1 = makeSoundEvent("paimon_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_2 = makeSoundEvent("paimon_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_3 = makeSoundEvent("paimon_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_4 = makeSoundEvent("paimon_4");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_5 = makeSoundEvent("paimon_5");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_6 = makeSoundEvent("paimon_6");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_7 = makeSoundEvent("paimon_7");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_8 = makeSoundEvent("paimon_8");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_9 = makeSoundEvent("paimon_9");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_10 = makeSoundEvent("paimon_10");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_11 = makeSoundEvent("paimon_11");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_12 = makeSoundEvent("paimon_12");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_13 = makeSoundEvent("paimon_13");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_tp_0 = makeSoundEvent("paimon_tp_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_tp_1 = makeSoundEvent("paimon_tp_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_tp_2 = makeSoundEvent("paimon_tp_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_tp_3 = makeSoundEvent("paimon_tp_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_spawn_0 = makeSoundEvent("paimon_spawn_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_0 = makeSoundEvent("paimon_vanish_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_1 = makeSoundEvent("paimon_vanish_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_2 = makeSoundEvent("paimon_vanish_2");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_3 = makeSoundEvent("paimon_vanish_3");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_4 = makeSoundEvent("paimon_vanish_4");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_5 = makeSoundEvent("paimon_vanish_5");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_vanish_6 = makeSoundEvent("paimon_vanish_6");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_thank_0 = makeSoundEvent("paimon_thank_0");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_thank_1 = makeSoundEvent("paimon_thank_1");
    public static final DeferredHolder<SoundEvent, SoundEvent> paimon_thank_2 = makeSoundEvent("paimon_thank_2");

    private static DeferredHolder<SoundEvent, SoundEvent> makeSoundEvent(String name) {
        return SOUNDS.register(name, () -> SoundEvent.createVariableRangeEvent(new ResourceLocation(Constants.MOD_ID, name)));
    }
}