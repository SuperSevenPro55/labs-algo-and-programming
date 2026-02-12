package Labs;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import Classes.Student;
import Sorting.sorting;
import Sorting.StudentSort;

public class Lab_2_3 {
    public static void start() {
        List<Student> students = new ArrayList<>();
        Input(students);

        sorting<Student> sorting = new StudentSort();
        sorting.sort(students);

        Output(students);
    }

    public static void Input(List<Student> students) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите количество студентов: ");
        int quantity = sc.nextInt();
        System.out.println("Введите данные студентов (Имя Возраст) : ");

        for (int i = 0; i < quantity; i++) {
            students.add(new Student(sc.next(), sc.nextInt()));
        }
    }

    public static void Output(List<Student> students) {
        for (Student student:students) {
            System.out.println(student);
        }
    }
}
