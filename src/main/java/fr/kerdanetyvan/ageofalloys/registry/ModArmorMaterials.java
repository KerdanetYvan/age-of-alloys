package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.AlloyArmorMaterial;
import fr.kerdanetyvan.ageofalloys.content.ArmorStats;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/** Armor materials of the mod. Forge-specific by its place next to the registries, so it lives in the registry package. */
public final class ModArmorMaterials {
    /** The bronze: its numbers, the bronze ingot to repair it, the sound of iron, and the name of its worn layers. */
    public static final ArmorMaterial BRONZE = new AlloyArmorMaterial(ArmorStats.BRONZE,
            AgeOfAlloys.MOD_ID + ":bronze", SoundEvents.ARMOR_EQUIP_IRON,
            () -> Ingredient.of(ModItems.BRONZE_INGOT.get()));

    private ModArmorMaterials() {
    }
}
