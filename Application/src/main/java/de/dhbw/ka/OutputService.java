package de.dhbw.ka;

public interface OutputService {
    void displayError(String errorMessage);
    void displayWarning(String warningMessage);
    void displayMessage(String message);
}