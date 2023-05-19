package de.dhbw.ka;

import de.dhbw.ka.characterClasses.Fighter;
import de.dhbw.ka.commands.CharacterRollCommand;
import de.dhbw.ka.commands.CreateCharacterCommand;
import de.dhbw.ka.commands.DiceRollCommand;
import de.dhbw.ka.commands.ExitCommand;
import de.dhbw.ka.inputOutput.ConsoleOutputInputSystem;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;
import de.dhbw.ka.interfaces.RandomNumberService;
import de.dhbw.ka.races.Human;
import de.dhbw.ka.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.rng.RandomNumberGeneratorImplementation;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Application {

    private final InputService input = new TextBasedInputAdapter(new ConsoleOutputInputSystem());
    private final OutputService output = new TextBasedOutputAdapter(new ConsoleOutputInputSystem());
    private final RandomNumberService randomNumberGenerator = new RandomNumberGeneratorAdapter(new RandomNumberGeneratorImplementation());

    private final Dice dice = new Dice(randomNumberGenerator);
    private final CommandController controller = new CommandController(input, output);

    private List<Character> characters = new ArrayList<>();
    private Character currentCharacter;
    private boolean characterSelected = false;
    private Character testChar = CreateCharacter.named("Test")
            .ofRace(new Human())
            .isA(new Fighter())
            .withStrength(15)
            .withDexterity(14)
            .withConstitution(13)
            .withIntelligence(12)
            .withWisdom(10)
            .withCharisma(8)
            .maxHitPoints(10)
            .currentLevel(1)
            .speaking(new String[]{"Celestial", "Elvish"})
            .equippedWith(new String[]{"Leather Armor", "Longsword", "Dagger"})
            .build();

    public void main() {
        characters.add(testChar);

        while (true) {
            String command = input.requestString("Enter a command: ").toLowerCase();

            switch (command.split(" ")[0]) {
                case "exit" -> exit();
                case "roll" -> {
                    if (characterSelected) rollWithCharacterSelected(command);
                    else rollWithoutSelection(command);
                }
                case "create_character", "cc" -> createCharacter();
                case "list_characters", "list" -> listCharacters();
                case "select_character" -> selectCharacter(command);
                case "selected_character", "current", "curr" -> selectedCharacter();
                case "delete_character" -> deleteCharacter();
                case "deselect_character" -> deselectCharacter();
                default -> output.displayError("Unknown command");
            }
            controller.executeCommands();
        }
    }

    private void createCharacter() {
        controller.enqueueCommand(new CreateCharacterCommand(characters));
    }

    private void rollWithoutSelection(String command) {
        if (command.contains("-help")) {
            output.displayMessage(DiceRollCommand.help());
            return;
        }
        controller.enqueueCommand(new DiceRollCommand(dice, command));
    }

    private void rollWithCharacterSelected(String command) {
        if (command.contains("-help")) {
            output.displayMessage(CharacterRollCommand.help());
            return;
        }
        output.displayMessage("Rolling for " + currentCharacter.getName());
        controller.enqueueCommand(new CharacterRollCommand(dice, command, currentCharacter));
    }

    private void listCharacters() {
        for (Character character : characters) {
            output.displayMessage(character.toString());
        }
    }

    private void selectedCharacter() {
        if (characterSelected) {
            output.displayMessage(currentCharacter.toString());
        } else {
            output.displayError("No character selected");
        }
    }

    private void selectCharacter(String command) {
        String[] parameter = command.split(" ");
        if (parameter.length != 2) {
            output.displayError("Invalid command");
            return;
        }
        String name = parameter[1];
        currentCharacter = characters.stream().filter(character -> character.getName().equalsIgnoreCase(name)).findFirst().orElse(null);

        if (currentCharacter == null) {
            output.displayError("Character not found");
            return;
        }
        characterSelected = true;
        output.displayMessage("Selected character: " + currentCharacter.getName());
    }

    private void exit() {
        controller.enqueueCommand(new ExitCommand());
    }

    private void deselectCharacter() {
        if (characterSelected) {
            characterSelected = false;
            output.displayMessage("Deselected character");
        } else {
            output.displayError("No character selected");
        }
    }

    private void deleteCharacter() {
        if (characterSelected) {
            characters.remove(currentCharacter);
            characterSelected = false;
            output.displayMessage("Deleted character");
        } else {
            output.displayError("No character selected");
        }
    }
}