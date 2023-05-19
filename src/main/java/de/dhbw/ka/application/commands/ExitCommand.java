package de.dhbw.ka.application.commands;

import de.dhbw.ka.application.interfaces.InputService;
import de.dhbw.ka.application.interfaces.OutputService;

public class ExitCommand extends Command {
    @Override
    public void execute(InputService inputService, OutputService outputService) {
        System.out.println("ExitCommand executed");
        System.exit(0);
    }
}
