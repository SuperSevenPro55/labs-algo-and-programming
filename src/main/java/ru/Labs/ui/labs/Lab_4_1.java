package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.core.structures.stack.MinStack;
import ru.Labs.core.structures.stack.MinStackImpl;
import ru.Labs.util.MessageManager;

public class Lab_4_1 {
    public static void start(Scanner scanner) {
        StringBuilder sb = new StringBuilder(); // Для конечного ответа
        MinStack<Integer> stack = new MinStackImpl();

        System.out.println(MessageManager.get("menu.lab4.item.1.commands"));

        runCommandLoop(scanner, stack, sb);

        printOutputStack(sb);
    }

    private static void runCommandLoop(Scanner scanner, MinStack<Integer> stack, StringBuilder sb) {
        while (true) {
            String command = scanner.next();

            if (command.equals("exit")) {
                break;
            }

            processCommand(scanner, command, stack, sb);
        }
    }

    private static void processCommand(Scanner scanner, String command, MinStack<Integer> stack, StringBuilder sb) {
        switch (command) {
            case "push" -> {
                if (scanner.hasNextInt()) {
                    int data = scanner.nextInt();
                    stack.push(data);
                } else {
                    System.out.println(MessageManager.get("error.invalid_input.required.int"));
                    scanner.next();
                }
            }
            case "pop" -> {
                if (stack.isEmpty()) {
                    System.out.println(MessageManager.get("error.empty_stack"));
                } else {
                    stack.pop();
                }
            }
            case "top" -> {
                if (stack.isEmpty()) {
                    System.out.println(MessageManager.get("error.empty_stack"));
                } else {
                    sb.append(stack.top()).append('\n');
                }
            }
            case "min" -> {
                if (stack.isEmpty()) {
                    System.out.println(MessageManager.get("error.empty_stack"));
                } else {
                    sb.append(stack.min()).append('\n');
                }
            }
            default -> System.out.println(MessageManager.get("error.invalid_input"));
        }
    }

    private static void printOutputStack(StringBuilder sb) {
        System.out.println(MessageManager.get("menu.lab4.item.1.output"));
        System.out.println(sb);
    }
}
