package de.dhbw.ka.test;

import de.dhbw.ka.application.commands.Command;
import de.dhbw.ka.application.CommandController;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Queue;

import static org.junit.jupiter.api.Assertions.*;

public class CommandControllerTest {
    private CommandController controller;
    @Mock
    private InputService inputService;
    @Mock
    private OutputService outputService;



    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        controller = new CommandController(inputService, outputService);
    }

    @Test
    void testEnqueueCommand() {
        Command command = Mockito.mock(Command.class);
        controller.enqueueCommand(command);
        Queue<Command> commandQueue = controller.getCommandQueue();
        assertEquals(1, commandQueue.size());
        assertEquals(command, commandQueue.peek());
    }
}
