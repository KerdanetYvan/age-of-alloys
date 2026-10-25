package fr.kerdanetyvan.ageofalloys.recipe;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.util.ArrayList;
import java.util.List;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeSerializer;
import net.minecraft.world.item.crafting.ShapedRecipe;

/** Reads an alloying recipe from its JSON file, and sends it to the clients over the network. */
public class AlloyingRecipeSerializer implements RecipeSerializer<AlloyingRecipe> {
    @Override
    public AlloyingRecipe fromJson(ResourceLocation id, JsonObject json) {
        List<AlloyIngredient> ingredients = new ArrayList<>();
        JsonArray array = GsonHelper.getAsJsonArray(json, "ingredients");
        if (array.isEmpty() || array.size() > AlloyingRecipe.INPUT_SLOTS) {
            throw new JsonParseException(
                    "An alloying recipe takes 1 to " + AlloyingRecipe.INPUT_SLOTS + " ingredients: " + id);
        }
        for (int i = 0; i < array.size(); i++) {
            ingredients.add(AlloyIngredient.fromJson(GsonHelper.convertToJsonObject(array.get(i), "ingredient")));
        }
        ItemStack result = ShapedRecipe.itemStackFromJson(GsonHelper.getAsJsonObject(json, "result"));
        int heat = GsonHelper.getAsInt(json, "heat");
        int time = GsonHelper.getAsInt(json, "time");
        return new AlloyingRecipe(id, ingredients, result, heat, time);
    }

    // The network has no field names: fromNetwork must read in the exact order toNetwork writes.

    @Override
    public AlloyingRecipe fromNetwork(ResourceLocation id, FriendlyByteBuf buf) {
        int count = buf.readVarInt();
        List<AlloyIngredient> ingredients = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            ingredients.add(AlloyIngredient.fromNetwork(buf));
        }
        ItemStack result = buf.readItem();
        int heat = buf.readVarInt();
        int time = buf.readVarInt();
        return new AlloyingRecipe(id, ingredients, result, heat, time);
    }

    @Override
    public void toNetwork(FriendlyByteBuf buf, AlloyingRecipe recipe) {
        buf.writeVarInt(recipe.alloyIngredients().size());
        for (AlloyIngredient ingredient : recipe.alloyIngredients()) {
            ingredient.toNetwork(buf);
        }
        buf.writeItem(recipe.result());
        buf.writeVarInt(recipe.heat());
        buf.writeVarInt(recipe.time());
    }
}
