package fr.kerdanetyvan.ageofalloys.recipe;

import com.google.gson.JsonObject;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.FriendlyByteBuf;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.util.GsonHelper;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;

/** An ingredient of an alloying recipe: a tag and a quantity, for example three copper ingots. */
public record AlloyIngredient(TagKey<Item> tag, int count) {
    /** Whether a stack belongs to the tag of this ingredient. */
    public boolean accepts(ItemStack stack) {
        return stack.is(tag);
    }

    public static AlloyIngredient fromJson(JsonObject json) {
        ResourceLocation tagId = ResourceLocation.parse(GsonHelper.getAsString(json, "tag"));
        return new AlloyIngredient(TagKey.create(Registries.ITEM, tagId), GsonHelper.getAsInt(json, "count"));
    }

    public JsonObject toJson() {
        JsonObject json = new JsonObject();
        json.addProperty("tag", tag.location().toString());
        json.addProperty("count", count);
        return json;
    }

    public static AlloyIngredient fromNetwork(FriendlyByteBuf buf) {
        ResourceLocation tagId = buf.readResourceLocation();
        return new AlloyIngredient(TagKey.create(Registries.ITEM, tagId), buf.readVarInt());
    }

    public void toNetwork(FriendlyByteBuf buf) {
        buf.writeResourceLocation(tag.location());
        buf.writeVarInt(count);
    }
}
