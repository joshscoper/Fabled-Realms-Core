package net.fabledrealms.fabledrealmscore.models;

import java.util.List;
import java.util.Map;

public class Race {
    private final String name; // Name of the race (e.g., Human, Elf)
    private final String description; // Description of the race
    private final Map<String, Integer> attributes; // Base attributes (STR, DEX, etc.)
    private final List<Map<String, String>> bonuses; // Racial bonuses (name and description)

    public Race(String name, String description, Map<String, Integer> attributes, List<Map<String, String>> bonuses) {
        this.name = name;
        this.description = description;
        this.attributes = attributes;
        this.bonuses = bonuses;
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public List<Map<String, String>> getBonuses() {
        return bonuses;
    }
}
