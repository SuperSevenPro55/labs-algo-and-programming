package Labs;

import Sorting.MergeSort;
import Sorting.sorting;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Lab_2_2 {
    public static void start() {
        Scanner scanner = new Scanner(System.in);

        // Считываем N (хотя для List оно нам не особо нужно, но считать надо)
        if (scanner.hasNextInt()) {
            int n = scanner.nextInt();
            List<Integer> numbers = new ArrayList<>(n);

            for (int i = 0; i < n; i++) {
                numbers.add(scanner.nextInt());
            }

            // Запускаем сортировку
            sorting<Integer> sorter = new MergeSort();
            sorter.sort(numbers);

            // Выводим результат
            for (int i = 0; i < numbers.size(); i++) {
                System.out.print(numbers.get(i) + (i == numbers.size() - 1 ? "" : " "));
            }
            System.out.println();
        }
    }
}
