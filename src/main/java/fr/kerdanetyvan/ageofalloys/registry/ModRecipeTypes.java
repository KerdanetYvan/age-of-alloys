package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.recipe.AlloyingRecipe;
import fr.kerdanetyvan.ageofalloys.recipe.AlloyingRecipeSerializer;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

/** Registration of the recipe types of the mod. Forge-specific, so it lives in the registry package. */
public final class ModRecipeTypes {
    private static final DeferredRegister<RecipeType<?>> TYPES =
            DeferredRegister.create(ForgeRegistries.RECIPE_TYPES, AgeOfAlloys.MOD_ID);
    private static final DeferredRegister<RecipeSerializer<?>> SERIALIZERS =
            DeferredRegister.create(ForgeRegistries.RECIPE_SERIALIZERS, AgeOfAlloys.MOD_ID);

    public static final RegistryObject<RecipeType<AlloyingRecipe>> ALLOYING = TYPES.register("alloying",
            () -> RecipeType.simple(ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "alloying")));
    public static final RegistryObject<RecipeSerializer<AlloyingRecipe>> ALLOYING_SERIALIZER =
            SERIALIZERS.register("alloying", AlloyingRecipeSerializer::new);

    public static void register(IEventBus modEventBus) {
        TYPES.register(modEventBus);
        SERIALIZERS.register(modEventBus);
    }

    private ModRecipeTypes() {
    }
}
