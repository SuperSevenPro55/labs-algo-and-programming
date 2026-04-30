package ru.labs.ui.labs;

import java.util.Scanner;
import ru.labs.core.structures.queue.PriorityQueue;
import ru.labs.core.structures.queue.PriorityQueueImpl;
import ru.labs.util.MessageManager;

public class Lab_4_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_4_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.println(MessageManager.get("menu.lab4.item.2.commands"));
        PriorityQueue<Integer> queue = new PriorityQueueImpl();

        runCommandLoop(queue);
    }

    private void runCommandLoop(PriorityQueue<Integer> queue) {
        while (scanner.hasNextLine()) {
            String commandLine = scanner.nextLine();

            if (commandLine.isEmpty()) {
                continue;
            }

            if (commandLine.equals("exit")) {
                break;
            }

            processCommand(commandLine, queue);
        }
    }

    private void processCommand(String commandLine, PriorityQueue<Integer> queue) {
        String[] parts = commandLine.split("\\s+");
        String command = parts[0].toLowerCase();

        switch (command) {
            case "enqueue" -> {
                int value = Integer.parseInt(parts[1]);
                queue.enqueue(value);
            }

            case "dequeue-max" -> queue.dequeueMax();

            case "inc" -> {
                long operationId = Integer.parseInt(parts[1]);
                int addition = Integer.parseInt(parts[2]);
                queue.increment(operationId, addition);
            }

            default -> System.out.println(MessageManager.get("error.invalid_input"));
        }
    }
}