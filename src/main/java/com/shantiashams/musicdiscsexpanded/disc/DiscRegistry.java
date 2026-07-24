package com.shantiashams.musicdiscsexpanded.disc;

import com.shantiashams.musicdiscsexpanded.util.StructureLootTarget;
import com.shantiashams.musicdiscsexpanded.util.Structures;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * THE ONLY JAVA FILE TO EDIT WHEN ADDING A MUSIC DISC.
 *
 * Add another register(...) call, then add the matching .ogg and .png files.
 * Comparator outputs are assigned automatically in registration order (1..15, then repeat).
 *
 * Loot chance is written as a decimal from 0.0F to 1.0F:
 * 0.10F = 10%, 0.02F = 2%, 0.005F = 0.5%.
 */
public final class DiscRegistry {
    private static final Pattern VALID_ID = Pattern.compile("[a-z0-9_./-]+");
    private static final List<DiscDefinition> BUILDING = new ArrayList<>();
    private static final Set<String> REGISTERED_IDS = new HashSet<>();

    private static final List<DiscDefinition> DISCS;

    static {
        register(
                "no_escape",
                "No Escape",
                "T_en_M ft. Laudividni",
                159,
                Rarity.RARE,
                0.10F,
                Structures.FORTRESS
        );
        register(
                "breeze",
                "Breeze",
                "T_en_M ft",
                120,
                Rarity.RARE,
                0.05F,
                Structures.TRIAL_CHAMBERS_REWARD  
        );
        register(
                "not_dead_yet",
                "Not Dead Yet",
                "Laudividni",
                123,
                Rarity.RARE,
                0.075F,
                Structures.DUNGEON
        );
        register(
                "voyage",
                "Voyage",
                "T_en_M ft. Laudividni",
                128,
                Rarity.RARE,
                0.1F,
                Structures.BURIED_TREASURE
        );

        // Add future discs above this line using the same register(...) shape.

        if (BUILDING.isEmpty()) {
            throw new IllegalStateException("At least one disc is required for the creative-tab icon.");
        }

        DISCS = List.copyOf(BUILDING);
    }

    private DiscRegistry() {
    }

    private static void register(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            StructureLootTarget... structures
    ) {
        validate(id, songName, artist, lootChance, structures);

        int comparatorOutput = (BUILDING.size() % 15) + 1;
        BUILDING.add(new DiscDefinition(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                comparatorOutput,
                List.of(structures)
        ));
    }

    private static void validate(
            String id,
            String songName,
            String artist,
            float lootChance,
            StructureLootTarget[] structures
    ) {
        if (id == null || !VALID_ID.matcher(id).matches()) {
            throw new IllegalArgumentException("Invalid disc id: " + id);
        }

        if (!REGISTERED_IDS.add(id)) {
            throw new IllegalArgumentException("Duplicate disc id: " + id);
        }

        if (songName == null || songName.isBlank()) {
            throw new IllegalArgumentException("Song name cannot be blank: " + id);
        }

        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException("Artist cannot be blank: " + id);
        }

        if (!Float.isFinite(lootChance) || lootChance < 0.0F || lootChance > 1.0F) {
            throw new IllegalArgumentException(
                    "Loot chance must be a finite value between 0.0 and 1.0: " + id
            );
        }

        if (structures == null || structures.length == 0) {
            throw new IllegalArgumentException("At least one structure is required: " + id);
        }

        for (StructureLootTarget structure : structures) {
            if (structure == null) {
                throw new IllegalArgumentException("Structure targets cannot be null: " + id);
            }
        }
    }

    public static List<DiscDefinition> all() {
        return DISCS;
    }

    public static DiscDefinition first() {
        return DISCS.getFirst();
    }
}
