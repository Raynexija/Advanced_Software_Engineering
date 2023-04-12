package de.dhbw.ka.inputOutput;

public interface OutputInputSystem {
    void printWarning(String warning);
    void printError(String error);
    void printList(String[] list);
    <T> T requestTextSelection(String request, T[] options);
    String requestText(String request);
    int requestNumber(String request);
    boolean requestConfirmation(String request);
}
