package ru.Labs.ui.labs;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestLab_10_1 {
    @Test
    public void controller_correctInput_correctOutput() {
        String userInput = """
                10
                7
                8 1
                1 0
                0 2
                1 4
                3 6
                3 7
                7 9
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LabRunner lab = new Lab_10_1(scanner);
            lab.start();
        } finally {
            System.setOut(printStream);
        }

        String consoleOutput = outputStream.toString().replace("\r", "");

        assertTrue(consoleOutput.contains("0 1 2 4 8"));
        assertTrue(consoleOutput.contains("3 6 7 9"));
        assertTrue(consoleOutput.contains("5"));
    }

    @Test
    public void controller_correctInputRandomized_correctOutput() {
        String userInput = """
                10
                7
                3 7
                1 0
                7 9
                8 1
                1 4
                0 2
                3 6
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LabRunner lab = new Lab_10_1(scanner);
            lab.start();
        } finally {
            System.setOut(printStream);
        }

        String consoleOutput = outputStream.toString().replace("\r", "");

        assertTrue(consoleOutput.contains("0 1 2 4 8"));
        assertTrue(consoleOutput.contains("3 6 7 9"));
        assertTrue(consoleOutput.contains("5"));
    }
}
