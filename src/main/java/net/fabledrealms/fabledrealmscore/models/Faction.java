package net.fabledrealms.fabledrealmscore.models;

import java.util.List;

public class Faction {
    private String name;
    private String description;
    private List<Bonus> buffs;

    // Constructor
    public Faction(String name, String description, List<Bonus> buffs) {
        this.name = name;
        this.description = description;
        this.buffs = buffs;
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

    public List<Bonus> getBuffs() {
        return buffs;
    }

    public void setBuffs(List<Bonus> buffs) {
        this.buffs = buffs;
    }
}
