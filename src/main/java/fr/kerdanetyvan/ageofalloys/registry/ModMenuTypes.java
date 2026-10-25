package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.menu.AlloyFoundryMenu;
import net.minecraft.world.inventory.MenuType;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Registration of the menu types of the mod. Forge-specific, so it lives in the registry package. */
public final class ModMenuTypes {
    private static final DeferredRegister<MenuType<?>> MENUS =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, AgeOfAlloys.MOD_ID);

    public static final RegistryObject<MenuType<AlloyFoundryMenu>> ALLOY_FOUNDRY =
            MENUS.register("alloy_foundry", () -> IForgeMenuType.create(AlloyFoundryMenu::new));

    public static void register(IEventBus modEventBus) {
        MENUS.register(modEventBus);
    }

    private ModMenuTypes() {
    }
}
