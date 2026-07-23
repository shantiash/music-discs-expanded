package com.shantiashams.musicdiscsexpanded.util;

import net.minecraft.resources.Identifier;

/** Central location for the mod id and namespaced identifiers. */
public final class ModIds {
    public static final String MOD_ID = "musicdiscsexpanded";

    private ModIds() {
    }

    public static Identifier id(String path) {
        return Identifier.fromNamespaceAndPath(MOD_ID, path);
    }
}
