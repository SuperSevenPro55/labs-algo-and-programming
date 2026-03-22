package ru.Labs.util;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class FileUtils {
    private static final String DATA_DIR = "data";

    private static Path resolvePath(String fileName) throws IOException {
        Path dirPath = Paths.get(DATA_DIR);
        if (Files.notExists(dirPath)) {
            try {
                Files.createDirectories(dirPath);
            } catch (IOException e) {
                System.out.println(MessageManager.get("error.file.create_dir"));
                throw e;
            }
        }
        return dirPath.resolve(fileName);
    }

    public static List<String> readOrCreateFile(String fileName) {
        try {
            Path path = resolvePath(fileName);

            if (Files.notExists(path)) {
                try {
                    Files.createFile(path);
                    System.out.println(MessageManager.get("file.created") + ": " + path.toAbsolutePath());
                    System.out.println(MessageManager.get("file.request"));
                    return null;
                } catch (IOException e) {
                    System.out.println(MessageManager.get("error.file.create"));
                    return new ArrayList<>();
                }
            }

            return Files.readAllLines(path);

        } catch (IOException e) {
            System.out.println(MessageManager.get("error.file.read"));
            return null;
        }
    }

    public static void writeOrCreateFile(String fileName, String content) {
        try {
            Path path = resolvePath(fileName);

            if (Files.notExists(path)) {
                try {
                    Files.createFile(path);
                    System.out.println(MessageManager.get("file.created") + ": " + path.toAbsolutePath());
                } catch (IOException e) {
                    System.out.println(MessageManager.get("error.file.create"));
                    return;
                }
            }

            Files.writeString(path, content);
            System.out.println(MessageManager.get("file.output"));

        } catch (IOException e) {
            System.out.println(MessageManager.get("error.file.write"));
        }
    }
}