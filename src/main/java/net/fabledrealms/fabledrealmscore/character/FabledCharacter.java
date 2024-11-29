package net.fabledrealms.fabledrealmscore.character;

import net.fabledrealms.fabledrealmscore.util.ItemSerializationUtils;
import org.bukkit.inventory.ItemStack;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class FabledCharacter {
    private final int characterId;
    private final String name;
    private int level;
    private int experience;
    private String characterClass;
    private String race;
    private String faction;
    private String guild;
    private Map<String, Integer> attributes; // e.g., STR, DEX, INT
    private Map<String, Map<String, Integer>> professions; // Profession name -> {level, experience}
    private Map<Integer, String> inventory; // Slot -> Serialized Item
    private Map<String, String> equipment; // Slot Name -> Serialized Item
    private String currentLocation; // Simplified for now
    private String createdAt;
    private String updatedAt;

    public FabledCharacter(int characterId, String name) {
        this.characterId = characterId;
        this.name = name;
        this.inventory = new HashMap<>();
        this.equipment = new HashMap<>();
        this.attributes = new HashMap<>();
        this.professions = new HashMap<>();
    }

    // Getters and Setters
    public int getCharacterId() {
        return characterId;
    }

    public String getName() {
        return name;
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

    public Map<Integer, String> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Integer, String> inventory) {
        this.inventory = inventory;
    }

    public Map<String, String> getEquipment() {
        return equipment;
    }

    public void setEquipment(Map<String, String> equipment) {
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

    // Inventory Management
    public void addToInventory(int slot, ItemStack item) throws IOException {
        inventory.put(slot, ItemSerializationUtils.serializeItem(item));
    }

    public ItemStack getFromInventory(int slot) throws IOException {
        return ItemSerializationUtils.deserializeItem(inventory.get(slot));
    }

    // Equipment Management
    public void equipItem(String slot, ItemStack item) throws IOException {
        equipment.put(slot, ItemSerializationUtils.serializeItem(item));
    }

    public ItemStack getEquippedItem(String slot) throws IOException {
        return ItemSerializationUtils.deserializeItem(equipment.get(slot));
    }
}
