package ru.Labs.ui.labs;

import java.io.*;
import java.nio.file.*;
import java.util.*;

public class Lab_7_2 {
    public static void start() {
        String inputFile = "src/main/resources/lis-input.txt";
        String outputFile = "src/main/resources/lis-output.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            int n = Integer.parseInt(lines.getFirst().trim());
            String[] elements = lines.get(1).split(" ");

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(elements[i]);
            }

            int[] lisLengths = new int[n]; // Хранит длины подпоследовательностей
            Arrays.fill(lisLengths, 1); // Сами по себе числа это подпоследовательности длины 1

            int[] paths = new int[n]; // Из какого индекса в эту точку пришел
            Arrays.fill(paths, -1); // По умолчанию -1

            int maxLisLength = 1;
            int maxLisLengthIndex = 0;

            for (int i = 0; i < n; i++) {
                for (int j = 0; j < i; j++) {
                    if (nums[j] < nums[i] && lisLengths[j] + 1 > lisLengths[i]) { // Сравниваем сами числа и возможную цепочку из этого числа с уже текущей
                        lisLengths[i] = lisLengths[j] + 1;
                        paths[i] = j;
                    }
                }

                if (lisLengths[i] >= maxLisLength) { // Максимальная длина подпоследовательности
                    maxLisLength = lisLengths[i];
                    maxLisLengthIndex = i;
                }
            }

            int[] result = new int[maxLisLength]; // Собираем путь этой последовательности
            int currentLisMemberIndex = maxLisLengthIndex;
            for (int i = maxLisLength - 1; i >= 0; i--) { // Заполняем с конца
                result[i] = nums[currentLisMemberIndex];
                currentLisMemberIndex = paths[currentLisMemberIndex];
            }

            StringBuilder sb = new StringBuilder(); // Собираем вывод
            sb.append(maxLisLength).append("\n");
            for (int i = 0; i < maxLisLength; i++) {
                sb.append(result[i]);
                if (i < maxLisLength - 1) {
                    sb.append(" ");
                }
            }

            Files.write(Paths.get(outputFile), sb.toString().getBytes());
            System.out.println("Результат записан в файл");
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
