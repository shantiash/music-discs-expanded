package com.shantiashams.musicdiscsexpanded.item;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import net.minecraft.core.Registry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.world.item.Item;

import java.util.LinkedHashMap;
import java.util.Map;

/** Registers basic Items carrying vanilla jukebox-playable components. */
public final class ModItems {
    private static final Map<String, Item> ITEMS = new LinkedHashMap<>();
    private static boolean initialized;

    private ModItems() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        for (DiscDefinition disc : DiscRegistry.all()) {
            Item.Properties properties = new Item.Properties()
                    .stacksTo(1)
                    .rarity(disc.rarity())
                    .jukeboxPlayable(disc.jukeboxSongKey())
                    .setId(disc.itemKey());

            Item item = Registry.register(
                    BuiltInRegistries.ITEM,
                    disc.itemKey(),
                    new Item(properties)
            );

            ITEMS.put(disc.id(), item);
        }

        initialized = true;
    }

    public static Item get(DiscDefinition disc) {
        initialize();

        Item item = ITEMS.get(disc.id());
        if (item == null) {
            throw new IllegalStateException("Item was not registered for disc: " + disc.id());
        }

        return item;
    }
}
