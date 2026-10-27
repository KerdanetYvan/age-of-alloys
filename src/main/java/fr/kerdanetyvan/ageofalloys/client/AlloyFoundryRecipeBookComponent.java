package fr.kerdanetyvan.ageofalloys.client;

import java.util.List;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.network.chat.Component;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.crafting.Recipe;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * The recipe book of the alloy foundry. Minecraft's component draws the panel (tabs, search, grid of
 * recipes); this one only says what is specific to the foundry. The book is for looking recipes up: our
 * ingredients are tags with quantities, so a click on a recipe puts nothing in the slots.
 */
public class AlloyFoundryRecipeBookComponent extends RecipeBookComponent {
    private static final Component FILTER_NAME = Component.translatable("gui.ageofalloys.recipe_book.meltable");

    @Override
    protected void initFilterButtonTextures() {
        // The same filter button as the furnace's.
        filterButton.initTextureValues(152, 182, 28, 18, RECIPE_BOOK_LOCATION);
    }

    @Override
    protected @NotNull Component getRecipeFilterName() {
        return FILTER_NAME;
    }

    @Override
    public void slotClicked(@Nullable Slot slot) {
        super.slotClicked(slot);
        if (slot != null && slot.index < menu.getSize()) {
            ghostRecipe.clear();
        }
    }

    /** No ghost recipe: nothing is placed, so there is nothing to show in the slots. */
    @Override
    public void setupGhostRecipe(@NotNull Recipe<?> recipe, @NotNull List<Slot> slots) {
    }
}
