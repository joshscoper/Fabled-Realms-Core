package net.fabledrealms.fabledrealmscore.character.skills;

import java.util.List;

public class SkillNode {
    private String name;
    private String type; // "active" or "passive"
    private int levelRequired;
    private List<String> prerequisites;
    private int skillPointsRequired;
    private boolean isUnlocked; // New attribute to track if the skill is unlocked

    // Constructor
    public SkillNode(String name, String type, int levelRequired, List<String> prerequisites, int skillPointsRequired) {
        this.name = name;
        this.type = type;
        this.levelRequired = levelRequired;
        this.prerequisites = prerequisites;
        this.skillPointsRequired = skillPointsRequired;
        this.isUnlocked = false; // Default to false
    }

    // Getters
    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public List<String> getPrerequisites() {
        return prerequisites;
    }

    public int getSkillPointsRequired() {
        return skillPointsRequired;
    }

    public boolean isUnlocked() {
        return isUnlocked;
    }

    // Setter for isUnlocked
    public void setUnlocked(boolean unlocked) {
        isUnlocked = unlocked;
    }
}
