package de.dhbw.ka;

import de.dhbw.ka.applicationStates.ApplicationState;
import de.dhbw.ka.applicationStates.ApplicationStates;
import de.dhbw.ka.application.commands.Command;
import de.dhbw.ka.application.commands.ExitCommand;
import de.dhbw.ka.application.commands.RequestIntFromUserCommand;
import de.dhbw.ka.application.commands.RequestStringFromUserCommand;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;


public class ApplicationStatesTest {
    @Mock
    private OutputService outputService;
    @Mock
    private InputService inputService;


    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCharacterCreationStateReturnsCorrectAvailableCommands() {
        ApplicationState state = ApplicationStates.CHARACTER_CREATION;
        Set<Command> expectedCommands = Set.of(
                new RequestStringFromUserCommand(),
                new RequestIntFromUserCommand()
        );
        Set<Command> actualCommands = state.getAvailableCommands();
        assertEquals(expectedCommands, actualCommands);
    }

    @Test
    void testExitStateReturnsCorrectAvailableCommands() {
        ApplicationState state = ApplicationStates.EXIT;
        Set<Command> expectedCommands = Set.of(new ExitCommand());
        Set<Command> actualCommands = state.getAvailableCommands();
        assertEquals(expectedCommands, actualCommands);
    }

    @Test
    void testExecuteCommandThrowsExceptionForInvalidCommand() {
        ApplicationState state = ApplicationStates.CHARACTER_CREATION;
        Command invalidCommand = new ExitCommand();
        assertThrows(IllegalArgumentException.class, () -> state.executeCommand(invalidCommand, inputService,outputService));
    }

    @Test
    void testExecuteCommandDoesNotThrowExceptionForValidCommand() {
        ApplicationState state = ApplicationStates.CHARACTER_CREATION;
        Command validCommand = new RequestStringFromUserCommand();
        assertDoesNotThrow(() -> state.executeCommand(validCommand,inputService,outputService));
    }
}