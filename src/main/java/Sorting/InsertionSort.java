package Sorting;

import java.util.List;

public class InsertionSort implements sorting<Integer> {
    @Override
    public void sort(List<Integer> nums) {
        if (nums == null || nums.size() <= 1) {
            return;
        }

        for (int i = 1; i < nums.size(); i++) {
            Integer key = nums.get(i);
            int j = i - 1;

            // Пока не дошли до начала И элемент слева больше текущего (key)
            // nums.get(j) > key -> nums.get(j).compareTo(key) > 0
            while (j >= 0 && nums.get(j).compareTo(key) > 0) {
                // Сдвигаем элемент вправо: list[j+1] = list[j]
                nums.set(j + 1, nums.get(j));
                j--;
            }
            // Вставляем key на нужное место
            nums.set(j + 1, key);
        }
    }
}