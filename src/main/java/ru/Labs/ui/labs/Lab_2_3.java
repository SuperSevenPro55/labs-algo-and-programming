package ru.Labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.Labs.core.models.Student;
import ru.Labs.core.algorithms.sorting.Sorter;
import ru.Labs.core.algorithms.sorting.StudentSort;
import ru.Labs.util.MessageManager;
import ru.Labs.util.InputOutputUtils;

public class Lab_2_3 {
    public static void start(Scanner scanner) {
        List<Student> students = InputOutputUtils.readInputStudents(scanner);

        if (students.isEmpty()) {
            System.out.println(MessageManager.get("error.empty.array"));
            return;
        }

        Sorter<Student> sorting = new StudentSort();
        sorting.sort(students);

        InputOutputUtils.printOutputStudents(students);
    }
}