package fr.kerdanetyvan.ageofalloys.datagen;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.content.ToolType;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import net.minecraft.advancements.Advancement;
import net.minecraft.advancements.FrameType;
import net.minecraft.advancements.RequirementsStrategy;
import net.minecraft.advancements.critereon.InventoryChangeTrigger;
import net.minecraft.core.HolderLookup;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.data.ExistingFileHelper;
import net.minecraftforge.common.data.ForgeAdvancementProvider;
import net.minecraftforge.registries.ForgeRegistries;
import org.jetbrains.annotations.Nullable;

/**
 * The advancements of the mod: one tab, "Age of Alloys", that guides the player from the first raw metal to
 * the three alloys, the tools and the armor. The texts are in the language files, under
 * {@code advancements.ageofalloys.<id>.title} and {@code .description}. Forge-specific, so it lives in datagen.
 */
public class ModAdvancementProvider implements ForgeAdvancementProvider.AdvancementGenerator {
    /** The background of the tab, from Minecraft's own textures. */
    private static final ResourceLocation BACKGROUND =
            new ResourceLocation("textures/gui/advancements/backgrounds/stone.png");

    @Override
    public void generate(HolderLookup.Provider registries, Consumer<Advancement> saver, ExistingFileHelper helper) {
        List<Item> rawMetals = new ArrayList<>();
        for (Metal metal : Metal.values()) {
            if (metal.kind() == Metal.Kind.RAW) {
                rawMetals.add(ModItems.rawOf(metal).get());
            }
        }

        // The root: the tab only appears when the player holds a first raw metal, no free advancement.
        Advancement.Builder prospector = builder("prospector", null, ModItems.rawOf(Metal.TIN).get(), FrameType.TASK)
                .requirements(RequirementsStrategy.OR);
        for (Item raw : rawMetals) {
            prospector.addCriterion(nameOf(raw), has(raw));
        }
        Advancement root = save(prospector, "prospector", saver, helper);

        Advancement.Builder metalTour = builder("metal_tour", root, ModItems.rawOf(Metal.MANGANESE).get(),
                FrameType.GOAL);
        for (Item raw : rawMetals) {
            metalTour.addCriterion(nameOf(raw), has(raw));
        }
        save(metalTour, "metal_tour", saver, helper);

        Advancement crucible = save(
                builder("crucible", root, ModItems.ALLOY_FOUNDRY.get(), FrameType.TASK)
                        .addCriterion("has_alloy_foundry", has(ModItems.ALLOY_FOUNDRY.get())),
                "crucible", saver, helper);

        Advancement firstAlloy = save(
                builder("first_alloy", crucible, ModItems.BRONZE_INGOT.get(), FrameType.TASK)
                        .addCriterion("has_bronze_ingot", has(ModItems.BRONZE_INGOT.get())),
                "first_alloy", saver, helper);

        save(builder("hotter", firstAlloy, ModItems.DURALUMIN_INGOT.get(), FrameType.TASK)
                        .addCriterion("has_duralumin_ingot", has(ModItems.DURALUMIN_INGOT.get())),
                "hotter", saver, helper);

        Advancement almostDiamond = save(
                builder("almost_diamond", firstAlloy, ModItems.STEEL_INGOT.get(), FrameType.GOAL)
                        .addCriterion("has_steel_ingot", has(ModItems.STEEL_INGOT.get())),
                "almost_diamond", saver, helper);

        // Any tool of any alloy.
        Advancement.Builder tools = builder("alloy_tools", firstAlloy, ModItems.BRONZE_PICKAXE.get(), FrameType.TASK)
                .requirements(RequirementsStrategy.OR);
        for (Metal metal : alloys()) {
            for (ToolType type : ToolType.values()) {
                Item tool = itemNamed(metal.toolName(type));
                tools.addCriterion(nameOf(tool), has(tool));
            }
        }
        Advancement alloyTools = save(tools, "alloy_tools", saver, helper);

        // The four pieces of the same alloy: one criterion per alloy, any of them is enough.
        Advancement.Builder armor = builder("under_armor", alloyTools, ModItems.STEEL_CHESTPLATE.get(), FrameType.GOAL)
                .requirements(RequirementsStrategy.OR);
        for (Metal metal : alloys()) {
            Item[] pieces = new Item[ArmorItem.Type.values().length];
            int i = 0;
            for (ArmorItem.Type type : ArmorItem.Type.values()) {
                pieces[i++] = itemNamed(metal.armorName(type));
            }
            armor.addCriterion("has_" + metal.id() + "_armor", InventoryChangeTrigger.TriggerInstance.hasItems(pieces));
        }
        save(armor, "under_armor", saver, helper);

        Advancement.Builder best = builder("best_of_every_metal", almostDiamond, ModItems.BRONZE_INGOT.get(),
                FrameType.CHALLENGE);
        for (Metal metal : alloys()) {
            best.addCriterion("has_" + metal.ingotName(), has(ModItems.ingotOf(metal).get()));
        }
        save(best, "best_of_every_metal", saver, helper);
    }

    /**
     * Starts an advancement: its icon, its frame and its texts (by language key). Only the root has the
     * background (it names the tab). The tasks stay quiet, the goals and the challenge are announced in the chat.
     */
    private static Advancement.Builder builder(String id, @Nullable Advancement parent, ItemLike icon, FrameType frame) {
        Advancement.Builder builder = Advancement.Builder.advancement().display(icon,
                Component.translatable("advancements." + AgeOfAlloys.MOD_ID + "." + id + ".title"),
                Component.translatable("advancements." + AgeOfAlloys.MOD_ID + "." + id + ".description"),
                parent == null ? BACKGROUND : null, frame, true, frame != FrameType.TASK, false);
        if (parent != null) {
            builder.parent(parent);
        }
        return builder;
    }

    private static Advancement save(Advancement.Builder builder, String id, Consumer<Advancement> saver,
            ExistingFileHelper helper) {
        return builder.save(saver, ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, id), helper);
    }

    private static InventoryChangeTrigger.TriggerInstance has(ItemLike item) {
        return InventoryChangeTrigger.TriggerInstance.hasItems(item);
    }

    private static List<Metal> alloys() {
        List<Metal> alloys = new ArrayList<>();
        for (Metal metal : Metal.values()) {
            if (metal.kind() == Metal.Kind.ALLOY) {
                alloys.add(metal);
            }
        }
        return alloys;
    }

    /** The name of a criterion for an item, for example {@code has_raw_tin}. */
    private static String nameOf(Item item) {
        return "has_" + ForgeRegistries.ITEMS.getKey(item).getPath();
    }

    /** Finds an item of the mod by its registered name, and fails loudly if it does not exist. */
    private static Item itemNamed(String name) {
        ResourceLocation id = ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, name);
        Item item = ForgeRegistries.ITEMS.getValue(id);
        if (item == null || item == Items.AIR) {
            throw new IllegalStateException("No item is registered as " + id);
        }
        return item;
    }
}
