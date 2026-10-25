package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModTiers;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.data.BlockTagsProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

/** Generates the block tags of the mod. Forge-specific, so it lives in the datagen package. */
public class ModBlockTagsProvider extends BlockTagsProvider {
    public ModBlockTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, AgeOfAlloys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        // No block needs more than the iron level yet, so these three tags stay empty for now.
        tag(ModTiers.NEEDS_BRONZE_TOOL);
        tag(ModTiers.NEEDS_DURALUMIN_TOOL);
        tag(ModTiers.NEEDS_STEEL_TOOL);

        // The foundry is mined with a pickaxe, and only drops its item with a stone pickaxe or better.
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.ALLOY_FOUNDRY.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.ALLOY_FOUNDRY.get());
    }
}
