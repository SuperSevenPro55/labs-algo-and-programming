package ru.labs.core.algorithms.search;

public class LcsSearch {
    public static int solve(String x, String y) {
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

        return lcsLengths[xLength][yLength];
    }
}
