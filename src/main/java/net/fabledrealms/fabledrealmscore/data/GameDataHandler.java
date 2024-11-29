package net.fabledrealms.fabledrealmscore.data;

import net.fabledrealms.fabledrealmscore.models.Faction;
import net.fabledrealms.fabledrealmscore.models.GameClass;
import net.fabledrealms.fabledrealmscore.models.Race;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class GameDataHandler {

    private final File dataFolder;
    private List<GameClass> classes;
    private List<Race> races;
    private List<Faction> factions;
    private List<Map<String, Object>> professions; // Professions remain as simpler maps

    public GameDataHandler(File dataFolder) {
        this.dataFolder = dataFolder;
        loadGameData();
    }

    /**
     * Loads all game data from YAML files.
     */
    private void loadGameData() {
        this.classes = loadClasses();
        this.races = loadRaces();
        this.factions = loadFactions();
        this.professions = loadProfessions();
    }

    /**
     * Loads classes from classes.yml.
     */
    private List<GameClass> loadClasses() {
        return loadGeneric("classes.yml", "classes", data -> new GameClass(
                (String) data.get("name"),
                (String) data.get("description"),
                (String) data.get("role"),
                (Map<String, Integer>) data.get("attributes"),
                (int) data.get("base_health"),
                (int) data.get("base_resource"),
                (String) data.get("resource_type"),
                (List<Map<String, Object>>) data.get("abilities"),
                (List<String>) data.get("weapon_specializations"),
                (List<String>) data.get("armor_specializations"),
                (List<String>) data.get("allowed_races")
        ));
    }

    /**
     * Loads races from races.yml.
     */
    private List<Race> loadRaces() {
        return loadGeneric("races.yml", "races", data -> new Race(
                (String) data.get("name"),
                (String) data.get("description"),
                (Map<String, Integer>) data.get("attributes"),
                (List<Map<String, String>>) data.get("bonuses")
        ));
    }

    /**
     * Loads factions from factions.yml.
     */
    private List<Faction> loadFactions() {
        return loadGeneric("factions.yml", "factions", data -> new Faction(
                (String) data.get("name"),
                (String) data.get("description"),
                (List<Map<String, String>>) data.get("buffs")
        ));
    }

    /**
     * Loads professions from professions.yml.
     */
    private List<Map<String, Object>> loadProfessions() {
        return loadGeneric("professions.yml", "professions", data -> data);
    }

    /**
     * Generic loader for game data.
     *
     * @param fileName The YAML file to load.
     * @param key      The top-level key in the YAML file.
     * @param mapper   A function to map raw data to objects.
     * @param <T>      The type of object to return.
     * @return A list of objects loaded from the file.
     */
    private <T> List<T> loadGeneric(String fileName, String key, DataMapper<T> mapper) {
        File file = new File(dataFolder, fileName);
        if (!file.exists()) {
            createDefaultFile(file, key);
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(file);
        List<Map<?, ?>> rawData = (List<Map<?, ?>>) config.getList(key);
        List<T> result = new ArrayList<>();
        if (rawData != null) {
            for (Map<?, ?> data : rawData) {
                result.add(mapper.map((Map<String, Object>) data));
            }
        }
        return result;
    }

    /**
     * Creates a default file if it doesn't exist.
     */
    private void createDefaultFile(File file, String key) {
        try {
            file.createNewFile();
            YamlConfiguration config = YamlConfiguration.loadConfiguration(file);
            config.set(key, new ArrayList<>()); // Initialize with an empty list
            config.save(file);
        } catch (IOException e) {
            System.out.println("Could not create file: " + file.getName());
            e.printStackTrace();
        }
    }

    // --- Getters for Loaded Data ---

    public List<GameClass> getClasses() {
        return classes;
    }

    public List<Race> getRaces() {
        return races;
    }

    public List<Faction> getFactions() {
        return factions;
    }

    public List<Map<String, Object>> getProfessions() {
        return professions;
    }

    // --- Functional Interface for Data Mapping ---

    @FunctionalInterface
    private interface DataMapper<T> {
        T map(Map<String, Object> data);
    }
}
