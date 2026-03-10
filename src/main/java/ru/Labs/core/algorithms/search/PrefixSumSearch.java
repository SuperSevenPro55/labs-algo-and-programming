package ru.Labs.core.algorithms.search;

import ru.Labs.util.MessageManager;

public class PrefixSumSearch {
    private final int[] prefixSums;

    public PrefixSumSearch(int[] array) {
        if (array == null || array.length == 0) {
            System.out.println(MessageManager.get("error.invalid_input.required.more"));
            this.prefixSums = new int[0];
            return;
        }

        this.prefixSums = new int[array.length];
        prefixSums[0] = array[0];

        for (int i = 1; i < array.length; i++) {
            prefixSums[i] = prefixSums[i - 1] + array[i];
        }
    }

    public int getSum(int left, int right) {
        if (left == 0) {
            return prefixSums[right];
        }
        return prefixSums[right] - prefixSums[left - 1];
    }
}
