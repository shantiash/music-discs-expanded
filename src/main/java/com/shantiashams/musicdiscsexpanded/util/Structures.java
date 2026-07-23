package com.shantiashams.musicdiscsexpanded.util;

import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.world.level.storage.loot.LootTable;

import java.util.Set;

/**
 * Vanilla generated-structure loot targets for Minecraft Java 26.1.2.
 *
 * <p>This class deliberately keeps every public constant typed as
 * {@link StructureLootTarget}, matching DiscRegistry's varargs API.</p>
 */
public final class Structures {
    private static final String MINECRAFT_NAMESPACE = "minecraft";

    private Structures() {
    }

    /** Creates a loot target containing one vanilla loot-table key. */
    private static StructureLootTarget target(String path) {
        ResourceKey<LootTable> key = ResourceKey.create(
                Registries.LOOT_TABLE,
                Identifier.fromNamespaceAndPath(MINECRAFT_NAMESPACE, path)
        );

        return new StructureLootTarget(Set.of(key));
    }

    private static StructureLootTarget[] group(StructureLootTarget... targets) {
        return targets;
    }

    // ---------------------------------------------------------------------
    // Ancient City
    // ---------------------------------------------------------------------

    public static final StructureLootTarget ANCIENT_CITY =
            target("chests/ancient_city");
    public static final StructureLootTarget ANCIENT_CITY_ICE_BOX =
            target("chests/ancient_city_ice_box");

    public static final StructureLootTarget[] ANCIENT_CITY_ALL = group(
            ANCIENT_CITY,
            ANCIENT_CITY_ICE_BOX
    );

    // ---------------------------------------------------------------------
    // Bastion Remnant — all four vanilla bastion types
    // ---------------------------------------------------------------------

    public static final StructureLootTarget BASTION_BRIDGE =
            target("chests/bastion_bridge");
    public static final StructureLootTarget BASTION_HOGLIN_STABLE =
            target("chests/bastion_hoglin_stable");
    public static final StructureLootTarget BASTION_OTHER =
            target("chests/bastion_other");
    public static final StructureLootTarget BASTION_HOUSING_UNITS =
            BASTION_OTHER;
    public static final StructureLootTarget BASTION_TREASURE =
            target("chests/bastion_treasure");

    public static final StructureLootTarget[] BASTION_ALL = group(
            BASTION_BRIDGE,
            BASTION_HOGLIN_STABLE,
            BASTION_OTHER,
            BASTION_TREASURE
    );

    public static final StructureLootTarget[] BASTION_REMNANT_ALL = BASTION_ALL;

    // ---------------------------------------------------------------------
    // Buried Treasure
    // ---------------------------------------------------------------------

    public static final StructureLootTarget BURIED_TREASURE =
            target("chests/buried_treasure");

    // ---------------------------------------------------------------------
    // Desert Pyramid and Desert Well archaeology
    // ---------------------------------------------------------------------

    public static final StructureLootTarget DESERT_PYRAMID =
            target("chests/desert_pyramid");
    public static final StructureLootTarget DESERT_PYRAMID_ARCHAEOLOGY =
            target("archaeology/desert_pyramid");
    public static final StructureLootTarget DESERT_WELL_ARCHAEOLOGY =
            target("archaeology/desert_well");

    public static final StructureLootTarget[] DESERT_PYRAMID_ALL = group(
            DESERT_PYRAMID,
            DESERT_PYRAMID_ARCHAEOLOGY
    );

    // ---------------------------------------------------------------------
    // End City
    // ---------------------------------------------------------------------

    public static final StructureLootTarget END_CITY =
            target("chests/end_city_treasure");

    // ---------------------------------------------------------------------
    // Nether Fortress
    // ---------------------------------------------------------------------

    public static final StructureLootTarget NETHER_FORTRESS =
            target("chests/nether_bridge");
    public static final StructureLootTarget FORTRESS = NETHER_FORTRESS;

    // ---------------------------------------------------------------------
    // Igloo
    // ---------------------------------------------------------------------

