package fr.kerdanetyvan.ageofalloys.recipe;

import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModRecipeTypes;
import java.util.List;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

/**
 * A recipe of the alloy foundry: up to four tag ingredients with a quantity, melted into a result.
 * The first slots of the container it is given are the inputs of the foundry.
 */
public class AlloyingRecipe implements Recipe<Container> {
    /** How many input slots of the container the recipe reads (the foundry has four). */
    public static final int INPUT_SLOTS = 4;

    private final ResourceLocation id;
    private final List<AlloyIngredient> ingredients;
    private final ItemStack result;
    private final int heat;
    private final int time;

    public AlloyingRecipe(ResourceLocation id, List<AlloyIngredient> ingredients, ItemStack result, int heat,
            int time) {
        this.id = id;
        this.ingredients = ingredients;
        this.result = result;
        this.heat = heat;
        this.time = time;
    }

    /** The ingredients, with the quantity of each. (Not {@code getIngredients}: that one is vanilla's.) */
    public List<AlloyIngredient> alloyIngredients() {
        return ingredients;
    }

    /** The item produced, with its quantity. */
    public ItemStack result() {
        return result;
    }

    /** The heat level the fuel must reach. Read and saved, but only enforced by a later ticket. */
    public int heat() {
        return heat;
    }

    /** The melting time in ticks. */
    public int time() {
        return time;
    }

    @Override
    public boolean matches(Container inputs, Level level) {
        for (AlloyIngredient ingredient : ingredients) {
            int total = 0;
            for (int slot = 0; slot < INPUT_SLOTS; slot++) {
                ItemStack stack = inputs.getItem(slot);
                if (ingredient.accepts(stack)) {
                    total += stack.getCount();
                }
            }
            if (total < ingredient.count()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public ItemStack assemble(Container inputs, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        return result;
    }

    @Override
    public ResourceLocation getId() {
        return id;
    }

    @Override
    public RecipeSerializer<?> getSerializer() {
        return ModRecipeTypes.ALLOYING_SERIALIZER.get();
    }

    @Override
    public RecipeType<?> getType() {
        return ModRecipeTypes.ALLOYING.get();
    }

    /**
     * The ingredients as the recipe book sees them: one vanilla ingredient per tag, without the quantity
     * (the book only needs to know what the recipe is made of). A recipe without any is hidden by the book.
     */
    @Override
    public NonNullList<Ingredient> getIngredients() {
        NonNullList<Ingredient> list = NonNullList.create();
        for (AlloyIngredient ingredient : ingredients) {
            list.add(Ingredient.of(ingredient.tag()));
        }
        return list;
    }

    /** The icon of the "recipe unlocked" toast. */
    @Override
    public ItemStack getToastSymbol() {
        return new ItemStack(ModBlocks.ALLOY_FOUNDRY.get());
    }
}
