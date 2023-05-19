package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;

public class RequestStringFromUserCommand extends Command {
    @Override
    public void execute(InputService inputService, OutputService outputService) {
        System.out.println("EnterStringCommand executed");

    }
}
