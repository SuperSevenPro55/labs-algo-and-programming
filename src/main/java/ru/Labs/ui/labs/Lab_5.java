package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.core.structures.hashmap.HashMapImpl;
import ru.Labs.util.MessageManager;

public class Lab_5 {
    public static void start(Scanner scanner) {
        HashMapImpl<String> hashMap = new HashMapImpl<>();
        StringBuilder sb = new StringBuilder();

        processCommand(scanner, hashMap, sb);

        System.out.println(sb.toString().trim());
    }

    private static void processCommand(Scanner scanner, HashMapImpl<String> hashMap, StringBuilder sb) {
        System.out.println(MessageManager.get("menu.lab5.item.1.commands"));
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) {
                continue;
            }
            if (line.equalsIgnoreCase("end")) {
                break;
            }

            String[] parts = line.split("\\s+");
            if (parts.length == 0) {
                continue;
            }

            String command = parts[0];

            switch (command) {
                case "put" -> {
                    if (parts.length >= 3) {
                        hashMap.put(parts[1], parts[2]);
                    }
                }
                case "get" -> {
                    if (parts.length >= 2) {
                        sb.append(hashMap.getValue(parts[1])).append("\n");
                    }
                }
                case "delete" -> {
                    if (parts.length >= 2) {
                        hashMap.delete(parts[1]);
                    }
                }
                default -> System.out.println(MessageManager.get("error.invalid_input"));
            }
        }
    }
}