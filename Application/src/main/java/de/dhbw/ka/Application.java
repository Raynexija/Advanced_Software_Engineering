package de.dhbw.ka;

import de.dhbw.ka.commands.CharacterRollCommand;
import de.dhbw.ka.commands.CreateCharacterCommand;
import de.dhbw.ka.commands.DiceRollCommand;
import de.dhbw.ka.commands.ExitCommand;
import de.dhbw.ka.inputOutput.ConsoleOutputInputSystem;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;
import de.dhbw.ka.interfaces.RandomNumberService;
import de.dhbw.ka.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.rng.RandomNumberGeneratorImplementation;

import java.util.ArrayList;
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

    public void main() {

        while (true) {
            String command = input.requestString("Enter a command: ").toLowerCase();

            if (command.equals("exit")) {
                controller.enqueueCommand(new ExitCommand());
            } else if (command.startsWith("roll") && !characterSelected) {
                if (command.contains("-help")) {
                    output.displayMessage(DiceRollCommand.help());
                    continue;
                }
                controller.enqueueCommand(new DiceRollCommand(dice, command));
            } else if (command.equals("roll") && characterSelected) {
                if (command.contains("-help")) {
                    output.displayMessage(CharacterRollCommand.help());
                    continue;
                }
                output.displayMessage("Rolling for " + currentCharacter.getName());
                controller.enqueueCommand(new CharacterRollCommand(dice, command, currentCharacter));
            } else if (command.equals("create_character") || command.equals("cc")) {
                controller.enqueueCommand(new CreateCharacterCommand(characters));
            } else if (command.equals("list_characters")) {
                for (Character character : characters) {
                    output.displayMessage(character.toString());
                }
            } else if (command.startsWith("select_character")) {
                String[] parameter = command.split(" ");
                if (parameter.length != 2) {
                    output.displayError("Invalid command");
                    continue;
                }
                int index = Integer.parseInt(parameter[1]);
                if (index < 0 || index >= characters.size()) {
                    output.displayError("Invalid index");
                    continue;
                }
                currentCharacter = characters.get(index);
                characterSelected = true;
                output.displayMessage("Selected character: " + currentCharacter.getName());
            } else if (command.equals("selected_character")) {
                if (characterSelected) {
                    output.displayMessage(currentCharacter.toString());
                } else {
                    output.displayError("No character selected");
                }
            } else if (command.equals("delete_character")) {
                if (characterSelected) {
                    characters.remove(currentCharacter);
                    characterSelected = false;
                    output.displayMessage("Deleted character");
                } else {
                    output.displayError("No character selected");
                }
            } else if (command.equals("deselect_character")) {
                if (characterSelected) {
                    characterSelected = false;
                    output.displayMessage("Deselected character");
                } else {
                    output.displayError("No character selected");
                }
            } else if (command.equals("help")) {
                output.displayMessage("Available commands: ");
                output.displayMessage("roll <count> <diceType> [-s]");
                output.displayMessage("create_character");
                output.displayMessage("list_characters");
                output.displayMessage("select_character <index>");
                output.displayMessage("selected_character");
                output.displayMessage("delete_character");
                output.displayMessage("exit");
            } else {
                output.displayError("Unknown command");
            }
            controller.executeCommands();
        }
    }
}