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
import net.minecraft.world.level.block.Block;
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

        // The blocks of ingots and of raw items are mined with a pickaxe, at the same level as the ores of their
        // metal. They join Forge's shared forge:storage_blocks tag, one sub-tag per metal.
        for (Metal metal : Metal.values()) {
            Block metalBlock = ModBlocks.metalBlockOf(metal).get();
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(metalBlock);
            tag(metal.oreNeedsIronTool() ? BlockTags.NEEDS_IRON_TOOL : BlockTags.NEEDS_STONE_TOOL).add(metalBlock);
            tag(ModTags.storageBlock(metal)).add(metalBlock);
            tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.storageBlock(metal));
            if (metal.kind() == Metal.Kind.RAW) {
                Block rawBlock = ModBlocks.rawBlockOf(metal).get();
                tag(BlockTags.MINEABLE_WITH_PICKAXE).add(rawBlock);
                tag(metal.oreNeedsIronTool() ? BlockTags.NEEDS_IRON_TOOL : BlockTags.NEEDS_STONE_TOOL).add(rawBlock);
                tag(ModTags.rawStorageBlock(metal)).add(rawBlock);
                tag(Tags.Blocks.STORAGE_BLOCKS).addTag(ModTags.rawStorageBlock(metal));
            }
        }

        // The ores of the raw metals are mined with a pickaxe, at the stone level for the tin and the aluminum,
        // and at the iron level for the magnesium and the manganese (our alloys mine at the iron level too).
        for (Metal metal : Metal.values()) {
            if (metal.kind() != Metal.Kind.RAW) {
                continue;
            }
            Block ore = ModBlocks.oreOf(metal).get();
            Block deepslateOre = ModBlocks.deepslateOreOf(metal).get();
            tag(BlockTags.MINEABLE_WITH_PICKAXE).add(ore, deepslateOre);
            tag(metal.oreNeedsIronTool() ? BlockTags.NEEDS_IRON_TOOL : BlockTags.NEEDS_STONE_TOOL).add(ore, deepslateOre);

            // Forge's shared tags, so that other mods recognize the ores: forge:ores holds one sub-tag per metal.
            tag(ModTags.oreBlock(metal)).add(ore, deepslateOre);
            tag(Tags.Blocks.ORES).addTag(ModTags.oreBlock(metal));
            tag(Tags.Blocks.ORES_IN_GROUND_STONE).add(ore);
            tag(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE).add(deepslateOre);
        }
    }
}
