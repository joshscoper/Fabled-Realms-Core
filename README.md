# Fabled Realms RPG Plugin

## Overview
The Fabled Realms RPG Plugin is a comprehensive role-playing game system built for Minecraft, inspired by the likes of World of Warcraft, Dungeons & Dragons, and other MMORPGs. The plugin features character progression, skill trees, multiple races, professions, classes, and dynamic abilities that players can unlock and use during gameplay.

### Features So Far
- **Multi-Character System**: Each player can have multiple characters, each with unique attributes, classes, professions, and skill trees.
- **Class System**: The plugin currently supports several RPG classes including Warrior, Mage, Paladin, Cleric, Rogue, Hunter, Shaman, and Druid. Each class has its own skill tree and unique abilities.
- **Race System**: Players can choose from a range of fantasy races such as Human, Elf, Dwarf, Troll, and Orc. Each race has unique base attributes.
- **Professions**: A range of crafting and gathering professions is available, inspired by Runescape's skill system. Professions include Alchemy, Enchanting, Mining, Smithing, Woodcutting, Fishing, and more.
- **Skill Trees and Abilities**: Players unlock abilities as they progress, with each class having a dedicated skill tree consisting of active and passive skills that can be unlocked using skill points.
- **Comprehensive Character Data Storage**: Character data is persisted using YAML, ensuring all information including skills, abilities, inventory, and professions is easily readable and modifiable.

## Current State of Implementation
The plugin is currently organized with the following core elements:

### **1. Game Data Management**
- **GameDataHandler**: Responsible for loading and managing all static game data such as classes, factions, races, and professions. Data is loaded from YAML files (`classes.yml`, `factions.yml`, `races.yml`, and `professions.yml`) to provide configuration-driven extensibility.
- **SkillTree Integration**: Each class has a unique skill tree, loaded from configuration, which defines the abilities available to that class.

### **2. Character Management**
- **CharacterDataHandler**: Handles character data persistence, including loading, saving, and removing character details. Data includes attributes such as level, class, race, professions, inventory, equipment, and unlocked abilities.
- **Character Attributes**: Each character has attributes like strength, agility, intelligence, constitution, wisdom, and charisma, which are influenced by race and class.
- **Professions**: Characters can level up different professions like Alchemy, Enchanting, Smithing, etc., each storing levels and experience.
- **Skill Tree and Abilities**: Characters have skill trees that can be customized with skill points as they progress, unlocking new abilities.

### **3. Configuration Files**
The plugin uses YAML files for configuration, making it easy for server administrators to modify and add new classes, professions, races, factions, and other gameplay elements.
- **`classes.yml`**: Defines classes, base health, resource types, attributes, restricted races, and skill trees for each class.
- **`factions.yml`**: Defines factions and their associated buffs and features.
- **`races.yml`**: Defines race-specific attributes.
- **`professions.yml`**: Defines professions, levels, and experience requirements.

## Plugin Data Folder Structure
```
plugins/
  FabledRealms/
    configuration/
      config.yml
    locale/
      en.yml
      nl.yml
    classes.yml
    factions.yml
    races.yml
    professions.yml
    PlayerData/
      <playerUUID>.yml
```
This structure includes configuration files for classes, factions, races, and professions, as well as individual player data stored in the `PlayerData` folder.

## Key Classes and API Usage

### **1. GameDataHandler**
- **Usage**: Manages all game-related data such as classes, factions, races, and professions.
- **Common Methods**:
  - `loadAllGameData(File dataFolder)`: Loads all game data from YAML files.
  - `getGameClass(String className)`: Retrieves a `GameClass` by its name.
  - `getFactionByName(String name)`: Retrieves a `Faction` by its name.

### **2. CharacterDataHandler**
- **Usage**: Manages character data, including saving and loading player characters.
- **Common Methods**:
  - `loadCharacter(UUID playerUUID, int characterId)`: Loads character data for a given player and character ID.
  - `saveCharacter(UUID playerUUID, FabledCharacter character)`: Saves character data for a given player.
  - `removeCharacter(UUID playerUUID, int characterId)`: Removes character data for a given player.

### **3. FabledCharacter**
- **Usage**: Represents an individual character and holds all related information such as level, attributes, skill tree, and inventory.
- **Common Methods**:
  - `levelUp()`: Increases the character's level.
  - `gainExperience(int exp)`: Adds experience to the character and checks for leveling up.
  - `unlockSkill(String skillName)`: Unlocks a skill in the character's skill tree.

## How to Use
1. Place the plugin in your server's `plugins` folder.
2. Ensure configuration files (`classes.yml`, `factions.yml`, `races.yml`, `professions.yml`) are located in the plugin's data folder.
3. Start the server and monitor the console for any errors related to data loading.
4. Configure classes, professions, and other elements as needed using the YAML files.

### Pull and Push Instructions for IntelliJ
1. **Clone the Repository**: Use the Terminal or Version Control tab in IntelliJ to clone the repository.
   ```
   git clone <repository-url>
   ```
2. **Pull Latest Changes**: Before starting work, ensure your local repository is up to date.
   ```
   git pull origin main
   ```
3. **Push Your Changes**: After making changes, commit and push your changes to the remote repository.
   ```
   git add .
   git commit -m "Your commit message"
   git push origin main
   ```

## Contributions

## Maven Repository
To include the Fabled Realms RPG Plugin in your project, add the following to your `pom.xml`:

```xml
<repositories>
    <repository>
        <id>jitpack.io</id>
        <url>https://jitpack.io</url>
    </repository>
</repositories>

<dependencies>
    <dependency>
        <groupId>com.github.joshscoper</groupId>
        <artifactId>fabled-core</artifactId>
        <version>1.0.0</version>
    </dependency>
</dependencies>
```

