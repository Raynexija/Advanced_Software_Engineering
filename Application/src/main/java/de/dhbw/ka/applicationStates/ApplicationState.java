package de.dhbw.ka.applicationStates;

import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;
import de.dhbw.ka.commands.Command;

import java.util.Set;

public interface ApplicationState {
    Set<Command> getAvailableCommands();

    void executeCommand(Command command, InputService inputService, OutputService outputService) throws Exception;
}
