package fr.kerdanetyvan.ageofalloys.content;

import java.util.function.Supplier;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

/**
 * The material of a family of tools: it reads its numbers from {@link ToolStats}.
 * Neutral code: only Minecraft types, no Forge import (see docs/architecture.md).
 * The repair ingredient is a {@link Supplier} given by the registry package, so this class
 * does not depend on the mod's items.
 */
public final class AlloyTier implements Tier {
    private final ToolStats stats;
    private final TagKey<Block> needsTag;
    private final Supplier<Ingredient> repairIngredient;

    public AlloyTier(ToolStats stats, TagKey<Block> needsTag, Supplier<Ingredient> repairIngredient) {
        this.stats = stats;
        this.needsTag = needsTag;
        this.repairIngredient = repairIngredient;
    }

    @Override
    public int getUses() {
        return stats.uses();
    }

    @Override
    public float getSpeed() {
        return stats.speed();
    }

    @Override
    public float getAttackDamageBonus() {
        return stats.attackDamageBonus();
    }

    @Override
    public int getLevel() {
        return stats.level();
    }

    @Override
    public int getEnchantmentValue() {
        return stats.enchantmentValue();
    }

    @Override
    public Ingredient getRepairIngredient() {
        return repairIngredient.get();
    }

    @Override
    public TagKey<Block> getTag() {
        return needsTag;
    }
}
