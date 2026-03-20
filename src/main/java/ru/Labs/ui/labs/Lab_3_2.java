package ru.Labs.ui.labs;

import java.math.BigInteger;
import java.util.List;
import java.util.Scanner;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.RadixSort;
import ru.Labs.util.MessageManager;
import ru.Labs.util.InputOutputUtils;

public class Lab_3_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_3_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        List<BigInteger> nums = InputOutputUtils.readInputBigInteger(scanner);

        if (nums.isEmpty()) {
            System.out.println(MessageManager.get("error.empty.array"));
            return;
        }

        Sorter<BigInteger> sorter = new RadixSort();
        sorter.sort(nums);

        InputOutputUtils.printOutputBigInteger(nums);
    }
}