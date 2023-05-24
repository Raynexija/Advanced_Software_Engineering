package de.dhbw.ka.test;

import de.dhbw.ka.adapter.textBased.TextBasedInputAdapter;
import de.dhbw.ka.adapter.textBased.TextBasedOutputAdapter;
import de.dhbw.ka.application.commands.Command;
import de.dhbw.ka.application.CommandController;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import de.dhbw.ka.plugins.inputOutput.ConsoleOutputInputSystem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CommandControllerTest {
    private CommandController controller;

    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();

    private ConsoleOutputInputSystem console = new ConsoleOutputInputSystem(outputStream, System.in);
    private InputService inputService = new TextBasedInputAdapter(console);
    private OutputService outputService = new TextBasedOutputAdapter(console);


    @BeforeEach
    void setUp() {
        controller = new CommandController(inputService, outputService);
    }

    @Test
    void testEnqueueCommand() {
        Command command = new Command() {
            @Override
            public void execute(InputService input, OutputService output) {
                output.displayMessage("Test");
            }
        };
        controller.enqueueCommand(command);
        Queue<Command> commandQueue = controller.getCommandQueue();
        assertEquals(1, commandQueue.size());
        assertEquals(command, commandQueue.peek());
    }
}
