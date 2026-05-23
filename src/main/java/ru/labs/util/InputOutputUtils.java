package ru.labs.util;

import ru.labs.core.models.sorting.Student;
import ru.labs.core.structures.graph.CommonGraph;

import java.math.BigInteger;
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

    public static double readInputDouble(Scanner scanner) {
        while (true) {
            try {
                return scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println(MessageManager.get("error.invalid_input.required.double"));
                System.out.println(MessageManager.get("error.try_again"));
                scanner.nextLine();
            }
        }
    }

    public static List<Student> readInputStudents(Scanner scanner) {
        System.out.print(MessageManager.get("menu.lab2.item.3.enter_quantity"));
        if (!scanner.hasNextInt()) {
            System.out.println(MessageManager.get("error.invalid_input.required.int"));
            scanner.next();
            return new ArrayList<>();
        }

        int quantity = scanner.nextInt();
        List<Student> students = new ArrayList<>();

        System.out.println(MessageManager.get("menu.lab2.item.3.enter_data"));

        for (int i = 0; i < quantity; i++) {
            try {
                String name = scanner.next();
                int age = scanner.nextInt();

                students.add(new Student(name, age));
            } catch (InputMismatchException e) {
                System.out.println(MessageManager.get("error.invalid_input.required.int"));
                System.out.println(MessageManager.get("error.try_again"));
                scanner.nextLine();
                i--;
            }
        }

        return students;
    }

    public static void printOutputStudents(List<Student> students) {
        for (Student student:students) {
            System.out.println(student);
        }
    }

    public static List<BigInteger> readInputBigInteger(Scanner scanner) {
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
                System.out.println(MessageManager.get("error.invalid_input.required.bigInt"));
                System.out.println(MessageManager.get("error.try_again"));
                scanner.nextLine();
                i--;
            }
        }

        return nums;
    }

    public static void printOutputBigInteger(List<BigInteger> nums) {
        for (BigInteger num : nums) {
            System.out.println(num);
        }
    }

    public static void readInputGraphData(Scanner scanner, CommonGraph graph) {
        System.out.println(MessageManager.get("menu.lab10.item.1.input.vertices.count"));
        int n = scanner.nextInt();
        System.out.println(MessageManager.get("menu.lab10.item.1.input.edges.count"));
        int m = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }

        System.out.println(MessageManager.get("menu.lab10.item.1.input.edges"));

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            graph.addEdge(u, v);
        }
    }
}