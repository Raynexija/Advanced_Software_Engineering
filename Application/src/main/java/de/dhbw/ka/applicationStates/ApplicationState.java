package de.dhbw.ka.applicationStates;

import de.dhbw.ka.CharacterBuilder;
import de.dhbw.ka.InputService;
import de.dhbw.ka.OutputService;
import de.dhbw.ka.commands.Command;

import java.util.Set;

public interface ApplicationState {
    Set<Command> getAvailableCommands();

    void executeCommand(Command command, InputService inputService, OutputService outputService, CharacterBuilder characterBuilder) throws Exception;
}
