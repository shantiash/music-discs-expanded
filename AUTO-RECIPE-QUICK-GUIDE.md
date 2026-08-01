# Automatic disc recipes

Only edit `DiscRegistry.java`.

## Loot-only disc

```java
register(
        "example",
        "Example",
        "Artist",
        120,
        Rarity.RARE,
        0.05F,
        Structures.FORTRESS
);
```

## Craftable disc

Insert a `DiscRecipe` immediately after the loot chance:

```java
register(
        "diamond_cave",
        "Diamond Cave",
        "Laudividni",
        117,
        Rarity.RARE,
        0.10F,
        DiscRecipe.shaped("DMD")
                .define('D', Items.DIAMOND)
                .define('M', DiscRecipe.ANY_MUSIC_DISC),
        Structures.MINESHAFT
);
```

`DiscRecipe.ANY_MUSIC_DISC` accepts vanilla music discs and modded discs that
use the common `c:music_discs` tag. Every Music Discs Expanded disc is added to
that tag automatically.

## Generate and test

```powershell
.\gradlew.bat runDatagen
.\gradlew.bat clean runClient
```

The recipe, recipe-book advancement, and item tag are generated under
`src/main/generated`.
