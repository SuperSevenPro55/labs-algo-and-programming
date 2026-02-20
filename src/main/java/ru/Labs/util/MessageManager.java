package ru.Labs.util;

import java.util.ResourceBundle;

public class MessageManager {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("messages");

    public static String get(String key) {
        return bundle.getString(key);
    }

    public static void printSubMenu(String prefix, int count) {
        System.out.println("\n" + get(prefix + ".header"));
        for (int i = 1; i <= count; i++) {
            System.out.println(get(prefix + ".item." + i));
        }
        System.out.println(get("menu.main.exit"));
        System.out.println(get("menu.main.prompt"));
    }
}
