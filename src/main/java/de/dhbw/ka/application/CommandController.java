package de.dhbw.ka.application;

import de.dhbw.ka.application.commands.Command;
import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;

import java.util.LinkedList;
import java.util.Queue;

public class CommandController {
    private final InputService inputService;
    private final OutputService outputService;
    private final Queue<Command> commandQueue;


    public CommandController(InputService inputService, OutputService outputService) {
        this.inputService = inputService;
        this.outputService = outputService;

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
}
