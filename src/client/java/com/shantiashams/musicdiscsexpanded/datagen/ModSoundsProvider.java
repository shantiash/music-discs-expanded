package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.sound.ModSounds;
import net.fabricmc.fabric.api.client.datagen.v1.builder.SoundTypeBuilder;
import net.fabricmc.fabric.api.client.datagen.v1.provider.FabricSoundsProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.minecraft.core.HolderLookup;

import java.util.concurrent.CompletableFuture;

/** Generates assets/musicdiscsexpanded/sounds.json with streaming disc audio. */
public final class ModSoundsProvider extends FabricSoundsProvider {
    public ModSoundsProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(
            HolderLookup.Provider registries,
            SoundExporter exporter
    ) {
        for (DiscDefinition disc : DiscRegistry.all()) {
            exporter.add(
                    ModSounds.get(disc),
                    SoundTypeBuilder.of().sound(
                            SoundTypeBuilder.RegistrationBuilder
                                    .ofFile(disc.soundFileId())
                                    .stream(true)
                    )
            );
        }
    }

    @Override
    public String getName() {
        return "Music Discs Expanded Sounds";
    }
}
