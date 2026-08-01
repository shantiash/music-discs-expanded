package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.item.ModItems;
import com.shantiashams.musicdiscsexpanded.sound.ModSounds;
import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

/** Registers every provider needed to materialize the catalog as JSON resources. */
public final class MusicDiscsExpandedDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator dataGenerator) {
        // Idempotent initialization also makes direct datagen runs robust.
        ModSounds.initialize();
        ModItems.initialize();

        FabricDataGenerator.Pack pack = dataGenerator.createPack();
        pack.addProvider(ModModelProvider::new);
        pack.addProvider(ModLanguageProvider::new);
        pack.addProvider(ModSoundsProvider::new);
        pack.addProvider(ModJukeboxSongProvider::new);
        pack.addProvider(ModItemTagProvider::new);
        pack.addProvider(ModRecipeProvider::new);
    }
}
