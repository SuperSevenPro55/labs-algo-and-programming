package ru.Labs.core.algorithms.search;

import java.util.Arrays;

public class LisSearch {
    public record SearchResults(int length, int[] sequence) {}

    public static SearchResults solve(int[] nums) {
        if (nums == null || nums.length == 0) {
            return new SearchResults(0, new int[0]);
        }

        int n = nums.length;

        int[] paths = new int[n]; // Из какого индекса в эту точку пришел
        int[] lisLengths = new int[n]; // Хранит длины подпоследовательностей'
        Arrays.fill(lisLengths, 1); // Сами по себе числа это подпоследовательности длины 1
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

        int[] result = getPath(maxLisLength, maxLisLengthIndex, nums, paths);

        return new SearchResults(maxLisLength, result);
    }

    private static int[] getPath(int maxLisLength, int maxLisLengthIndex, int[] nums, int[] paths) {
        int[] result = new int[maxLisLength]; // Собираем путь этой последовательности

        int currentLisMemberIndex = maxLisLengthIndex;
        for (int i = maxLisLength - 1; i >= 0; i--) { // Заполняем с конца
            result[i] = nums[currentLisMemberIndex];
            currentLisMemberIndex = paths[currentLisMemberIndex];
        }

        return result;
    }
}