    public static final StructureLootTarget IGLOO =
            target("chests/igloo_chest");

    // ---------------------------------------------------------------------
    // Jungle Pyramid / Jungle Temple
    // ---------------------------------------------------------------------

    public static final StructureLootTarget JUNGLE_TEMPLE =
            target("chests/jungle_temple");
    public static final StructureLootTarget JUNGLE_PYRAMID = JUNGLE_TEMPLE;
    public static final StructureLootTarget JUNGLE_TEMPLE_DISPENSER =
            target("chests/jungle_temple_dispenser");

    public static final StructureLootTarget[] JUNGLE_TEMPLE_ALL = group(
            JUNGLE_TEMPLE,
            JUNGLE_TEMPLE_DISPENSER
    );
    public static final StructureLootTarget[] JUNGLE_PYRAMID_ALL = JUNGLE_TEMPLE_ALL;

    // ---------------------------------------------------------------------
    // Woodland Mansion
    // ---------------------------------------------------------------------

    public static final StructureLootTarget WOODLAND_MANSION =
            target("chests/woodland_mansion");
    public static final StructureLootTarget MANSION = WOODLAND_MANSION;

    // ---------------------------------------------------------------------
    // Mineshaft variants share one loot table
    // ---------------------------------------------------------------------

    public static final StructureLootTarget MINESHAFT =
            target("chests/abandoned_mineshaft");
    public static final StructureLootTarget MINESHAFT_MESA = MINESHAFT;

    // ---------------------------------------------------------------------
    // Ocean Ruins
    // ---------------------------------------------------------------------

    public static final StructureLootTarget OCEAN_RUIN_BIG =
            target("chests/underwater_ruin_big");
    public static final StructureLootTarget OCEAN_RUIN_SMALL =
            target("chests/underwater_ruin_small");
    public static final StructureLootTarget OCEAN_RUIN_COLD_ARCHAEOLOGY =
            target("archaeology/ocean_ruin_cold");
    public static final StructureLootTarget OCEAN_RUIN_WARM_ARCHAEOLOGY =
            target("archaeology/ocean_ruin_warm");

    public static final StructureLootTarget[] OCEAN_RUIN_COLD_ALL = group(
            OCEAN_RUIN_BIG,
            OCEAN_RUIN_SMALL,
            OCEAN_RUIN_COLD_ARCHAEOLOGY
    );

    public static final StructureLootTarget[] OCEAN_RUIN_WARM_ALL = group(
            OCEAN_RUIN_BIG,
            OCEAN_RUIN_SMALL,
            OCEAN_RUIN_WARM_ARCHAEOLOGY
    );

    public static final StructureLootTarget[] OCEAN_RUINS_ALL = group(
            OCEAN_RUIN_BIG,
            OCEAN_RUIN_SMALL,
            OCEAN_RUIN_COLD_ARCHAEOLOGY,
            OCEAN_RUIN_WARM_ARCHAEOLOGY
    );

    // ---------------------------------------------------------------------
    // Pillager Outpost
    // ---------------------------------------------------------------------

    public static final StructureLootTarget PILLAGER_OUTPOST =
            target("chests/pillager_outpost");

    // ---------------------------------------------------------------------
    // Ruined Portal variants share one loot table
    // ---------------------------------------------------------------------

    public static final StructureLootTarget RUINED_PORTAL =
            target("chests/ruined_portal");
    public static final StructureLootTarget RUINED_PORTAL_STANDARD = RUINED_PORTAL;
    public static final StructureLootTarget RUINED_PORTAL_DESERT = RUINED_PORTAL;
    public static final StructureLootTarget RUINED_PORTAL_JUNGLE = RUINED_PORTAL;
    public static final StructureLootTarget RUINED_PORTAL_MOUNTAIN = RUINED_PORTAL;
    public static final StructureLootTarget RUINED_PORTAL_NETHER = RUINED_PORTAL;
    public static final StructureLootTarget RUINED_PORTAL_OCEAN = RUINED_PORTAL;
    public static final StructureLootTarget RUINED_PORTAL_SWAMP = RUINED_PORTAL;

