package ru.Labs.ui.menu;

import ru.Labs.core.structures.tree.AVLTree;
import ru.Labs.core.structures.tree.BinarySearchTree;
import ru.Labs.ui.labs.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.function.Supplier;

public class LabsInit {
    public static Map<Labs, Map<Integer, Supplier<LabRunner>>> createLabs(Scanner scanner) {
        Map<Labs, Map<Integer, Supplier<LabRunner>>> labs = new HashMap<>();

        labs.put(Labs.BASE, Map.of(
                1, Lab_1_1::new,
                2, Lab_1_2::new
        ));

        labs.put(Labs.SORTINGS_1, Map.of(
                1, () -> new Lab_2_1(scanner),
                2, () -> new Lab_2_2(scanner),
                3, () -> new Lab_2_3(scanner)
        ));

        labs.put(Labs.SORTINGS_2, Map.of(
                1, () -> new Lab_3_1(scanner),
                2, () -> new Lab_3_2(scanner),
                3, Lab_3_3::new
        ));

        labs.put(Labs.MINS, Map.of(
                1, () -> new Lab_4_1(scanner),
                2, () -> new Lab_4_2(scanner)
        ));

        labs.put(Labs.HASH_MAP, Map.of(
                1, () -> new Lab_5(scanner)
        ));

        labs.put(Labs.BINARY_SEARCH, Map.of(
                1, () -> new Lab_6_1(scanner),
                2, () -> new Lab_6_2(scanner),
                3, () -> new Lab_6_3(scanner)
        ));

        labs.put(Labs.DYNAMIC_PROGRAMMING, Map.of(
                1, Lab_7_1::new,
                2, Lab_7_2::new,
                3, Lab_7_3::new
        ));

        labs.put(Labs.REQUESTS, Map.of(
                1, Lab_8_1::new,
                2, () -> new Lab_8_2(scanner)
        ));

        labs.put(Labs.TREES, Map.of(
                1, () -> new Lab_9(scanner, new BinarySearchTree()),
                2, () -> new Lab_9(scanner, new AVLTree())
        ));

        return labs;
    }
}