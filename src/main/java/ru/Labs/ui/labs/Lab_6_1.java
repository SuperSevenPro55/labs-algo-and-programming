package ru.Labs.ui.labs;

import java.util.Arrays;
import java.util.Scanner;
import ru.Labs.util.MessageManager;
import static ru.Labs.core.algorithms.search.BinarySearch.binarySearch;
import ru.Labs.util.InputOutputUtils;

public class Lab_6_1 {
    public static void start(Scanner scanner) {
        int[] array = readAndSortArray(scanner);

        if (array.length == 0) {
            System.out.println(MessageManager.get("error.empty_array"));
            return;
        }

        processRequests(array, scanner);
    }

    private static void processRequests(int[] array, Scanner scanner) {
        System.out.println(MessageManager.get("menu.lab6.item.1.enter_quantity_requests"));
        int m = InputOutputUtils.readInputInt(scanner);

        System.out.println(MessageManager.get("menu.lab6.item.1.enter_requests"));
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < m; i++) {
            int request = InputOutputUtils.readInputInt(scanner);
            sb.append(getResult(array, request)).append(" ");
        }

        printOutputResults(sb);
    }

    private static void printOutputResults(StringBuilder sb) {
        System.out.println(MessageManager.get("menu.lab6.item.1.output"));
        System.out.println(sb.toString().trim());
    }

    private static int[] readAndSortArray(Scanner scanner) {
        System.out.print(MessageManager.get("menu.lab6.item.1.enter_quantity"));
        int n = InputOutputUtils.readInputInt(scanner);

        if (n < 0) {
            return new int[0];
        }

        System.out.println(MessageManager.get("menu.lab6.item.1.enter_elements"));
        int[] array = new int[n];

        for (int i = 0; i < n; i++) {
            array[i] = InputOutputUtils.readInputInt(scanner);
        }

        Arrays.sort(array);
        return array;
    }

    private static int getResult(int[] arr, int request) {
        int place = binarySearch(arr, request);
        int result;

        if (place == 0) {
            result = arr[0];
        }
        else if (place == arr.length) {
            result = arr[arr.length - 1];
        }
        else {
            int left = arr[place - 1];
            int right = arr[place];

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
