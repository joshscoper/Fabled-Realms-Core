package net.fabledrealms.fabledrealmscore.character.skills;

import java.util.HashMap;
import java.util.Map;

public class SkillTree {
    private String className;
    private Map<String, SkillNode> skillNodes;

    // Constructor
    public SkillTree(String className) {
        this.className = className;
        this.skillNodes = new HashMap<>();
    }

    // Method to add a skill node to the skill tree
    public void addSkillNode(SkillNode skillNode) {
        skillNodes.put(skillNode.getName(), skillNode);
    }

    // Method to get a skill node by its name
    public SkillNode getSkillNode(String name) {
        return skillNodes.get(name);
    }

    // Getter for all skill nodes
    public Map<String, SkillNode> getSkillNodes() {
        return skillNodes;
    }

    // Getter for class name
    public String getClassName() {
        return className;
    }
}
