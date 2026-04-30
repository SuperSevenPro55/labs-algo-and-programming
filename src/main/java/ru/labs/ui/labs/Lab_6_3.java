package ru.labs.ui.labs;

import java.util.Scanner;
import java.util.Locale;
import ru.labs.util.InputOutputUtils;

public class Lab_6_3 implements LabRunner {
    private final Scanner scanner;

    public Lab_6_3(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        double a = InputOutputUtils.readInputDouble(scanner);

        double x = solve(a);

        System.out.printf(Locale.US, "%.6f\n", x);
    }

    private static double solve(double a) {
        double left = 1.0;
        double right = Math.pow(10, 10);
        double epsilon = 1e-7; // Граница, чтобы не делилось до бесконечности

        while (right - left > epsilon) {
            double mid = left + (right - left) / 2;
            // f(x) = x^2 - x + sqrt(x)
            double funcAns = mid * mid - mid + Math.sqrt(mid);

            if (funcAns < a) {
                left = mid;
            }
            else {
                right = mid;
            }
        }
        return left;
    }
}
