package net.fabledrealms.fabledrealmscore.data;

import net.fabledrealms.fabledrealmscore.Core;
import net.fabledrealms.fabledrealmscore.character.FabledCharacter;
import net.fabledrealms.fabledrealmscore.character.skills.SkillNode;
import net.fabledrealms.fabledrealmscore.character.skills.SkillTree;
import net.fabledrealms.fabledrealmscore.util.ItemSerializationUtils;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class CharacterDataHandler {

    private final Core core;

    public CharacterDataHandler(Core core) {
        this.core = core;
    }

    public FabledCharacter loadCharacter(UUID playerUUID, int characterId) {
        FileConfiguration config = loadPlayerData(playerUUID);
        List<Map<?, ?>> characters = getCharactersList(config);

        for (Map<?, ?> characterData : characters) {
            if ((int) characterData.get("character_id") == characterId) {
                return mapToFabledCharacter(characterData);
            }
        }

        return null;
    }

    public void saveCharacter(UUID playerUUID, FabledCharacter character) {
        FileConfiguration config = loadPlayerData(playerUUID);
        List<Map<?, ?>> characters = getCharactersList(config);

        boolean found = false;
        for (int i = 0; i < characters.size(); i++) {
            Map<?, ?> characterData = characters.get(i);
            if ((int) characterData.get("character_id") == character.getCharacterId()) {
                characters.set(i, fabledCharacterToMap(character));
                found = true;
                break;
            }
        }

        if (!found) {
            characters.add(fabledCharacterToMap(character));
        }

        config.set("characters", characters);
        savePlayerData(playerUUID, config);
    }

    public void removeCharacter(UUID playerUUID, int characterId) {
        FileConfiguration config = loadPlayerData(playerUUID);
        List<Map<?, ?>> characters = getCharactersList(config);

        characters.removeIf(character -> (int) character.get("character_id") == characterId);
        config.set("characters", characters);
        savePlayerData(playerUUID, config);
    }

    private FabledCharacter mapToFabledCharacter(Map<?, ?> data) {
        FabledCharacter character = new FabledCharacter((int) data.get("character_id"), (String) data.get("name"), (String) data.get("class"));

        character.setLevel((int) data.get("level"));
        character.setExperience((int) data.get("experience"));
        character.setRace((String) data.get("race"));
        character.setFaction((String) data.get("faction"));
        character.setGuild((String) data.get("guild"));
        character.setAttributes((Map<String, Integer>) data.get("attributes"));
        character.setProfessions((Map<String, Map<String, Integer>>) data.get("professions"));

        // Use ItemSerializationUtils to deserialize inventory and equipment
        try {
            character.setInventory(ItemSerializationUtils.deserializeInventory((String) data.get("inventory")));
            character.setEquipment(ItemSerializationUtils.deserializeEquipment((String) data.get("equipment")));
        } catch (Exception e) {
            core.getLogger().warning("Failed to deserialize inventory or equipment for character " + character.getCharacterId());
        }

        character.setCurrentLocation((String) data.get("current_location"));
        character.setCreatedAt((String) data.get("created_at"));
        character.setUpdatedAt((String) data.get("updated_at"));

        // Add unlocked abilities and skill progression
        character.setUnlockedAbilities((List<String>) data.get("unlocked_abilities"));
        character.setSkills((Map<String, Integer>) data.get("skills"));

        // Load skill tree data
        SkillTree skillTree = new SkillTree(character.getCharacterClass());
        List<Map<?, ?>> skillNodes = (List<Map<?, ?>>) data.get("skill_tree");
        if (skillNodes != null) {
            for (Map<?, ?> nodeData : skillNodes) {
                SkillNode skillNode = new SkillNode(
                        (String) nodeData.get("name"),
                        (String) nodeData.get("type"),
                        (int) nodeData.get("level_required"),
                        (List<String>) nodeData.get("prerequisites"),
                        (int) nodeData.get("skill_points_required")
                );
                skillNode.setUnlocked((boolean) nodeData.get("is_unlocked"));
                skillTree.addSkillNode(skillNode);
            }
        }
        character.setSkillTree(skillTree);

        return character;
    }

    private Map<String, Object> fabledCharacterToMap(FabledCharacter character) {
        Map<String, Object> data = new HashMap<>();
        data.put("character_id", character.getCharacterId());
        data.put("name", character.getName());
        data.put("level", character.getLevel());
        data.put("experience", character.getExperience());
        data.put("class", character.getCharacterClass());
        data.put("race", character.getRace());
        data.put("faction", character.getFaction());
        data.put("guild", character.getGuild());
        data.put("attributes", character.getAttributes());
        data.put("professions", character.getProfessions());

        // Serialize inventory and equipment using ItemSerializationUtils
        try {
            data.put("inventory", ItemSerializationUtils.serializeInventory(character.getInventory()));
            data.put("equipment", ItemSerializationUtils.serializeEquipment(character.getEquipment()));
        } catch (IOException e) {
            core.getLogger().severe("Failed to serialize inventory or equipment for character " + character.getCharacterId());
            e.printStackTrace();
        }

        data.put("current_location", character.getCurrentLocation());
        data.put("created_at", character.getCreatedAt());
        data.put("updated_at", character.getUpdatedAt());

        // Add unlocked abilities and skill progression
        data.put("unlocked_abilities", character.getUnlockedAbilities());
        data.put("skills", character.getSkills());

        // Save skill tree data
        List<Map<String, Object>> skillNodes = new ArrayList<>();
        for (SkillNode node : character.getSkillTree().getSkillNodes().values()) {
            Map<String, Object> nodeData = new HashMap<>();
            nodeData.put("name", node.getName());
            nodeData.put("type", node.getType());
            nodeData.put("level_required", node.getLevelRequired());
            nodeData.put("prerequisites", node.getPrerequisites());
            nodeData.put("skill_points_required", node.getSkillPointsRequired());
            nodeData.put("is_unlocked", node.isUnlocked());
            skillNodes.add(nodeData);
        }
        data.put("skill_tree", skillNodes);

        return data;
    }

    private FileConfiguration loadPlayerData(UUID uuid) {
        File file = new File(core.getDataFolder() + "/PlayerData", uuid + ".yml");
        return YamlConfiguration.loadConfiguration(file);
    }

    private void savePlayerData(UUID uuid, FileConfiguration config) {
        File file = new File(core.getDataFolder() + "/PlayerData", uuid + ".yml");
        try {
            config.save(file);
        } catch (IOException e) {
            core.getLogger().severe("Failed to save player data for " + uuid);
            e.printStackTrace();
        }
    }

    private List<Map<?, ?>> getCharactersList(FileConfiguration config) {
        List<Map<?, ?>> characters = (List<Map<?, ?>>) config.getList("characters");
        return characters != null ? characters : new ArrayList<>();
    }
}
