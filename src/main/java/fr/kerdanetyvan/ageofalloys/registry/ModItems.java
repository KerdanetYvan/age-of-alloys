package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import net.minecraft.world.item.Item;
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

    private static RegistryObject<Item> ingot(Metal metal) {
        return ITEMS.register(metal.ingotName(), () -> new Item(new Item.Properties()));
    }

    public static void register(IEventBus modEventBus) {
        ITEMS.register(modEventBus);
    }

    private ModItems() {
    }
}
