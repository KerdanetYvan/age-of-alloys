package fr.kerdanetyvan.ageofalloys.content;

import java.util.function.Supplier;
import net.minecraft.sounds.SoundEvent;
import net.minecraft.world.item.ArmorItem;
import net.minecraft.world.item.ArmorMaterial;
import net.minecraft.world.item.crafting.Ingredient;

/**
 * The material of a family of armor pieces: it reads its numbers from {@link ArmorStats}.
 * Neutral code: only Minecraft types, no Forge import (see docs/architecture.md).
 * The repair ingredient is a {@link Supplier} given by the registry package, so this class
 * does not depend on the mod's items.
 */
public final class AlloyArmorMaterial implements ArmorMaterial {
    private final ArmorStats stats;
    private final String name;
    private final SoundEvent equipSound;
    private final Supplier<Ingredient> repairIngredient;

    /**
     * @param name the name with its namespace, for example {@code ageofalloys:bronze}: Minecraft looks for the
     *             worn layers at {@code textures/models/armor/<name>_layer_1.png} and {@code _layer_2.png}
     */
    public AlloyArmorMaterial(ArmorStats stats, String name, SoundEvent equipSound,
            Supplier<Ingredient> repairIngredient) {
        this.stats = stats;
        this.name = name;
        this.equipSound = equipSound;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getDurabilityForType(ArmorItem.Type type) {
        return stats.durability(type);
    }

    @Override
    public int getDefenseForType(ArmorItem.Type type) {
        return stats.protection(type);
    }

    @Override
    public int getEnchantmentValue() {
        return stats.enchantmentValue();
    }

    @Override
    public SoundEvent getEquipSound() {
        return equipSound;
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public float getToughness() {
        return stats.toughness();
    }

    @Override
    public float getKnockbackResistance() {
        return stats.knockbackResistance();
    }

    /** The name of the alloy without its namespace, for example {@code duralumin}: it names its texts. */
    public String alloyId() {
        return name.substring(name.indexOf(':') + 1);
    }

    /** The change of speed that each piece gives to its wearer, as a share of the base speed. */
    public double movementSpeedBonus() {
        return stats.movementSpeedBonus();
    }
}
