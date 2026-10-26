package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.block.AlloyFoundryBlock;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import java.util.List;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.DropExperienceBlock;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.MapColor;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Registration of the mod's blocks. Forge-specific, so it lives in the registry package. */
public final class ModBlocks {
    private static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, AgeOfAlloys.MOD_ID);

    public static final RegistryObject<Block> ALLOY_FOUNDRY = BLOCKS.register("alloy_foundry",
            () -> new AlloyFoundryBlock(BlockBehaviour.Properties.of()
                    .strength(3.5F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.METAL)
                    .lightLevel(state -> state.getValue(AlloyFoundryBlock.LIT) ? 13 : 0)
            ));

    // The ores have the properties of the vanilla copper ores: hard, and they only drop with the right tool.
    public static final RegistryObject<Block> TIN_ORE = ore(Metal.TIN, false);
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = ore(Metal.TIN, true);

    private static RegistryObject<Block> ore(Metal metal, boolean deepslate) {
        if (deepslate) {
            return BLOCKS.register(metal.deepslateOreName(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)));
        }
        return BLOCKS.register(metal.oreName(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                .mapColor(MapColor.STONE)
                .requiresCorrectToolForDrops()
                .strength(3.0F, 3.0F)));
    }

    /** Every block of the mod, for the loot tables. */
    public static List<Block> all() {
        return BLOCKS.getEntries().stream().map(RegistryObject::get).toList();
    }

    public static void register(IEventBus modEventBus) {
        BLOCKS.register(modEventBus);
    }

    private ModBlocks() {
    }
}
