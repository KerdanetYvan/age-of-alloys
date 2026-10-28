package fr.kerdanetyvan.ageofalloys.registry;

import fr.kerdanetyvan.ageofalloys.AgeOfAlloys;
import fr.kerdanetyvan.ageofalloys.content.Metal;
import net.minecraft.core.registries.Registries;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;

/** Names of the shared item tags of the mod. The shared namespace is written once, here. */
public final class ModTags {
    /** Namespace of the shared tags on Forge 1.20.1 (it is "c" on Fabric and NeoForge). */
    private static final String COMMON_NAMESPACE = "forge";

    /** The purified coal: the carbon of steel. */
    public static final TagKey<Item> PURIFIED_COAL = TagKey.create(Registries.ITEM,
            ResourceLocation.fromNamespaceAndPath(AgeOfAlloys.MOD_ID, "purified_coal"));

    /** The tag of the ingots of a metal, for example {@code forge:ingots/bronze}. */
    public static TagKey<Item> ingot(Metal metal) {
        return TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, "ingots/" + metal.id()));
    }

    /** The tag of the ore blocks of a metal, for example {@code forge:ores/tin}. */
    public static TagKey<Block> oreBlock(Metal metal) {
        return TagKey.create(Registries.BLOCK,
                ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, "ores/" + metal.id()));
    }

    /** The tag of the ore items of a metal, the same name as the block tag. */
    public static TagKey<Item> oreItem(Metal metal) {
        return TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, "ores/" + metal.id()));
    }

    /** The tag of the raw items of a metal, for example {@code forge:raw_materials/tin}. */
    public static TagKey<Item> rawMaterial(Metal metal) {
        return TagKey.create(Registries.ITEM,
                ResourceLocation.fromNamespaceAndPath(COMMON_NAMESPACE, "raw_materials/" + metal.id()));
    }

    private ModTags() {
    }
}
