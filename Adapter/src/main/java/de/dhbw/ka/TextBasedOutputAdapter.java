package de.dhbw.ka;

import de.dhbw.ka.interfaces.OutputService;

public class TextBasedOutputAdapter implements OutputService {
    private final TextBasedOutputInputSystem textBasedOutputInputSystem;

    public TextBasedOutputAdapter(TextBasedOutputInputSystem textBasedOutputInputSystem) {
        this.textBasedOutputInputSystem = textBasedOutputInputSystem;
    }

    @Override
    public void displayError(String errorMessage) {
        textBasedOutputInputSystem.printError(errorMessage);
    }

    @Override
    public void displayWarning(String warningMessage) {
        textBasedOutputInputSystem.printWarning(warningMessage);
    }

    @Override
    public void displayMessage(String message) {
        textBasedOutputInputSystem.printList(new String[]{message});
    }
}