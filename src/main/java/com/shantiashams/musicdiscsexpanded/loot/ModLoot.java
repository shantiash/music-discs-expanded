package com.shantiashams.musicdiscsexpanded.loot;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.item.ModItems;
import com.shantiashams.musicdiscsexpanded.util.StructureLootTarget;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
import net.minecraft.world.level.storage.loot.entries.LootItem;
import net.minecraft.world.level.storage.loot.predicates.LootItemRandomChanceCondition;
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/** Injects eligible discs into built-in structure loot tables at load time. */
public final class ModLoot {
    private static boolean initialized;

    private ModLoot() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        ModItems.initialize();
        Map<ResourceKey<LootTable>, List<DiscDefinition>> discsByLootTable = buildLootIndex();

        LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
            if (!source.isBuiltin()) {
                return;
            }

            List<DiscDefinition> eligibleDiscs = discsByLootTable.get(key);
            if (eligibleDiscs == null || eligibleDiscs.isEmpty()) {
                return;
            }

            /*
             * Each eligible disc gets its own independent loot pool and chance.
             * This allows every disc to have a different exact probability.
             * It also means an exceptionally lucky chest can contain more than
             * one custom disc when several discs target the same loot table.
             */
            for (DiscDefinition disc : eligibleDiscs) {
                LootPool.Builder pool = LootPool.lootPool()
                        .setRolls(ConstantValue.exactly(1.0F))
                        .when(LootItemRandomChanceCondition.randomChance(disc.lootChance()))
                        .add(LootItem.lootTableItem(ModItems.get(disc)));

                tableBuilder.withPool(pool);
            }
        });

        initialized = true;
    }

    private static Map<ResourceKey<LootTable>, List<DiscDefinition>> buildLootIndex() {
        Map<ResourceKey<LootTable>, List<DiscDefinition>> mutable = new LinkedHashMap<>();

        for (DiscDefinition disc : DiscRegistry.all()) {
            for (StructureLootTarget structure : disc.structures()) {
                for (ResourceKey<LootTable> lootTable : structure.lootTables()) {
                    mutable.computeIfAbsent(lootTable, ignored -> new ArrayList<>()).add(disc);
                }
            }
        }

        Map<ResourceKey<LootTable>, List<DiscDefinition>> immutable = new LinkedHashMap<>();
        mutable.forEach((key, value) -> immutable.put(key, List.copyOf(value)));
        return Collections.unmodifiableMap(immutable);
    }
}
