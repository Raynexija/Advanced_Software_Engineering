package de.dhbw.ka.exception;

public class BaseAbilityScoreOutOfBoundsException extends IllegalArgumentException{
    public BaseAbilityScoreOutOfBoundsException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public BaseAbilityScoreOutOfBoundsException(String errorMessage) {
        super(errorMessage);
    }
}
