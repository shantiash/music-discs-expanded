package com.shantiashams.musicdiscsexpanded.disc;

import net.fabricmc.fabric.api.tag.convention.v2.ConventionalItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.ItemLike;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Small recipe declaration used directly from DiscRegistry.
 *
 * Examples:
 *
 * DiscRecipe.shaped("DMD")
 *         .define('D', Items.DIAMOND)
 *         .define('M', DiscRecipe.ANY_MUSIC_DISC)
 *
 * DiscRecipe.shapeless()
 *         .requires(Items.DIAMOND)
 *         .requires(DiscRecipe.ANY_MUSIC_DISC)
 */
public final class DiscRecipe {

    /** Fabric's common tag containing vanilla and compatible modded music discs. */
    public static final TagKey<Item> ANY_MUSIC_DISC =
            ConventionalItemTags.MUSIC_DISCS;

    public enum Type {
        SHAPED,
        SHAPELESS
    }

    public sealed interface IngredientDefinition
            permits ItemIngredient, TagIngredient {
    }

    public record ItemIngredient(ItemLike item) implements IngredientDefinition {
        public ItemIngredient {
            Objects.requireNonNull(item, "item");
        }
    }

    public record TagIngredient(TagKey<Item> tag) implements IngredientDefinition {
        public TagIngredient {
            Objects.requireNonNull(tag, "tag");
        }
    }

    private final Type type;
    private final List<String> pattern;
    private final LinkedHashMap<Character, IngredientDefinition> key;
    private final List<IngredientDefinition> ingredients;

    private int resultCount = 1;
    private String group;
    private boolean showNotification = true;
    private IngredientDefinition unlockIngredient;

    private DiscRecipe(
            Type type,
            List<String> pattern
    ) {
        this.type = Objects.requireNonNull(type, "type");
        this.pattern = new ArrayList<>(pattern);
        this.key = new LinkedHashMap<>();
        this.ingredients = new ArrayList<>();
    }

    public static DiscRecipe shaped(String... rows) {
        Objects.requireNonNull(rows, "rows");
        return new DiscRecipe(Type.SHAPED, Arrays.asList(rows));
    }

    public static DiscRecipe shapeless() {
        return new DiscRecipe(Type.SHAPELESS, List.of());
    }

    public DiscRecipe define(char symbol, ItemLike item) {
        requireShaped("define");
        putDefinition(symbol, new ItemIngredient(item));
        return this;
    }

    public DiscRecipe define(char symbol, TagKey<Item> tag) {
        requireShaped("define");
        putDefinition(symbol, new TagIngredient(tag));
        return this;
    }

    public DiscRecipe requires(ItemLike item) {
        requireShapeless("requires");
        ingredients.add(new ItemIngredient(item));
        return this;
    }

    public DiscRecipe requires(TagKey<Item> tag) {
        requireShapeless("requires");
        ingredients.add(new TagIngredient(tag));
        return this;
    }

    public DiscRecipe count(int count) {
        if (count < 1 || count > 64) {
            throw new IllegalArgumentException(
                    "Recipe result count must be between 1 and 64."
            );
        }

        this.resultCount = count;
        return this;
    }

    public DiscRecipe group(String group) {
        if (group == null || group.isBlank()) {
            throw new IllegalArgumentException("Recipe group cannot be blank.");
        }

        this.group = group;
        return this;
    }

    public DiscRecipe showNotification(boolean showNotification) {
        this.showNotification = showNotification;
        return this;
    }

    public DiscRecipe unlockWith(ItemLike item) {
        this.unlockIngredient = new ItemIngredient(item);
        return this;
    }

    public DiscRecipe unlockWith(TagKey<Item> tag) {
        this.unlockIngredient = new TagIngredient(tag);
        return this;
    }

    private void putDefinition(
            char symbol,
            IngredientDefinition ingredient
    ) {
        if (symbol == ' ') {
            throw new IllegalArgumentException(
                    "A space cannot be used as a recipe key."
            );
        }

        if (key.putIfAbsent(symbol, ingredient) != null) {
            throw new IllegalArgumentException(
                    "Recipe key '" + symbol + "' is already defined."
            );
        }
    }

    private void requireShaped(String method) {
        if (type != Type.SHAPED) {
            throw new IllegalStateException(
                    method + " can only be used on a shaped recipe."
            );
        }
    }

    private void requireShapeless(String method) {
        if (type != Type.SHAPELESS) {
            throw new IllegalStateException(
                    method + " can only be used on a shapeless recipe."
            );
        }
    }

    DiscRecipe validatedCopy(String discId) {
        validate(discId);

        DiscRecipe copy = new DiscRecipe(type, pattern);
        copy.key.putAll(key);
        copy.ingredients.addAll(ingredients);
        copy.resultCount = resultCount;
        copy.group = group;
        copy.showNotification = showNotification;
        copy.unlockIngredient = unlockIngredient;
        return copy;
    }

    private void validate(String discId) {
        if (type == Type.SHAPED) {
            if (pattern.isEmpty() || pattern.size() > 3) {
                throw new IllegalArgumentException(
                        "A shaped recipe needs 1 to 3 rows: " + discId
                );
            }

            int width = -1;
            boolean hasUsedSymbol = false;

            for (String row : pattern) {
                if (row == null || row.isEmpty() || row.length() > 3) {
                    throw new IllegalArgumentException(
                            "Every recipe row must contain 1 to 3 characters: "
                                    + discId
                    );
                }

                if (width == -1) {
                    width = row.length();
                } else if (row.length() != width) {
                    throw new IllegalArgumentException(
                            "All shaped recipe rows must have equal width: "
                                    + discId
                    );
                }

                for (char symbol : row.toCharArray()) {
                    if (symbol == ' ') {
                        continue;
                    }

                    hasUsedSymbol = true;

                    if (!key.containsKey(symbol)) {
                        throw new IllegalArgumentException(
                                "Recipe symbol '" + symbol
                                        + "' is not defined: " + discId
                        );
                    }
                }
            }

            if (!hasUsedSymbol) {
                throw new IllegalArgumentException(
                        "A shaped recipe cannot be empty: " + discId
                );
            }

            for (char definedSymbol : key.keySet()) {
                boolean used = pattern.stream()
                        .anyMatch(row -> row.indexOf(definedSymbol) >= 0);

                if (!used) {
                    throw new IllegalArgumentException(
                            "Recipe key '" + definedSymbol
                                    + "' is defined but unused: " + discId
                    );
                }
            }
        } else if (ingredients.isEmpty()) {
            throw new IllegalArgumentException(
                    "A shapeless recipe needs at least one ingredient: " + discId
            );
        }
    }

    public Type type() {
        return type;
    }

    public List<String> pattern() {
        return Collections.unmodifiableList(pattern);
    }

    public Map<Character, IngredientDefinition> key() {
        return Collections.unmodifiableMap(key);
    }

    public List<IngredientDefinition> ingredients() {
        return Collections.unmodifiableList(ingredients);
    }

    public int resultCount() {
        return resultCount;
    }

    public String group() {
        return group;
    }

    public boolean showNotification() {
        return showNotification;
    }

    public IngredientDefinition unlockIngredient() {
        if (unlockIngredient != null) {
            return unlockIngredient;
        }

        if (type == Type.SHAPED) {
            return key.values().iterator().next();
        }

        return ingredients.getFirst();
    }
}
