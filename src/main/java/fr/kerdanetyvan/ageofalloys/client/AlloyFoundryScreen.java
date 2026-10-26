package fr.kerdanetyvan.ageofalloys.client;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Heat;
import fr.kerdanetyvan.ageofalloys.menu.AlloyFoundryMenu;
import net.minecraft.client.gui.GuiGraphics;
import net.minecraft.client.gui.screens.inventory.AbstractContainerScreen;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.player.Inventory;
import org.jetbrains.annotations.NotNull;

/** The screen of the alloy foundry. Client only: it draws what the menu holds, and decides nothing. */
public class AlloyFoundryScreen extends AbstractContainerScreen<AlloyFoundryMenu> {
    private static final ResourceLocation TEXTURE =
            new ResourceLocation(AgeOfAlloys.MOD_ID, "textures/gui/alloy_foundry.png");

    public AlloyFoundryScreen(AlloyFoundryMenu menu, Inventory playerInventory, Component title) {
        super(menu, playerInventory, title);
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
        super.render(graphics, mouseX, mouseY, partialTick);
        renderTooltip(graphics, mouseX, mouseY);
    }
}
