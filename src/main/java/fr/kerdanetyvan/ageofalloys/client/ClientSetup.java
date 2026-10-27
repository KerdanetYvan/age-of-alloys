package fr.kerdanetyvan.ageofalloys.client;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.registry.ModItems;
import fr.kerdanetyvan.ageofalloys.registry.ModMenuTypes;
import fr.kerdanetyvan.ageofalloys.registry.ModRecipeBookTypes;
import fr.kerdanetyvan.ageofalloys.registry.ModRecipeTypes;
import java.util.List;
import net.minecraft.client.RecipeBookCategories;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterRecipeBookCategoriesEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

/**
 * Client-only wiring. The {@code value = Dist.CLIENT} makes Forge skip this class on a dedicated
 * server, which would otherwise crash when it meets the screen classes.
 */
@Mod.EventBusSubscriber(modid = AgeOfAlloys.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public final class ClientSetup {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        event.enqueueWork(() -> MenuScreens.register(ModMenuTypes.ALLOY_FOUNDRY.get(), AlloyFoundryScreen::new));
    }

    /**
     * The tabs of the recipe book of the foundry, and which recipes go where. The categories are created
     * here (Forge extends the vanilla enum), not earlier: their icons need the registered items.
     */
    @SubscribeEvent
    public static void onRegisterRecipeBookCategories(RegisterRecipeBookCategoriesEvent event) {
        RecipeBookCategories search = RecipeBookCategories.create("AGEOFALLOYS_ALLOY_FOUNDRY_SEARCH",
                new ItemStack(Items.COMPASS));
        RecipeBookCategories alloys = RecipeBookCategories.create("AGEOFALLOYS_ALLOY_FOUNDRY_ALLOYS",
                new ItemStack(ModItems.BRONZE_INGOT.get()));

        event.registerBookCategories(ModRecipeBookTypes.ALLOY_FOUNDRY, List.of(search, alloys));
        // The search tab shows what all the other tabs show.
        event.registerAggregateCategory(search, List.of(alloys));
        event.registerRecipeCategoryFinder(ModRecipeTypes.ALLOYING.get(), recipe -> alloys);
    }

    private ClientSetup() {
    }
}
