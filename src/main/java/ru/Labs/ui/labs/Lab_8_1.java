package ru.Labs.ui.labs;

import ru.Labs.util.Requests;

import java.util.List;

public class Lab_8_1 {
    public static void start() throws Exception {
        String fileSource = "src/main/resources/8.1_input.txt";

        Requests requests = new Requests();
        List<Integer> sums = requests.sums(fileSource);
        sums.forEach(System.out::println);
    }
}