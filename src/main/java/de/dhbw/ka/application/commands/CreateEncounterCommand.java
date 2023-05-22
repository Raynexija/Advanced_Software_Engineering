package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.encounter.Creature;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.character.Character;

import java.util.List;

public class CreateEncounterCommand extends Command {
    private final List<Encounter> encounters;
    private final List<Character> characters;

    public CreateEncounterCommand(List<Encounter> encounters, List<Character> characters) {
        this.encounters = encounters;
        this.characters = characters;
    }

    public static String help() {
        return """
                Creates an encounter.
                Usage: create encounter""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        Encounter encounter = new Encounter(input.requestString("What is the name of the encounter?"));

        creatureAddition(input, encounter);
        characterAddition(input, output, encounter);

        encounters.add(encounter);
        output.displayMessage("Encounter: " + encounter.getName() + " created.");
    }

    private void creatureAddition(InputService input, Encounter encounter) {
        boolean createCreatures = true;
        while (createCreatures) {
            switch (input.requestSelection("Do you want to add a creature?", new String[]{"Yes", "No"})) {
                case "Yes" -> {
                    createAndAddCreatureToEncounter(input, encounter);
                }
                case "No" -> createCreatures = false;
            }
        }
    }

    private void characterAddition(InputService input, OutputService output, Encounter encounter) {
        boolean addCharacters = true;
        while (addCharacters) {
            switch (input.requestSelection("Do you want to add a character?", new String[]{"Yes", "No"})) {
                case "Yes" -> {
                    addCharacterToEncounter(input, output, characters, encounter);
                }
                case "No" -> addCharacters = false;
            }
        }
    }

    static void createAndAddCreatureToEncounter(InputService input, Encounter encounter) {
        String name = input.requestString("What is the name of the creature?");
        int maxHitPoints = input.requestInt("What is the max hit points of the creature?");
        int armorClass = input.requestInt("What is the armor class of the creature?");
        int initiative = input.requestInt("What is the initiative modifier of the creature?");
        int strength = input.requestInt("What is the strength of the creature?");
        int dexterity = input.requestInt("What is the dexterity of the creature?");
        int constitution = input.requestInt("What is the constitution of the creature?");
        int intelligence = input.requestInt("What is the intelligence of the creature?");
        int wisdom = input.requestInt("What is the wisdom of the creature?");
        int charisma = input.requestInt("What is the charisma of the creature?");

        encounter.addCreature(new Creature(name, armorClass, maxHitPoints, initiative, strength, dexterity, constitution, intelligence, wisdom, charisma));
    }

    static void addCharacterToEncounter(InputService input, OutputService output, List<Character> characters, Encounter encounter) {
        String[] remainingCharacters = characters.stream()
                .filter(character -> !encounter.getPlayerCharacters().contains(character))
                .map(Character::getName)
                .toArray(String[]::new);
        if (remainingCharacters.length == 0) {
            output.displayMessage("There are no characters left to add.");
            return;
        }
        String name = input.requestSelection("Which character do you want to add?", remainingCharacters);
        encounter.addPlayerCharacter(characters.stream().filter(character -> character.getName().equals(name)).findFirst().orElse(null));
    }
}
