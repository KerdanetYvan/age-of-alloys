package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.block.AlloyFoundryBlock;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
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

    // The ores of each raw metal, in stone and in deepslate. The tables come first: the constants below fill them.
    private static final Map<Metal, RegistryObject<Block>> ORES = new EnumMap<>(Metal.class);
    private static final Map<Metal, RegistryObject<Block>> DEEPSLATE_ORES = new EnumMap<>(Metal.class);

    // The ores have the properties of the vanilla copper ores: hard, and they only drop with the right tool.
    public static final RegistryObject<Block> TIN_ORE = ore(Metal.TIN, false);
    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE = ore(Metal.TIN, true);
    public static final RegistryObject<Block> ALUMINUM_ORE = ore(Metal.ALUMINUM, false);
    public static final RegistryObject<Block> DEEPSLATE_ALUMINUM_ORE = ore(Metal.ALUMINUM, true);
    public static final RegistryObject<Block> MAGNESIUM_ORE = ore(Metal.MAGNESIUM, false);
    public static final RegistryObject<Block> DEEPSLATE_MAGNESIUM_ORE = ore(Metal.MAGNESIUM, true);
    public static final RegistryObject<Block> MANGANESE_ORE = ore(Metal.MANGANESE, false);
    public static final RegistryObject<Block> DEEPSLATE_MANGANESE_ORE = ore(Metal.MANGANESE, true);

    private static RegistryObject<Block> ore(Metal metal, boolean deepslate) {
        RegistryObject<Block> block;
        if (deepslate) {
            block = BLOCKS.register(metal.deepslateOreName(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.DEEPSLATE)
                    .requiresCorrectToolForDrops()
                    .strength(4.5F, 3.0F)
                    .sound(SoundType.DEEPSLATE)));
            DEEPSLATE_ORES.put(metal, block);
        } else {
            block = BLOCKS.register(metal.oreName(), () -> new DropExperienceBlock(BlockBehaviour.Properties.of()
                    .mapColor(MapColor.STONE)
                    .requiresCorrectToolForDrops()
                    .strength(3.0F, 3.0F)));
            ORES.put(metal, block);
        }
        return block;
    }

    /** The ore of a raw metal in stone, for example the tin ore. */
    public static RegistryObject<Block> oreOf(Metal metal) {
        return ORES.get(metal);
    }

    /** The ore of a raw metal in deepslate. */
    public static RegistryObject<Block> deepslateOreOf(Metal metal) {
        return DEEPSLATE_ORES.get(metal);
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
