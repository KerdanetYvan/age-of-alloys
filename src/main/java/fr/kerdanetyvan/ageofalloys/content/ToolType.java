package fr.kerdanetyvan.ageofalloys.content;

/**
 * The five tools of a family. Neutral code: it must not import anything from Forge
 * (see docs/architecture.md).
 */
public enum ToolType {
    PICKAXE("pickaxe"),
    AXE("axe"),
    SHOVEL("shovel"),
    HOE("hoe"),
    SWORD("sword");

    private final String id;

    ToolType(String id) {
        this.id = id;
    }

    public String id() {
        return id;
    }
}
