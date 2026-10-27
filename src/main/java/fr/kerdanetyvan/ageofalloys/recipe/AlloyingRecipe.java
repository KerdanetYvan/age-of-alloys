package fr.kerdanetyvan.ageofalloys.recipe;

import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModRecipeTypes;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import net.minecraft.ChatFormatting;
import net.minecraft.core.NonNullList;
import net.minecraft.core.RegistryAccess;
import net.minecraft.locale.Language;
import net.minecraft.nbt.ListTag;
import net.minecraft.nbt.StringTag;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.Container;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.item.crafting.Recipe;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;

/**
 * A recipe of the alloy foundry: up to four tag ingredients with a quantity, melted into a result.
 * The first slots of the container it is given are the inputs of the foundry. The inputs must hold
 * nothing but the ingredients of the recipe: an item that is not one of them blocks it.
 */
public class AlloyingRecipe implements Recipe<Container> {
    /** How many input slots of the container the recipe reads (the foundry has four). */
    public static final int INPUT_SLOTS = 4;

    private final ResourceLocation id;
    private final List<AlloyIngredient> ingredients;
    private final ItemStack result;
    private final int heat;
    private final int time;
    /** The result with its description, built the first time the recipe book asks for it. */
    private ItemStack described;

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
        for (int slot = 0; slot < INPUT_SLOTS; slot++) {
            ItemStack stack = inputs.getItem(slot);
            if (!stack.isEmpty() && !isIngredient(stack)) {
                return false;
            }
        }
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

    /** Whether a stack belongs to one of the ingredients of the recipe. */
    private boolean isIngredient(ItemStack stack) {
        for (AlloyIngredient ingredient : ingredients) {
            if (ingredient.accepts(stack)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public ItemStack assemble(Container inputs, RegistryAccess registryAccess) {
        return result.copy();
    }

    @Override
    public boolean canCraftInDimensions(int width, int height) {
        return true;
    }

    /**
     * The result as the recipe book shows it: the item with a description (its ingredients, the heat and
     * the melting time) as lore. The book only displays this item and its tooltip, so this is how the
     * quantities reach the player. What the foundry really produces is {@link #result()}.
     */
    @Override
    public ItemStack getResultItem(RegistryAccess registryAccess) {
        if (described != null) {
            return described;
        }
        List<Component> lines = new ArrayList<>();
        for (AlloyIngredient ingredient : ingredients) {
            ItemStack[] items = Ingredient.of(ingredient.tag()).getItems();
            if (items.length == 0) {
                // The tags are not loaded yet: show the plain result, and describe it on a later call.
                return result;
            }
            lines.add(Component.translatable("tooltip.ageofalloys.recipe_book.ingredient", ingredient.count(),
                    ingredientName(ingredient.tag(), items[0])));
        }
        lines.add(Component.translatable("tooltip.ageofalloys.recipe_book.heat." + heat));
        String seconds = time % 20 == 0 ? String.valueOf(time / 20) : String.format(Locale.ROOT, "%.1f", time / 20.0);
        lines.add(Component.translatable("tooltip.ageofalloys.recipe_book.time", seconds));

        ListTag lore = new ListTag();
        for (Component line : lines) {
            // Lore is purple and italic by default: plain gray text reads better here.
            lore.add(StringTag.valueOf(Component.Serializer.toJson(
                    line.copy().withStyle(style -> style.withColor(ChatFormatting.GRAY).withItalic(false)))));
        }
        ItemStack stack = result.copy();
        stack.getOrCreateTagElement("display").put("Lore", lore);
        described = stack;
        return stack;
    }

    /**
     * The name of an ingredient: a language key for the tag when the mod has one (for example "coal or
     * charcoal"), else the name of the first item of the tag.
     */
    private static Component ingredientName(TagKey<Item> tag, ItemStack first) {
        ResourceLocation id = tag.location();
        String key = "tag.item." + id.getNamespace() + "." + id.getPath().replace('/', '.');
        return Language.getInstance().has(key) ? Component.translatable(key) : first.getHoverName();
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
