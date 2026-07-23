# Music Discs Expanded 26.1.2 overlay

Java package: `com.shantiashams.musicdiscsexpanded`

Mod ID: `musicdiscsexpanded`

## Install the overlay

1. Back up your Fabric 26.1.2 template project.
2. Copy the supplied `src` directory into the root of your project and merge it with your existing `src` directory.
3. Merge the supplied `build.gradle` settings into your project. In particular, keep:

   ```gradle
   fabricApi {
       configureDataGeneration() {
           client = true
       }
   }
   ```

4. Use the supplied entrypoints in `src/main/resources/fabric.mod.json`:

   ```json
   "main": [
     "com.shantiashams.musicdiscsexpanded.MusicDiscsExpanded"
   ],
   "fabric-datagen": [
     "com.shantiashams.musicdiscsexpanded.datagen.MusicDiscsExpandedDataGenerator"
   ]
   ```

5. Remove unused example-mod Java classes, client initializers, and mixin declarations from the original template when they are no longer needed.
6. Minecraft 26.1.2 requires Java 25.

## Where to register discs

The only Java file you edit when adding a disc is:

```text
src/main/java/com/shantiashams/musicdiscsexpanded/disc/DiscRegistry.java
```

Example:

```java
register(
        "no_escape",
        "No Escape",
        "Unknown Artist",
        159,
        Rarity.UNCOMMON,
        Structures.ANCIENT_CITY,
        Structures.BASTION_TREASURE
);
```

The first argument, `no_escape`, is the resource name. The OGG and PNG filenames must use exactly the same lowercase name.

## Where to place OGG files

Place each mono OGG Vorbis file here:

```text
src/main/resources/assets/musicdiscsexpanded/sounds/<disc_id>.ogg
```

For the example above:

```text
src/main/resources/assets/musicdiscsexpanded/sounds/no_escape.ogg
```

The file must be **mono**, not stereo, so jukebox audio attenuates directionally like vanilla music discs.

Do not put the OGG in `src/main/generated`. Generated resources are recreated by datagen.

## Where to place PNG files

Place each item texture here:

```text
src/main/resources/assets/musicdiscsexpanded/textures/item/<disc_id>.png
```

For the example above:

```text
src/main/resources/assets/musicdiscsexpanded/textures/item/no_escape.png
```

Recommended texture format:

- PNG
- square dimensions
- normally 16×16, 32×32, or another Minecraft-compatible multiple
- transparent background where appropriate

Do not put the PNG in `src/main/generated`.

## Run datagen

Windows:

```bat
gradlew.bat runDatagen
```

macOS or Linux:

```bash
./gradlew runDatagen
```

Datagen writes files beneath:

```text
src/main/generated/
```

For `no_escape`, it generates resources including:

```text
src/main/generated/assets/musicdiscsexpanded/items/no_escape.json
src/main/generated/assets/musicdiscsexpanded/models/item/no_escape.json
src/main/generated/assets/musicdiscsexpanded/sounds.json
src/main/generated/assets/musicdiscsexpanded/lang/en_us.json
src/main/generated/data/musicdiscsexpanded/jukebox_song/no_escape.json
```

The source OGG and PNG remain under `src/main/resources`; datagen references them but does not copy or recreate them.

## Adding another disc

For a disc registered as:

```java
register(
        "lost_signal",
        "Lost Signal",
        "Shanti Ashams",
        203,
        Rarity.RARE,
        Structures.END_CITY
);
```

add exactly these two files:

```text
src/main/resources/assets/musicdiscsexpanded/sounds/lost_signal.ogg
src/main/resources/assets/musicdiscsexpanded/textures/item/lost_signal.png
```

Then run datagen. No hand-written model, item, language, sound, jukebox-song, creative-tab, or loot-table JSON is required.
