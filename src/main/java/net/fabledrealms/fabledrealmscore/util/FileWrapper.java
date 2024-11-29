package net.fabledrealms.fabledrealmscore.util;

import net.fabledrealms.fabledrealmscore.Core;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class FileWrapper {

    private Core core;
    private final File file;
    private final FileConfiguration configuration;

    /**
     * Constructor for the FileWrapper class.
     *
     * @param core       The instance of the Core plugin.
     * @param fileName   The name of the file (e.g., "config.yml").
     * @param directory  The directory where the file should be located.
     */
    public FileWrapper(Core core, String fileName, String directory) {
        this.core = core;
        // Ensure the directory exists
        File dir = new File(core.getDataFolder(), directory);
        if (!dir.exists()) {
            dir.mkdirs();
        }

        // Create the file
        this.file = new File(dir, fileName);
        if (!this.file.exists()) {
            try {
                this.file.createNewFile();
                core.getLogger().info("Created new file: " + fileName);
            } catch (IOException e) {
                core.getLogger().severe("Could not create file: " + fileName);
                e.printStackTrace();
            }
        }

        // Load the configuration
        this.configuration = YamlConfiguration.loadConfiguration(this.file);
    }

    /**
     * Gets the file associated with this wrapper.
     *
     * @return The File object.
     */
    public File getFile() {
        return this.file;
    }

    /**
     * Gets the YAML configuration.
     *
     * @return The FileConfiguration object.
     */
    public FileConfiguration getConfiguration() {
        return this.configuration;
    }

    /**
     * Saves the current configuration to the file.
     */
    public void save() {
        try {
            this.configuration.save(this.file);
        } catch (IOException e) {
            core.getLogger().severe("Could not save file: " + this.file.getName());
            e.printStackTrace();
        }
    }

    /**
     * Reloads the configuration from the file.
     */
    public void reload() {
        try {
            this.configuration.load(this.file);
        } catch (Exception e) {
            core.getLogger().severe("Could not reload file: " + this.file.getName());
            e.printStackTrace();
        }
    }
}