    // ---------------------------------------------------------------------
    // Shipwreck variants share the same three tables
    // ---------------------------------------------------------------------

    public static final StructureLootTarget SHIPWRECK_MAP =
            target("chests/shipwreck_map");
    public static final StructureLootTarget SHIPWRECK_SUPPLY =
            target("chests/shipwreck_supply");
    public static final StructureLootTarget SHIPWRECK_TREASURE =
            target("chests/shipwreck_treasure");

    public static final StructureLootTarget[] SHIPWRECK_ALL = group(
            SHIPWRECK_MAP,
            SHIPWRECK_SUPPLY,
            SHIPWRECK_TREASURE
    );

    public static final StructureLootTarget[] SHIPWRECK_BEACHED_ALL = SHIPWRECK_ALL;

    // ---------------------------------------------------------------------
    // Stronghold
    // ---------------------------------------------------------------------

    public static final StructureLootTarget STRONGHOLD_CORRIDOR =
            target("chests/stronghold_corridor");
    public static final StructureLootTarget STRONGHOLD_CROSSING =
            target("chests/stronghold_crossing");
    public static final StructureLootTarget STRONGHOLD_LIBRARY =
            target("chests/stronghold_library");
    public static final StructureLootTarget STRONGHOLD = STRONGHOLD_CORRIDOR;

    public static final StructureLootTarget[] STRONGHOLD_ALL = group(
            STRONGHOLD_CORRIDOR,
            STRONGHOLD_CROSSING,
            STRONGHOLD_LIBRARY
    );

    // ---------------------------------------------------------------------
    // Trail Ruins
    // ---------------------------------------------------------------------

    public static final StructureLootTarget TRAIL_RUINS_COMMON =
            target("archaeology/trail_ruins_common");
    public static final StructureLootTarget TRAIL_RUINS_RARE =
            target("archaeology/trail_ruins_rare");

    public static final StructureLootTarget[] TRAIL_RUINS_ALL = group(
            TRAIL_RUINS_COMMON,
            TRAIL_RUINS_RARE
    );

    // ---------------------------------------------------------------------
    // Trial Chambers
    // ---------------------------------------------------------------------

    public static final StructureLootTarget TRIAL_CHAMBERS_CORRIDOR =
            target("chests/trial_chambers/corridor");
    public static final StructureLootTarget TRIAL_CHAMBERS_ENTRANCE =
            target("chests/trial_chambers/entrance");
    public static final StructureLootTarget TRIAL_CHAMBERS_INTERSECTION =
            target("chests/trial_chambers/intersection");
    public static final StructureLootTarget TRIAL_CHAMBERS_INTERSECTION_BARREL =
            target("chests/trial_chambers/intersection_barrel");
    public static final StructureLootTarget TRIAL_CHAMBERS_SUPPLY =
            target("chests/trial_chambers/supply");
    public static final StructureLootTarget TRIAL_CHAMBERS_REWARD =
            target("chests/trial_chambers/reward");
    public static final StructureLootTarget TRIAL_CHAMBERS_REWARD_OMINOUS =
            target("chests/trial_chambers/reward_ominous");

    public static final StructureLootTarget[] TRIAL_CHAMBERS_ALL = group(
            TRIAL_CHAMBERS_CORRIDOR,
            TRIAL_CHAMBERS_ENTRANCE,
            TRIAL_CHAMBERS_INTERSECTION,
            TRIAL_CHAMBERS_INTERSECTION_BARREL,
            TRIAL_CHAMBERS_SUPPLY,
            TRIAL_CHAMBERS_REWARD,
            TRIAL_CHAMBERS_REWARD_OMINOUS
    );

    // ---------------------------------------------------------------------
    // Villages
    // ---------------------------------------------------------------------

