package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.core.structures.tree.ITree;
import ru.Labs.util.MessageManager;

public class Lab_9 implements LabRunner {
    private final Scanner scanner;
    private final ITree tree;

    public Lab_9(Scanner scanner, ITree tree) {
        this.scanner = scanner;
        this.tree = tree;
    }

    @Override
    public void start() {
        System.out.println(MessageManager.get("menu.lab9.item.1.commands"));

        runCommandLoop(tree);
    }

    private void runCommandLoop(ITree tree) {
        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine();

            if (commandLine.isEmpty()) {
                continue;
            }

            if (commandLine.equals("exit")) {
                break;
            }

            processCommand(commandLine, tree);
        }
    }

    private void processCommand(String commandLine, ITree tree) {
        String[] parts = commandLine.split("\\s+");
        String command = parts[0].toLowerCase();

        try {
            switch (command) {
                case "insert" -> tree.insert(Integer.parseInt(parts[1]));

                case "delete" -> tree.delete(Integer.parseInt(parts[1]));

                case "has" -> {
                    if (tree.hasValue(Integer.parseInt(parts[1]))) {
                        System.out.println("t");
                    } else {
                        System.out.println("f");
                    }
                }

                case "next" -> System.out.println(tree.next(Integer.parseInt(parts[1])));

                case "prev" -> System.out.println(tree.prev(Integer.parseInt(parts[1])));

                default -> System.out.println(MessageManager.get("error.invalid_input"));
            }
        } catch (Exception ignored) {
            System.out.println(MessageManager.get("error.invalid_input"));
        }
    }
}