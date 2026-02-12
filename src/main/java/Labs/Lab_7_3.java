package Labs;

import java.io.*;
import java.nio.file.*;
import java.util.*;


public class Lab_7_3 {
    public static void start() {
        String inputFile = "src/main/resources/lcs-input.txt";

        try {
            List<String> lines = Files.readAllLines(Paths.get(inputFile));

            String x = lines.get(0).trim();
            String y = lines.get(1).trim();

            int xLength = x.length();
            int yLength = y.length();

            int[][] lcsLengths = new int[xLength + 1][yLength + 1];

            for (int i = 1; i <= xLength; i++) {
                for (int j = 1; j <= yLength; j++) {
                    char xChar = x.charAt(i - 1);
                    char yChar = y.charAt(j - 1);

                    if (xChar == yChar) {
                        lcsLengths[i][j] = lcsLengths[i - 1][j - 1] + 1;
                    }
                    else {
                        lcsLengths[i][j] = Math.max(lcsLengths[i - 1][j], lcsLengths[i][j - 1]);
                    }
                }
            }

            int lcsLength = lcsLengths[xLength][yLength];
            System.out.println(lcsLength);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
