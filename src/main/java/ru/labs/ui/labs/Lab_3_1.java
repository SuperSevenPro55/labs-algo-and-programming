package ru.labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.labs.core.algorithms.sorting.Sorter;
import ru.labs.core.algorithms.sorting.CountingSort;
import ru.labs.util.InputOutputUtils;
import ru.labs.util.MessageManager;

public class Lab_3_1 implements LabRunner {
    private final Scanner scanner;

    public Lab_3_1(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        List<Integer> numbers = InputOutputUtils.readInput(scanner);

        if (numbers.isEmpty()) {
            System.out.println(MessageManager.get("error.empty.array"));
            return;
        }

        Sorter<Integer> sorter = new CountingSort();
        sorter.sort(numbers);

        InputOutputUtils.printOutput(numbers);
    }
}