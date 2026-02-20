package ru.Labs.ui.labs;

import java.io.*;
import java.nio.file.*;
import java.util.List;

public class Lab_7_1 {
    public static void start() {
        String inputFile = "src/main/resources/roguelike-input.csv";
        String outputFile = "src/main/resources/roguelike-output.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile)); // Считываем строки
            if (lines.isEmpty()) {
                return;
            }

            int h = lines.size();                                   //
            String[] firstRow = lines.getFirst().split(";"); //Считываем размеры сетки
            int w = firstRow.length;                                //

            int[][] grid = new int[h][w];                           // И воссоздаем ее

            for (int i = 0; i < h; i ++) {
                String[] elements = lines.get(i).split(";"); // Сначала берем строку
                for (int j = 0; j < w; j++) {
                    grid[i][j] = Integer.parseInt(elements[j].trim()); // Потом парсим ее в сетку
                }
            }

            // Таблица, хранящая в ячейках максимальное количество монет,
            // которое можно получить, придя в определенную ячейку
            int[][] moneyTable = new int[h][w];

            // Базовые случаи
            moneyTable[0][0] = grid[0][0]; // В начальной ячейке всегда изначальное количество монет
            for (int i = 1; i < w; i++) { // В первую строчку можно прийти только слева
                moneyTable[0][i] = moneyTable[0][i - 1] + grid[0][i];
            }
            for (int i = 1; i < h; i++) { // В первый столбец можно прийти только сверху
                moneyTable[i][0] = moneyTable[i - 1][0] + grid[i][0];
            }

            // Не базовые случаи
            for (int i = 1; i < h; i++) {
                for (int j = 1; j < w; j++) { //               Ячейка сверху           Ячейка слева
                    moneyTable[i][j] = grid[i][j] + Math.max(moneyTable[i - 1][j], moneyTable[i][j - 1]);
                }
            }

            long maxCoins = moneyTable[h - 1][w - 1]; // Максимальное значение монет

            StringBuilder path = new StringBuilder();

            int i = h - 1;
            int j = w - 1;
            while (i > 0 || j > 0) { // Восстанавливаем путь с конца
                if (i == 0) { // Первая строчка - можно было прийти только слева
                    path.append("R");
                    j--;
                }
                else if (j == 0) { // Первый столбец - можно было прийти только сверху
                    path.append("D");
                    i--;
                }
                else {
                    if (moneyTable[i - 1][j] >= moneyTable[i][j - 1]) {
                        path.append("D");
                        i--;
                    }
                    else {
                        path.append("R");
                        j--;
                    }
                }
            }

            String pathFinal = path.reverse().toString(); // Разворачиваем путь
            String output = "Монеты: " + maxCoins + "\nПуть: " + pathFinal;
            Files.write(Paths.get(outputFile), output.getBytes()); // Записываем в файл

            System.out.println("Результат записан в файл");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
