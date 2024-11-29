package net.fabledrealms.fabledrealmscore.models;

public class Bonus {
    private String name;
    private String description;
    private int levelRequired;

    // Constructor
    public Bonus(String name, String description, int levelRequired) {
        this.name = name;
        this.description = description;
        this.levelRequired = levelRequired;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }
}
