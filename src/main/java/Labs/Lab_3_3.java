package Labs;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import Sorting.sorting;
import Sorting.SpecialSort;
import Classes.Student;

public class Lab_3_3 {
    public static void start() {
        List<Integer> integers = new ArrayList<>(Arrays.asList(5, 2, 9, 1, 5, 6));
        sorting<Integer> intSort = new SpecialSort<>();
        intSort.sort(integers);
        System.out.println("Integers: " + integers);

        List<String> strings = new ArrayList<>(Arrays.asList("Banana", "Apple", "Cherry", "Date"));
        sorting<String> stringSort = new SpecialSort<>();
        stringSort.sort(strings);
        System.out.println("Strings: " + strings);

        LocalDateTime now = LocalDateTime.now();
        List<LocalDateTime> dates = new ArrayList<>(Arrays.asList(
                now.plusDays(5),
                now.minusDays(1),
                now.plusHours(1)
        ));
        sorting<LocalDateTime> dateSort = new SpecialSort<>();
        dateSort.sort(dates);
        System.out.println("Dates: " + dates);

        List<Student> students = new ArrayList<>(Arrays.asList(
                new Student("Vasya", 21),
                new Student("Anton", 22),
                new Student("Vasya", 19),
                new Student("Antonio", 23)
        ));
        sorting<Student> studentSort = new SpecialSort<>();
        studentSort.sort(students);
        System.out.println("Students: " + students);

        List<UUID> uuids = new ArrayList<>(Arrays.asList(
                UUID.randomUUID(),
                UUID.randomUUID(),
                UUID.randomUUID()
        ));
        sorting<UUID> uuidSort = new SpecialSort<>();
        uuidSort.sort(uuids);
        System.out.println("UUIDs sorted: " + uuids);
    }
}
