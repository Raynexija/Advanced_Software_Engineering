package de.dhbw.ka;

public class TextBasedInputAdapter implements InputService{
    private final TextBasedOutputInputSystem textBasedOutputInputSystem;

    public TextBasedInputAdapter(TextBasedOutputInputSystem textBasedOutputInputSystem) {
        this.textBasedOutputInputSystem = textBasedOutputInputSystem;
    }

    @Override
    public String requestString(String prompt) {
        return textBasedOutputInputSystem.requestText(prompt);
    }

    @Override
    public int requestInt(String prompt) {
        return textBasedOutputInputSystem.requestNumber(prompt);
    }

    @Override
    public double requestDouble(String prompt) {
        return textBasedOutputInputSystem.requestNumber(prompt);
    }

    @Override
    public boolean requestBoolean(String prompt) {
        return textBasedOutputInputSystem.requestConfirmation(prompt);
    }

    @Override
    public String requestSelection(String prompt, String[] options) {
        return textBasedOutputInputSystem.requestTextSelection(prompt, options);
    }
}