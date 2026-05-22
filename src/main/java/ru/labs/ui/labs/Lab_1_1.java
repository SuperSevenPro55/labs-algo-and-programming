package ru.labs.ui.labs;

import ru.labs.core.algorithms.search.ValidBracketsSearch;
import ru.labs.util.MessageManager;

import java.util.Scanner;

public class Lab_1_1 implements LabRunner {
    private final Scanner scanner;

    public Lab_1_1(Scanner scanner) {
        this.scanner = scanner;
    }

    @Override
    public void start() {
        System.out.println(MessageManager.get("menu.lab1.item.1.enter_brackets"));
        String brackets = scanner.next();

        boolean isValid;

        try {
            isValid = ValidBracketsSearch.isValid(brackets);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
            return;
        }

        if (isValid) {
            System.out.println(MessageManager.get("menu.lab1.item.1.valid"));
        } else {
            System.out.println(MessageManager.get("menu.lab1.item.1.wrong"));
        }
    }
}