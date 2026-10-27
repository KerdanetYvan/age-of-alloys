package fr.kerdanetyvan.ageofalloys.client;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Heat;
import fr.kerdanetyvan.ageofalloys.menu.AlloyFoundryMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.components.ImageButton;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.client.gui.screens.recipebook.RecipeBookComponent;
import net.minecraft.client.gui.screens.recipebook.RecipeUpdateListener;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.ClickType;
import net.minecraft.world.inventory.Slot;
import org.jetbrains.annotations.NotNull;

/**
 * The screen of the alloy foundry. Client only: it draws what the menu holds, and decides nothing. It
 * hosts the recipe book the way the furnace screen does: the book gets the clicks and the keys first, and
 * the window moves to the right while the book is open.
 */
public class AlloyFoundryScreen extends AbstractContainerScreen<AlloyFoundryMenu> implements RecipeUpdateListener {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AgeOfAlloys.MOD_ID, "textures/gui/alloy_foundry.png");
    /** The button of the furnace and of the crafting table, from Minecraft's own textures. */
    private static final ResourceLocation RECIPE_BUTTON = new ResourceLocation("textures/gui/recipe_button.png");

    /** Position of the recipe book button in the window. */
    private static final int BUTTON_X = 20;
    private static final int BUTTON_Y = 54;

    private final AlloyFoundryRecipeBookComponent recipeBook = new AlloyFoundryRecipeBookComponent();
    private boolean widthTooNarrow;

    public AlloyFoundryScreen(AlloyFoundryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
    }

    @Override
    protected void init() {
        super.init();
        widthTooNarrow = width < 379;
        recipeBook.init(width, height, minecraft, widthTooNarrow, menu);
        leftPos = recipeBook.updateScreenPosition(width, imageWidth);
        // On the left, next to where the book opens. Under the four inputs, which sit where the furnace has its button.
        addRenderableWidget(new ImageButton(leftPos + BUTTON_X, topPos + BUTTON_Y, 20, 18, 0, 0, 19, RECIPE_BUTTON, button -> {
            recipeBook.toggleVisibility();
            leftPos = recipeBook.updateScreenPosition(width, imageWidth);
            button.setPosition(leftPos + BUTTON_X, topPos + BUTTON_Y);
        }));
    }

    @Override
    protected void containerTick() {
        super.containerTick();
        recipeBook.tick();
    }

    @Override
    protected void renderBg(@NotNull GuiGraphics graphics, float partialTick, int mouseX, int mouseY) {
        // The panel: the 176 x 166 area of the texture, drawn at the top left corner of the window.
        graphics.blit(TEXTURE, leftPos, topPos, 0, 0, imageWidth, imageHeight);

        // The flame empties from the top: draw only the bottom part of the full flame (14 pixels high in the texture).
        int burnDuration = menu.getBurnDuration();
        // Rounded up, so that one line remains while the fire burns: it must not vanish before the fuel is gone.
        int flameHeight = burnDuration > 0 ? (menu.getBurnTime() * 14 + burnDuration - 1) / burnDuration : 0;
        // One flame per heat level, 14 pixels apart in the texture: orange, orange-red, blue.
        int flameU = 176 + (Heat.levelOf(burnDuration) - 1) * 14;
        graphics.blit(TEXTURE, leftPos + 75, topPos + 36 + 14 - flameHeight, flameU, 14 - flameHeight, 14, flameHeight);

        // The arrow grows from left to right with the progress of the melting.
        int progressMax = menu.getProgressMax();
        int arrowWidth = progressMax > 0 ? menu.getProgress() * 24 / progressMax : 0;
        graphics.blit(TEXTURE, leftPos + 96, topPos + 34, 176, 14, arrowWidth, 17);
    }

    @Override
    public void render(@NotNull GuiGraphics graphics, int mouseX, int mouseY, float partialTick) {
        renderBackground(graphics);
        if (recipeBook.isVisible() && widthTooNarrow) {
            // No room beside the window: the book replaces the slots instead of sitting next to them.
            renderBg(graphics, partialTick, mouseX, mouseY);
            recipeBook.render(graphics, mouseX, mouseY, partialTick);
        } else {
            recipeBook.render(graphics, mouseX, mouseY, partialTick);
            super.render(graphics, mouseX, mouseY, partialTick);
        }
        renderTooltip(graphics, mouseX, mouseY);
        recipeBook.renderTooltip(graphics, leftPos, topPos, mouseX, mouseY);
    }

    @Override
    public boolean mouseClicked(double mouseX, double mouseY, int button) {
        if (recipeBook.mouseClicked(mouseX, mouseY, button)) {
            return true;
        }
        return widthTooNarrow && recipeBook.isVisible() || super.mouseClicked(mouseX, mouseY, button);
    }

    @Override
    protected void slotClicked(@NotNull Slot slot, int slotId, int mouseButton, @NotNull ClickType type) {
        super.slotClicked(slot, slotId, mouseButton, type);
        recipeBook.slotClicked(slot);
    }

    @Override
    public boolean keyPressed(int keyCode, int scanCode, int modifiers) {
        return !recipeBook.keyPressed(keyCode, scanCode, modifiers) && super.keyPressed(keyCode, scanCode, modifiers);
    }

    @Override
    public boolean charTyped(char codePoint, int modifiers) {
        return recipeBook.charTyped(codePoint, modifiers) || super.charTyped(codePoint, modifiers);
    }

    @Override
    protected boolean hasClickedOutside(double mouseX, double mouseY, int guiLeft, int guiTop, int mouseButton) {
        boolean outsideWindow = mouseX < guiLeft || mouseY < guiTop
                || mouseX >= guiLeft + imageWidth || mouseY >= guiTop + imageHeight;
        return recipeBook.hasClickedOutside(mouseX, mouseY, leftPos, topPos, imageWidth, imageHeight, mouseButton)
                && outsideWindow;
    }

    @Override
    public void recipesUpdated() {
        recipeBook.recipesUpdated();
    }

    @Override
    public @NotNull RecipeBookComponent getRecipeBookComponent() {
        return recipeBook;
    }
}
