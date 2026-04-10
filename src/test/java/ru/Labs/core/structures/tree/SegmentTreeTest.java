package ru.Labs.core.structures.tree;

import org.junit.jupiter.api.Test;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;
import ru.Labs.ui.labs.Lab_8_2;

import static org.junit.jupiter.api.Assertions.*;

public class SegmentTreeTest {
    @Test
    void testController82_correctInput_correctOutput() {
        String userInput = """
                10
                1 4 3 -6 2 6 -8 2 3 5
                0 9
                2 4
                4 5
                0 3
                7 10
                exit
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Lab_8_2 lab = new Lab_8_2(scanner);
            lab.start();
        } finally {
            System.setOut(printStream);
        }

        String consoleOutput = outputStream.toString().replace("\r", "");

        assertTrue(consoleOutput.contains("""
                -8
                -6
                2
                1
                2
                """));
    }

    @Test
    void testController82_missingArrayCount_should() {
        String userInput = """
                1 4 3 -6 2 6 -8 2 3 5
                0 9
                2 4
                4 5
                0 3
                7 10
                exit
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            Lab_8_2 lab = new Lab_8_2(scanner);
            lab.start();
        } finally {
            System.setOut(printStream);
        }

        String consoleOutput = outputStream.toString().replace("\r", "");

        assertTrue(consoleOutput.contains("""
                -8
                -6
                2
                1
                2
                """));
    }
}