package net.fabledrealms.fabledrealmscore.models;

public class Material {
    private String name;
    private String source;

    // Constructor
    public Material(String name, String source) {
        this.name = name;
        this.source = source;
    }

    // Getters and Setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }
}
