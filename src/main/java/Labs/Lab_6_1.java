package Labs;

import java.util.Arrays;
import java.util.Scanner;
import static Classes.BinarySearch.binarySearch;

public class Lab_6_1 {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Введите размер массива: ");
        int n = sc.nextInt(); // Число элементов
        System.out.println("Введите элементы массива:");
        int[] arr = new int[n]; // Массив
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        Arrays.sort(arr);
        System.out.print("Введите число запросов: ");
        int m = sc.nextInt(); // Число запросов

        StringBuilder sb = new StringBuilder(); // Для вывода

        System.out.println("Введите запросы: ");
        for (int i = 0; i < m; i++) {
            int request = sc.nextInt(); // Обрабатываем запросы поочередно
            int result = getResult(arr, request, n);
            sb.append(result).append(" ");
        }
        System.out.println("Ваши числа: ");
        System.out.println(sb.toString().trim());
    }

    private static int getResult(int[] arr, int request, int n) {
        int place = binarySearch(arr, request); // Где бы стоял элемент
        int result;

        if (place == 0) {
            result = arr[0]; // place меньше всего в массиве - ближайший элемент начальный
        }
        else if (place == n) {
            result = arr[n - 1]; // place больше всех в массиве - ближайший элемент последний
        }
        else {
            int left = arr[place - 1]; // Сосед слева
            int right = arr[place]; // Сосед справа

            if (right - request < request - left) {
                result = right;
            }
            else {
                result = left;
            }
        }
        return result;
    }
}
