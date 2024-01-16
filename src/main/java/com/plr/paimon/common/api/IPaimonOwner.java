package com.plr.paimon.common.api;

public interface IPaimonOwner {
    void paimon$setPaimonEntityId(int paimonEntityId);

    int paimon$getPaimonEntityId();

    void paimon$setMedalAcquired(boolean medalAcquired);

    boolean paimon$medalAcquired();
}
