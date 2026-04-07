package ru.Labs.core.structures.tree;

import org.junit.jupiter.api.Test;
import ru.Labs.ui.labs.LabRunner;
import ru.Labs.ui.labs.Lab_9_1;
import ru.Labs.ui.labs.Lab_9_2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class BinarySearchTreeTest {
    @Test
    void websiteTest_BST_correctOutput() {
        runLabTest(false);
    }

    @Test
    void websiteTest_AVL_correctOutput() {
        runLabTest(true);
    }

    private void runLabTest(boolean isAVL) {
        String userInput = """
                insert 5
                insert 7
                insert 3
                insert 4
                has 3
                has 8
                insert 8
                has 8
                delete 3
                has 4
                next 7
                prev 5
                exit
                """;

        ByteArrayInputStream inputStream = new ByteArrayInputStream(userInput.getBytes());
        Scanner scanner = new Scanner(inputStream);

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        PrintStream printStream = System.out;
        System.setOut(new PrintStream(outputStream));

        try {
            LabRunner lab = isAVL ? new Lab_9_2(scanner) : new Lab_9_1(scanner);
            lab.start();
        } finally {
            System.setOut(printStream);
        }

        String consoleOutput = outputStream.toString().replace("\r", "");

        assertTrue(consoleOutput.contains("""
                t
                f
                t
                t
                8
                4
                """));
    }
}