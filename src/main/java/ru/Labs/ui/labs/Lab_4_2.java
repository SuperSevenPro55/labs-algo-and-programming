package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.core.structures.queue.PriorityQueue;
import ru.Labs.core.structures.queue.PriorityQueueImpl;
import ru.Labs.util.MessageManager;

public class Lab_4_2 {
    public static void start(Scanner scanner) {
        StringBuilder sb = new StringBuilder();
        PriorityQueue<Integer> queue = new PriorityQueueImpl();

        System.out.println(MessageManager.get("menu.lab4.item.2.commands"));

        runCommandLoop(scanner, queue, sb);
    }

    private static void runCommandLoop(Scanner scanner, PriorityQueue<Integer> queue, StringBuilder sb) {
        while (true) {
            String command = scanner.next();

            if (command.equals("exit")) {
                break;
            }

            processCommand(scanner, command, queue, sb);
        }
    }

    private static void processCommand(Scanner scanner, String command, PriorityQueue<Integer> queue, StringBuilder sb) {
        switch (command) {
            case "enqueue" -> {
                queue.enqueue(0);
            }
            case "dequeue-max" -> {
                queue.dequeueMax();
            }
            case "inc" -> {
                queue.increment(0, 1);
            }

            default -> System.out.println(MessageManager.get("error.invalid_input"));
        }
    }
}
