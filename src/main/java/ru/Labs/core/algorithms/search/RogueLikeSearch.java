package ru.Labs.core.algorithms.search;

import ru.Labs.util.MessageManager;

import java.util.InputMismatchException;

public class RogueLikeSearch {
    public record SearchResults(long maxMoney, String path) {}

    public static SearchResults solve(int h, int w, int[][] grid) {
        int[][] moneyTable = getMoneyTable(h, w, grid);

        long maxMoney = moneyTable[h - 1][w - 1];

        String path = getPathReversed(h, w, moneyTable);

        return new SearchResults(maxMoney, path);
    }

    private static int[][] getMoneyTable(int h, int w, int[][] grid) {
        int[][] moneyTable = new int[h][w];
        moneyTable[0][0] = grid[0][0];

        try {
            // Базовые случаи
            for (int i = 1; i < w; i++) {
                moneyTable[0][i] = moneyTable[0][i - 1] + grid[0][i];
            }

            for (int i = 1; i < h; i++) {
                moneyTable[i][0] = moneyTable[i - 1][0] + grid[i][0];
            }

            // Не базовые случаи
            for (int i = 1; i < h; i++) {
                for (int j = 1; j < w; j++) { //               Ячейка сверху           Ячейка слева
                    moneyTable[i][j] = grid[i][j] + Math.max(moneyTable[i - 1][j], moneyTable[i][j - 1]);
                }
            }
        } catch (InputMismatchException e) {
            System.out.println(MessageManager.get("error.invalid_input.required.int"));
            return new int[0][0];
        }


        return moneyTable;
    }

    private static String getPathReversed(int h, int w, int[][] moneyTable) {
        StringBuilder path = new StringBuilder();

        int i = h - 1;
        int j = w - 1;

        while (i > 0 || j > 0) { // Восстанавливаем путь с конца
            if (i == 0) { // Первая строчка - можно было прийти только слева
                path.append("R");
                j--;
            } else if (j == 0) { // Первый столбец - можно было прийти только сверху
                path.append("D");
                i--;
            } else {
                if (moneyTable[i - 1][j] >= moneyTable[i][j - 1]) {
                    path.append("D");
                    i--;
                } else {
                    path.append("R");
                    j--;
                }
            }
        }

        return path.reverse().toString();
    }
}
