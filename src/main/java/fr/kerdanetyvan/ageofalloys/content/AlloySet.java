package fr.kerdanetyvan.ageofalloys.content;

import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.LivingEntity;

/**
 * The rule that tells whether a creature wears a set of an alloy: one piece of that alloy in each of the four
 * armor slots (see docs/design/alloy-traits.md). It holds for every creature that wears the armor, not only players.
 * Neutral code: only Minecraft types, no Forge import (see docs/architecture.md).
 */
public final class AlloySet {
    /** The number of pieces of a full set. */
    public static final int PIECES = 4;

    /**
     * How many pieces of this alloy the creature wears. A piece counts only in its own slot: a chestplate that a
     * command put in the helmet slot does not.
     */
    public static int piecesWorn(LivingEntity entity, AlloyArmorMaterial material) {
        int count = 0;
        for (EquipmentSlot slot : EquipmentSlot.values()) {
            if (slot.getType() != EquipmentSlot.Type.ARMOR) {
                continue;
            }
            if (entity.getItemBySlot(slot).getItem() instanceof AlloyArmorItem piece
                    && piece.alloyMaterial() == material
                    && piece.getEquipmentSlot() == slot) {
                count++;
            }
        }
        return count;
    }

    /** Whether the creature wears the four pieces of this alloy. */
    public static boolean isComplete(LivingEntity entity, AlloyArmorMaterial material) {
        return piecesWorn(entity, material) == PIECES;
    }

    private AlloySet() {
    }
}
