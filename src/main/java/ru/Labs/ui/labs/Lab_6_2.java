package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.util.InputOutputUtils;

public class Lab_6_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_6_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        long n = InputOutputUtils.readInputLong(scanner);
        long w = InputOutputUtils.readInputLong(scanner);
        long h = InputOutputUtils.readInputLong(scanner);

        long result = findMinSquare(n, w, h);
        System.out.println(result);
    }

    private static long findMinSquare(long n, long w, long h) {
        long left = 0;
        long right = Math.max(w, h) * n;

        while (left < right) {
            long mid = left + (right - left) / 2;

            if (canFit(n, w, h, mid)) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    private static boolean canFit(long n, long w, long h, long squareSide) {
        long squareLength = squareSide / w;
        long squareHeight = squareSide / h;

        if (squareHeight == 0) {
            return false;
        }

        return squareLength >= (n + squareHeight - 1) / squareHeight;
    }
}
