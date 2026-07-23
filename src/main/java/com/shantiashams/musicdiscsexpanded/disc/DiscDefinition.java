package com.shantiashams.musicdiscsexpanded.disc;

import com.shantiashams.musicdiscsexpanded.util.ModIds;
import com.shantiashams.musicdiscsexpanded.util.StructureLootTarget;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.Identifier;
import net.minecraft.resources.ResourceKey;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.JukeboxSong;
import net.minecraft.world.item.Rarity;

import java.util.List;
import java.util.Objects;

/** Immutable source-of-truth record for one custom music disc. */
public record DiscDefinition(
        String id,
        String songName,
        String artist,
        int lengthSeconds,
        Rarity rarity,
        float lootChance,
        int comparatorOutput,
        List<StructureLootTarget> structures
) {
    public DiscDefinition {
        Objects.requireNonNull(id, "id");
        Objects.requireNonNull(songName, "songName");
        Objects.requireNonNull(artist, "artist");
        Objects.requireNonNull(rarity, "rarity");
        Objects.requireNonNull(structures, "structures");

        if (lengthSeconds <= 0) {
            throw new IllegalArgumentException("Disc length must be greater than zero: " + id);
        }

        if (!Float.isFinite(lootChance) || lootChance < 0.0F || lootChance > 1.0F) {
            throw new IllegalArgumentException(
                    "Loot chance must be a finite value between 0.0 and 1.0: " + id
            );
        }

        if (comparatorOutput < 1 || comparatorOutput > 15) {
            throw new IllegalArgumentException("Comparator output must be between 1 and 15: " + id);
        }

        structures = List.copyOf(structures);
    }

    public Identifier itemId() {
        return ModIds.id(id);
    }

    public ResourceKey<Item> itemKey() {
        return ResourceKey.create(Registries.ITEM, itemId());
    }

    /** Uses vanilla's music_disc.* sound-event naming style. */
    public Identifier soundEventId() {
        return ModIds.id("music_disc." + id);
    }

    public ResourceKey<SoundEvent> soundEventKey() {
        return ResourceKey.create(Registries.SOUND_EVENT, soundEventId());
    }

    /** Points to assets/musicdiscsexpanded/sounds/<id>.ogg. */
    public Identifier soundFileId() {
        return ModIds.id(id);
    }

    public ResourceKey<JukeboxSong> jukeboxSongKey() {
        return ResourceKey.create(Registries.JUKEBOX_SONG, ModIds.id(id));
    }

    public String songTranslationKey() {
        return "jukebox_song." + ModIds.MOD_ID + "." + id;
    }

    public String songDisplayText() {
        return artist + " - " + songName;
    }
}
