package com.shantiashams.musicdiscsexpanded.sound;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import net.minecraft.core.registries.BuiltInRegistries;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.core.Registry;

import java.util.LinkedHashMap;
import java.util.Map;

/** Registers every catalog sound event using vanilla variable-range behavior. */
public final class ModSounds {
    private static final Map<String, SoundEvent> SOUNDS = new LinkedHashMap<>();
    private static boolean initialized;

    private ModSounds() {
    }

    public static synchronized void initialize() {
        if (initialized) {
            return;
        }

        for (DiscDefinition disc : DiscRegistry.all()) {
            SoundEvent soundEvent = SoundEvent.createVariableRangeEvent(disc.soundEventId());
            Registry.register(BuiltInRegistries.SOUND_EVENT, disc.soundEventId(), soundEvent);
            SOUNDS.put(disc.id(), soundEvent);
        }

        initialized = true;
    }

    public static SoundEvent get(DiscDefinition disc) {
        initialize();

        SoundEvent soundEvent = SOUNDS.get(disc.id());
        if (soundEvent == null) {
            throw new IllegalStateException("Sound was not registered for disc: " + disc.id());
        }

        return soundEvent;
    }
}
