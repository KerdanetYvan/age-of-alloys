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

    /** The duralumin: light and fragile, with the chain sound and the duralumin ingot to repair it. */
    public static final ArmorMaterial DURALUMIN = new AlloyArmorMaterial(ArmorStats.DURALUMIN,
            AgeOfAlloys.MOD_ID + ":duralumin", SoundEvents.ARMOR_EQUIP_CHAIN,
            () -> Ingredient.of(ModItems.DURALUMIN_INGOT.get()));

    /** The steel: close to diamond, with the diamond sound and the steel ingot to repair it. */
    public static final ArmorMaterial STEEL = new AlloyArmorMaterial(ArmorStats.STEEL,
            AgeOfAlloys.MOD_ID + ":steel", SoundEvents.ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.of(ModItems.STEEL_INGOT.get()));

    private ModArmorMaterials() {
    }
}
