package net.fabledrealms.fabledrealmscore.models;

import net.fabledrealms.fabledrealmscore.character.skills.SkillTree;
import java.util.List;
import java.util.Map;

public class GameClass {
    private String className;
    private String resourceType;
    private int baseHealth;
    private Map<String, Integer> baseAttributes;
    private List<String> restrictedRaces;
    private SkillTree skillTree;

    // Constructor
    public GameClass(String className, String resourceType, int baseHealth, Map<String, Integer> baseAttributes, List<String> restrictedRaces) {
        this.className = className;
        this.resourceType = resourceType;
        this.baseHealth = baseHealth;
        this.baseAttributes = baseAttributes;
        this.restrictedRaces = restrictedRaces;
        this.skillTree = new SkillTree(className); // Initialize skill tree for the class
    }

    // Getters and Setters
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public Map<String, Integer> getBaseAttributes() {
        return baseAttributes;
    }

    public void setBaseAttributes(Map<String, Integer> baseAttributes) {
        this.baseAttributes = baseAttributes;
    }

    public List<String> getRestrictedRaces() {
        return restrictedRaces;
    }

    public void setRestrictedRaces(List<String> restrictedRaces) {
        this.restrictedRaces = restrictedRaces;
    }

    public SkillTree getSkillTree() {
        return skillTree;
    }

    public void setSkillTree(SkillTree skillTree) {
        this.skillTree = skillTree;
    }
}
