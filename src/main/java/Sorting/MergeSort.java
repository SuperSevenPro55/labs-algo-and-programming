package Sorting;

import java.util.ArrayList;
import java.util.List;

public class MergeSort implements sorting<Integer> {

    // Рекурсивный метод для разделения массива
    private void slice(List<Integer> nums, int start, int end) {
        // Базовый случай рекурсии: если элемент один или меньше, сортировать нечего
        if (start >= end) {
            return;
        }

        // Находим середину
        int mid = start + (end - start) / 2;

        // Рекурсивно сортируем левую половину
        slice(nums, start, mid);
        // Рекурсивно сортируем правую половину
        slice(nums, mid + 1, end);

        // Сливаем две отсортированные половины
        merge(nums, start, mid, end);
    }

    // Метод для слияния двух отсортированных подмассивов
    private void merge(List<Integer> nums, int start, int mid, int end) {
        // Временный список для хранения результата слияния
        // Размер = (end - start + 1)
        List<Integer> temp = new ArrayList<>(end - start + 1);

        int left = start;       // Указатель на начало левой части
        int right = mid + 1;    // Указатель на начало правой части

        // Пока есть элементы в обоих подмассивах
        while (left <= mid && right <= end) {
            if (nums.get(left) <= nums.get(right)) {
                temp.add(nums.get(left));
                left++;
            } else {
                temp.add(nums.get(right));
                right++;
            }
        }

        // Если остались элементы в левой части
        while (left <= mid) {
            temp.add(nums.get(left));
            left++;
        }

        // Если остались элементы в правой части
        while (right <= end) {
            temp.add(nums.get(right));
            right++;
        }

        // Копируем из временного списка обратно в основной
        for (int i = 0; i < temp.size(); i++) {
            nums.set(start + i, temp.get(i));
        }
    }

    @Override
    public void sort(List<Integer> nums) {
        if (nums != null && nums.size() > 1) {
            slice(nums, 0, nums.size() - 1);
        }
    }
}