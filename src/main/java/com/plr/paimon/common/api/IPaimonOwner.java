package com.plr.paimon.common.api;

public interface IPaimonOwner {
    void paimon$setPaimonEntityId(int paimon$paimonEntityId);

    int paimon$getPaimonEntityId();

    void paimon$setMedalAcquired(boolean paimon$medalAcquired);

    boolean paimon$medalAcquired();
}
