package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import java.util.List;
import java.util.Set;
import java.util.concurrent.CompletableFuture;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.DataGenerator;
import net.minecraft.data.PackOutput;
import net.minecraftforge.common.data.DatapackBuiltinEntriesProvider;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.data.event.GatherDataEvent;

/** Entry point of the data generation, started by the {@code runData} Gradle task. */
public final class DataGenerators {
    public static void gatherData(GatherDataEvent event) {
        DataGenerator generator = event.getGenerator();
        PackOutput output = generator.getPackOutput();
        CompletableFuture<HolderLookup.Provider> lookupProvider = event.getLookupProvider();
        ExistingFileHelper existingFileHelper = event.getExistingFileHelper();

        ModBlockTagsProvider blockTags = new ModBlockTagsProvider(output, lookupProvider, existingFileHelper);
        generator.addProvider(event.includeServer(), blockTags);
        generator.addProvider(event.includeServer(),
                new ModItemTagsProvider(output, lookupProvider, blockTags.contentsGetter(), existingFileHelper));
        generator.addProvider(event.includeServer(), new ModRecipeProvider(output));
        generator.addProvider(event.includeServer(), ModLootTableProvider.create(output));
        generator.addProvider(event.includeServer(), new ForgeAdvancementProvider(output, lookupProvider,
                existingFileHelper, List.of(new ModAdvancementProvider())));
        // The generation of the ores: configured features, placed features and biome modifiers.
        generator.addProvider(event.includeServer(),
                new DatapackBuiltinEntriesProvider(output, lookupProvider, ModWorldGen.BUILDER, Set.of(AgeOfAlloys.MOD_ID)));
    }

    private DataGenerators() {
    }
}
