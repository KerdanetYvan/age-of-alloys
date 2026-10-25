package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import java.util.Set;
import net.minecraft.data.loot.BlockLootSubProvider;
import net.minecraft.world.flag.FeatureFlags;
import net.minecraft.world.level.block.Block;

/** What the blocks of the mod drop when they are broken. Forge-specific, so it lives in the datagen package. */
public class ModBlockLootSubProvider extends BlockLootSubProvider {
    public ModBlockLootSubProvider() {
        super(Set.of(), FeatureFlags.REGISTRY.allFlags());
    }

    @Override
    protected void generate() {
        dropSelf(ModBlocks.ALLOY_FOUNDRY.get());
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.all();
    }
}
