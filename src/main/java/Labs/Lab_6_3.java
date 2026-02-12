package Labs;

import java.util.Scanner;
import java.util.Locale;

public class Lab_6_3 {
    public static void start() {
        Scanner sc = new Scanner(System.in).useLocale(Locale.US); // Для ввода с разделителем точкой

        double a = sc.nextDouble();
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
