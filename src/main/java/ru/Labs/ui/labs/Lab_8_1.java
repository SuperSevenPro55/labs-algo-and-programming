package ru.Labs.ui.labs;

import java.util.List;
import ru.Labs.util.FileUtils;
import ru.Labs.util.MessageManager;
import ru.Labs.core.algorithms.search.PrefixSumSearch;

public class Lab_8_1 {
    public static final String inputFile = "8.1_input.txt";
    public static final String outputFile = "8.1_output.txt";

    public static void start() {
        List<String> lines = FileUtils.readOrCreateFile(inputFile);
        if (lines.size() < 2) {
            System.out.println(MessageManager.get("error.invalid_input.required.more"));
            return;
        }

        int[] array = FileUtils.parseData(lines);
        if (array == null || array.length == 0) {
            return;
        }

        PrefixSumSearch search = new PrefixSumSearch(array);

        StringBuilder sb = new StringBuilder();

        try {
            for (int i = 2; i < lines.size(); i++) {
                String[] command = lines.get(i).trim().split("\\s+");
                if (command.length >= 2) {
                    int left = Integer.parseInt(command[0]);
                    int right = Integer.parseInt(command[1]);

                    sb.append(search.getSum(left, right)).append("\n");
                } else {
                    System.out.println(MessageManager.get("menu.lab8.item.1.command_passed") + " " + lines.get(i));
                }
            }
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            System.out.println(MessageManager.get("error.file.check_input"));
            return;
        }

        FileUtils.writeOrCreateFile(outputFile, sb.toString().trim());
    }
}