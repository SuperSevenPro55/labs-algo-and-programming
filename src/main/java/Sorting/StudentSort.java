package Sorting;

import Classes.Student;
import java.util.List;

public class StudentSort implements sorting<Student> {
    @Override
    public void sort(List<Student> students) {
        students.sort(Student::compareTo);
    }
}
