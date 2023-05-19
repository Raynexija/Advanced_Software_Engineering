package de.dhbw.ka.application.interfaces;

public interface OutputService {
    void displayError(String errorMessage);
    void displayWarning(String warningMessage);
    void displayMessage(String message);
}