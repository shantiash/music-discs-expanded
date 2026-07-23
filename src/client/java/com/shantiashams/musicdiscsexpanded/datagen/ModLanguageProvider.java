package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.item.ModCreativeTabs;
import com.shantiashams.musicdiscsexpanded.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricLanguageProvider;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/** Generates item names, jukebox descriptions, and the creative-tab title. */
public final class ModLanguageProvider extends FabricLanguageProvider {
    public ModLanguageProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, "en_us", registriesFuture);
    }

    @Override
    public void generateTranslations(
            HolderLookup.Provider registries,
            TranslationBuilder translations
    ) {
        translations.add(ModCreativeTabs.titleTranslationKey(), "Music Discs Expanded");

        for (DiscDefinition disc : DiscRegistry.all()) {
            translations.add(ModItems.get(disc), "Music Disc");
            translations.add(disc.songTranslationKey(), disc.songDisplayText());
        }
    }
}
