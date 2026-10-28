package fr.kerdanetyvan.ageofalloys.content;

/**
 * The numbers of the traits of the tools and weapons of the alloys (see docs/design/alloy-traits.md).
 * Neutral code: it must not import anything from Forge (see docs/architecture.md).
 */
public final class ToolTrait {
    /** Duralumin: a tool mines rock and ores this many times faster (2 is twice as fast). */
    public static final float DURALUMIN_MINING_MULTIPLIER = 2.0F;
    /** Steel: a tool mines stone and deepslate this many times faster (1.25 is 25 % faster). */
    public static final float STEEL_STONE_MINING_MULTIPLIER = 1.25F;

    /** Duralumin: the sword and the axe reach this many blocks further (the attack reach is 3 blocks). */
    public static final double DURALUMIN_ATTACK_REACH_BONUS = 0.5;

    /**
     * Steel: the sword and the axe add this to the attack knockback attribute. The attack pushes the target by
     * half of the attribute (with the enchantment Knockback and the sprint) on top of the push of the hit, which is
     * 0.4: so 0.2 is a push of 0.1 more, about 25 % more than a plain hit.
     */
    public static final double STEEL_ATTACK_KNOCKBACK_BONUS = 0.2;

    /** The percentage shown in a tooltip for a multiplier (100 for 2, 25 for 1.25), so the text follows the numbers. */
    public static int displayedPercent(float multiplier) {
        return Math.round((multiplier - 1.0F) * 100.0F);
    }

    private ToolTrait() {
    }
}
