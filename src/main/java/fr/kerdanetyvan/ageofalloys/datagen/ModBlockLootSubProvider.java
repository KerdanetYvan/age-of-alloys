package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
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

        // Every block drops itself.
        for (Metal metal : Metal.values()) {
            dropSelf(ModBlocks.metalBlockOf(metal).get());
            if (metal.kind() == Metal.Kind.RAW) {
                dropSelf(ModBlocks.rawBlockOf(metal).get());
            }
        }

        // Every ore drops the raw item of its metal (more with Fortune), or itself with Silk Touch.
        for (Metal metal : Metal.values()) {
            if (metal.kind() == Metal.Kind.RAW) {
                Block ore = ModBlocks.oreOf(metal).get();
                Block deepslateOre = ModBlocks.deepslateOreOf(metal).get();
                add(ore, createOreDrop(ore, ModItems.rawOf(metal).get()));
                add(deepslateOre, createOreDrop(deepslateOre, ModItems.rawOf(metal).get()));
            }
        }
    }

    @Override
    protected Iterable<Block> getKnownBlocks() {
        return ModBlocks.all();
    }
}
