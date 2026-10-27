package fr.kerdanetyvan.ageofalloys.datagen;

import java.util.concurrent.CompletableFuture;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
import fr.kerdanetyvan.ageofalloys.registry.ModTags;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.tags.ItemTagsProvider;
import net.minecraft.data.tags.TagsProvider;
import net.minecraft.tags.ItemTags;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.data.ExistingFileHelper;

/** Generates the item tags of the mod. Forge-specific, so it lives in the datagen package. */
public class ModItemTagsProvider extends ItemTagsProvider {
    public ModItemTagsProvider(PackOutput output, CompletableFuture<HolderLookup.Provider> lookupProvider,
            CompletableFuture<TagsProvider.TagLookup<Block>> blockTags, ExistingFileHelper existingFileHelper) {
        super(output, lookupProvider, blockTags, AgeOfAlloys.MOD_ID, existingFileHelper);
    }

    @Override
    protected void addTags(HolderLookup.Provider provider) {
        tag(ModTags.ingot(Metal.TIN)).add(ModItems.TIN_INGOT.get());
        tag(ModTags.ingot(Metal.BRONZE)).add(ModItems.BRONZE_INGOT.get());
        tag(ModTags.ingot(Metal.DURALUMIN)).add(ModItems.DURALUMIN_INGOT.get());
        tag(ModTags.ingot(Metal.STEEL)).add(ModItems.STEEL_INGOT.get());
        tag(ModTags.ingot(Metal.ALUMINUM)).add(ModItems.ALUMINUM_INGOT.get());
        tag(ModTags.ingot(Metal.MAGNESIUM)).add(ModItems.MAGNESIUM_INGOT.get());
        tag(ModTags.ingot(Metal.MANGANESE)).add(ModItems.MANGANESE_INGOT.get());

        tag(ModTags.COAL).add(Items.COAL);

        // Forge's own forge:ingots tag holds one sub-tag per metal; ours join them.
        for (Metal metal : Metal.values()) {
            tag(Tags.Items.INGOTS).addTags(ModTags.ingot(metal));
        }

        // The ore items and the raw items, for the same shared tags as the blocks.
        copy(Tags.Blocks.ORES, Tags.Items.ORES);
        copy(Tags.Blocks.ORES_IN_GROUND_STONE, Tags.Items.ORES_IN_GROUND_STONE);
        copy(Tags.Blocks.ORES_IN_GROUND_DEEPSLATE, Tags.Items.ORES_IN_GROUND_DEEPSLATE);
        for (Metal metal : Metal.values()) {
            if (metal.kind() == Metal.Kind.RAW) {
                copy(ModTags.oreBlock(metal), ModTags.oreItem(metal));
                tag(ModTags.rawMaterial(metal)).add(ModItems.rawOf(metal).get());
                tag(Tags.Items.RAW_MATERIALS).addTag(ModTags.rawMaterial(metal));
            }
        }

        tag(ItemTags.PICKAXES).add(ModItems.BRONZE_PICKAXE.get(), ModItems.DURALUMIN_PICKAXE.get(),
                ModItems.STEEL_PICKAXE.get());
        tag(ItemTags.AXES).add(ModItems.BRONZE_AXE.get(), ModItems.DURALUMIN_AXE.get(),
                ModItems.STEEL_AXE.get());
        tag(ItemTags.SHOVELS).add(ModItems.BRONZE_SHOVEL.get(), ModItems.DURALUMIN_SHOVEL.get(),
                ModItems.STEEL_SHOVEL.get());
        tag(ItemTags.HOES).add(ModItems.BRONZE_HOE.get(), ModItems.DURALUMIN_HOE.get(),
                ModItems.STEEL_HOE.get());
        tag(ItemTags.SWORDS).add(ModItems.BRONZE_SWORD.get(), ModItems.DURALUMIN_SWORD.get(),
                ModItems.STEEL_SWORD.get());
    }
}
