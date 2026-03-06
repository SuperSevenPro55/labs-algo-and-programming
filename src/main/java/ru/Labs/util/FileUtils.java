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
                System.out.println(MessageManager.get("menu.lab7.item.1.file_created"));
                System.out.println(MessageManager.get("menu.lab7.item.1.file_input_request"));
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

    public static void writeOrCreateFile(String fileName, long maxMoney, String pathFounded) {
        Path path = Paths.get(fileName);
        String output = MessageManager.get("menu.lab7.item.1.output_money") + maxMoney + "\n" +
                MessageManager.get("menu.lab7.item.1.output_path") + pathFounded;

        if (Files.notExists(path)) {
            try {
                Files.createFile(path);
                System.out.println(MessageManager.get("menu.lab7.item.1.file_created"));
            } catch (IOException e) {
                System.out.println(MessageManager.get("error.file.create"));
                return;
            }
        }

        try {
            Files.write(path, output.getBytes());
            System.out.println(MessageManager.get("menu.lab7.item.1.file_output"));
        } catch (IOException e) {
            System.out.println(MessageManager.get("error.file.write"));
        }
    }
}
