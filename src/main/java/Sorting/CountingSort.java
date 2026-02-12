package Sorting;

import java.util.List;

public class CountingSort implements sorting<Integer> {
    @Override
    public void sort(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return;
        }

        // 1. Создаем массив для подсчета (диапазон [0, 127], значит размер 128)
        int[] count = new int[128];

        // 2. Считаем количество вхождений каждого числа
        for (Integer num : nums) {
            // По условию числа корректные, но можно добавить проверку if (num >= 0 && num < 128)
            count[num]++;
        }

        // 3. Перезаписываем исходный список
        int index = 0;
        // Идем по всем возможным значениям чисел от 0 до 127
        for (int i = 0; i < count.length; i++) {
            // Пока счетчик для числа i не обнулится
            while (count[i] > 0) {
                nums.set(index, i); // Ставим число на нужное место
                index++;
                count[i]--;
            }
        }
    }
}
