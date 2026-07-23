package com.shantiashams.musicdiscsexpanded;

import com.shantiashams.musicdiscsexpanded.item.ModCreativeTabs;
import com.shantiashams.musicdiscsexpanded.item.ModItems;
import com.shantiashams.musicdiscsexpanded.loot.ModLoot;
import com.shantiashams.musicdiscsexpanded.sound.ModSounds;
import com.shantiashams.musicdiscsexpanded.util.ModIds;
import net.fabricmc.api.ModInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** Common mod entrypoint. */
public final class MusicDiscsExpanded implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger(ModIds.MOD_ID);

    @Override
    public void onInitialize() {
        // Order is intentional: sound events first, then the items that reference jukebox songs.
        ModSounds.initialize();
        ModItems.initialize();
        ModCreativeTabs.initialize();
        ModLoot.initialize();

        LOGGER.info("Registered Music Discs Expanded.");
    }
}
