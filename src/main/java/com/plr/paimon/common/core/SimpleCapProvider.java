package com.plr.paimon.common.core;

import net.minecraft.core.Direction;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SimpleCapProvider<C>
        implements ICapabilityProvider {
    private final C capInstance;
    private final LazyOptional<C> capOptional;
    private final Capability<C> capability;

    public SimpleCapProvider(Capability<C> capability, C capInstance) {
        this.capability = capability;
        this.capInstance = capInstance;
        this.capOptional = LazyOptional.of(() -> this.capInstance);
    }


    @Nonnull
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        return this.capability.orEmpty(cap, this.capOptional);
    }
}