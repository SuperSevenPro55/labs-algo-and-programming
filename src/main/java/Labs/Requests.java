package Labs;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Requests {
    private int[] readData(StringTokenizer tokenizer, int n) throws Exception {
        int[] a = new int[n];

        for (int i = 0; i < n; i++) {
            if (!tokenizer.hasMoreTokens()) {
                throw new Exception("Ожидолось " + n + " значений, но получено " + i);
            }
            int value = Integer.parseInt(tokenizer.nextToken());
            if (i == 0) {
                a[i] = value;
            } else {
                a[i] = value + a[i - 1];
            }
        }
        return a;
    }

    public List<Integer> sums(String filePath) throws Exception {
        List<Integer> sums = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line = br.readLine();
            int n = Integer.parseInt(line.trim());
            int[] a = readData(new StringTokenizer(br.readLine()), n);

            line = br.readLine();
            while (line != null) {
                StringTokenizer tokenizer = new StringTokenizer(line);

                int left = Integer.parseInt(tokenizer.nextToken());
                int right = Integer.parseInt(tokenizer.nextToken());

                int sum = 0;
                if (left == 0) {
                    sum = a[right];
                } else {
                    sum = a[right] - a[left - 1];
                }
                sums.add(sum);
                line = br.readLine();
            }
        }
        return sums;
    }
}
