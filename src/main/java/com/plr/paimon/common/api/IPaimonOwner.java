package com.plr.paimon.common.api;

public interface IPaimonOwner {
    void paimon$setPaimonId(int id);

    int paimon$getPaimonId();

    boolean paimon$rewardGained();

    void paimon$setRewardGained(boolean gained);
}
