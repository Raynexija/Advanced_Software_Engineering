package de.dhbw.ka.commands;

import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;

public class RequestStringFromUserCommand extends Command {
    @Override
    public void execute(InputService inputService, OutputService outputService) {
        System.out.println("EnterStringCommand executed");

    }
}
