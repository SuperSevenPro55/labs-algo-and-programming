package ru.Labs.ui.labs;

import java.io.*;
import java.nio.file.*;
import java.util.InputMismatchException;
import java.util.List;
import ru.Labs.util.FileUtils;
import ru.Labs.util.MessageManager;
import ru.Labs.core.algorithms.search.RogueLikeSearch;

public class Lab_7_1 {
    public static void start() {
        String inputFile = "roguelike-input.csv";
        String outputFile = "roguelike-output.txt";

        List<String> lines = FileUtils.readOrCreateFile(inputFile);

        if (lines.isEmpty()) {
            System.out.println(MessageManager.get("error.empty_array"));
            return;
        }

        int h = getH(lines);
        int w = getW(lines);

        int[][] grid = parseGrid(h, w, lines);

        if (grid.length == 0) {
            System.out.println(MessageManager.get("error.empty_array"));
            return;
        }

        RogueLikeSearch.SearchResults results = RogueLikeSearch.solve(h, w, grid);

        FileUtils.writeOrCreateFile(outputFile, results.maxMoney(), results.path());
    }

    private static int getH(List<String> lines) {
        return lines.size();
    }

    private static int getW(List<String> lines) {
        String[] firstRow = lines.getFirst().split(";");
        return firstRow.length;
    }

    private static int[][] parseGrid(int h, int w, List<String> lines) {
        int[][] grid = new int[h][w];

        try {
            for (int i = 0; i < h; i ++) {
                String[] elements = lines.get(i).split(";");
                for (int j = 0; j < w; j++) {
                    grid[i][j] = Integer.parseInt(elements[j].trim());
                }
            }
        } catch (InputMismatchException | NumberFormatException e) {
            System.out.println(MessageManager.get("error.invalid_input.required.int"));
            System.out.println(MessageManager.get("error.file.check_again"));
            return new int[0][0];
        }

        return grid;
    }
}
