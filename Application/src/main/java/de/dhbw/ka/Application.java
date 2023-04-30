package de.dhbw.ka;

import de.dhbw.ka.inputOutput.ConsoleOutputInputSystem;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;
import de.dhbw.ka.interfaces.RandomNumberService;
import de.dhbw.ka.rng.RandomNumberGeneratorAdapter;
import de.dhbw.ka.rng.RandomNumberGeneratorImplementation;

public class Application {

    private InputService input = new TextBasedInputAdapter(new ConsoleOutputInputSystem());
    private OutputService output = new TextBasedOutputAdapter(new ConsoleOutputInputSystem());
    private RandomNumberService randomNumberGenerator = new RandomNumberGeneratorAdapter(new RandomNumberGeneratorImplementation());

    private Dice dice = new Dice(randomNumberGenerator);

    private CommandController controller = new CommandController(input, output);

    public void main() {
        int tmp = input.requestInt("Enter a number: ");

        output.displayMessage("Random number between 1 and " + tmp + " : " + dice.roll(tmp));

    }
}