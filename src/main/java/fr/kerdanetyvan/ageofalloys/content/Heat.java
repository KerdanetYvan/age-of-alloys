package fr.kerdanetyvan.ageofalloys.content;

/**
 * The heat levels of the foundry: how hot a fuel burns, from its burn time. A recipe asks for a level,
 * and a hotter fuel also suits the cooler recipes (see docs/design/alloy-creation.md, section 4).
 * Neutral code: it must not import anything from Forge (see docs/architecture.md).
 */
public final class Heat {
    /** The burn time, in ticks, from which a fuel reaches level 2 (coal, charcoal, blaze rod). */
    public static final int LEVEL_2_MIN_TICKS = 1600;
    /** The burn time, in ticks, from which a fuel reaches level 3 (coal block, lava bucket). */
    public static final int LEVEL_3_MIN_TICKS = 10000;

    /** The heat level (1, 2 or 3) of a fuel that burns for the given number of ticks. */
    public static int levelOf(int burnTicks) {
        if (burnTicks >= LEVEL_3_MIN_TICKS) {
            return 3;
        } else if (burnTicks >= LEVEL_2_MIN_TICKS) {
            return 2;
        } else {
            return 1;
        }
    }

    private Heat() {
    }
}
