package ru.labs.ui.labs;

import ru.labs.core.algorithms.graph.GraphAlgorithm;
import ru.labs.core.algorithms.graph.TopologicalSort;
import ru.labs.core.structures.graph.DirectedGraph;
import ru.labs.util.MessageManager;

import java.util.List;
import java.util.Scanner;

import static ru.labs.util.InputOutputUtils.readInputGraphData;

public class Lab_10_2 implements LabRunner {
    private final DirectedGraph graph;
    private final Scanner scanner;
    private final GraphAlgorithm<DirectedGraph, List<Integer>> algorithm;

    public Lab_10_2(Scanner scanner) {
        this.scanner = scanner;
        this.graph = new DirectedGraph();
        this.algorithm = new TopologicalSort();
    }

    @Override
    public void start() {
        readInputGraphData(scanner, graph);

        try {
            List<Integer> sortedVertices = algorithm.execute(graph);

            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < sortedVertices.size(); i++) {
                sb.append(sortedVertices.get(i));
                if (i < sortedVertices.size() - 1) {
                    sb.append(' ');
                }
            }

            System.out.println(MessageManager.get("menu.lab10.item.2.output.sorted"));
            System.out.println(sb);
        } catch (IllegalStateException e) {
            System.out.println("Ошибка: " + e.getMessage());
        }
    }
}
