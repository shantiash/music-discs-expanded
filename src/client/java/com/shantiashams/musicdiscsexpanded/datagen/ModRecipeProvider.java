package com.shantiashams.musicdiscsexpanded.datagen;

import com.shantiashams.musicdiscsexpanded.disc.DiscDefinition;
import com.shantiashams.musicdiscsexpanded.disc.DiscRecipe;
import com.shantiashams.musicdiscsexpanded.disc.DiscRegistry;
import com.shantiashams.musicdiscsexpanded.item.ModItems;
import net.fabricmc.fabric.api.datagen.v1.FabricPackOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeOutput;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

/** Generates crafting recipes declared directly in DiscRegistry. */
public final class ModRecipeProvider extends FabricRecipeProvider {

    public ModRecipeProvider(
            FabricPackOutput output,
            CompletableFuture<HolderLookup.Provider> registriesFuture
    ) {
        super(output, registriesFuture);
    }

    @Override
    protected RecipeProvider createRecipeProvider(
            HolderLookup.Provider registries,
            RecipeOutput output
    ) {
        return new Provider(registries, output);
    }

    @Override
    public String getName() {
        return "Music Discs Expanded Recipes";
    }

    private static final class Provider extends RecipeProvider {

        private Provider(
                HolderLookup.Provider registries,
                RecipeOutput output
        ) {
            super(registries, output);
        }

        @Override
        public void buildRecipes() {
            for (DiscDefinition disc : DiscRegistry.all()) {
                disc.craftingRecipe().ifPresent(
                        recipe -> generateDiscRecipe(disc, recipe)
                );
            }
        }

        private void generateDiscRecipe(
                DiscDefinition disc,
                DiscRecipe recipe
        ) {
            if (recipe.type() == DiscRecipe.Type.SHAPED) {
                generateShaped(disc, recipe);
            } else {
                generateShapeless(disc, recipe);
            }
        }

        private void generateShaped(
                DiscDefinition disc,
                DiscRecipe recipe
        ) {
            ShapedRecipeBuilder builder = shaped(
                    RecipeCategory.MISC,
                    ModItems.get(disc),
                    recipe.resultCount()
            );

            for (String row : recipe.pattern()) {
                builder.pattern(row);
            }

            for (Map.Entry<Character, DiscRecipe.IngredientDefinition> entry
                    : recipe.key().entrySet()) {
                define(builder, entry.getKey(), entry.getValue());
            }

            if (recipe.group() != null) {
                builder.group(recipe.group());
            }

            builder.showNotification(recipe.showNotification());
            addUnlock(builder, recipe.unlockIngredient());
            builder.save(output);
        }

        private void generateShapeless(
                DiscDefinition disc,
                DiscRecipe recipe
        ) {
            ShapelessRecipeBuilder builder = shapeless(
                    RecipeCategory.MISC,
                    ModItems.get(disc),
                    recipe.resultCount()
            );

            for (DiscRecipe.IngredientDefinition ingredient
                    : recipe.ingredients()) {
                require(builder, ingredient);
            }

            if (recipe.group() != null) {
                builder.group(recipe.group());
            }

            addUnlock(builder, recipe.unlockIngredient());
            builder.save(output);
        }

        private static void define(
                ShapedRecipeBuilder builder,
                char symbol,
                DiscRecipe.IngredientDefinition ingredient
        ) {
            if (ingredient instanceof DiscRecipe.ItemIngredient item) {
                builder.define(symbol, item.item());
            } else if (ingredient instanceof DiscRecipe.TagIngredient tag) {
                builder.define(symbol, tag.tag());
            } else {
                throw new IllegalStateException(
                        "Unsupported recipe ingredient: " + ingredient
                );
            }
        }

        private static void require(
                ShapelessRecipeBuilder builder,
                DiscRecipe.IngredientDefinition ingredient
        ) {
            if (ingredient instanceof DiscRecipe.ItemIngredient item) {
                builder.requires(item.item());
            } else if (ingredient instanceof DiscRecipe.TagIngredient tag) {
                builder.requires(tag.tag());
            } else {
                throw new IllegalStateException(
                        "Unsupported recipe ingredient: " + ingredient
                );
            }
        }

        private void addUnlock(
                RecipeBuilder builder,
                DiscRecipe.IngredientDefinition ingredient
        ) {
            if (ingredient instanceof DiscRecipe.ItemIngredient item) {
                builder.unlockedBy(
                        getHasName(item.item()),
                        has(item.item())
                );
            } else if (ingredient instanceof DiscRecipe.TagIngredient tag) {
                String criterionName = "has_"
                        + tag.tag().location().getPath().replace('/', '_');

                builder.unlockedBy(
                        criterionName,
                        has(tag.tag())
                );
            } else {
                throw new IllegalStateException(
                        "Unsupported recipe unlock ingredient: " + ingredient
                );
            }
        }
    }
}
