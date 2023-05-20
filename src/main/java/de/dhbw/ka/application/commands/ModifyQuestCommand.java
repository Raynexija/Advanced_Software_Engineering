package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.campaign.quests.Quest;
import de.dhbw.ka.domain.character.Character;

import java.util.Arrays;
import java.util.List;

public class ModifyQuestCommand extends Command {
    private final Quest quest;
    private final List<Encounter> encounters;
    private final List<Character> characters;

    public ModifyQuestCommand(Quest currentQuest, List<Encounter> encounters, List<Character> characters) {
        this.quest = currentQuest;
        this.encounters = encounters;
        this.characters = characters;
    }

    public static String help() {
        return """
                Modifies the current quest.
                Usage: modify_quest
                You then have to select the stat you want to modify.""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        String[] options = new String[]{"Add reward", "Remove reward", "Assign character", "Remove character", "Add encounter", "Remove encounter", "Back"};
        String selection = input.requestSelection("What do you want to modify?", options);

        switch (selection) {
            case "Add reward" -> {
                String newReward = input.requestString("Enter new reward:");
                quest.addReward(newReward);
            }
            case "Remove reward" -> {
                String rewardToRemove = input.requestString("Enter reward to remove:");
                quest.removeReward(rewardToRemove);
            }
            case "Assign character" -> {
                String[] availableCharacters = characters.stream().map(Character::getName).toArray(String[]::new);
                if (availableCharacters.length == 0) {
                    output.displayMessage("No characters available.");
                    options = Arrays.stream(options).filter(option -> !option.equals("Assign character")).toArray(String[]::new);
                    return;
                }
                String characterToAdd = input.requestSelection("Which character do you want to assign?:", availableCharacters);
                quest.assignCharacter(characters.stream().filter(character -> character.getName().equals(characterToAdd)).findFirst().orElseThrow());
            }
            case "Remove character" -> {
                String[] availableCharacters = quest.assignedCharacters().stream().map(Character::getName).toArray(String[]::new);
                if (availableCharacters.length == 0) {
                    output.displayMessage("No characters assigned to the quest.");
                    options = Arrays.stream(options).filter(option -> !option.equals("Remove character")).toArray(String[]::new);
                    return;
                }
                String characterToRemove = input.requestSelection("Which character do you want to remove?:", availableCharacters);
                quest.removeCharacter(characters.stream().filter(character -> character.getName().equals(characterToRemove)).findFirst().orElseThrow());
            }
            case "Add encounter" -> {
                String[] availableEncounters = encounters.stream().map(Encounter::getName).toArray(String[]::new);
                if (availableEncounters.length == 0) {
                    output.displayMessage("No encounters available to add.");
                    options = Arrays.stream(options).filter(option -> !option.equals("Add encounter")).toArray(String[]::new);
                    return;
                }
                String encounterToAdd = input.requestSelection("Which encounter do you want to add?:", availableEncounters);
                quest.addEncounter(encounters.stream().filter(encounter -> encounter.getName().equals(encounterToAdd)).findFirst().orElseThrow());
            }
            case "Remove encounter" -> {
                String[] availableEncounters = quest.encounters().stream().map(Encounter::getName).toArray(String[]::new);
                if (availableEncounters.length == 0) {
                    output.displayMessage("No encounters assigned to the quest.");
                    options = Arrays.stream(options).filter(option -> !option.equals("Remove encounter")).toArray(String[]::new);
                    return;
                }
                String encounterToRemove = input.requestSelection("Which encounter do you want to remove?:", availableEncounters);
                quest.removeEncounter(encounters.stream().filter(encounter -> encounter.getName().equals(encounterToRemove)).findFirst().orElseThrow());
            }
            case "Back" -> output.displayMessage("Going back.");
        }
    }
}
