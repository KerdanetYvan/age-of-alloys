package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModTags;
import fr.kerdanetyvan.ageofalloys.registry.ModTiers;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.tags.BlockTags;
import net.minecraftforge.common.Tags;
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

        // The tin ores are mined with a pickaxe, at the stone level like the copper ores.
        tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get());
        tag(BlockTags.NEEDS_STONE_TOOL).add(ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get());

        // Forge's shared tags, so that other mods recognize the ores of tin: forge:ores holds one sub-tag per metal.
        tag(ModTags.oreBlock(Metal.TIN)).add(ModBlocks.TIN_ORE.get(), ModBlocks.DEEPSLATE_TIN_ORE.get());
        tag(Tags.Blocks.ORES).addTag(ModTags.oreBlock(Metal.TIN));
        tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ModBlocks.TIN_ORE.get());
        tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(ModBlocks.DEEPSLATE_TIN_ORE.get());
    }
}
