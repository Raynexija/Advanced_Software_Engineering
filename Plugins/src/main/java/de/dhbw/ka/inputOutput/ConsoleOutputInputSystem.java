package de.dhbw.ka.inputOutput;

import de.dhbw.ka.TextBasedOutputInputSystem;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;


public class ConsoleOutputInputSystem implements TextBasedOutputInputSystem {
    enum ColorCodes {
        RESET("\u001B[0m"),
        BLACK("\u001B[30m"),
        GREY("\u001B[38;5;8m"),
        RED("\u001B[31m"),
        GREEN("\u001B[32m"),
        YELLOW("\u001B[33m"),
        ORANGE("\u001B[38;5;208m"),
        BLUE("\u001B[34m"),
        PURPLE("\u001B[35m"),
        CYAN("\u001B[36m"),
        WHITE("\u001B[37m");

        private final String code;

        ColorCodes(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }

    private OutputStream outputStream;
    private InputStream inputStream;

    public ConsoleOutputInputSystem() {
        this.outputStream = System.out;
        this.inputStream = System.in;
    }

    public void setOutputStream(OutputStream outputStream) {
        this.outputStream = outputStream;
    }

    public  void setInputStream(InputStream inputStream) {
        this.inputStream = inputStream;
    }

    private void print(String text) {
        try {
            outputStream.write(text.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void printLine(String text) {
        print(text);
        print("\n");
    }

    @Override
    public void printWarning(String warning) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(String.format("%s%-10s%s", ColorCodes.ORANGE.getCode(), "[WARNING]", ColorCodes.RESET.getCode()));
        outputBuilder.append("\t");
        outputBuilder.append(warning);
        printLine(outputBuilder.toString());
    }

    @Override
    public void printError(String error) {
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(String.format("%s%-10s%s", ColorCodes.RED.getCode(), "[ERROR]", ColorCodes.RESET.getCode()));
        outputBuilder.append("\t");
        outputBuilder.append(error);
        printLine(outputBuilder.toString());
    }

    @Override
    public void printList(String[] list) {
        for (String item : list) {
            printLine(item);
        }
    }

    @Override
    public <T> T requestTextSelection(String request, T[] options) {
        printLine(request);
        for (int i = 0; i < options.length; i++) {
            StringBuilder outputBuilder = new StringBuilder();
            outputBuilder.append(String.format("%s%-3s%s", ColorCodes.GREY.getCode(), "[" + i + "]", ColorCodes.RESET.getCode()));
            outputBuilder.append(":\t");
            outputBuilder.append(options[i]);
            printLine(outputBuilder.toString());
        }
        int selectedOptionIndex;
        while (true) {
            selectedOptionIndex = requestNumber("Please select an option:");
            if (selectedOptionIndex >= 0 && selectedOptionIndex < options.length) {
                break;
            } else {
                printError("Invalid option selected. " + selectedOptionIndex + " is not a valid option.");
            }
        }
        return options[selectedOptionIndex];
    }

    @Override
    public String requestText(String request) {
        Scanner inputScanner = new Scanner(inputStream);
        print(request);
        while (!inputScanner.hasNextLine()) {
            print("Invalid input. ");
            print(request);
            inputScanner.next();
        }
        return inputScanner.nextLine();
    }

    @Override
    public int requestNumber(String request) {
        Scanner inputScanner = new Scanner(inputStream);
        print(request);
        while (!inputScanner.hasNextInt()) {
            print("Invalid input. ");
            print(request);
            inputScanner.next();
        }
        return inputScanner.nextInt();
    }

    @Override
    public boolean requestConfirmation(String request) {
        Scanner inputScanner = new Scanner(inputStream);
        StringBuilder outputBuilder = new StringBuilder();
        outputBuilder.append(request);
        outputBuilder.append(" [y/n]");
        print(outputBuilder.toString());
        while (true) {
            String input = inputScanner.next();
            if (input.equals("y")) {
                return true;
            } else if (input.equals("n")) {
                return false;
            } else {
                print("Invalid input. ");
                print(outputBuilder.toString());
            }
        }
    }
}
