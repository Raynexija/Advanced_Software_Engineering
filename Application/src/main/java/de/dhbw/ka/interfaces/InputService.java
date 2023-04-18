package de.dhbw.ka.interfaces;

public interface InputService {
    String requestString(String prompt);
    int requestInt(String prompt);
    double requestDouble(String prompt);
    boolean requestBoolean(String prompt);
    String requestSelection(String prompt, String[] options);
}