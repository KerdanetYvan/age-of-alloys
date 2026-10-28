package fr.kerdanetyvan.ageofalloys.content;

/**
 * The numbers of the set bonuses: what the four pieces of an alloy do together (see docs/design/alloy-traits.md).
 * Neutral code: it must not import anything from Forge (see docs/architecture.md).
 */
public final class SetBonus {
    /** Duralumin: the damage of a fall is multiplied by this (0.7 is 30 % less). */
    public static final float DURALUMIN_FALL_DAMAGE_MULTIPLIER = 0.7F;
    /** Steel: the damage of fire is multiplied by this (0.5 is half). */
    public static final float STEEL_FIRE_DAMAGE_MULTIPLIER = 0.5F;
    /** Bronze: each piece regains 1 point of durability every so many ticks (3,600 ticks are 3 minutes). */
    public static final int BRONZE_REPAIR_INTERVAL_TICKS = 3600;

    private static final int TICKS_PER_MINUTE = 20 * 60;

    /**
     * The number shown in the tooltip of an alloy (30 for the percent of fall damage of the duralumin, 50 for
     * the percent of fire damage of the steel, 3 for the minutes of the bronze), worked out from the numbers
     * above so that the text never disagrees with them.
     */
    public static int displayedValue(String alloyId) {
        return switch (alloyId) {
            case "duralumin" -> percentOff(DURALUMIN_FALL_DAMAGE_MULTIPLIER);
            case "steel" -> percentOff(STEEL_FIRE_DAMAGE_MULTIPLIER);
            case "bronze" -> BRONZE_REPAIR_INTERVAL_TICKS / TICKS_PER_MINUTE;
            default -> 0;
        };
    }

    private static int percentOff(float multiplier) {
        return Math.round((1.0F - multiplier) * 100.0F);
    }

    private SetBonus() {
    }
}
