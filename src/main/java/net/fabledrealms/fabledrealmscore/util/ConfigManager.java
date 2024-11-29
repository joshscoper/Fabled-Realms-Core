package net.fabledrealms.fabledrealmscore.util;

import net.fabledrealms.fabledrealmscore.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.io.File;

public class ConfigManager {

    private final Core core;
    private final FileWrapper configWrapper;

    /**
     * Constructor for the ConfigManager.
     *
     * @param core The instance of the Core plugin.
     */
    public ConfigManager(Core core) {
        this.core = core;
        this.configWrapper = new FileWrapper(core, "config.yml", core.getDataFolder() + File.separator + "configuration");
        setupDefaults();
    }

    /**
     * Sets default configuration values if they do not exist.
     */
    private void setupDefaults() {
        FileConfiguration config = configWrapper.getConfiguration();

        // General settings
        config.addDefault("general.language", "en");
        config.addDefault("general.debug-mode", false);

        // Database settings
        config.addDefault("database.enabled", true);
        config.addDefault("database.type", "mysql");
        config.addDefault("database.name", "fabledrealms");
        config.addDefault("database.username", "admin");
        config.addDefault("database.password", "password123");
        config.addDefault("database.url", "localhost:3306");

        // Logging settings
        config.addDefault("logging.log-level", "INFO");

        // Save defaults if new
        config.options().copyDefaults(true);
        configWrapper.save();
    }

    /**
     * Reloads the configuration file.
     */
    public void reloadConfig() {
        configWrapper.reload();
        core.getLogger().info("Configuration reloaded.");
    }

    /**
     * Retrieves a value from the configuration.
     *
     * @param path The path to the value in the configuration.
     * @return The value as an Object, or null if not found.
     */
    public Object getValue(String path) {
        return configWrapper.getConfiguration().get(path);
    }

    /**
     * Retrieves a value from the configuration with a specific type.
     *
     * @param path The path to the value in the configuration.
     * @param clazz The class of the value (e.g., String.class).
     * @param <T> The type of the value.
     * @return The value as the specified type, or null if not found or incorrect type.
     */
    public <T> T getValue(String path, Class<T> clazz) {
        Object value = configWrapper.getConfiguration().get(path);
        if (clazz.isInstance(value)) {
            return clazz.cast(value);
        }
        return null;
    }

    /**
     * Sets a value in the configuration and saves the file.
     *
     * @param path  The path to set the value at.
     * @param value The value to set.
     */
    public void setValue(String path, Object value) {
        configWrapper.getConfiguration().set(path, value);
        configWrapper.save();
        core.getLogger().info("Updated config path: " + path);
    }

    /**
     * Gets the underlying FileConfiguration object.
     *
     * @return The FileConfiguration object.
     */
    public FileConfiguration getConfiguration() {
        return configWrapper.getConfiguration();
    }
}
