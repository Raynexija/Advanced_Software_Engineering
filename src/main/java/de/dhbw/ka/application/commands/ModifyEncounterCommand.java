package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.domain.campaign.encounter.Creature;
import de.dhbw.ka.domain.campaign.encounter.Encounter;
import de.dhbw.ka.domain.character.Character;

import java.util.List;

public class ModifyEncounterCommand extends Command {
    private final Encounter encounter;
    private final List<Character> characters;

    public static final String[] availableOptions = {"Add Creature", "Add Character", "Remove Creature", "Remove Character", "Back"};

    public ModifyEncounterCommand(Encounter encounter, List<Character> characters) {
        this.encounter = encounter;
        this.characters = characters;
    }

    public static String help() {
        return """
                Modifies an encounter.
                Usage: modify_encounter
                You then have to select the stat you want to modify.""";
    }

    @Override
    public void execute(InputService input, OutputService output) {
        String toModify = input.requestSelection("What do you want to do?", availableOptions);

        switch (toModify) {
            case "Add Creature" -> CreateEncounterCommand.createAndAddCreatureToEncounter(input, encounter);
            case "Add Character" -> CreateEncounterCommand.addCharacterToEncounter(input, output, characters, encounter);
            case "Remove Creature" -> {
                String[] remainingCreatures = encounter.getCreatures().stream()
                        .map(Creature::name)
                        .toArray(String[]::new);
                if (remainingCreatures.length == 0) {
                    output.displayMessage("There are no creatures left to remove.");
                    return;
                }
                String name = input.requestSelection("Which creature do you want to remove?", remainingCreatures);
                encounter.removeCreature(encounter.getCreatures().stream().filter(creature -> creature.name().equals(name)).findFirst().orElse(null));
            }
            case "Remove Character" -> {
                String[] remainingCharacters = encounter.getPlayerCharacters().stream()
                        .map(Character::getName)
                        .toArray(String[]::new);
                if (remainingCharacters.length == 0) {
                    output.displayMessage("There are no characters left to remove.");
                    return;
                }
                String name = input.requestSelection("Which character do you want to remove?", remainingCharacters);
                encounter.removePlayerCharacter(encounter.getPlayerCharacters().stream().filter(character -> character.getName().equals(name)).findFirst().orElse(null));
            }
            default -> output.displayMessage("Nothing was modified.");
        }
    }
}
