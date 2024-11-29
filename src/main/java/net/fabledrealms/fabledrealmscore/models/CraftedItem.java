package net.fabledrealms.fabledrealmscore.models;

import java.util.List;

public class CraftedItem {
    private String name;
    private int levelRequired;
    private List<String> materialsRequired;

    // Constructor
    public CraftedItem(String name, int levelRequired, List<String> materialsRequired) {
        this.name = name;
        this.levelRequired = levelRequired;
        this.materialsRequired = materialsRequired;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    public List<String> getMaterialsRequired() {
        return materialsRequired;
    }

    public void setMaterialsRequired(List<String> materialsRequired) {
        this.materialsRequired = materialsRequired;
    }
}
