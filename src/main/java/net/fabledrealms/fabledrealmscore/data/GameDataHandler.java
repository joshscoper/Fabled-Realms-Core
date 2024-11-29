package net.fabledrealms.fabledrealmscore.data;

import net.fabledrealms.fabledrealmscore.models.GameClass;
import net.fabledrealms.fabledrealmscore.models.Faction;
import net.fabledrealms.fabledrealmscore.models.Race;
import net.fabledrealms.fabledrealmscore.models.Profession;
import net.fabledrealms.fabledrealmscore.character.skills.SkillNode;
import net.fabledrealms.fabledrealmscore.character.skills.SkillTree;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.error.YAMLException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class GameDataHandler {

    private static final Logger LOGGER = Logger.getLogger(GameDataHandler.class.getName());

    private final Map<String, GameClass> classes = new HashMap<>();
    private final Map<String, Faction> factions = new HashMap<>();
    private final Map<String, Race> races = new HashMap<>();
    private final Map<String, Profession> professions = new HashMap<>();

    // Load all game data
    public void loadAllGameData(File dataFolder) {
        loadClasses(dataFolder);
        loadFactions(dataFolder);
        loadRaces(dataFolder);
        loadProfessions(dataFolder);
    }

    // Load all game classes from the configuration file
    public void loadClasses(File dataFolder) {
        File classesFile = new File(dataFolder, "classes.yml");
        if (!classesFile.exists()) {
            throw new IllegalStateException("classes.yml file not found!");
        }

        FileConfiguration config = YamlConfiguration.loadConfiguration(classesFile);
        for (String className : config.getKeys(false)) {
            String resourceType = config.getString(className + ".resource_type");
            int baseHealth = config.getInt(className + ".base_health");

            Map<String, Integer> baseAttributes = new HashMap<>();
            for (String attribute : config.getConfigurationSection(className + ".base_attributes").getKeys(false)) {
                baseAttributes.put(attribute, config.getInt(className + ".base_attributes." + attribute));
            }

            List<String> restrictedRaces = config.getStringList(className + ".restricted_races");

            // Load Skill Tree
            SkillTree skillTree = new SkillTree(className);
            List<Map<?, ?>> skillNodes = (List<Map<?, ?>>) config.getList(className + ".skill_tree");
            if (skillNodes != null) {
                for (Map<?, ?> nodeData : skillNodes) {
                    SkillNode skillNode = new SkillNode(
                            (String) nodeData.get("name"),
                            (String) nodeData.get("type"),
                            (int) nodeData.get("level_required"),
                            (List<String>) nodeData.get("prerequisites"),
                            (int) nodeData.get("skill_points_required")
                    );
                    skillTree.addSkillNode(skillNode);
                }
            }

            GameClass gameClass = new GameClass(className, resourceType, baseHealth, baseAttributes, restrictedRaces);
            gameClass.setSkillTree(skillTree);
            classes.put(className, gameClass);
        }
    }

    // Load all factions from the configuration file
    private void loadFactions(File dataFolder) {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("factions.yml");
            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "Could not find factions.yml file");
                return;
            }
            List<Faction> loadedFactions = yaml.load(inputStream);
            for (Faction faction : loadedFactions) {
                factions.put(faction.getName(), faction);
            }
        } catch (YAMLException e) {
            LOGGER.log(Level.SEVERE, "Error loading factions.yml: " + e.getMessage(), e);
        }
    }

    // Load all races from the configuration file
    private void loadRaces(File dataFolder) {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("races.yml");
            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "Could not find races.yml file");
                return;
            }
            List<Race> loadedRaces = yaml.load(inputStream);
            for (Race race : loadedRaces) {
                races.put(race.getName(), race);
            }
        } catch (YAMLException e) {
            LOGGER.log(Level.SEVERE, "Error loading races.yml: " + e.getMessage(), e);
        }
    }

    // Load all professions from the configuration file
    private void loadProfessions(File dataFolder) {
        try {
            Yaml yaml = new Yaml();
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("professions.yml");
            if (inputStream == null) {
                LOGGER.log(Level.SEVERE, "Could not find professions.yml file");
                return;
            }
            List<Profession> loadedProfessions = yaml.load(inputStream);
            for (Profession profession : loadedProfessions) {
                professions.put(profession.getName(), profession);
            }
        } catch (YAMLException e) {
            LOGGER.log(Level.SEVERE, "Error loading professions.yml: " + e.getMessage(), e);
        }
    }

    // Get a class by its name
    public GameClass getGameClass(String className) {
        return classes.get(className);
    }

    // Get a faction by its name
    public Faction getFactionByName(String name) {
        return factions.get(name);
    }

    // Get a race by its name
    public Race getRaceByName(String name) {
        return races.get(name);
    }

    // Get a profession by its name
    public Profession getProfessionByName(String name) {
        return professions.get(name);
    }

    // Get all classes
    public Map<String, GameClass> getAllClasses() {
        return classes;
    }
}
