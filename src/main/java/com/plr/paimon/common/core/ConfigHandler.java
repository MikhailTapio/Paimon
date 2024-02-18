package com.plr.paimon.common.core;

import net.neoforged.neoforge.common.ModConfigSpec;
import org.apache.commons.lang3.tuple.Pair;

public final class ConfigHandler {
    public static final Client CLIENT;
    public static final ModConfigSpec CLIENT_SPEC;
    public static final Common COMMON;
    public static final ModConfigSpec COMMON_SPEC;

    static {
        Pair<Client, ModConfigSpec> pair = new ModConfigSpec.Builder().configure(Client::new);
        CLIENT_SPEC = pair.getRight();
        CLIENT = pair.getLeft();


        Pair<Common, ModConfigSpec> specPair = new ModConfigSpec.Builder().configure(Common::new);
        COMMON_SPEC = specPair.getRight();
        COMMON = specPair.getLeft();
    }

    public static class Common {
        public final ModConfigSpec.BooleanValue spawnWithMedal;
        public final ModConfigSpec.BooleanValue getMedalByFishing;
        public final ModConfigSpec.IntValue soundInterval;

        public Common(ModConfigSpec.Builder builder) {
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
        public Client(ModConfigSpec.Builder builder) {
        }
    }
}