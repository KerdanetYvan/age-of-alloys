package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.content.ToolType;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModTags;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.SimpleCookingRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.crafting.AbstractCookingRecipe;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.registries.ForgeRegistries;

/** Generates the recipes of the mod. Forge-specific, so it lives in the datagen package. */
public class ModRecipeProvider extends RecipeProvider {
    public ModRecipeProvider(PackOutput output) {
        super(output);
    }

    @Override
    protected void buildRecipes(Consumer<FinishedRecipe> writer) {
        // X is an ingot of the alloy, S is a stick: the same shapes as the vanilla tools.
        for (Metal metal : Metal.values()) {
            if (metal.kind() != Metal.Kind.ALLOY) {
                continue;
            }
            tool(writer, metal, ToolType.PICKAXE, RecipeCategory.TOOLS, "XXX", " S ", " S ");
            tool(writer, metal, ToolType.AXE, RecipeCategory.TOOLS, "XX", "XS", " S");
            tool(writer, metal, ToolType.SHOVEL, RecipeCategory.TOOLS, "X", "S", "S");
            tool(writer, metal, ToolType.HOE, RecipeCategory.TOOLS, "XX", " S", " S");
            tool(writer, metal, ToolType.SWORD, RecipeCategory.COMBAT, "X", "X", "S");

        }

        AlloyingRecipeBuilder.alloying(ModItems.BRONZE_INGOT.get(), 4)
            .ingredient(ModTags.ingot(Metal.TIN), 1)
            .ingredient(Tags.Items.INGOTS_COPPER, 3)
            .heat(1)
            .time(200)
            .save(writer);

        // The four components are all required. The heat level asks for coal or better (1,600 ticks and more).
        AlloyingRecipeBuilder.alloying(ModItems.DURALUMIN_INGOT.get(), 8)
                .ingredient(ModTags.ingot(Metal.ALUMINUM), 5)
                .ingredient(Tags.Items.INGOTS_COPPER, 1)
                .ingredient(ModTags.ingot(Metal.MAGNESIUM), 1)
                .ingredient(ModTags.ingot(Metal.MANGANESE), 1)
                .heat(2)
                .time(200)
                .save(writer);

        // The coal is the carbon of the steel. The heat level asks for a coal block, a lava bucket or better.
        AlloyingRecipeBuilder.alloying(ModItems.STEEL_INGOT.get(), 4)
                .ingredient(Tags.Items.INGOTS_IRON, 4)
                .ingredient(ItemTags.COALS, 1)
                .heat(3)
                .time(200)
                .save(writer);

        // The foundry looks like a crucible: walls of stone bricks, a bucket on top and a smooth stone base.
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModItems.ALLOY_FOUNDRY.get())
                .pattern("XSX")
                .pattern("X X")
                .pattern("PPP")
                .define('X', Items.STONE_BRICKS)
                .define('S', Items.BUCKET)
                .define('P', Items.SMOOTH_STONE)
                .unlockedBy("has_stone_bricks", has(Items.STONE_BRICKS))
                .save(writer);

        // The raw item and both ores of each raw metal melt into its ingot, in a furnace and in a blast furnace.
        for (Metal metal : Metal.values()) {
            if (metal.kind() != Metal.Kind.RAW) {
                continue;
            }
            List<ItemLike> sources = List.of(ModItems.rawOf(metal).get(), ModBlocks.oreOf(metal).get(),
                    ModBlocks.deepslateOreOf(metal).get());
            Item ingot = ModItems.ingotOf(metal).get();
            smelting(writer, sources, RecipeCategory.MISC, ingot, 0.7F, 200, metal.ingotName());
            blasting(writer, sources, RecipeCategory.MISC, ingot, 0.7F, 100, metal.ingotName());
        }
    }

    /**
     * Melts each ingredient into the result in a furnace. Minecraft's own {@code oreSmelting} saves the recipes
     * in the "minecraft" namespace, so this one saves them under the id of the mod instead.
     */
    private static void smelting(Consumer<FinishedRecipe> writer, List<ItemLike> ingredients, RecipeCategory category,
            ItemLike result, float experience, int time, String group) {
        cooking(writer, RecipeSerializer.SMELTING_RECIPE, "smelting", ingredients, category, result, experience, time,
                group);
    }

    /** The same in a blast furnace, which is twice as fast. */
    private static void blasting(Consumer<FinishedRecipe> writer, List<ItemLike> ingredients, RecipeCategory category,
            ItemLike result, float experience, int time, String group) {
        cooking(writer, RecipeSerializer.BLASTING_RECIPE, "blasting", ingredients, category, result, experience, time,
                group);
    }

    private static void cooking(Consumer<FinishedRecipe> writer, RecipeSerializer<? extends AbstractCookingRecipe> type,
            String how, List<ItemLike> ingredients, RecipeCategory category, ItemLike result, float experience,
            int time, String group) {
        for (ItemLike ingredient : ingredients) {
            SimpleCookingRecipeBuilder.generic(Ingredient.of(ingredient), category, result, experience, time, type)
                    .group(group)
                    .unlockedBy(getHasName(ingredient), has(ingredient))
                    .save(writer, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID,
                            getItemName(result) + "_from_" + how + "_" + getItemName(ingredient)));
        }
    }

    private static void tool(Consumer<FinishedRecipe> writer, Metal metal, ToolType type, RecipeCategory category,
            String... rows) {
        TagKey<Item> ingot = ModTags.ingot(metal);
        ShapedRecipeBuilder recipe = ShapedRecipeBuilder.shaped(category, itemOf(metal, type));
        for (String row : rows) {
            recipe.pattern(row);
        }
        recipe.define('X', ingot)
                .define('S', Items.STICK)
                .unlockedBy("has_" + metal.ingotName(), has(ingot))
                .save(writer);
    }

    /** Finds a tool by its registered name, and fails loudly if it does not exist. */
    private static Item itemOf(Metal metal, ToolType type) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, metal.toolName(type));
        Item item = ForgeRegistries.ITEMS.getValue(id);
        if (item == null || item == Items.AIR) {
            throw new IllegalStateException("No item is registered as " + id);
        }
        return item;
    }
}
