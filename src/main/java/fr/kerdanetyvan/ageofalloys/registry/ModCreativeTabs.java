package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

/** Registration of the mod's creative tab. */
public final class ModCreativeTabs {
    private static final DeferredRegister<CreativeModeTab> TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, AgeOfAlloys.MOD_ID);

    public static final RegistryObject<CreativeModeTab> MAIN = TABS.register("main", () -> CreativeModeTab.builder()
            .title(Component.translatable("itemGroup." + AgeOfAlloys.MOD_ID))
            .icon(() -> ModItems.STEEL_INGOT.get().getDefaultInstance())
            .displayItems((parameters, output) -> {
                output.accept(ModItems.BRONZE_INGOT.get());
                output.accept(ModItems.DURALUMIN_INGOT.get());
                output.accept(ModItems.STEEL_INGOT.get());
                output.accept(ModItems.TIN_INGOT.get());
                output.accept(ModItems.ALUMINUM_INGOT.get());
                output.accept(ModItems.MAGNESIUM_INGOT.get());
                output.accept(ModItems.MANGANESE_INGOT.get());
                output.accept(ModItems.BRONZE_PICKAXE.get());
                output.accept(ModItems.BRONZE_AXE.get());
                output.accept(ModItems.BRONZE_SHOVEL.get());
                output.accept(ModItems.BRONZE_HOE.get());
                output.accept(ModItems.BRONZE_SWORD.get());
                output.accept(ModItems.DURALUMIN_PICKAXE.get());
                output.accept(ModItems.DURALUMIN_AXE.get());
                output.accept(ModItems.DURALUMIN_SHOVEL.get());
                output.accept(ModItems.DURALUMIN_HOE.get());
                output.accept(ModItems.DURALUMIN_SWORD.get());
                output.accept(ModItems.STEEL_PICKAXE.get());
                output.accept(ModItems.STEEL_AXE.get());
                output.accept(ModItems.STEEL_SHOVEL.get());
                output.accept(ModItems.STEEL_HOE.get());
                output.accept(ModItems.STEEL_SWORD.get());
                output.accept(ModItems.ALLOY_FOUNDRY.get());
            })
            .build());

    public static void register(IEventBus modEventBus) {
        TABS.register(modEventBus);
    }

    private ModCreativeTabs() {
    }
}
