package net.fabledrealms.fabledrealmscore.models;

import java.util.List;
import java.util.Map;

public class GameClass {
    private final String name; // Name of the class (e.g., Warrior, Mage)
    private final String description; // Description of the class
    private final String role; // Role in gameplay (Tank, DPS, Support, Hybrid)
    private final Map<String, Integer> attributes; // Base attributes (STR, DEX, etc.)
    private final int baseHealth; // Base health value
    private final int baseResource; // Base resource pool size
    private final String resourceType; // Type of resource (Mana, Rage, Energy, etc.)
    private final List<Map<String, Object>> abilities; // List of abilities
    private final List<String> weaponSpecializations; // Preferred weapon types
    private final List<String> armorSpecializations; // Preferred armor types
    private final List<String> allowedRaces; // List of allowed races for this class

    public GameClass(
            String name,
            String description,
            String role,
            Map<String, Integer> attributes,
            int baseHealth,
            int baseResource,
            String resourceType,
            List<Map<String, Object>> abilities,
            List<String> weaponSpecializations,
            List<String> armorSpecializations,
            List<String> allowedRaces
    ) {
        this.name = name;
        this.description = description;
        this.role = role;
        this.attributes = attributes;
        this.baseHealth = baseHealth;
        this.baseResource = baseResource;
        this.resourceType = resourceType;
        this.abilities = abilities;
        this.weaponSpecializations = weaponSpecializations;
        this.armorSpecializations = armorSpecializations;
        this.allowedRaces = allowedRaces;
    }

    // --- Getters ---
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getRole() {
        return role;
    }

    public Map<String, Integer> getAttributes() {
        return attributes;
    }

    public int getBaseHealth() {
        return baseHealth;
    }

    public int getBaseResource() {
        return baseResource;
    }

    public String getResourceType() {
        return resourceType;
    }

    public List<Map<String, Object>> getAbilities() {
        return abilities;
    }

    public List<String> getWeaponSpecializations() {
        return weaponSpecializations;
    }

    public List<String> getArmorSpecializations() {
        return armorSpecializations;
    }

    public List<String> getAllowedRaces() {
        return allowedRaces;
    }
}
