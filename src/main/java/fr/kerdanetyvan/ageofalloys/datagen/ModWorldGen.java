package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.registry.ModBlocks;
import java.util.List;
import net.minecraft.core.HolderGetter;
import net.minecraft.core.HolderSet;
import net.minecraft.tags.BiomeTags;
import net.minecraft.world.level.biome.Biome;
import net.minecraft.world.level.levelgen.GenerationStep;
import net.minecraftforge.common.world.ForgeBiomeModifiers;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.heightproviders.TrapezoidHeight;
import net.minecraft.world.level.levelgen.placement.BiomeFilter;
import net.minecraft.world.level.levelgen.placement.CountPlacement;
import net.minecraft.world.level.levelgen.placement.HeightRangePlacement;
import net.minecraft.world.level.levelgen.placement.InSquarePlacement;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.TagMatchTest;
import net.minecraft.core.registries.Registries;
import net.minecraft.data.worldgen.BootstapContext;
import net.minecraft.resources.ResourceKey;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.core.RegistrySetBuilder;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.placement.PlacedFeature;
import net.minecraftforge.common.world.BiomeModifier;
import net.minecraftforge.registries.ForgeRegistries;

/**
 * The generation of the ores in the world, written by datagen as three JSON files per ore:
 * the configured feature (what is placed: a vein), the placed feature (where and how many) and the
 * biome modifier (in which biomes). Forge-specific, so it lives in the datagen package.
 */
public final class ModWorldGen {
    // The names of the three pieces of the tin ore. The same name in two registries is fine.
    public static final ResourceKey<ConfiguredFeature<?, ?>> TIN_CONFIGURED = ResourceKey.create(
            Registries.CONFIGURED_FEATURE, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "ore_tin"));
    public static final ResourceKey<PlacedFeature> TIN_PLACED = ResourceKey.create(
            Registries.PLACED_FEATURE, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "ore_tin"));
    public static final ResourceKey<BiomeModifier> ADD_TIN_ORE = ResourceKey.create(
            ForgeRegistries.Keys.BIOME_MODIFIERS, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "add_tin_ore"));

    /** Tells the data generator which registries to write, and with which code. */
    public static final RegistrySetBuilder BUILDER = new RegistrySetBuilder()
            .add(Registries.CONFIGURED_FEATURE, ModWorldGen::configuredFeatures)
            .add(Registries.PLACED_FEATURE, ModWorldGen::placedFeatures)
            .add(ForgeRegistries.Keys.BIOME_MODIFIERS, ModWorldGen::biomeModifiers);

    /** What is placed: the veins. */
    private static void configuredFeatures(BootstapContext<ConfiguredFeature<?, ?>> context) {
        List<OreConfiguration.TargetBlockState> targets = List.of(
            OreConfiguration.target(new TagMatchTest(BlockTags.STONE_ORE_REPLACEABLES),
                    ModBlocks.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(new TagMatchTest(BlockTags.DEEPSLATE_ORE_REPLACEABLES),
                    ModBlocks.DEEPSLATE_TIN_ORE.get().defaultBlockState())
        );
        context.register(TIN_CONFIGURED, new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(targets, 8)));
    }

    /** Where and how many. */
    private static void placedFeatures(BootstapContext<PlacedFeature> context) {
        HolderGetter<ConfiguredFeature<?, ?>> configured = context.lookup(Registries.CONFIGURED_FEATURE);
        context.register(TIN_PLACED, new PlacedFeature(configured.getOrThrow(TIN_CONFIGURED), List.of(
                CountPlacement.of(12),
                InSquarePlacement.spread(),
                HeightRangePlacement.of(TrapezoidHeight.of(VerticalAnchor.absolute(-24), VerticalAnchor.absolute(80))),
                BiomeFilter.biome())));
    }

    /** In which biomes. */
    private static void biomeModifiers(BootstapContext<BiomeModifier> context) {
        // The tin is added to every biome of the overworld, at the step of the underground ores like copper and iron.
        HolderGetter<Biome> biomes = context.lookup(Registries.BIOME);
        HolderGetter<PlacedFeature> placed = context.lookup(Registries.PLACED_FEATURE);
        context.register(ADD_TIN_ORE, new ForgeBiomeModifiers.AddFeaturesBiomeModifier(
                biomes.getOrThrow(BiomeTags.IS_OVERWORLD),
                HolderSet.direct(placed.getOrThrow(TIN_PLACED)),
                GenerationStep.Decoration.UNDERGROUND_ORES));
    }

    private ModWorldGen() {
    }
}
