package fr.kerdanetyvan.ageofalloys.content;

import net.minecraft.world.item.ArmorItem;

/**
 * Every metal of the mod: the three alloys and the raw metals they are made from.
 * Neutral code: it must not import anything from Forge (see docs/architecture.md).
 */
public enum Metal {
    BRONZE("bronze", Kind.ALLOY),
    DURALUMIN("duralumin", Kind.ALLOY),
    STEEL("steel", Kind.ALLOY),
    TIN("tin", Kind.RAW),
    ALUMINUM("aluminum", Kind.RAW),
    MAGNESIUM("magnesium", Kind.RAW),
    MANGANESE("manganese", Kind.RAW);

    public enum Kind {
        ALLOY,
        RAW
    }

    private final String id;
    private final Kind kind;

    Metal(String id, Kind kind) {
        this.id = id;
        this.kind = kind;
    }

    public String id() {
        return id;
    }

    public Kind kind() {
        return kind;
    }

    public String ingotName() {
        return id + "_ingot";
    }

    /**
     * Whether the ores of this metal need a pickaxe of the iron level (iron, bronze, duralumin or steel), where
     * the other ores only need one of stone (see docs/design/raw-materials.md).
     */
    public boolean oreNeedsIronTool() {
        return this == MAGNESIUM || this == MANGANESE;
    }

    /** The ore in stone, for example {@code tin_ore}. */
    public String oreName() {
        return id + "_ore";
    }

    /** The ore in deepslate, for example {@code deepslate_tin_ore}. */
    public String deepslateOreName() {
        return "deepslate_" + id + "_ore";
    }

    /** The raw item an ore drops, which is melted into an ingot, for example {@code raw_tin}. */
    public String rawName() {
        return "raw_" + id;
    }

    /** The block of nine ingots, for example {@code tin_block}. */
    public String blockName() {
        return id + "_block";
    }

    /** The block of nine raw items, for example {@code raw_tin_block}. */
    public String rawBlockName() {
        return "raw_" + id + "_block";
    }

    /** The name of an armor piece, for example {@code bronze_helmet}. */
    public String armorName(ArmorItem.Type type) {
        return id + "_" + type.getName();
    }

    public String toolName(ToolType type) {
        return id + "_" + type.id();
    }
}
