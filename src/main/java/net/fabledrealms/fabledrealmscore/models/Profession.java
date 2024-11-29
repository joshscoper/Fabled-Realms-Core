package net.fabledrealms.fabledrealmscore.models;

import java.util.List;

public class Profession {
    private String name;
    private String description;
    private int baseLevel;
    private int maxLevel;
    private int experienceRequired;
    private List<String> tools;
    private List<ResourceNode> resourceNodes;
    private List<Material> materials;
    private List<CraftedItem> craftedItems;
    private List<Bonus> bonuses;

    // Constructor
    public Profession(String name, String description, int baseLevel, int maxLevel, int experienceRequired,
                      List<String> tools, List<ResourceNode> resourceNodes, List<Material> materials,
                      List<CraftedItem> craftedItems, List<Bonus> bonuses) {
        this.name = name;
        this.description = description;
        this.baseLevel = baseLevel;
        this.maxLevel = maxLevel;
        this.experienceRequired = experienceRequired;
        this.tools = tools;
        this.resourceNodes = resourceNodes;
        this.materials = materials;
        this.craftedItems = craftedItems;
        this.bonuses = bonuses;
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

    public int getBaseLevel() {
        return baseLevel;
    }

    public void setBaseLevel(int baseLevel) {
        this.baseLevel = baseLevel;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public void setMaxLevel(int maxLevel) {
        this.maxLevel = maxLevel;
    }

    public int getExperienceRequired() {
        return experienceRequired;
    }

    public void setExperienceRequired(int experienceRequired) {
        this.experienceRequired = experienceRequired;
    }

    public List<String> getTools() {
        return tools;
    }

    public void setTools(List<String> tools) {
        this.tools = tools;
    }

    public List<ResourceNode> getResourceNodes() {
        return resourceNodes;
    }

    public void setResourceNodes(List<ResourceNode> resourceNodes) {
        this.resourceNodes = resourceNodes;
    }

    public List<Material> getMaterials() {
        return materials;
    }

    public void setMaterials(List<Material> materials) {
        this.materials = materials;
    }

    public List<CraftedItem> getCraftedItems() {
        return craftedItems;
    }

    public void setCraftedItems(List<CraftedItem> craftedItems) {
        this.craftedItems = craftedItems;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }
}


