package ru.labs.core.algorithms.sorting;

import ru.labs.core.models.sorting.Student;
import java.util.List;

public class StudentSort implements Sorter<Student> {
    @Override
    public void sort(List<Student> students) {
        students.sort(Student::compareTo);
    }
}
