package fr.kerdanetyvan.ageofalloys.content;

import net.minecraft.world.item.ArmorItem;

/**
 * The numbers that describe an armor material (see docs/design/armor.md, section 3).
 * Neutral code: it must not import anything from Forge (see docs/architecture.md).
 *
 * @param boots               protection of the boots
 * @param leggings            protection of the leggings
 * @param chestplate          protection of the chestplate
 * @param helmet              protection of the helmet
 * @param durabilityMultiplier the durability of a piece is this multiplier times a number of its own
 * @param toughness           reduces the big hits taken at once
 * @param knockbackResistance resistance to being pushed back by a hit
 * @param enchantmentValue    enchantability: the higher, the better the enchantments offered
 */
public record ArmorStats(int boots, int leggings, int chestplate, int helmet, int durabilityMultiplier, float toughness, float knockbackResistance, int enchantmentValue) {

    public static final ArmorStats BRONZE = new ArmorStats(2, 5, 6, 2, 20, 0.0F, 0.0F, 10);
    public static final ArmorStats DURALUMIN = new ArmorStats(2, 5, 6, 2, 10, 0.0F, 0.0F, 15);
    public static final ArmorStats STEEL = new ArmorStats(3, 5, 7, 3, 27, 1.0F, 0.0F, 9);

    /** The protection of a piece. */
    public int protection(ArmorItem.Type type) {
        return switch (type) {
            case BOOTS -> boots;
            case LEGGINGS -> leggings;
            case CHESTPLATE -> chestplate;
            case HELMET -> helmet;
        };
    }

    /** The durability of a piece: the multiplier of the material times 13, 15, 16 or 11, like Minecraft. */
    public int durability(ArmorItem.Type type) {
        int base = switch (type) {
            case BOOTS -> 13;
            case LEGGINGS -> 15;
            case CHESTPLATE -> 16;
            case HELMET -> 11;
        };
        return durabilityMultiplier * base;
    }
}
