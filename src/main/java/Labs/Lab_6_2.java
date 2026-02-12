package Labs;

import java.util.Scanner;

public class Lab_6_2 {
    public static void start() {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long w = sc.nextLong();  // Ввод данных
        long h = sc.nextLong();

        long left = 0;
        long right = Math.max(w, h) * n; // В худшем случае (все в ряд)

        while (left < right) {
            long mid = left + (right - left) / 2;

            long squareLength = mid / w; // Сколько дипломов влезет в "Строку" квадрата размера mid
            long squareHeight = mid / h; // Сколько влезет "Строк" дипломов в квадрат размера mid

            boolean possibleSquare = false;

            if (squareHeight > 0) { // Если влезает хотя бы одна строка
                long requiredLength = (n + squareHeight - 1) / squareHeight; // Сколько дипломов ДОЛЖНО влезть в "строку"
                if (squareLength >= requiredLength) {
                    possibleSquare = true;
                }
            }

            if (possibleSquare) {
                right = mid; // Хватает, но может можно взять квадрат меньше?
            }
            else {
                left = mid + 1; // Не хватает, нужен квадрат больше
            }
        }

        System.out.println(left);
    }
}