    public static final StructureLootTarget VILLAGE_ARMORER =
            target("chests/village/village_armorer");
    public static final StructureLootTarget VILLAGE_BUTCHER =
            target("chests/village/village_butcher");
    public static final StructureLootTarget VILLAGE_CARTOGRAPHER =
            target("chests/village/village_cartographer");
    public static final StructureLootTarget VILLAGE_FISHER =
            target("chests/village/village_fisher");
    public static final StructureLootTarget VILLAGE_FLETCHER =
            target("chests/village/village_fletcher");
    public static final StructureLootTarget VILLAGE_MASON =
            target("chests/village/village_mason");
    public static final StructureLootTarget VILLAGE_SHEPHERD =
            target("chests/village/village_shepherd");
    public static final StructureLootTarget VILLAGE_TANNERY =
            target("chests/village/village_tannery");
    public static final StructureLootTarget VILLAGE_TEMPLE =
            target("chests/village/village_temple");
    public static final StructureLootTarget VILLAGE_TOOLSMITH =
            target("chests/village/village_toolsmith");
    public static final StructureLootTarget VILLAGE_WEAPONSMITH =
            target("chests/village/village_weaponsmith");

    public static final StructureLootTarget VILLAGE_DESERT_HOUSE =
            target("chests/village/village_desert_house");
    public static final StructureLootTarget VILLAGE_PLAINS_HOUSE =
            target("chests/village/village_plains_house");
    public static final StructureLootTarget VILLAGE_SAVANNA_HOUSE =
            target("chests/village/village_savanna_house");
    public static final StructureLootTarget VILLAGE_SNOWY_HOUSE =
            target("chests/village/village_snowy_house");
    public static final StructureLootTarget VILLAGE_TAIGA_HOUSE =
            target("chests/village/village_taiga_house");

    public static final StructureLootTarget[] VILLAGE_ALL = group(
            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_DESERT_HOUSE,
            VILLAGE_PLAINS_HOUSE,
            VILLAGE_SAVANNA_HOUSE,
            VILLAGE_SNOWY_HOUSE,
            VILLAGE_TAIGA_HOUSE
    );

    public static final StructureLootTarget[] VILLAGE_DESERT_ALL = group(
            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_DESERT_HOUSE
    );

    public static final StructureLootTarget[] VILLAGE_PLAINS_ALL = group(
            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_PLAINS_HOUSE
    );

    public static final StructureLootTarget[] VILLAGE_SAVANNA_ALL = group(
            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_SAVANNA_HOUSE
    );

    public static final StructureLootTarget[] VILLAGE_SNOWY_ALL = group(
            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_SNOWY_HOUSE
    );

    public static final StructureLootTarget[] VILLAGE_TAIGA_ALL = group(
            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_TAIGA_HOUSE
    );

    // ---------------------------------------------------------------------
    // Generated places that are not registered worldgen structures
    // ---------------------------------------------------------------------

    public static final StructureLootTarget DUNGEON =
            target("chests/simple_dungeon");

    // ---------------------------------------------------------------------
    // Master groups
    // ---------------------------------------------------------------------

