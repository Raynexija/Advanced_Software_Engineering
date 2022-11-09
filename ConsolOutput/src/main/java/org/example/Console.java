package org.example;

import java.io.OutputStream;

public class Console {
    private static OutputStream out;

    public Console(OutputStream out) {
        this.out = out;
    }

    public void out2(String str) {
        try {
            out.write(str.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void debug2(String text) {
        String temp = "[DEBUG] " + text;
        try {
            out.write(temp.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void out(String text) {
        System.out.println(text);
    }

    public static void err(String text) {
        System.err.println("[ERROR]" + "\t" + text);
    }

    public static void warn(String text) {
        System.err.println("[WARNING]" + "\t" + text);
    }

    public static void info(String text) {
        System.out.println("[INFO]" + "\t" + text);
    }

    public static void debug(String text) {
        System.out.println("[DEBUG]" + "\t" + text);
    }

    /**
     * Method to read a line from the console using a StreamReader
     *
     * @return String
     */
    public static String readLine() {
        String line = "";
        try {
            java.io.BufferedReader in = new java.io.BufferedReader(new java.io.InputStreamReader(System.in));
            line = in.readLine();
        } catch (java.io.IOException e) {
            System.err.println(e);
        }
        return line;
    }
}
