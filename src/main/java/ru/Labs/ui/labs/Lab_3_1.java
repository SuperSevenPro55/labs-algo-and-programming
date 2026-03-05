package ru.Labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.CountingSort;
import ru.Labs.util.InputOutputUtils;

public class Lab_3_1 {
    public static void start(Scanner scanner) {
        List<Integer> numbers = InputOutputUtils.readInput(scanner);

        Sorter<Integer> sorter = new CountingSort();
        sorter.sort(numbers);

        InputOutputUtils.printOutput(numbers);
    }
}