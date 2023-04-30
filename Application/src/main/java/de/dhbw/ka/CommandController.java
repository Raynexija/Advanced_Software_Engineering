package de.dhbw.ka;

import de.dhbw.ka.applicationStates.ApplicationState;
import de.dhbw.ka.applicationStates.ApplicationStates;
import de.dhbw.ka.commands.Command;
import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;

import java.util.LinkedList;
import java.util.Queue;

public class CommandController {
    private final InputService inputService;
    private final OutputService outputService;
    private ApplicationState currentState;
    private final Queue<Command> commandQueue;


    public CommandController(InputService inputService, OutputService outputService) {
        this.inputService = inputService;
        this.outputService = outputService;

        this.currentState = ApplicationStates.EXIT;
        this.commandQueue = new LinkedList<>();
    }

    public void enqueueCommand(Command command) {
        commandQueue.offer(command);
    }

    public Queue<Command> getCommandQueue() {
        return commandQueue;
    }

    public void executeCommands() {
        while (!commandQueue.isEmpty()) {
            Command currentCommand = commandQueue.peek();
            try {
                // currentState.executeCommand(currentCommand, inputService, outputService);
                currentCommand.execute(inputService, outputService);
                commandQueue.poll();
            } catch (Exception e) {
                outputService.displayError(e.getMessage());
                break;
            }
        }
    }

    public ApplicationState getCurrentState() {
        return currentState;
    }

    public void setCurrentState(ApplicationState currentState) {
        this.currentState = currentState;
    }
}
