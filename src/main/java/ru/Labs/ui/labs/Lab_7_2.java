package ru.Labs.ui.labs;

import java.util.List;
import ru.Labs.util.FileUtils;
import ru.Labs.core.algorithms.search.LisSearch;
import ru.Labs.util.MessageManager;

public class Lab_7_2 implements LabRunner {
    private final String inputFile;
    private final String outputFile;

    public Lab_7_2(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    public Lab_7_2() {
        this("lis-input.txt", "lis-output.txt");
    }

    @Override
    public void start() {
        List<String> lines = FileUtils.readOrCreateFile(inputFile);
        if (lines == null || lines.isEmpty()) {
            if (lines != null) {
                System.out.println(MessageManager.get("error.empty.array"));
            }
            return;
        }

        int[] nums = parseData(lines);
        if (nums == null || nums.length == 0) {
            return;
        }

        LisSearch.SearchResults results = LisSearch.solve(nums);

        String outputMessage = buildAnswer(results.length(), results.sequence());

        FileUtils.writeOrCreateFile(outputFile, outputMessage);
    }

    private static int[] parseData(List<String> lines) {
        try {
            int n = Integer.parseInt(lines.getFirst().trim());
            if (n <= 0) {
                System.out.println(MessageManager.get("error.empty.array"));
                return null;
            }

            String[] elements = lines.get(1).split("\\s+");

            int[] nums = new int[n];
            for (int i = 0; i < n; i++) {
                nums[i] = Integer.parseInt(elements[i]);
            }

            return nums;

        } catch (NumberFormatException e) {
            System.out.println(MessageManager.get("error.invalid_input.required.int"));
            System.out.println(MessageManager.get("error.file.check_input"));
            return null;
        } catch (IndexOutOfBoundsException e) {
            System.out.println(MessageManager.get("error.invalid_input.required.more"));
            System.out.println(MessageManager.get("error.file.check_input"));
            return null;
        }
    }

    private static String buildAnswer(int maxLisLength, int[] result) {
        StringBuilder sb = new StringBuilder(); // Собираем вывод
        sb.append(maxLisLength).append("\n");
        for (int i = 0; i < maxLisLength; i++) {
            sb.append(result[i]);
            if (i < maxLisLength - 1) {
                sb.append(" ");
            }
        }
        return sb.toString();
    }
}
