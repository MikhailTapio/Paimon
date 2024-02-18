package com.plr.paimon.common.core;

import net.minecraftforge.common.ForgeConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ConfigHandler {
    public static final Client CLIENT;
    public static final ForgeConfigSpec CLIENT_SPEC;
    public static final Common COMMON;
    public static final ForgeConfigSpec COMMON_SPEC;

    static {
        Pair<Client, ForgeConfigSpec> pair = new ForgeConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = pair.getRight();
        CLIENT = pair.getLeft();


        Pair<Common, ForgeConfigSpec> specPair = new ForgeConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        public final ForgeConfigSpec.BooleanValue spawnWithMedal;
        public final ForgeConfigSpec.BooleanValue getMedalByFishing;
        public final ForgeConfigSpec.IntValue soundInterval;

        public Common(ForgeConfigSpec.Builder builder) {
            builder.push("common");
            this.spawnWithMedal = builder
                    .comment("Whether players will be rewarded with a Paimon Medal when they first join the world.")
                    .define("spawnWithMedal", false);
            this.getMedalByFishing = builder
                    .comment("Whether players will be rewarded with a Paimon Medal when they first fish an item up.")
                    .define("getMedalByFishing", true);
            this.soundInterval = builder
                    .comment("The interval of Paimon's speech in tick.")
                    .defineInRange("soundInterval", 1200, 0, Integer.MAX_VALUE);
            builder.pop();
        }
    }

    public static class Client {
        public Client(ForgeConfigSpec.Builder builder) {
        }
    }
}