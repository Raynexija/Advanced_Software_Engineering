package de.dhbw.ka.commands;

import de.dhbw.ka.interfaces.InputService;
import de.dhbw.ka.interfaces.OutputService;

public abstract class Command {
    public abstract void execute(InputService input, OutputService output);

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