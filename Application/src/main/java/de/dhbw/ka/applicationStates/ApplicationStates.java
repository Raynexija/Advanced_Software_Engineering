package de.dhbw.ka.applicationStates;

import de.dhbw.ka.CharacterBuilder;
import de.dhbw.ka.InputService;
import de.dhbw.ka.OutputService;
import de.dhbw.ka.commands.Command;
import de.dhbw.ka.commands.ExitCommand;
import de.dhbw.ka.commands.RequestIntFromUserCommand;
import de.dhbw.ka.commands.RequestStringFromUserCommand;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public enum ApplicationStates implements ApplicationState {
    START(),
    CHARACTER_CREATION(
            new RequestStringFromUserCommand(),
            new RequestIntFromUserCommand()
    ),
    EXIT(new ExitCommand()),
    GAME();


    private final HashSet<Command> commands = new HashSet<>();

    ApplicationStates(Command... commands) {
        this.commands.addAll(Arrays.asList(commands));
    }

    @Override
    public Set<Command> getAvailableCommands() {
        return this.commands;
    }

    @Override
    public void executeCommand(Command command,
                               InputService inputService,
                               OutputService outputService,
                               CharacterBuilder characterBuilder) throws IllegalArgumentException {
        if (this.commands.contains(command))
            command.execute(inputService, outputService, characterBuilder);
        else {
            throw new IllegalArgumentException("Command " + command + " not available in state " + this);
        }
    }

}