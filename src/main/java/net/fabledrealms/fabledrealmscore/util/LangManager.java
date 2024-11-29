package net.fabledrealms.fabledrealmscore.util;

import net.fabledrealms.fabledrealmscore.Core;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class LangManager {

    private final Core core;
    private final Map<String, FileWrapper> localeFiles = new HashMap<>();
    private FileConfiguration currentLang;

    /**
     * Constructor for the LangManager.
     *
     * @param core The instance of the Core plugin.
     */
    public LangManager(Core core) {
        this.core = core;

        // Load available language files
        loadLocale("en", "English");
        loadLocale("nl", "Dutch");

        // Set default language
        setLanguage(core.getConfigManager().getValue("general.language", String.class));
    }

    /**
     * Loads a locale file into memory.
     *
     * @param code     The language code (e.g., "en").
     * @param language The display name of the language.
     */
    private void loadLocale(String code, String language) {
        FileWrapper langFile = new FileWrapper(core, code + ".yml", "locales");
        localeFiles.put(code, langFile);

        if (!langFile.getFile().exists()) {
            FileConfiguration config = langFile.getConfiguration();
            populateDefaults(config, code);
            langFile.save();
            core.getLogger().info("Created default " + language + " locale file.");
        }
    }

    /**
     * Sets the active language for the plugin.
     *
     * @param languageCode The language code to set (e.g., "en" or "nl").
     */
    public void setLanguage(String languageCode) {
        FileWrapper langFile = localeFiles.get(languageCode);
        if (langFile != null) {
            currentLang = langFile.getConfiguration();
            core.getLogger().info("Language set to: " + languageCode.toUpperCase(Locale.ROOT));
        } else {
            core.getLogger().warning("Language code '" + languageCode + "' not found. Falling back to English.");
            currentLang = localeFiles.get("en").getConfiguration();
        }
    }

    /**
     * Retrieves a localized message by key and applies formatting.
     *
     * @param key          The key for the message (e.g., "example.welcome").
     * @param placeholders A map of placeholders to replace (e.g., {player: "Steve"}).
     * @return The formatted and localized message.
     */
    public String getMessage(String key, Map<String, String> placeholders) {
        String message = currentLang.getString(key, "Missing translation for: " + key);
        return StringFormatter.format(message, placeholders);
    }

    /**
     * Retrieves a localized message by key without placeholders.
     *
     * @param key The key for the message (e.g., "example.welcome").
     * @return The formatted and localized message.
     */
    public String getMessage(String key) {
        return getMessage(key, null);
    }

    /**
     * Populates default messages for a locale.
     *
     * @param config The FileConfiguration for the locale.
     * @param code   The language code (e.g., "en" or "nl").
     */
    private void populateDefaults(FileConfiguration config, String code) {
        if (code.equalsIgnoreCase("en")) {
            config.addDefault("example.welcome", "&aWelcome {player} to &#00ff00Fabled Realms!");
            config.addDefault("example.farewell", "&cGoodbye {player}!");
        } else if (code.equalsIgnoreCase("nl")) {
            config.addDefault("example.welcome", "&aWelkom {player} bij &#ff4500Fabled Realms!");
            config.addDefault("example.farewell", "&cVaarwel {player}!");
        }

        config.options().copyDefaults(true);
    }
}
