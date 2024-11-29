package net.fabledrealms.fabledrealmscore;

import net.fabledrealms.fabledrealmscore.data.CharacterDataHandler;
import net.fabledrealms.fabledrealmscore.data.PlayerDataHandler;
import net.fabledrealms.fabledrealmscore.util.ConfigManager;
import net.fabledrealms.fabledrealmscore.util.LangManager;
import org.bukkit.plugin.java.JavaPlugin;

public final class Core extends JavaPlugin {

    private ConfigManager configManager;
    private LangManager langManager;
    private PlayerDataHandler playerDataHandler;
    private CharacterDataHandler characterDataHandler;

    @Override
    public void onEnable() {
        // Plugin startup logic
        getLogger().info("Debug Mode: " + configManager.getValue("general.debug-mode", Boolean.class));
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    private void registerManagers(){
        getLogger().info("Registering Managers...");
        this.configManager = new ConfigManager(this);
        this.langManager = new LangManager(this);
        getLogger().info("Managers Registered!");
    }

    private void registerHandlers(){
        getLogger().info("Registering Handlers...");
        this.playerDataHandler = new PlayerDataHandler(this);
        this.characterDataHandler = new CharacterDataHandler(this);
        getLogger().info("Handlers Registered!");
    }

    public ConfigManager getConfigManager(){return configManager;}
    public LangManager getLangManager(){return langManager;}
    public PlayerDataHandler getPlayerDataHandler(){return playerDataHandler;}
    public CharacterDataHandler getCharacterDataHandler(){return characterDataHandler;}

}
