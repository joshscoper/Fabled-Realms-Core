package net.fabledrealms.fabledrealmscore.models;

import java.util.List;

public class Race {
    private String name;
    private String description;
    private int baseHealth;
    private List<Bonus> bonuses;

    // Constructor
    public Race(String name, String description, int baseHealth, List<Bonus> bonuses) {
        this.name = name;
        this.description = description;
        this.baseHealth = baseHealth;
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

    public int getBaseHealth() {
        return baseHealth;
    }

    public void setBaseHealth(int baseHealth) {
        this.baseHealth = baseHealth;
    }

    public List<Bonus> getBonuses() {
        return bonuses;
    }

    public void setBonuses(List<Bonus> bonuses) {
        this.bonuses = bonuses;
    }
}
