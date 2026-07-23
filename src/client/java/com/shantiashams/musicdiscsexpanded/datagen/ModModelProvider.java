package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.item.ModItems;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricModelProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.client.data.models.BlockModelGenerators;
import net.minecraft.client.data.models.ItemModelGenerators;
import net.minecraft.client.data.models.model.ModelTemplates;

/** Generates both item model JSON and the modern client-item JSON. */
public final class ModModelProvider extends FabricModelProvider {
    public ModModelProvider(FabricPackOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockModelGenerators blockModelGenerators) {
        // This mod has no blocks.
    }

    @Override
    public void generateItemModels(ItemModelGenerators itemModelGenerators) {
        for (DiscDefinition disc : DiscRegistry.all()) {
            itemModelGenerators.generateFlatItem(ModItems.get(disc), ModelTemplates.FLAT_ITEM);
        }
    }

    @Override
    public String getName() {
        return "Music Discs Expanded Models";
    }
}
