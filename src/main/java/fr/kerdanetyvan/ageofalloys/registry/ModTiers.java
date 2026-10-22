package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.AlloyTier;
import fr.kerdanetyvan.ageofalloys.content.ToolStats;
import java.util.List;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.TierSortingRegistry;

/** Tool materials of the mod. Forge-specific (tier registration), so it lives in the registry package. */
public final class ModTiers {
    public static final TagKey<Block> NEEDS_BRONZE_TOOL =
            TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "needs_bronze_tool"));
    public static final TagKey<Block> NEEDS_DURALUMIN_TOOL =
            TagKey.create(Registries.BLOCK, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "needs_duralumin_tool"));

    public static final Tier BRONZE =
            new AlloyTier(ToolStats.BRONZE, NEEDS_BRONZE_TOOL, () -> Ingredient.of(ModItems.BRONZE_INGOT.get()));
    public static final Tier DURALUMIN =
            new AlloyTier(ToolStats.DURALUMIN, NEEDS_DURALUMIN_TOOL, () -> Ingredient.of(ModItems.DURALUMIN_INGOT.get()));

    /** Tells Forge where each tier ranks between the vanilla ones. Call it once, during common setup. */
    public static void register() {
        TierSortingRegistry.registerTier(BRONZE, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "bronze"),
                List.of(Tiers.IRON), List.of(Tiers.DIAMOND));

        TierSortingRegistry.registerTier(DURALUMIN, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "duralumin"),
                List.of(Tiers.IRON), List.of(Tiers.DIAMOND));
    }

    private ModTiers() {
    }
}
