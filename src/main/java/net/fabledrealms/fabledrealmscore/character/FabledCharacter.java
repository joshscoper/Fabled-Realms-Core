package net.fabledrealms.fabledrealmscore.character;

import net.fabledrealms.fabledrealmscore.character.skills.SkillNode;
import org.bukkit.Bukkit;
import org.bukkit.inventory.Inventory;
import net.fabledrealms.fabledrealmscore.character.skills.SkillTree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FabledCharacter {
    private int characterId;
    private String name;
    private int level;
    private int experience;
    private String characterClass;
    private String race;
    private String faction;
    private String guild;
    private Map<String, Integer> attributes;
    private Map<String, Map<String, Integer>> professions;
    private List<String> unlockedAbilities;
    private Map<String, Integer> skills;
    private Inventory inventory;
    private Inventory equipment;
    private String currentLocation;
    private String createdAt;
    private String updatedAt;
    private SkillTree skillTree;
    private int skillPoints;

    // Constructor
    public FabledCharacter(int characterId, String name, String characterClass) {
        this.characterId = characterId;
        this.name = name;
        this.level = 1;
        this.experience = 0;
        this.characterClass = characterClass;
        this.attributes = new HashMap<>();
        this.professions = new HashMap<>();
        this.unlockedAbilities = new ArrayList<>();
        this.skills = new HashMap<>();
        this.inventory = Bukkit.createInventory(null, 36); // Default inventory size
        this.equipment = Bukkit.createInventory(null, 9); // Default equipment size
        this.skillPoints = 0;
        this.skillTree = new SkillTree(characterClass); // Initialize skill tree based on class
    }

    // Getters and Setters
    public int getCharacterId() {
        return characterId;
    }

    public void setCharacterId(int characterId) {
        this.characterId = characterId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public String getCharacterClass() {
        return characterClass;
    }

    public void setCharacterClass(String characterClass) {
        this.characterClass = characterClass;
    }

    public String getRace() {
        return race;
    }

    public void setRace(String race) {
        this.race = race;
    }

    public String getFaction() {
        return faction;
    }

    public void setFaction(String faction) {
        this.faction = faction;
    }

    public String getGuild() {
        return guild;
    }

    public void setGuild(String guild) {
        this.guild = guild;
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public void setAttributes(Map<String, Integer> attributes) {
        this.attributes = attributes;
    }

    public Map<String, Map<String, Integer>> getProfessions() {
        return professions;
    }

    public void setProfessions(Map<String, Map<String, Integer>> professions) {
        this.professions = professions;
    }

    public List<String> getUnlockedAbilities() {
        return unlockedAbilities;
    }

    public void setUnlockedAbilities(List<String> unlockedAbilities) {
        this.unlockedAbilities = unlockedAbilities;
    }

    public Map<String, Integer> getSkills() {
        return skills;
    }

    public void setSkills(Map<String, Integer> skills) {
        this.skills = skills;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Inventory getEquipment() {
        return equipment;
    }

    public void setEquipment(Inventory equipment) {
        this.equipment = equipment;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }

    public SkillTree getSkillTree() {
        return skillTree;
    }

    public void setSkillTree(SkillTree skillTree) {
        this.skillTree = skillTree;
    }

    public int getSkillPoints() {
        return skillPoints;
    }

    public void setSkillPoints(int skillPoints) {
        this.skillPoints = skillPoints;
    }

    // Utility Methods
    public void addSkill(String skillName, int level) {
        this.skills.put(skillName, level);
    }

    public void addUnlockedAbility(String ability) {
        if (!this.unlockedAbilities.contains(ability)) {
            this.unlockedAbilities.add(ability);
        }
    }

    public void levelUp() {
        this.level++;
        // Additional logic for leveling up can be added here (e.g., increasing attributes)
    }

    public void gainExperience(int exp) {
        this.experience += exp;
        // Logic to check if experience threshold for leveling up is met can be added here
    }

    // Method to unlock a skill in the skill tree
    public boolean unlockSkill(String skillName) {
        SkillNode node = skillTree.getSkillNode(skillName);
        if (node == null) return false;

        // Check level and skill points requirement
        if (level >= node.getLevelRequired() && skillPoints >= node.getSkillPointsRequired()) {
            // Check if prerequisites are met
            for (String prerequisite : node.getPrerequisites()) {
                if (!unlockedAbilities.contains(prerequisite)) {
                    return false; // Prerequisite not met
                }
            }

            // Unlock skill
            addUnlockedAbility(skillName);
            skillPoints -= node.getSkillPointsRequired();
            return true;
        }
        return false;
    }
}
