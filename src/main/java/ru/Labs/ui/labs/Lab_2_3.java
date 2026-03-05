package ru.Labs.ui.labs;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;
import ru.Labs.core.models.Student;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.StudentSort;
import ru.Labs.util.MessageManager;

public class Lab_2_3 {
    public static void start(Scanner scanner) {
        List<Student> students = readInputStudents(scanner);

        if (students.isEmpty()) {
            System.out.println(MessageManager.get("error.empty_array"));
            return;
        }

        Sorter<Student> sorting = new StudentSort();
        sorting.sort(students);

        printOutputStudents(students);
    }

    private static List<Student> readInputStudents(Scanner scanner) {
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

    private static void printOutputStudents(List<Student> students) {
        for (Student student:students) {
            System.out.println(student);
        }
    }
}
