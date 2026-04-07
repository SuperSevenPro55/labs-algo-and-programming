package ru.Labs.ui.labs;

import java.util.Scanner;

import ru.Labs.core.structures.tree.AVLTree;
import ru.Labs.core.structures.tree.BinarySearchTree;
import ru.Labs.util.MessageManager;

public class Lab_9_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_9_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.println(MessageManager.get("menu.lab9.item.1.commands"));
        BinarySearchTree tree = new AVLTree();

        runCommandLoop(tree);
    }

    private void runCommandLoop(BinarySearchTree tree) {
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

    private void processCommand(String commandLine, BinarySearchTree tree) {
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