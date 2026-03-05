package ru.Labs.ui.labs;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.RadixSort;
import ru.Labs.util.MessageManager;

public class Lab_3_2 {
    public static void start(Scanner scanner) {
        List<BigInteger> nums = readInputBigInteger(scanner);

        if (nums.isEmpty()) {
            System.out.println(MessageManager.get("error.empty_array"));
            return;
        }

        Sorter<BigInteger> sorter = new RadixSort();
        sorter.sort(nums);

        printOutputBigInteger(nums);
    }

    private static List<BigInteger> readInputBigInteger(Scanner scanner) {
        System.out.print(MessageManager.get("menu.lab3.item.2.enter_quantity"));
        if (!scanner.hasNextInt()) {
            System.out.println(MessageManager.get("error.invalid_input.required.int"));
            scanner.next();
            return new ArrayList<>();
        }

        int quantity = scanner.nextInt();
        List<BigInteger> nums = new ArrayList<>(quantity);

        System.out.println(MessageManager.get("menu.lab3.item.2.enter_elements"));

        for (int i = 0; i < quantity; i++) {
            try {
                BigInteger num = scanner.nextBigInteger();
                nums.add(num);
            } catch (InputMismatchException e) {
                System.out.println(MessageManager.get("error.invalid_input.required.int"));
                System.out.println(MessageManager.get("error.try_again"));
                scanner.nextLine();
                i--;
            }
        }

        return nums;
    }

    private static void printOutputBigInteger(List<BigInteger> nums) {
        for (BigInteger num : nums) {
            System.out.println(num);
        }
    }
}
