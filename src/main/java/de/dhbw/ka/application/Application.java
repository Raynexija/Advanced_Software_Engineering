package de.dhbw.ka.application;

import de.dhbw.ka.adapter.textBased.TextBasedInputAdapter;
import de.dhbw.ka.adapter.textBased.TextBasedOutputAdapter;
import de.dhbw.ka.adapter.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.application.commands.*;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.application.interfaces.RandomNumberService;
import de.dhbw.ka.domain.character.CreateCharacter;
import de.dhbw.ka.domain.character.Character;
import de.dhbw.ka.domain.character.characterClasses.Fighter;
import de.dhbw.ka.domain.character.races.Human;
import de.dhbw.ka.plugins.inputOutput.ConsoleOutputInputSystem;
import de.dhbw.ka.plugins.rng.RandomNumberGeneratorImplementation;

import java.util.ArrayList;
import java.util.List;

public class Application {

    private final InputService input = new TextBasedInputAdapter(new ConsoleOutputInputSystem());
    private final OutputService output = new TextBasedOutputAdapter(new ConsoleOutputInputSystem());
    private final RandomNumberService randomNumberGenerator = new RandomNumberGeneratorAdapter(new RandomNumberGeneratorImplementation());

    private final Dice dice = new Dice(randomNumberGenerator);
    private final CommandController controller = new CommandController(input, output);

    private final List<Character> characters = new ArrayList<>();
    private Character currentCharacter;
    private boolean characterSelected = false;

    private final Character testChar = CreateCharacter.named("Test")
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

        //noinspection InfiniteLoopStatement
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
                case "select_character", "select" -> selectCharacter(command);
                case "selected_character", "current", "curr" -> selectedCharacter();
                case "delete_character" -> deleteCharacter();
                case "deselect_character", "deselect" -> deselectCharacter();
                case "modify_character", "modify" -> modifyCharacter(command);
                case "get_Stat", "get" -> singleStatCommand(command);
                default -> output.displayError("Unknown command");
            }
            controller.executeCommands();
        }
    }

    private void singleStatCommand(String command) {
        if (checkCharacterSelected()) {
            if (command.contains("-help")) {
                output.displayMessage(GetSingleCharacterStatCommand.help());
                return;
            }
            controller.enqueueCommand(new GetSingleCharacterStatCommand(command, currentCharacter));
        }
    }

    private boolean checkCharacterSelected() {
        if (!characterSelected) {
            output.displayError("No character selected");
            return false;
        }
        return true;
    }

    private void modifyCharacter(String command) {
        if (checkCharacterSelected()) {
            if (command.contains("-help")) {
                output.displayMessage(ModifyCharacterCommand.help());
                return;
            }
            controller.enqueueCommand(new ModifyCharacterCommand(command, currentCharacter));
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
        if (command.split(" ").length > 1 && command.split(" ")[1].matches("\\d+")) {
            rollWithoutSelection(command);
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
        if (checkCharacterSelected()) {
            output.displayMessage(currentCharacter.toString());
        }
    }

    private void selectCharacter(String command) {
        String name = command.split(" ")[1];
        currentCharacter = characters.stream().filter(character -> character.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
        if (currentCharacter == null) {
            output.displayError("Character: \"" + name + "\" not found");
            return;
        }
        characterSelected = true;
        output.displayMessage("Selected character: " + currentCharacter.getName());
    }

    private void exit() {
        controller.enqueueCommand(new ExitCommand());
    }

    private void deselectCharacter() {
        if (checkCharacterSelected()) {
            characterSelected = false;
            output.displayMessage("Deselected character");
        }
    }

    private void deleteCharacter() {
        if (checkCharacterSelected()) {
            characters.remove(currentCharacter);
            characterSelected = false;
            output.displayMessage("Deleted character");
        }
    }
}