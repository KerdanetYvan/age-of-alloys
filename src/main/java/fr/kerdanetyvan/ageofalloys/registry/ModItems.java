package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import fr.kerdanetyvan.ageofalloys.content.ToolType;
import java.util.function.Supplier;
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

    public static final RegistryObject<Item> TIN_INGOT = ingot(Metal.TIN);
    public static final RegistryObject<Item> BRONZE_INGOT = ingot(Metal.BRONZE);
    public static final RegistryObject<Item> DURALUMIN_INGOT = ingot(Metal.DURALUMIN);
    public static final RegistryObject<Item> STEEL_INGOT = ingot(Metal.STEEL);
    public static final RegistryObject<Item> ALUMINUM_INGOT = ingot(Metal.ALUMINUM);
    public static final RegistryObject<Item> MAGNESIUM_INGOT = ingot(Metal.MAGNESIUM);
    public static final RegistryObject<Item> MANGANESE_INGOT = ingot(Metal.MANGANESE);

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

    /** The item of the alloy foundry: the thing in the inventory that places the block. */
    public static final RegistryObject<Item> ALLOY_FOUNDRY = ITEMS.register("alloy_foundry",
            () -> new BlockItem(ModBlocks.ALLOY_FOUNDRY.get(), new Item.Properties()));

    /** The raw tin an ore drops: it is melted into a tin ingot in a furnace. */
    public static final RegistryObject<Item> RAW_TIN = raw(Metal.TIN);

    /** The items of the tin ores, the things in the inventory that place the blocks. */
    public static final RegistryObject<Item> TIN_ORE = blockItem(Metal.TIN.oreName(), ModBlocks.TIN_ORE);
    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE =
            blockItem(Metal.TIN.deepslateOreName(), ModBlocks.DEEPSLATE_TIN_ORE);

    private static RegistryObject<Item> raw(Metal metal) {
        return ITEMS.register(metal.rawName(), () -> new Item(new Item.Properties()));
    }

    private static RegistryObject<Item> blockItem(String name, RegistryObject<Block> block) {
        return ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    private static RegistryObject<Item> ingot(Metal metal) {
        return ITEMS.register(metal.ingotName(), () -> new Item(new Item.Properties()));
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
