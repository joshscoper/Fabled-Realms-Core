package net.fabledrealms.fabledrealmscore.models;

import java.util.List;
import java.util.Map;

public class Faction {
    private final String name; // Name of the faction (e.g., Aegis, Vanguard)
    private final String description; // Description of the faction
    private final List<Map<String, String>> buffs; // List of buffs (name and description)

    public Faction(String name, String description, List<Map<String, String>> buffs) {
        this.name = name;
        this.description = description;
        this.buffs = buffs;
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<Map<String, String>> getBuffs() {
        return buffs;
    }
}
