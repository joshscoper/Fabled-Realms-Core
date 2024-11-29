package net.fabledrealms.fabledrealmscore.models;

public class ResourceNode {
    private String name;
    private int levelRequired;
    private int respawnTime;

    // Constructor
    public ResourceNode(String name, int levelRequired, int respawnTime) {
        this.name = name;
        this.levelRequired = levelRequired;
        this.respawnTime = respawnTime;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLevelRequired() {
        return levelRequired;
    }

    public void setLevelRequired(int levelRequired) {
        this.levelRequired = levelRequired;
    }

    public int getRespawnTime() {
        return respawnTime;
    }

    public void setRespawnTime(int respawnTime) {
        this.respawnTime = respawnTime;
    }
}
