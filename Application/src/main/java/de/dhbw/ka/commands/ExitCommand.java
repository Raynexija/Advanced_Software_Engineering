package de.dhbw.ka.commands;

import de.dhbw.ka.CharacterBuilder;
import de.dhbw.ka.InputService;
import de.dhbw.ka.OutputService;

public class ExitCommand extends Command {
    @Override
    public void execute(InputService inputService, OutputService outputService, CharacterBuilder characterBuilder) {
        System.out.println("ExitCommand executed");

    }
}
