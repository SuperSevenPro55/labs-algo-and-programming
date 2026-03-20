package ru.Labs;

import ru.Labs.ui.labs.*;
import ru.Labs.ui.menu.Labs;
import ru.Labs.util.MessageManager;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.Locale;

public class Main {
    private final Map<Labs, Map<Integer, LabRunner>> labs;
    private final Scanner scanner;

    public Main() {
        this.scanner = new Scanner(System.in).useLocale(Locale.US);
        this.labs = new HashMap<>();

        labs.put(Labs.BASE, Map.of(
                1, new Lab_1_1(),
                2, new Lab_1_2()
        ));

        labs.put(Labs.SORTINGS_1, Map.of(
                1, new Lab_2_1(scanner),
                2, new Lab_2_2(scanner),
                3, new Lab_2_3(scanner)
        ));

        labs.put(Labs.SORTINGS_2, Map.of(
                1, new Lab_3_1(scanner),
                2, new Lab_3_2(scanner),
                3, new Lab_3_3()
        ));

        labs.put(Labs.MINS, Map.of(
                1, new Lab_4_1(scanner),
                2, new Lab_4_2(scanner)
        ));

        labs.put(Labs.HASH_MAP, Map.of(
                1, new Lab_5(scanner)
        ));

        labs.put(Labs.BINARY_SEARCH, Map.of(
                1, new Lab_6_1(scanner),
                2, new Lab_6_2(scanner),
                3, new Lab_6_3(scanner)
        ));

        labs.put(Labs.DYNAMIC_PROGRAMMING, Map.of(
                1, new Lab_7_1("roguelike-input.csv", "roguelike-output.txt"),
                2, new Lab_7_2("lis-input.txt", "lis-output.txt"),
                3, new Lab_7_3("lcs-input.txt")
        ));

        labs.put(Labs.REQUESTS, Map.of(
                1, new Lab_8_1("8.1_input.txt", "8.1_output.txt"),
                2, new Lab_8_2()
        ));

        labs.put(Labs.TREES, Map.of(
                1, new Lab_9_1(),
                2, new Lab_9_2()
        ));

        startApp();
    }

    public void startApp() {
        System.out.print(MessageManager.get("app.title"));

        runMainMenu(scanner);

        System.out.println(MessageManager.get("app.exit"));
        scanner.close();
    }

    public static void main(String[] args) {
        new Main();
    }

    public void runMainMenu(Scanner scanner) {
        Labs selected;

        do {
            printMainMenu();

            int choice = readInt(scanner);
            selected = Labs.getById(choice);

            if (selected == Labs.UNKNOWN && choice != 0) {
                System.out.println(MessageManager.get("error.invalid_input"));
            } else if (choice != 0) {
                handleSubMenu(selected, scanner);
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

    private void handleSubMenu(Labs lab, Scanner scanner) {
        MessageManager.printSubMenu("menu.lab" + lab.getId(), lab.getSubItemsCount());

        int choice = readInt(scanner);
        if (choice == 0) return;
        if (choice > lab.getSubItemsCount()) {
            System.out.println(MessageManager.get("error.invalid_input"));
        }
        executeLab(lab, choice);
    }

    private void executeLab(Labs lab, int choice) {
        labs.get(lab).get(choice).start();
    }

    private static int readInt(Scanner scanner) {
        while (!scanner.hasNextInt()) {
            System.out.println(MessageManager.get("error.invalid_input"));
            System.out.println(MessageManager.get("error.try_again"));
            scanner.next();
        }
        return scanner.nextInt();
    }
}