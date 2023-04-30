package de.dhbw.ka;

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

    public void main() {

        while (true) {
            String command = input.requestString("Enter a command: ").toLowerCase();

            if (command.equals("exit")) {
                controller.enqueueCommand(new ExitCommand());
            } else if (command.startsWith("roll")) {
                if (command.contains("-help")) {
                    output.displayMessage(DiceRollCommand.help());
                    continue;
                }
                controller.enqueueCommand(new DiceRollCommand(dice, command));
            } else if (command.equals("create_character")) {
                controller.enqueueCommand(new CreateCharacterCommand(characters));
            }
            else if (command.equals("list_characters")) {
                for (Character character : characters) {
                    output.displayMessage(character.toString());
                }
            }

            else {
                output.displayError("Unknown command");
            }
            controller.executeCommands();
        }
    }
}