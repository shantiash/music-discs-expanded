package com.shantiashams.musicdiscsexpanded.util;

import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents one logical structure target.
 * A target may contain several chest loot tables, as strongholds do.
 */
public record StructureLootTarget(Set<ResourceKey<LootTable>> lootTables) {
    public StructureLootTarget {
        Objects.requireNonNull(lootTables, "lootTables");

        if (lootTables.isEmpty()) {
            throw new IllegalArgumentException("A structure target must contain at least one loot table.");
        }

        lootTables = Collections.unmodifiableSet(new LinkedHashSet<>(lootTables));
    }
}
