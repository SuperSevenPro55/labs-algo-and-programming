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
}
