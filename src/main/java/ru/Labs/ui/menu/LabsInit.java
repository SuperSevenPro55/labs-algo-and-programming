package ru.Labs.ui.menu;

import ru.Labs.ui.labs.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class LabsInit {
    public static Map<Labs, Map<Integer, LabRunner>> createLabs(Scanner scanner) {
        Map<Labs, Map<Integer, LabRunner>> labs = new HashMap<>();

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

        return labs;
    }
}