package ru.Labs.core.algorithms.sorting;

import ru.Labs.core.models.Student;
import java.util.List;

public class StudentSort implements Sorter<Student> {
    @Override
    public void sort(List<Student> students) {
        students.sort(Student::compareTo);
    }
}
