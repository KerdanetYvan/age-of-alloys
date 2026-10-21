package fr.kerdanetyvan.ageofalloys.content;

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

    public String toolName(ToolType type) {
        return id + "_" + type.id();
    }
}
