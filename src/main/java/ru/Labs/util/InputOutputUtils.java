package ru.Labs.util;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class InputOutputUtils {
    public static List<Integer> readInput(Scanner scanner) {
        System.out.println(MessageManager.get("menu.lab3.item.2.enter_quantity"));
        if (!scanner.hasNextInt()) {
            scanner.next();
            return new ArrayList<>();
        }

        int n = scanner.nextInt();
        List<Integer> numbers = new ArrayList<>(n);

        System.out.println(MessageManager.get("menu.lab3.item.2.enter_elements"));
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

    public static int readInputInt(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println(MessageManager.get("error.invalid_input.required.int"));
                System.out.println(MessageManager.get("error.try_again"));
                scanner.nextLine();
            }
        }
    }
    public static long readInputLong(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextLong();
            } catch (InputMismatchException e) {
                System.out.println(MessageManager.get("error.invalid_input.required.long"));
                System.out.println(MessageManager.get("error.try_again"));
                scanner.nextLine();
            }
        }
    }
}
