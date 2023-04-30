package de.dhbw.ka.commands;


import de.dhbw.ka.CharacterBuilder;
import de.dhbw.ka.InputService;
import de.dhbw.ka.OutputService;

public abstract class Command {
    public abstract void execute(InputService inputService, OutputService outputService, CharacterBuilder characterBuilder);

    // Override equals and hashCode to make sure that commands
    // are only equal if they are of the same class
    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Command)) return false;
        return getClass().equals(o.getClass());
    }

    @Override
    public final int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return getClass().getSimpleName();
    }
}