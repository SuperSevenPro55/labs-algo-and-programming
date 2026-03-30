package ru.Labs.ui.labs;

import java.util.Scanner;
import ru.Labs.core.structures.queue.PriorityQueue;
import ru.Labs.core.structures.queue.PriorityQueueImpl;
import ru.Labs.util.MessageManager;

public class Lab_4_2 implements LabRunner {
    private final Scanner scanner;

    public Lab_4_2(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.println(MessageManager.get("menu.lab4.item.2.commands"));
        PriorityQueue<Integer> queue = new PriorityQueueImpl();


    }
}