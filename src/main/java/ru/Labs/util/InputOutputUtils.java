package ru.Labs.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputOutputUtils {
    public static List<Integer> readInput(Scanner scanner) {
        System.out.println("Введите количество элементов массива: ");
        if (!scanner.hasNextInt()) {
            scanner.next();
            return new ArrayList<>();
        }

        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>(n);

        System.out.println("Введите элементы массива: ");
        for (int i = 0; i < n; i++) {
            if (scanner.hasNextInt()) {
                numbers.add(scanner.nextInt());
            } else {
                scanner.next();
            }
        }
        return numbers;
    }

    public static void printOutput(List<Integer> numbers) {
        for (int i = 0; i < numbers.size(); i++) {
            System.out.print(numbers.get(i) + (i == numbers.size() - 1 ? "" : " "));
        }
        System.out.println();
    }
}
