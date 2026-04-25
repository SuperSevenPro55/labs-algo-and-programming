package ru.Labs.ui.menu;

import java.util.Map;
import java.util.Scanner;
import java.util.Locale;
import java.util.function.Supplier;

import ru.Labs.ui.labs.LabRunner;
import ru.Labs.util.MessageManager;

public class Application {
    private final Map<Labs, Map<Integer, Supplier<LabRunner>>> labs;
    private final Scanner scanner;

    public Application() {
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
        this.labs = LabsInit.createLabs(this.scanner);
    }

    public void start() {

        runMainMenu();

        System.out.println(MessageManager.get("app.exit"));
        scanner.close();
    }

    private void runMainMenu() {
        Labs selected;

        do {
            printMainMenu();

            int choice = readInt();
            selected = Labs.getById(choice);

            if (selected == Labs.UNKNOWN && choice != 0) {
                System.out.println(MessageManager.get("error.invalid_input"));
            } else if (choice != 0) {
                handleSubMenu(selected);
            }

        } while (selected != Labs.UNKNOWN || selected.getId() != 0);
    }

    private static void printMainMenu() {
        System.out.println("\n" + MessageManager.get("menu.main.header"));
        for (Labs lab : Labs.values()) {
            if (lab != Labs.UNKNOWN) {
                System.out.println(MessageManager.get("menu.lab" + lab.getId()));
            }
        }
        System.out.println(MessageManager.get("menu.exit"));
        System.out.println(MessageManager.get("menu.main.prompt"));
    }

    private void handleSubMenu(Labs lab) {
        MessageManager.printSubMenu("menu.lab" + lab.getId(), lab.getSubItemsCount());

        int choice = readInt();
        if (choice == 0) return;
        if (choice > lab.getSubItemsCount()) {
            System.out.println(MessageManager.get("error.invalid_input"));
        }
        executeLab(lab, choice);
    }

    private void executeLab(Labs lab, int choice) {
        labs.get(lab).get(choice).get().start();
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.println(MessageManager.get("error.invalid_input"));
            System.out.println(MessageManager.get("error.try_again"));
            scanner.next();
        }
        return scanner.nextInt();
    }
}