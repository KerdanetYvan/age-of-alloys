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

    /** Steel: the chance, for each hit of the sword or the axe, to stun the target (0.15 is 15 %). */
    public static final float STEEL_STUN_CHANCE = 0.15F;
    /** Steel: how long the stun lasts, in ticks (50 ticks are 2.5 seconds). */
    public static final int STEEL_STUN_DURATION_TICKS = 50;
    /** Steel: the stun is the effect Slowness at this level, counted from 0 (3 is Slowness IV, 60 % slower). */
    public static final int STEEL_STUN_AMPLIFIER = 3;

    private static final int TICKS_PER_SECOND = 20;

    /** The chance of the stun, as a percentage for a tooltip (15). */
    public static int stunChancePercent() {
        return Math.round(STEEL_STUN_CHANCE * 100.0F);
    }

    /**
     * The whole seconds of the stun (2). A tooltip writes the duration as two digits, so that the decimal sign
     * follows the language: "2.5 s" in English and "2,5 s" in French.
     */
    public static int stunWholeSeconds() {
        return STEEL_STUN_DURATION_TICKS / TICKS_PER_SECOND;
    }

    /** The tenth of second after the whole seconds of the stun (5). */
    public static int stunTenthOfSecond() {
        return (STEEL_STUN_DURATION_TICKS % TICKS_PER_SECOND) * 10 / TICKS_PER_SECOND;
    }

    /** The percentage shown in a tooltip for a multiplier (100 for 2, 25 for 1.25), so the text follows the numbers. */
    public static int displayedPercent(float multiplier) {
        return Math.round((multiplier - 1.0F) * 100.0F);
    }

    private ToolTrait() {
    }
}
