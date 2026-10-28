package fr.kerdanetyvan.ageofalloys.content;

import com.google.common.collect.ImmutableMultimap;
import com.google.common.collect.Multimap;
import java.util.EnumMap;
import java.util.Map;
import java.util.UUID;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.ai.attributes.Attribute;
import net.minecraft.world.entity.ai.attributes.AttributeModifier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.item.ArmorItem;

/**
 * A piece of armor of an alloy: an {@link ArmorItem} that also changes the speed of whoever wears it, by the
 * percentage of its material (see docs/design/alloy-traits.md). A material with no speed bonus adds nothing,
 * so its pieces behave like plain armor.
 * Neutral code: only Minecraft types, no Forge import (see docs/architecture.md).
 */
public class AlloyArmorItem extends ArmorItem {
    /**
     * One identifier per type of piece: Minecraft tells modifiers apart by it, so the four pieces of a set would
     * overwrite each other if they shared one.
     */
    private static final Map<Type, UUID> SPEED_MODIFIER_IDS = new EnumMap<>(Type.class);

    static {
        SPEED_MODIFIER_IDS.put(Type.BOOTS, UUID.fromString("5d3c7a42-81f4-4b1e-9a60-3e2b7c914a01"));
        SPEED_MODIFIER_IDS.put(Type.LEGGINGS, UUID.fromString("5d3c7a42-81f4-4b1e-9a60-3e2b7c914a02"));
        SPEED_MODIFIER_IDS.put(Type.CHESTPLATE, UUID.fromString("5d3c7a42-81f4-4b1e-9a60-3e2b7c914a03"));
        SPEED_MODIFIER_IDS.put(Type.HELMET, UUID.fromString("5d3c7a42-81f4-4b1e-9a60-3e2b7c914a04"));
    }

    private final AlloyArmorMaterial alloyMaterial;
    private final Multimap<Attribute, AttributeModifier> modifiers;

    public AlloyArmorItem(AlloyArmorMaterial material, Type type, Properties properties) {
        super(material, type, properties);
        this.alloyMaterial = material;
        ImmutableMultimap.Builder<Attribute, AttributeModifier> builder = ImmutableMultimap.builder();
        // What the armor already gives: protection, toughness and knockback resistance.
        builder.putAll(super.getDefaultAttributeModifiers(type.getSlot()));
        double speedBonus = material.movementSpeedBonus();
        if (speedBonus != 0) {
            // MULTIPLY_BASE: a percentage of the base speed, added from one piece to the next (4 x 0.03 = +12 %).
            builder.put(Attributes.MOVEMENT_SPEED, new AttributeModifier(SPEED_MODIFIER_IDS.get(type),
                    "Armor movement speed", speedBonus, AttributeModifier.Operation.MULTIPLY_BASE));
        }
        this.modifiers = builder.build();
    }

    /** The material of this piece, as an alloy material (the same object for the four pieces of a set). */
    public AlloyArmorMaterial alloyMaterial() {
        return alloyMaterial;
    }

    @Override
    public Multimap<Attribute, AttributeModifier> getDefaultAttributeModifiers(EquipmentSlot slot) {
        return slot == getEquipmentSlot() ? modifiers : super.getDefaultAttributeModifiers(slot);
    }
}
