package net.fabledrealms.fabledrealmscore.data;

import net.fabledrealms.fabledrealmscore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PlayerDataHandler {

    private final Core core;
    private final File dataFolder;

    public PlayerDataHandler(Core core) {
        this.core = core;
        this.dataFolder = new File(core.getDataFolder(), "PlayerData");

        // Ensure the directory exists
        if (!dataFolder.exists()) {
            dataFolder.mkdirs();
        }
    }

    /**
     * Loads a player's data file. Creates a new file with default values if it doesn't exist.
     *
     * @param uuid The UUID of the player.
     * @return The player's FileConfiguration object.
     */
    public FileConfiguration loadPlayerData(UUID uuid) {
        File playerFile = getPlayerFile(uuid);

        if (!playerFile.exists()) {
            try {
                playerFile.createNewFile();
                FileConfiguration config = YamlConfiguration.loadConfiguration(playerFile);
                initializeDefaults(config, uuid);
                savePlayerData(uuid, config);
                return config;
            } catch (IOException e) {
                core.getLogger().severe("Could not create data file for player " + uuid + ": " + e.getMessage());
            }
        }

        return YamlConfiguration.loadConfiguration(playerFile);
    }

    /**
     * Saves a player's data to their YAML file.
     *
     * @param uuid   The UUID of the player.
     * @param config The player's FileConfiguration object.
     */
    public void savePlayerData(UUID uuid, FileConfiguration config) {
        try {
            config.save(getPlayerFile(uuid));
        } catch (IOException e) {
            core.getLogger().severe("Could not save data for player " + uuid + ": " + e.getMessage());
        }
    }

    /**
     * Initializes a player's data file with default values.
     *
     * @param config The FileConfiguration object to initialize.
     * @param uuid   The UUID of the player.
     */
    private void initializeDefaults(FileConfiguration config, UUID uuid) {
        config.set("account.username", "PlayerName"); // Placeholder, update dynamically.
        config.set("account.language", "en");
        config.set("account.settings.global_pvp_toggle", true);
        config.set("account.friends", new ArrayList<>()); // Empty friends list
        config.set("account.ignored", new ArrayList<>()); // Empty ignored list
        config.set("characters", new ArrayList<>()); // Initialize as empty.
    }

    /**
     * Gets the file for a specific player's data.
     *
     * @param uuid The UUID of the player.
     * @return The File object for the player's data file.
     */
    private File getPlayerFile(UUID uuid) {
        return new File(dataFolder, uuid.toString() + ".yml");
    }

    // --- Friends and Ignored List Methods ---

    public void addFriend(UUID uuid, UUID friendUuid) {
        FileConfiguration config = loadPlayerData(uuid);
        List<String> friends = config.getStringList("account.friends");
        if (!friends.contains(friendUuid.toString())) {
            friends.add(friendUuid.toString());
            config.set("account.friends", friends);
            savePlayerData(uuid, config);
        }
    }

    public void removeFriend(UUID uuid, UUID friendUuid) {
        FileConfiguration config = loadPlayerData(uuid);
        List<String> friends = config.getStringList("account.friends");
        if (friends.contains(friendUuid.toString())) {
            friends.remove(friendUuid.toString());
            config.set("account.friends", friends);
            savePlayerData(uuid, config);
        }
    }

    public boolean isFriend(UUID uuid, UUID friendUuid) {
        FileConfiguration config = loadPlayerData(uuid);
        List<String> friends = config.getStringList("account.friends");
        return friends.contains(friendUuid.toString());
    }

    public void addIgnored(UUID uuid, UUID ignoredUuid) {
        FileConfiguration config = loadPlayerData(uuid);
        List<String> ignored = config.getStringList("account.ignored");
        if (!ignored.contains(ignoredUuid.toString())) {
            ignored.add(ignoredUuid.toString());
            config.set("account.ignored", ignored);
            savePlayerData(uuid, config);
        }
    }

    public void removeIgnored(UUID uuid, UUID ignoredUuid) {
        FileConfiguration config = loadPlayerData(uuid);
        List<String> ignored = config.getStringList("account.ignored");
        if (ignored.contains(ignoredUuid.toString())) {
            ignored.remove(ignoredUuid.toString());
            config.set("account.ignored", ignored);
            savePlayerData(uuid, config);
        }
    }

    public boolean isIgnored(UUID uuid, UUID ignoredUuid) {
        FileConfiguration config = loadPlayerData(uuid);
        List<String> ignored = config.getStringList("account.ignored");
        return ignored.contains(ignoredUuid.toString());
    }

}
