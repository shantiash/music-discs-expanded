package com.shantiashams.musicdiscsexpanded.disc;

import com.shantiashams.musicdiscsexpanded.util.StructureLootTarget;
import com.shantiashams.musicdiscsexpanded.util.Structures;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.Rarity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.regex.Pattern;

/**
 * THE ONLY JAVA FILE TO EDIT WHEN ADDING A MUSIC DISC.
 *
 * Add another register(...) call, then add the matching .ogg and .png files.
 *
 * Comparator outputs are assigned automatically in registration order:
 * 1 through 15, then repeating from 1.
 *
 * Loot chance examples:
 * 0.10F  = 10%
 * 0.075F = 7.5%
 * 0.02F  = 2%
 * 0.005F = 0.5%
 */
public final class DiscRegistry {

    private static final Pattern VALID_ID =
            Pattern.compile("[a-z0-9_./-]+");

    private static final List<DiscDefinition> BUILDING =
            new ArrayList<>();

    private static final Set<String> REGISTERED_IDS =
            new HashSet<>();

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
                "T_en_M",
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
                0.10F,
                Structures.BURIED_TREASURE
        );
        
        register(
                "shared_fate",
                "Shared Fate",
                "Laudividni",
                120,
                Rarity.RARE,
                0.10F,
                Structures.TRIAL_CHAMBERS_REWARD_OMINOUS
        );

        /*
         * BASTION_ALL is a StructureLootTarget[] array.
         * The normal varargs register method accepts it directly.
         */
        register(
                "gilded",
                "Gilded",
                "T_en_M",
                133,
                Rarity.RARE,
                0.075F,
                Structures.BASTION_ALL
        );

        register(
                "the_ender_dragon",
                "The Ender Dragon",
                "T_en_M",
                173,
                Rarity.RARE,
                0.10F,
                Structures.END_CITY
        );

        register(
                "below",
                "Below",
                "T_en_M",
                121,
                Rarity.RARE,
                0.10F,
                Structures.OCEAN_RUIN_COLD_ARCHAEOLOGY,
                Structures.OCEAN_RUIN_WARM_ARCHAEOLOGY
        );

        register(
                "diamond_cave",
                "Diamond Cave",
                "Laudividni",
                117,
                Rarity.RARE,
                0.00F,
                DiscRecipe.shaped("DMD")
                        .define('D', Items.DIAMOND)
                        .define('M', DiscRecipe.ANY_MUSIC_DISC),
                Structures.MINESHAFT
        );

        register(
                "lightning",
                "Lightning",
                "T_en_M",
                141,
                Rarity.RARE,
                0.10F,
                Structures.TRIAL_CHAMBERS_REWARD
        );

        register(
                "undead",
                "Undead",
                "T_en_M",
                136,
                Rarity.RARE,
                0.05F,
                Structures.DUNGEON,
                Structures.DESERT_PYRAMID_ALL
        );

        register(
                "nightfall",
                "Nightfall",
                "Laudividni",
                137,
                Rarity.RARE,
                0.20F,
                Structures.IGLOO
        );

        register(
                "shulker",
                "Shulker",
                "T_en_M",
                128,
                Rarity.RARE,
                0.10F,
                Structures.END_CITY
        );

        register(
                "lava_monster",
                "Lava Monster",
                "Aidan Haglund",
                96,
                Rarity.RARE,
                0.10F,
                Structures.FORTRESS
        );

        register(
                "tundra",
                "Tundra",
                "T_en_M",
                118,
                Rarity.RARE,
                0.10F,
                Structures.VILLAGE_SNOWY_HOUSE
        );

        register(
                "last_hallway",
                "Last Hallway",
                "Laudividni",
                153,
                Rarity.RARE,
                0.10F,
                Structures.STRONGHOLD_CORRIDOR
        );
        register(
                "final_stage",
                "Final Stage",
                "Laudividni",
                122,
                Rarity.RARE,
                0.10F,
                Structures.FORTRESS
        );

        register(
                "celebration",
                "Celebration",
                "Laudividni ft. T_en_M",
                131,
                Rarity.RARE,
                0.00F,
                DiscRecipe.shapeless()
                        .requires(Items.CAKE)
                        .requires(DiscRecipe.ANY_MUSIC_DISC),
                Structures.TRIAL_CHAMBERS_REWARD
        );

        register(
                "the_end",
                "The End",
                "Laudividni",
                155,
                Rarity.RARE,
                0.10F,
                Structures.END_CITY
        );

        register(
                "frosted",
                "Frosted",
                "T_en_M",
                97,
                Rarity.RARE,
                0.10F,
                Structures.VILLAGE_SNOWY_HOUSE
        );

        register(
                "trick_or_treat",
                "Trick or Treat",
                "Laudividni",
                121,
                Rarity.RARE,
                0.00F,
                DiscRecipe.shapeless()
                        .requires(Items.CARVED_PUMPKIN)
                        .requires(DiscRecipe.ANY_MUSIC_DISC),
                Structures.WOODLAND_MANSION
        );

        register(
                "axolotl_paradise",
                "Axolotl Paradise",
                "Laudividni",
                126,
                Rarity.RARE,
                0.10F,
                Structures.BURIED_TREASURE
        );

        // Add future discs above this line.

        if (BUILDING.isEmpty()) {
            throw new IllegalStateException(
                    "At least one disc is required for the creative-tab icon."
            );
        }

        DISCS = List.copyOf(BUILDING);
    }

    private DiscRegistry() {
    }

    /**
     * Registers a loot-only disc in one or more individual loot targets.
     * This also accepts a StructureLootTarget[] directly, such as
     * Structures.BASTION_ALL.
     */
    private static void register(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            StructureLootTarget... structures
    ) {
        if (structures == null) {
            throw new IllegalArgumentException(
                    "Structure targets cannot be null: " + id
            );
        }

        registerInternal(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                null,
                List.of(structures)
        );
    }

    /**
     * Registers a disc with an automatically generated crafting recipe.
     *
     * Example:
     *
     * DiscRecipe.shaped("DMD")
     *         .define('D', Items.DIAMOND)
     *         .define('M', DiscRecipe.ANY_MUSIC_DISC)
     */
    private static void register(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            DiscRecipe craftingRecipe,
            StructureLootTarget... structures
    ) {
        if (structures == null) {
            throw new IllegalArgumentException(
                    "Structure targets cannot be null: " + id
            );
        }

        registerInternal(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                craftingRecipe,
                List.of(structures)
        );
    }

    /**
     * Registers a loot-only disc using one individual structure followed by
     * a grouped StructureLootTarget array.
     */
    private static void register(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            StructureLootTarget firstStructure,
            StructureLootTarget[] groupedStructures
    ) {
        register(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                null,
                firstStructure,
                groupedStructures
        );
    }

    /**
     * Registers a craftable disc using one individual structure followed by
     * a grouped StructureLootTarget array.
     */
    private static void register(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            DiscRecipe craftingRecipe,
            StructureLootTarget firstStructure,
            StructureLootTarget[] groupedStructures
    ) {
        if (firstStructure == null) {
            throw new IllegalArgumentException(
                    "First structure target cannot be null: " + id
            );
        }

        if (groupedStructures == null) {
            throw new IllegalArgumentException(
                    "Grouped structure targets cannot be null: " + id
            );
        }

        List<StructureLootTarget> structures = new ArrayList<>();
        structures.add(firstStructure);
        structures.addAll(List.of(groupedStructures));

        registerInternal(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                craftingRecipe,
                structures
        );
    }

    private static void registerInternal(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            DiscRecipe craftingRecipe,
            List<StructureLootTarget> structures
    ) {
        validate(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                structures
        );

        int comparatorOutput = (BUILDING.size() % 15) + 1;

        BUILDING.add(new DiscDefinition(
                id,
                songName,
                artist,
                lengthSeconds,
                rarity,
                lootChance,
                comparatorOutput,
                Optional.ofNullable(craftingRecipe),
                List.copyOf(structures)
        ));
    }

    private static void validate(
            String id,
            String songName,
            String artist,
            int lengthSeconds,
            Rarity rarity,
            float lootChance,
            List<StructureLootTarget> structures
    ) {
        if (id == null || !VALID_ID.matcher(id).matches()) {
            throw new IllegalArgumentException(
                    "Invalid disc id: " + id
            );
        }

        if (!REGISTERED_IDS.add(id)) {
            throw new IllegalArgumentException(
                    "Duplicate disc id: " + id
            );
        }

        if (songName == null || songName.isBlank()) {
            throw new IllegalArgumentException(
                    "Song name cannot be blank: " + id
            );
        }

        if (artist == null || artist.isBlank()) {
            throw new IllegalArgumentException(
                    "Artist cannot be blank: " + id
            );
        }

        if (lengthSeconds <= 0) {
            throw new IllegalArgumentException(
                    "Disc length must be greater than zero: " + id
            );
        }

        if (rarity == null) {
            throw new IllegalArgumentException(
                    "Rarity cannot be null: " + id
            );
        }

        if (!Float.isFinite(lootChance)
                || lootChance < 0.0F
                || lootChance > 1.0F) {
            throw new IllegalArgumentException(
                    "Loot chance must be a finite value between 0.0 and 1.0: "
                            + id
            );
        }

        if (structures == null || structures.isEmpty()) {
            throw new IllegalArgumentException(
                    "At least one structure is required: " + id
            );
        }

        for (StructureLootTarget structure : structures) {
            if (structure == null) {
                throw new IllegalArgumentException(
                        "Structure targets cannot contain null: " + id
                );
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