    /**
     * Every sensible vanilla loot source belonging to a registered structure.
     * This excludes generated places with no structure-owned loot table.
     */
    public static final StructureLootTarget[] ALL_LOOTABLE_STRUCTURES = group(
            ANCIENT_CITY,
            ANCIENT_CITY_ICE_BOX,

            BASTION_BRIDGE,
            BASTION_HOGLIN_STABLE,
            BASTION_OTHER,
            BASTION_TREASURE,

            BURIED_TREASURE,

            DESERT_PYRAMID,
            DESERT_PYRAMID_ARCHAEOLOGY,

            END_CITY,
            NETHER_FORTRESS,
            IGLOO,
            JUNGLE_TEMPLE,
            WOODLAND_MANSION,
            MINESHAFT,

            OCEAN_RUIN_BIG,
            OCEAN_RUIN_SMALL,
            OCEAN_RUIN_COLD_ARCHAEOLOGY,
            OCEAN_RUIN_WARM_ARCHAEOLOGY,

            PILLAGER_OUTPOST,
            RUINED_PORTAL,

            SHIPWRECK_MAP,
            SHIPWRECK_SUPPLY,
            SHIPWRECK_TREASURE,

            STRONGHOLD_CORRIDOR,
            STRONGHOLD_CROSSING,
            STRONGHOLD_LIBRARY,

            TRAIL_RUINS_COMMON,
            TRAIL_RUINS_RARE,

            TRIAL_CHAMBERS_CORRIDOR,
            TRIAL_CHAMBERS_ENTRANCE,
            TRIAL_CHAMBERS_INTERSECTION,
            TRIAL_CHAMBERS_INTERSECTION_BARREL,
            TRIAL_CHAMBERS_SUPPLY,
            TRIAL_CHAMBERS_REWARD,
            TRIAL_CHAMBERS_REWARD_OMINOUS,

            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_DESERT_HOUSE,
            VILLAGE_PLAINS_HOUSE,
            VILLAGE_SAVANNA_HOUSE,
            VILLAGE_SNOWY_HOUSE,
            VILLAGE_TAIGA_HOUSE
    );

    /** Alias intended for DiscRegistry registration calls. */
    public static final StructureLootTarget[] ALL_STRUCTURES =
            ALL_LOOTABLE_STRUCTURES;

    /**
     * Broadest generated-place group. Adds dungeon chests, desert-well
     * archaeology, and the jungle-temple trap dispenser.
     */
    public static final StructureLootTarget[] ALL_GENERATED_PLACE_LOOT = group(
            ANCIENT_CITY,
            ANCIENT_CITY_ICE_BOX,

            BASTION_BRIDGE,
            BASTION_HOGLIN_STABLE,
            BASTION_OTHER,
            BASTION_TREASURE,

            BURIED_TREASURE,

            DESERT_PYRAMID,
            DESERT_PYRAMID_ARCHAEOLOGY,
            DESERT_WELL_ARCHAEOLOGY,

            DUNGEON,
            END_CITY,
            NETHER_FORTRESS,
            IGLOO,
            JUNGLE_TEMPLE,
            JUNGLE_TEMPLE_DISPENSER,
            WOODLAND_MANSION,
            MINESHAFT,

            OCEAN_RUIN_BIG,
            OCEAN_RUIN_SMALL,
            OCEAN_RUIN_COLD_ARCHAEOLOGY,
            OCEAN_RUIN_WARM_ARCHAEOLOGY,

            PILLAGER_OUTPOST,
            RUINED_PORTAL,

            SHIPWRECK_MAP,
            SHIPWRECK_SUPPLY,
            SHIPWRECK_TREASURE,

            STRONGHOLD_CORRIDOR,
            STRONGHOLD_CROSSING,
            STRONGHOLD_LIBRARY,

            TRAIL_RUINS_COMMON,
            TRAIL_RUINS_RARE,

            TRIAL_CHAMBERS_CORRIDOR,
            TRIAL_CHAMBERS_ENTRANCE,
            TRIAL_CHAMBERS_INTERSECTION,
            TRIAL_CHAMBERS_INTERSECTION_BARREL,
            TRIAL_CHAMBERS_SUPPLY,
            TRIAL_CHAMBERS_REWARD,
            TRIAL_CHAMBERS_REWARD_OMINOUS,

            VILLAGE_ARMORER,
            VILLAGE_BUTCHER,
            VILLAGE_CARTOGRAPHER,
            VILLAGE_FISHER,
            VILLAGE_FLETCHER,
            VILLAGE_MASON,
            VILLAGE_SHEPHERD,
            VILLAGE_TANNERY,
            VILLAGE_TEMPLE,
            VILLAGE_TOOLSMITH,
            VILLAGE_WEAPONSMITH,
            VILLAGE_DESERT_HOUSE,
            VILLAGE_PLAINS_HOUSE,
            VILLAGE_SAVANNA_HOUSE,
            VILLAGE_SNOWY_HOUSE,
            VILLAGE_TAIGA_HOUSE
    );
}
