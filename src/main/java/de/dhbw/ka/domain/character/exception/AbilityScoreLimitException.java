package de.dhbw.ka.domain.character.exception;

public class AbilityScoreLimitException extends IllegalArgumentException {
    public AbilityScoreLimitException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }

    public AbilityScoreLimitException(String errorMessage) {
        super(errorMessage);
    }
}
