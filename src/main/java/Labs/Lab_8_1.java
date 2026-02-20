package Labs;

import java.io.*;
import java.nio.file.*;
import java.util.StringTokenizer;

public class Lab_8_1 {
    public static void start() throws IOException {
        String fileSource = "src/main/resources/8.1_input.txt";
        BufferedReader br = new BufferedReader(new FileReader(fileSource));
        StringTokenizer st = new StringTokenizer("");
        StringBuilder sb = new StringBuilder();

        String line = br.readLine();
        int n = Integer.parseInt(line.trim());

        int[] a = new int[n];
        line = br.readLine();

        for (int i = 0; i < n; i++) {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(line);
            }
            int value = Integer.parseInt(st.nextToken());

            if (i == 0) {
                a[i] = value;
            }
            else {
                a[i] = value + a[i - 1];
            }
        }

        line = br.readLine();
        while (line != null) {
            while (!st.hasMoreTokens()) {
                st = new StringTokenizer(line);
                if (!st.hasMoreTokens()) {
                    line = br.readLine();
                    if (line == null) {
                        System.out.println(sb);
                        return;
                    }
                }
            }

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());
            int sum = 0;

            if (left == 0) {
                sum = a[right];
            }
            else {
                sum = a[right] - a[left - 1];
            }
            sb.append(sum).append("\n");
            line = br.readLine();
        }

        System.out.println(sb);

        return;
    }
}