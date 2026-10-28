package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.content.ToolType;
import java.util.EnumMap;
import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.HoeItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.ShovelItem;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Registration of the mod's items. Forge-specific, so it lives in the registry package. */
public final class ModItems {
    private static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, AgeOfAlloys.MOD_ID);

    // The raw items, the ore items and the ingots by metal, for the code that treats every raw metal the same way.
    // The tables come first: the constants below fill them.
    private static final Map<Metal, RegistryObject<Item>> RAW = new EnumMap<>(Metal.class);
    private static final Map<Metal, RegistryObject<Item>> ORE_ITEMS = new EnumMap<>(Metal.class);
    private static final Map<Metal, RegistryObject<Item>> DEEPSLATE_ORE_ITEMS = new EnumMap<>(Metal.class);
    private static final Map<Metal, RegistryObject<Item>> INGOTS = new EnumMap<>(Metal.class);

    public static final RegistryObject<Item> TIN_INGOT = ingot(Metal.TIN);
    public static final RegistryObject<Item> BRONZE_INGOT = ingot(Metal.BRONZE);
    public static final RegistryObject<Item> DURALUMIN_INGOT = ingot(Metal.DURALUMIN);
    public static final RegistryObject<Item> STEEL_INGOT = ingot(Metal.STEEL);
    public static final RegistryObject<Item> ALUMINUM_INGOT = ingot(Metal.ALUMINUM);
    public static final RegistryObject<Item> MAGNESIUM_INGOT = ingot(Metal.MAGNESIUM);
    public static final RegistryObject<Item> MANGANESE_INGOT = ingot(Metal.MANGANESE);

    /** Coal cooked in a furnace: the carbon of steel. It is not a fuel. */
    public static final RegistryObject<Item> PURIFIED_COAL =
            ITEMS.register("purified_coal", () -> new Item(new Item.Properties()));

    // Attack damage and attack speed are set per tool, not per material: the values below are the
    // vanilla iron ones (read from net.minecraft.world.item.Items in 1.20.1).
    public static final RegistryObject<Item> BRONZE_PICKAXE = tool(Metal.BRONZE, ToolType.PICKAXE,
            () -> new PickaxeItem(ModTiers.BRONZE, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_AXE = tool(Metal.BRONZE, ToolType.AXE,
            () -> new AxeItem(ModTiers.BRONZE, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SHOVEL = tool(Metal.BRONZE, ToolType.SHOVEL,
            () -> new ShovelItem(ModTiers.BRONZE, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_HOE = tool(Metal.BRONZE, ToolType.HOE,
            () -> new HoeItem(ModTiers.BRONZE, -2, -1.0F, new Item.Properties()));
    public static final RegistryObject<Item> BRONZE_SWORD = tool(Metal.BRONZE, ToolType.SWORD,
            () -> new SwordItem(ModTiers.BRONZE, 3, -2.4F, new Item.Properties()));

    public static final RegistryObject<Item> DURALUMIN_PICKAXE = tool(Metal.DURALUMIN, ToolType.PICKAXE,
            () -> new PickaxeItem(ModTiers.DURALUMIN, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> DURALUMIN_AXE = tool(Metal.DURALUMIN, ToolType.AXE,
            () -> new AxeItem(ModTiers.DURALUMIN, 6.0F, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> DURALUMIN_SHOVEL = tool(Metal.DURALUMIN, ToolType.SHOVEL,
            () -> new ShovelItem(ModTiers.DURALUMIN, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> DURALUMIN_HOE = tool(Metal.DURALUMIN, ToolType.HOE,
            () -> new HoeItem(ModTiers.DURALUMIN, -2, -1.0F, new Item.Properties()));
    public static final RegistryObject<Item> DURALUMIN_SWORD = tool(Metal.DURALUMIN, ToolType.SWORD,
            () -> new SwordItem(ModTiers.DURALUMIN, 3, -2.0F, new Item.Properties()));

    public static final RegistryObject<Item> STEEL_PICKAXE = tool(Metal.STEEL, ToolType.PICKAXE,
            () -> new PickaxeItem(ModTiers.STEEL, 1, -2.8F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_AXE = tool(Metal.STEEL, ToolType.AXE,
            () -> new AxeItem(ModTiers.STEEL, 6.0F, -3.1F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SHOVEL = tool(Metal.STEEL, ToolType.SHOVEL,
            () -> new ShovelItem(ModTiers.STEEL, 1.5F, -3.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_HOE = tool(Metal.STEEL, ToolType.HOE,
            () -> new HoeItem(ModTiers.STEEL, -2, -1.0F, new Item.Properties()));
    public static final RegistryObject<Item> STEEL_SWORD = tool(Metal.STEEL, ToolType.SWORD,
            () -> new SwordItem(ModTiers.STEEL, 3, -2.4F, new Item.Properties()));

    // The bronze armor: four pieces made from the same material.
    public static final RegistryObject<Item> BRONZE_HELMET =
            armor(Metal.BRONZE, ModArmorMaterials.BRONZE, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> BRONZE_CHESTPLATE =
            armor(Metal.BRONZE, ModArmorMaterials.BRONZE, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> BRONZE_LEGGINGS =
            armor(Metal.BRONZE, ModArmorMaterials.BRONZE, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> BRONZE_BOOTS =
            armor(Metal.BRONZE, ModArmorMaterials.BRONZE, ArmorItem.Type.BOOTS);

    // The duralumin and steel armor.
    public static final RegistryObject<Item> DURALUMIN_HELMET =
            armor(Metal.DURALUMIN, ModArmorMaterials.DURALUMIN, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> DURALUMIN_CHESTPLATE =
            armor(Metal.DURALUMIN, ModArmorMaterials.DURALUMIN, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> DURALUMIN_LEGGINGS =
            armor(Metal.DURALUMIN, ModArmorMaterials.DURALUMIN, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> DURALUMIN_BOOTS =
            armor(Metal.DURALUMIN, ModArmorMaterials.DURALUMIN, ArmorItem.Type.BOOTS);
    public static final RegistryObject<Item> STEEL_HELMET =
            armor(Metal.STEEL, ModArmorMaterials.STEEL, ArmorItem.Type.HELMET);
    public static final RegistryObject<Item> STEEL_CHESTPLATE =
            armor(Metal.STEEL, ModArmorMaterials.STEEL, ArmorItem.Type.CHESTPLATE);
    public static final RegistryObject<Item> STEEL_LEGGINGS =
            armor(Metal.STEEL, ModArmorMaterials.STEEL, ArmorItem.Type.LEGGINGS);
    public static final RegistryObject<Item> STEEL_BOOTS =
            armor(Metal.STEEL, ModArmorMaterials.STEEL, ArmorItem.Type.BOOTS);

    /** The item of the alloy foundry: the thing in the inventory that places the block. */
    public static final RegistryObject<Item> ALLOY_FOUNDRY = ITEMS.register("alloy_foundry",
            () -> new BlockItem(ModBlocks.ALLOY_FOUNDRY.get(), new Item.Properties()));

    /** The raw items an ore drops: they are melted into an ingot in a furnace. */
    public static final RegistryObject<Item> RAW_TIN = raw(Metal.TIN);
    public static final RegistryObject<Item> RAW_ALUMINUM = raw(Metal.ALUMINUM);
    public static final RegistryObject<Item> RAW_MAGNESIUM = raw(Metal.MAGNESIUM);
    public static final RegistryObject<Item> RAW_MANGANESE = raw(Metal.MANGANESE);

    /** The items of the ores, the things in the inventory that place the blocks. */
    public static final RegistryObject<Item> TIN_ORE = oreItem(Metal.TIN, false);
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE = oreItem(Metal.TIN, true);
    public static final RegistryObject<Item> ALUMINUM_ORE = oreItem(Metal.ALUMINUM, false);
    public static final RegistryObject<Item> DEEPSLATE_ALUMINUM_ORE = oreItem(Metal.ALUMINUM, true);
    public static final RegistryObject<Item> MAGNESIUM_ORE = oreItem(Metal.MAGNESIUM, false);
    public static final RegistryObject<Item> DEEPSLATE_MAGNESIUM_ORE = oreItem(Metal.MAGNESIUM, true);
    public static final RegistryObject<Item> MANGANESE_ORE = oreItem(Metal.MANGANESE, false);
    public static final RegistryObject<Item> DEEPSLATE_MANGANESE_ORE = oreItem(Metal.MANGANESE, true);

    private static RegistryObject<Item> raw(Metal metal) {
        RegistryObject<Item> item = ITEMS.register(metal.rawName(), () -> new Item(new Item.Properties()));
        RAW.put(metal, item);
        return item;
    }

    private static RegistryObject<Item> oreItem(Metal metal, boolean deepslate) {
        RegistryObject<Block> block = deepslate ? ModBlocks.deepslateOreOf(metal) : ModBlocks.oreOf(metal);
        String name = deepslate ? metal.deepslateOreName() : metal.oreName();
        RegistryObject<Item> item = ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
        (deepslate ? DEEPSLATE_ORE_ITEMS : ORE_ITEMS).put(metal, item);
        return item;
    }

    private static RegistryObject<Item> ingot(Metal metal) {
        RegistryObject<Item> item = ITEMS.register(metal.ingotName(), () -> new Item(new Item.Properties()));
        INGOTS.put(metal, item);
        return item;
    }

    /** The raw item of a raw metal, for example the raw tin. */
    public static RegistryObject<Item> rawOf(Metal metal) {
        return RAW.get(metal);
    }

    /** The item of the ore of a raw metal, in stone. */
    public static RegistryObject<Item> oreItemOf(Metal metal) {
        return ORE_ITEMS.get(metal);
    }

    /** The item of the ore of a raw metal, in deepslate. */
    public static RegistryObject<Item> deepslateOreItemOf(Metal metal) {
        return DEEPSLATE_ORE_ITEMS.get(metal);
    }

    /** The ingot of a metal. */
    public static RegistryObject<Item> ingotOf(Metal metal) {
        return INGOTS.get(metal);
    }

    private static RegistryObject<Item> armor(Metal metal, ArmorMaterial material, ArmorItem.Type type) {
        return ITEMS.register(metal.armorName(type), () -> new ArmorItem(material, type, new Item.Properties()));
    }

    private static RegistryObject<Item> tool(Metal metal, ToolType type, Supplier<Item> factory) {
        return ITEMS.register(metal.toolName(type), factory);
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private ModItems() {
    }
}
