package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.core.structures.tree.SegmentTree;

public class Lab_8_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_8_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        long[] array = scanArray();
        SegmentTree tree = new SegmentTree(array);

        runCommandLoop(tree);
    }

    private long[] scanArray() {
        int n = scanner.nextInt();
        long[] array = new long[n];

        for (int i = 0; i < n; i++) {
            if (scanner.hasNextInt()) {
                array[i] = scanner.nextLong();
            }
        }

        if (scanner.hasNextLine()) {
            scanner.nextLine();
        }

        return array;
    }

    private void runCommandLoop(SegmentTree tree) {
        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine();

            if (commandLine.isEmpty()) {
                continue;
            }

            if (commandLine.equals("exit")) {
                break;
            }

            processCommand(tree, commandLine);
        }
    }

    private void processCommand(SegmentTree tree, String commandLine) {
        String[] parts = commandLine.split("\\s+");

        System.out.println(tree.getMin(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]) - 1));
    }
}
