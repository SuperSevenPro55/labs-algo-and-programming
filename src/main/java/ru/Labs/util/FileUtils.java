package ru.Labs.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    public static List<String> readOrCreateFile(String fileName) {
        Path path = Paths.get(fileName);

        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
                System.out.println(MessageManager.get("file.created"));
                System.out.println(MessageManager.get("file.request"));
                return new ArrayList<>();
            } catch (IOException e) {
                System.out.println(MessageManager.get("error.file.create"));
                return new ArrayList<>();
            }
        }

        try {
            return Files.readAllLines(path);
        } catch (IOException e) {
            System.out.println(MessageManager.get("error.file.read"));
            return new ArrayList<>();
        }
    }

    public static void writeOrCreateFile(String fileName, String content) {
        Path path = Paths.get(fileName);

        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
                System.out.println(MessageManager.get("file.created"));
            } catch (IOException e) {
                System.out.println(MessageManager.get("error.file.create"));
                return;
            }
        }

        try {
            Files.write(path, content.getBytes());
            System.out.println(MessageManager.get("file.output"));
        } catch (IOException e) {
            System.out.println(MessageManager.get("error.file.write"));
        }
    }

    public static int[] parseData(List<String> lines) {
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
