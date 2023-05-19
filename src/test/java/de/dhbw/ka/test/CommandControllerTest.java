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

import java.util.HashSet;
import java.util.Queue;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.*;

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

// I don't know why, but this took me like 1 hour, and is still not completely working.
//    @Test
//    void testExecuteCommands() throws Exception {
//        Command command1 = mock(Command.class);
//        Command command2 = mock(Command.class);
//        Command command3 = mock(Command.class);
////        // since we are mocking an interface with an abstract method
////        // we need to provide an implementation for it
////        // otherwise we are not able to check, whether the command was executed
////        doAnswer(invocation -> {
////            Object[] args = invocation.getArguments();
////            ((Command) args[0]).execute((InputService) args[1], (OutputService) args[2], (CharacterBuilder) args[3]);
////            return null;
////        }).when(currentState).executeCommand(any(Command.class), any(InputService.class), any(OutputService.class), any(CharacterBuilder.class));
//        when(currentState.getAvailableCommands()).thenReturn(new HashSet<Command>() {{
//            add(command1);
//            add(command2);
//        }});
//        controller.setCurrentState(currentState);
//        controller.enqueueCommand(command1);
//        controller.enqueueCommand(command2);
//        controller.enqueueCommand(command3);
//        controller.executeCommands();
//
//
//        assertTrue(controller.getCommandQueue().isEmpty());
//        verify(currentState).executeCommand(command1, inputService, outputService, characterBuilder);
//        verify(currentState).executeCommand(command2, inputService, outputService, characterBuilder);
//        verify(currentState, never()).executeCommand(command3, inputService, outputService, characterBuilder);
//    }

    @Test
    void testExecuteCommandsException() {
        Command command = Mockito.mock(Command.class);
       // when(currentState.getAvailableCommands()).thenReturn(new HashSet<>() {{
        //    add(command);
      //  }});
      //  doThrow(new Exception()).when(currentState).executeCommand(command, inputService, outputService);
        controller.enqueueCommand(command);
        controller.executeCommands();
        assertFalse(controller.getCommandQueue().isEmpty());
        verify(outputService).displayError(any());
    }

}
