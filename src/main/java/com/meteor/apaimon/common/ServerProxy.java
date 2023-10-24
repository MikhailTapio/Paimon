package com.meteor.apaimon.common;

import com.meteor.apaimon.common.core.IProxy;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.eventbus.api.IEventBus;

public class ServerProxy
        implements IProxy {
    public void registerHandlers() {
        IEventBus forgeBus = MinecraftForge.EVENT_BUS;
    }
}