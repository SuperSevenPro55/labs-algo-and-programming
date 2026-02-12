package Labs;

public class Lab_1_2 {
    public static void start() {
        Task1_2 cleaner = new Task1_2();

        // Тест 1
        int[] arr1 = {3, 2, 2, 3};
        int val1 = 3;
        int len1 = cleaner.removeElement(arr1, val1);

        System.out.print("Test 1 (len=" + len1 + "): ");
        for (int i = 0; i < len1; i++) System.out.print(arr1[i] + " ");
        System.out.println(); // Ожидаем: 2 2

        // Тест 2
        int[] arr2 = {0, 1, 2, 2, 3, 0, 4, 2};
        int val2 = 2;
        int len2 = cleaner.removeElement(arr2, val2);

        System.out.print("Test 2 (len=" + len2 + "): ");
        for (int i = 0; i < len2; i++) System.out.print(arr2[i] + " ");
        System.out.println(); // Ожидаем: 0 1 3 0 4
    }

    public static class Task1_2 {

        public int removeElement(int[] arr, int val) {
            if (arr == null || arr.length == 0) {
                return 0;
            }

            int writer = 0;

            for (int reader = 0; reader < arr.length; reader++) {
                if (arr[reader] != val) {
                    // Если элемент не равен val, "сохраняем" его
                    arr[writer] = arr[reader];
                    writer++;
                }
            }

            // writer теперь указывает на длину "чистого" массива
            return writer;
        }
    }
}
