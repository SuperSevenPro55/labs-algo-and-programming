package ru.Labs.ui.labs;

import java.util.List;
import java.util.Scanner;
import ru.Labs.core.structures.graph.Graph;
import ru.Labs.util.MessageManager;

public class Lab_10_1 implements LabRunner {
    private final Graph graph;
    private final Scanner scanner;

    public Lab_10_1(Scanner scanner) {
        this.scanner = scanner;
        this.graph = new Graph();
    }

    @Override
    public void start() {
        System.out.println(MessageManager.get("menu.lab10.item.1.input.vertices.count"));
        int n = scanner.nextInt();
        System.out.println(MessageManager.get("menu.lab10.item.1.input.edges.count"));
        int m = scanner.nextInt();

        for (int i = 0; i < n; i++) {
            graph.addVertex(i);
        }

        System.out.println(MessageManager.get("menu.lab10.item.1.input.edges"));

        for (int i = 0; i < m; i++) {
            int u = scanner.nextInt();
            int v = scanner.nextInt();

            graph.addEdge(u, v);
        }

        List<List<Integer>> components = graph.findConnectivityComponents();

        System.out.println(MessageManager.get("menu.lab10.item.1.output.components"));
        for (List<Integer> component : components) {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < component.size(); i++) {
                sb.append(component.get(i));
                sb.append(" ");
            }

            System.out.println(sb);
        }
    }
}
