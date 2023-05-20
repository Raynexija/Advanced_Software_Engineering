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

    public static String[] availableOptions = {"Add Creature", "Add Character", "Remove Creature", "Remove Character", "Back"};

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
            case "Add Creature" -> {
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
            case "Add Character" -> {
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
