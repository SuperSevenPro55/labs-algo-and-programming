package ru.Labs.ui.labs;

import java.util.List;
import ru.Labs.util.FileUtils;
import ru.Labs.util.MessageManager;
import ru.Labs.core.algorithms.search.PrefixSumSearch;

public class Lab_8_1 implements LabRunner {
    private final String inputFile;
    private final String outputFile;

    public Lab_8_1(String inputFile, String outputFile) {
        this.inputFile = inputFile;
        this.outputFile = outputFile;
    }

    @Override
    public void start() {
        List<String> lines = FileUtils.readOrCreateFile(inputFile);
        if (lines == null) {
            return;
        }

        if (lines.size() < 2) {
            System.out.println(MessageManager.get("error.invalid_input.required.more"));
            return;
        }

        int[] array = parseData(lines);
        if (array == null || array.length == 0) {
            return;
        }

        StringBuilder sb = new StringBuilder();
        try {
            PrefixSumSearch search = new PrefixSumSearch(array);

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
        } catch (IllegalArgumentException e) {
            System.out.println(MessageManager.get("error.invalid_input.required.more"));
            return;
        }

        FileUtils.writeOrCreateFile(outputFile, sb.toString().trim());
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
}