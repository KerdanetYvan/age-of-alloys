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

    /** The percentage shown in a tooltip for a multiplier (100 for 2, 25 for 1.25), so the text follows the numbers. */
    public static int displayedPercent(float multiplier) {
        return Math.round((multiplier - 1.0F) * 100.0F);
    }

    private ToolTrait() {
    }
}
