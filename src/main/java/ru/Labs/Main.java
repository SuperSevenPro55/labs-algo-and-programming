import ru.Labs.ui.labs.*;
import ru.Labs.ui.menu.Labs;
import ru.Labs.util.MessageManager;
import java.util.Scanner;
import java.util.Locale;

void main() {
    Scanner scanner = new Scanner(System.in).useLocale(Locale.US);

    System.out.print(MessageManager.get("app.title"));

    runMainMenu(scanner);

    System.out.println(MessageManager.get("app.exit"));
    scanner.close();
}

public static void runMainMenu(Scanner scanner) {
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

private static void handleSubMenu(Labs lab, Scanner scanner) {
    MessageManager.printSubMenu("menu.lab" + lab.getId(), lab.getSubItemsCount());

    int choice = readInt(scanner);
    if (choice == 0) return;

    executeLab(lab, choice, scanner);
}

private static void executeLab(Labs lab, int choice, Scanner scanner) {
    switch (lab) {
        case BASE -> {
            if (choice == 1) Lab_1_1.start();
            if (choice == 2) Lab_1_2.start();
        }
        case SORTINGS_1 -> {
            if (choice == 1) Lab_2_1.start(scanner);
            if (choice == 2) Lab_2_2.start(scanner);
            if (choice == 3) Lab_2_3.start(scanner);
        }
        case SORTINGS_2 -> {
            if (choice == 1) Lab_3_1.start(scanner);
            if (choice == 2) Lab_3_2.start(scanner);
            if (choice == 3) Lab_3_3.start();
        }
        case MINS -> {
            if (choice == 1) Lab_4_1.start(scanner);
            if (choice == 2) Lab_4_2.start();
        }
        case HASH_MAP -> {
            if (choice == 1) Lab_5.start(scanner);
        }

        case BINARY_SEARCH -> {
            if (choice == 1) Lab_6_1.start(scanner);
            if (choice == 2) Lab_6_2.start(scanner);
            if (choice == 3) Lab_6_3.start(scanner);
        }
        case DYNAMIC_PROGRAMMING -> {
            if (choice == 1) Lab_7_1.start();
            if (choice == 2) Lab_7_2.start();
            if (choice == 3) Lab_7_3.start();
        }
        case REQUESTS -> {
            if (choice == 1) Lab_8_1.start();
            if (choice == 2) Lab_8_2.start();
        }
        case TREES -> {
            if (choice == 1) Lab_9_1.start();
            if (choice == 2) Lab_9_2.start();
        }
    }
}

private static int readInt(Scanner scanner) {
    while (!scanner.hasNextInt()) {
        System.out.println(MessageManager.get("error.invalid_input"));
        System.out.println(MessageManager.get("error.try_again"));
        scanner.next();
    }
    return scanner.nextInt();
}