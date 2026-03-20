package ru.Labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.MergeSort;
import ru.Labs.util.InputOutputUtils;
import ru.Labs.util.MessageManager;

public class Lab_2_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_2_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        List<Integer> numbers = InputOutputUtils.readInput(scanner);

        if (numbers.isEmpty()) {
            System.out.println(MessageManager.get("error.empty.array"));
            return;
        }

        Sorter<Integer> sorter = new MergeSort();
        sorter.sort(numbers);

        InputOutputUtils.printOutput(numbers);
    }
}