package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagsProvider;
import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.tags.TagAppender;
import net.minecraft.world.item.Item;

import java.util.concurrent.CompletableFuture;

/** Adds every custom disc to the common c:music_discs item tag. */
public final class ModItemTagProvider
        extends FabricTagsProvider.ItemTagsProvider {

    public ModItemTagProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void addTags(HolderLookup.Provider registries) {
        TagAppender<Item, Item> musicDiscs =
                valueLookupBuilder(ConventionalItemTags.MUSIC_DISCS);

        for (DiscDefinition disc : DiscRegistry.all()) {
            musicDiscs.add(ModItems.get(disc));
        }
    }

    @Override
    public String getName() {
        return "Music Discs Expanded Item Tags";
    }
}
