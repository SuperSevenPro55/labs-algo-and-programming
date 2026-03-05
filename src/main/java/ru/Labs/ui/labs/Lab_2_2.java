package ru.Labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.MergeSort;
import ru.Labs.util.InputOutputUtils;

public class Lab_2_2 {
    public static void start(Scanner scanner) {
        List<Integer> numbers = InputOutputUtils.readInput(scanner);

        if (numbers.isEmpty()) {
            System.out.println("Массив пуст или некорректные данные");
            return;
        }

        Sorter<Integer> sorter = new MergeSort();
        sorter.sort(numbers);

        InputOutputUtils.printOutput(numbers);
    }
}