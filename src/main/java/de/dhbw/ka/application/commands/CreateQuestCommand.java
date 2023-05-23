package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.campaign.quests.Quest;
import de.dhbw.ka.domain.character.Character;

import java.util.Arrays;
import java.util.List;

public class CreateQuestCommand extends Command {
    private final List<Quest> quests;
    private final List<Encounter> encounters;
    private final List<Character> characters;


    public CreateQuestCommand(List<Quest> quests, List<Encounter> encounters, List<Character> characters) {
        this.quests = quests;
        this.encounters = encounters;
        this.characters = characters;
    }

    public static String help() {
        return """
                Creates a new quest.
                Usage: create_quest""";
    }


    @Override
    public void execute(InputService input, OutputService output) {
        String name = input.requestString("What is the name of the quest?");
        String description = input.requestString("What is the description of the quest?");
        Quest quest = new Quest(name, description);
        continuesAdditionToQuest(input, output, quest);
        output.displayMessage("You created a quest with the name " + name + " and the description " + description + ".");
        quests.add(quest);
    }

    private void continuesAdditionToQuest(InputService input, OutputService output, Quest quest) {
        boolean stillCreating = true;
        while (stillCreating) {
            String[] availableOptions = new String[]{"Add an Encounter", "Add a Character", "Add a Reward", "Finish"};
            String answer = input.requestSelection("What do you want do to?", availableOptions);
            switch (answer) {
                case "Add an Encounter" -> {
                    String[] availableEncounters = encounters.stream().filter(e -> !quest.encounters().contains(e)).map(Encounter::getName).toArray(String[]::new);
                    if (availableEncounters.length == 0) {
                        output.displayMessage("There are no encounters available.");
                        availableOptions = Arrays.stream(availableOptions).filter(s -> !s.equals("Add an Encounter")).toArray(String[]::new);
                        break;
                    }
                    String encounterName = input.requestSelection("Which encounter do you want to add?", availableEncounters);
                    Encounter encounter = encounters.stream().filter(e -> e.getName().equals(encounterName)).findFirst().orElse(null);
                    quest.addEncounter(encounter);
                }
                case "Add a Character" -> {
                    String[] availableCharacters = characters.stream().filter(c -> !quest.assignedCharacters().contains(c)).map(Character::getName).toArray(String[]::new);
                    if (availableCharacters.length == 0) {
                        output.displayMessage("There are no characters available.");
                        availableOptions = Arrays.stream(availableOptions).filter(s -> !s.equals("Add a Character")).toArray(String[]::new);
                        break;
                    }
                    String characterName = input.requestSelection("Which character do you want to add?", availableCharacters);
                    Character character = characters.stream().filter(c -> c.getName().equals(characterName)).findFirst().orElse(null);
                    quest.assignCharacter(character);
                }
                case "Add a Reward" -> {
                    String reward = input.requestString("What is the reward you want to add?");
                    quest.addReward(reward);
                }
                case "Finish" -> stillCreating = false;
            }
        }
    }
}
