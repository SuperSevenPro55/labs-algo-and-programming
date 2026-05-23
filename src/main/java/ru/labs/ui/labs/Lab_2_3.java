package ru.labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.labs.core.models.sorting.Student;
import ru.labs.core.algorithms.sorting.Sorter;
import ru.labs.core.algorithms.sorting.StudentSort;
import ru.labs.util.MessageManager;
import ru.labs.util.InputOutputUtils;

public class Lab_2_3 implements LabRunner {
    private final Scanner scanner;

    public Lab_2_3(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
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