package fr.kerdanetyvan.ageofalloys.content;

/**
 * The five numbers that describe a tool material (see docs/design/starter-alloys.md, section 3).
 * Neutral code: it must not import anything from Forge (see docs/architecture.md).
 *
 * @param uses              durability: how many uses before the tool breaks
 * @param speed             mining speed
 * @param attackDamageBonus extra damage added to the damage of the tool itself
 * @param level             mining level (1 = stone, 2 = iron, 3 = diamond)
 * @param enchantmentValue  enchantability: the higher, the better the enchantments offered
 */
public record ToolStats(int uses, float speed, float attackDamageBonus, int level, int enchantmentValue) {
    public static final ToolStats BRONZE = new ToolStats(400, 6.0F, 2.0F, 2, 12);
}
