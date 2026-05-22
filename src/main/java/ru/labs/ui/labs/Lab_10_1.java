package ru.labs.ui.labs;

import java.util.List;
import java.util.Scanner;

import ru.labs.core.algorithms.graph.FindConnectivityComponents;
import ru.labs.core.algorithms.graph.GraphAlgorithm;
import ru.labs.core.structures.graph.UndirectedGraph;
import ru.labs.util.MessageManager;

import static ru.labs.util.InputOutputUtils.readInputGraphData;

public class Lab_10_1 implements LabRunner {
    private final UndirectedGraph graph;
    private final Scanner scanner;
    private final GraphAlgorithm<UndirectedGraph, List<List<Integer>>> algorithm;

    public Lab_10_1(Scanner scanner) {
        this.scanner = scanner;
        this.graph = new UndirectedGraph();
        this.algorithm = new FindConnectivityComponents();
    }

    @Override
    public void start() {
        readInputGraphData(scanner, graph);

        List<List<Integer>> components = algorithm.execute(graph);

        System.out.println(MessageManager.get("menu.lab10.item.1.output.components"));
        for (List<Integer> component : components) {
            StringBuilder sb = new StringBuilder();
            for (Integer integer : component) {
                sb.append(integer);
                sb.append(" ");
            }

            System.out.println(sb);
        }
    }
}
