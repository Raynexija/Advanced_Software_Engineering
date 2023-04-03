package de.dhbw.ka.inputOutput;

public interface OutputInputSystem {
    void printWarning(String warning);
    void printError(String error);
    <T> T requestTextSelection(String request, T[] options);
    String requestText(String request);
    int requestNumber(String request);
}
