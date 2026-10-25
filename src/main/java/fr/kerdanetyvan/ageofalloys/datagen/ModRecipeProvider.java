package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.content.ToolType;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
import fr.kerdanetyvan.ageofalloys.registry.ModTags;
import java.util.function.Consumer;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
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
