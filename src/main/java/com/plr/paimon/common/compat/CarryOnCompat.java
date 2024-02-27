package com.plr.paimon.common.compat;

import net.minecraftforge.fml.InterModComms;

public class CarryOnCompat {
    public static void init() {
        InterModComms.sendTo("carryon", "blacklistEntity", () -> "paimon:paimon");
    }
}
