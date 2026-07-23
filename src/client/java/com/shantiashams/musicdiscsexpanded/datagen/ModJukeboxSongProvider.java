package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricDynamicRegistryProvider;
import net.minecraft.core.Holder;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderLookup;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.JukeboxSong;

import java.util.concurrent.CompletableFuture;

/** Generates data/musicdiscsexpanded/jukebox_song/<disc>.json. */
public final class ModJukeboxSongProvider extends FabricDynamicRegistryProvider {
    public ModJukeboxSongProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(HolderLookup.Provider registries, Entries entries) {
        HolderGetter<SoundEvent> soundEvents = entries.getLookup(Registries.SOUND_EVENT);

        for (DiscDefinition disc : DiscRegistry.all()) {
            Holder<SoundEvent> soundEvent = soundEvents.getOrThrow(disc.soundEventKey());

            entries.add(
                    disc.jukeboxSongKey(),
                    new JukeboxSong(
                            soundEvent,
                            Component.translatable(disc.songTranslationKey()),
                            disc.lengthSeconds(),
                            disc.comparatorOutput()
                    )
            );
        }
    }

    @Override
    public String getName() {
        return "Music Discs Expanded Jukebox Songs";
    }
}
