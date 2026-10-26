package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import java.util.List;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.BiomeTags;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * The generation of the ores in the world, written by datagen as three JSON files per ore:
 * the configured feature (what is placed: a vein), the placed feature (where and how many) and the
 * biome modifier (in which biomes). Forge-specific, so it lives in the datagen package.
 */
public final class ModWorldGen {
    /** The vein of one raw metal: its size, the attempts per chunk and the trapezoid of heights. */
    private record Vein(Metal metal, int size, int count, int minY, int maxY) {
    }

    /** One vein per raw metal, with the values of docs/design/raw-materials.md (provisional). */
    private static final List<Vein> VEINS = List.of(
            new Vein(Metal.TIN, 8, 12, -24, 80),
            new Vein(Metal.ALUMINUM, 9, 14, 0, 128),
            new Vein(Metal.MAGNESIUM, 6, 4, -48, 48),
            new Vein(Metal.MANGANESE, 6, 4, -64, 16)
    );

    // The names of the three pieces of an ore. The same name in two registries is fine.
    public static ResourceKey<ConfiguredFeature<?, ?>> configuredKey(Metal metal) {
        return ResourceKey.create(Registries.CONFIGURED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "ore_" + metal.id()));
    }

    public static ResourceKey<PlacedFeature> placedKey(Metal metal) {
        return ResourceKey.create(Registries.PLACED_FEATURE,
                ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "ore_" + metal.id()));
    }

    public static ResourceKey<BiomeModifier> modifierKey(Metal metal) {
        return ResourceKey.create(ForgeRegistries.Keys.BIOME_MODIFIERS,
                ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "add_" + metal.id() + "_ore"));
    }

    /** Tells the data generator which registries to write, and with which code. */
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModWorldGen::configuredFeatures)
            .add(Registries.PLACED_FEATURE, ModWorldGen::placedFeatures)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModWorldGen::biomeModifiers);

    /** What is placed: the veins. Stone is replaced by the ore, deepslate by the deepslate ore. */
    private static void configuredFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {
        for (Vein vein : VEINS) {
            Metal metal = vein.metal();
            List<OreConfiguration.TargetBlockState> targets = List.of(
                    OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                            ModBlocks.oreOf(metal).get().defaultBlockState()),
                    OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                            ModBlocks.deepslateOreOf(metal).get().defaultBlockState()));
            context.register(configuredKey(metal),
                    new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets, vein.size())));
        }
    }

    /** Where and how many: attempts per chunk, spread in the chunk, height in a trapezoid, biome filter. */
    private static void placedFeatures(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);
        for (Vein vein : VEINS) {
            Metal metal = vein.metal();
            context.register(placedKey(metal), new PlacedFeature(configured.getOrThrow(configuredKey(metal)), List.of(
                    CountPlacement.of(vein.count()),
                    InSquarePlacement.spread(),
                    HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(vein.minY()),
                            VerticalAnchor.absolute(vein.maxY()))),
                    BiomeFilter.biome())));
        }
    }

    /** In which biomes: every biome of the overworld, at the step of the underground ores like copper and iron. */
    private static void biomeModifiers(BootstapContext<BiomeModifier> context) {
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        for (Vein vein : VEINS) {
            Metal metal = vein.metal();
            context.register(modifierKey(metal), new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                    biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                    HolderSet.direct(placed.getOrThrow(placedKey(metal))),
                    GenerationStep.Decoration.UNDERGROUND_ORES));
        }
    }

    private ModWorldGen() {
    }
}
