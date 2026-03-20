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

    @Override
    public void start() {
        List<String> lines = FileUtils.readOrCreateFile(inputFile);
        if (lines.isEmpty()) {
            System.out.println(MessageManager.get("error.empty.array"));
            return;
        }

        int[] nums = FileUtils.parseData(lines);
        if (nums == null || nums.length == 0) {
            return;
        }

        LisSearch.SearchResults results = LisSearch.solve(nums);

        String outputMessage = buildAnswer(results.length(), results.sequence());

        FileUtils.writeOrCreateFile(outputFile, outputMessage);
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
