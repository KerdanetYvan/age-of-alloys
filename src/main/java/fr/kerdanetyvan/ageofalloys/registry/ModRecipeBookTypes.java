package fr.kerdanetyvan.ageofalloys.registry;

import net.minecraft.world.inventory.RecipeBookType;

/**
 * The recipe book of the alloy foundry. Minecraft has one book type per kind of station (crafting,
 * furnace, blast furnace, smoker): Forge lets a mod add its own by extending the enum at load time.
 */
public final class ModRecipeBookTypes {
    /**
     * The name also builds the keys under which the player's settings for this book are saved, so it
     * carries the mod id. Forge replaces the body of {@code create} when the enum is extended.
     */
    public static final RecipeBookType ALLOY_FOUNDRY = RecipeBookType.create("AGEOFALLOYS_ALLOY_FOUNDRY");

    /**
     * Loads this class, which creates the book type. Called from the mod constructor, on both sides:
     * the server reads the same enum, for example to save the settings of the book of each player.
     */
    public static void register() {
    }

    private ModRecipeBookTypes() {
    }
}
