package com.plr.paimon.common.api;

public interface IPaimonOwner {
    void setPaimonEntityId(int paimonEntityId);

    int getPaimonEntityId();

    void setMedalAcquired(boolean medalAcquired);

    boolean medalAcquired();
}
