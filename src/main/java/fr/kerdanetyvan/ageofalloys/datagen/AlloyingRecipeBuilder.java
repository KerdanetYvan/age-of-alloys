package fr.kerdanetyvan.ageofalloys.datagen;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.recipe.AlloyIngredient;
import fr.kerdanetyvan.ageofalloys.recipe.AlloyingRecipe;
import fr.kerdanetyvan.ageofalloys.registry.ModRecipeTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.AdvancementRewards;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.advancements.critereon.ItemPredicate;
import net.minecraft.advancements.critereon.RecipeUnlockedTrigger;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeBuilder;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

/**
 * Writes the JSON file of an alloying recipe, the way the vanilla builders write theirs.
 * Forge-specific, so it lives in the datagen package.
 */
public class AlloyingRecipeBuilder {
    private final List<AlloyIngredient> ingredients = new ArrayList<>();
    private final Item result;
    private final int count;
    private int heat = 1;
    private int time = 200;

    private AlloyingRecipeBuilder(ItemLike result, int count) {
        this.result = result.asItem();
        this.count = count;
    }

    public static AlloyingRecipeBuilder alloying(ItemLike result, int count) {
        return new AlloyingRecipeBuilder(result, count);
    }

    /** Adds an ingredient: {@code count} items of the tag. */
    public AlloyingRecipeBuilder ingredient(TagKey<Item> tag, int count) {
        ingredients.add(new AlloyIngredient(tag, count));
        return this;
    }

    /** The heat level the fuel must reach (1, 2 or 3). */
    public AlloyingRecipeBuilder heat(int heat) {
        this.heat = heat;
        return this;
    }

    /** The melting time in ticks. */
    public AlloyingRecipeBuilder time(int time) {
        this.time = time;
        return this;
    }

    /** Saves the recipe as {@code ageofalloys:alloying/<result name>}. */
    public void save(Consumer<FinishedRecipe> writer) {
        ResourceLocation resultId = ForgeRegistries.ITEMS.getKey(result);
        save(writer, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "alloying/" + resultId.getPath()));
    }

    public void save(Consumer<FinishedRecipe> writer, ResourceLocation id) {
        if (ingredients.isEmpty() || ingredients.size() > AlloyingRecipe.INPUT_SLOTS) {
            throw new IllegalStateException("An alloying recipe takes 1 to " + AlloyingRecipe.INPUT_SLOTS
                    + " ingredients: " + id);
        }
        // The recipe unlocks in the recipe book as soon as the player holds an item of any of its ingredients
        // (the requirements are "or"), or when the recipe is given by a command.
        Advancement.Builder advancement = Advancement.Builder.recipeAdvancement()
                .parent(RecipeBuilder.ROOT_RECIPE_ADVANCEMENT)
                .addCriterion("has_the_recipe", RecipeUnlockedTrigger.unlocked(id))
                .rewards(AdvancementRewards.Builder.recipe(id))
                .requirements(RequirementsStrategy.OR);
        for (AlloyIngredient ingredient : ingredients) {
            advancement.addCriterion("has_" + ingredient.tag().location().getPath().replace('/', '_'),
                    InventoryChangeTrigger.TriggerInstance.hasItems(
                            ItemPredicate.Builder.item().of(ingredient.tag()).build()));
        }
        ResourceLocation advancementId = ResourceLocation.fromNamespaceAndPath(id.getNamespace(),
                "recipes/" + RecipeCategory.MISC.getFolderName() + "/" + id.getPath());
        writer.accept(new Result(id, ingredients, result, count, heat, time, advancement, advancementId));
    }

    private record Result(ResourceLocation id, List<AlloyIngredient> ingredients, Item result, int count, int heat,
            int time, Advancement.Builder advancement, ResourceLocation advancementId) implements FinishedRecipe {
        @Override
        public void serializeRecipeData(JsonObject json) {
            JsonArray array = new JsonArray();
            for (AlloyIngredient ingredient : ingredients) {
                array.add(ingredient.toJson());
            }
            json.add("ingredients", array);

            JsonObject resultJson = new JsonObject();
            resultJson.addProperty("item", ForgeRegistries.ITEMS.getKey(result).toString());
            resultJson.addProperty("count", count);
            json.add("result", resultJson);

            json.addProperty("heat", heat);
            json.addProperty("time", time);
        }

        @Override
        public ResourceLocation getId() {
            return id;
        }

        @Override
        public RecipeSerializer<?> getType() {
            return ModRecipeTypes.ALLOYING_SERIALIZER.get();
        }

        @Override
        public @Nullable JsonObject serializeAdvancement() {
            return advancement.serializeToJson();
        }

        @Override
        public @Nullable ResourceLocation getAdvancementId() {
            return advancementId;
        }
    }
}
