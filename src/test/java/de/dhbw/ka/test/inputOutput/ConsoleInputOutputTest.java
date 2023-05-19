package de.dhbw.ka.test.inputOutput;

import de.dhbw.ka.plugins.inputOutput.ConsoleOutputInputSystem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.*;

public class ConsoleInputOutputTest {
    private final ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    private ConsoleOutputInputSystem console;

    @BeforeEach
    void setUp() {
        console = new ConsoleOutputInputSystem(outputStream, System.in);
    }

    @Test
    void testRequestText() {
        String request = "Enter some text:";
        String expected = "test input";
        console.setInputStream(new ByteArrayInputStream("test input\n".getBytes()));
        String actual = console.requestText(request);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testRequestNumber() {
        String request = "Enter a number:";
        int expected = 42;
        console.setInputStream(new ByteArrayInputStream("42\n".getBytes()));
        int actual = console.requestNumber(request);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testRequestTextSelection() {
        String request = "Choose an option:";
        String[] options = {"Option 1", "Option 2", "Option 3"};
        String expected = "Option 2";
        console.setInputStream(new ByteArrayInputStream("1\n".getBytes()));
        String actual = console.requestTextSelection(request, options);
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testRequestConfirmation() {
        String request = "Are you sure, that you want to proceed?";
        console.setInputStream(new ByteArrayInputStream("y\n".getBytes()));
        boolean actual = console.requestConfirmation(request);
        boolean expected = true;
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPrintWarning() {
        String warning = "This is a warning message";
        String expected = "\u001B[38;5;208m[WARNING] \u001B[0m\tThis is a warning message\n";
        console.printWarning(warning);
        String actual = outputStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPrintError() {
        String error = "This is an error message";
        String expected = "\u001B[31m[ERROR]   \u001B[0m\tThis is an error message\n";
        console.printError(error);
        String actual = outputStream.toString();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void testPrintList() {
        String[] list = {"Item 1", "Item 2", "Item 3"};
        String expected = "Item 1\nItem 2\nItem 3\n";
        console.printList(list);
        String actual = outputStream.toString();
        Assertions.assertEquals(expected, actual);
    }
}

