package ru.Labs.core.structures.graph;

import java.util.*;

public class Graph {
    private final Map<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(int id) {
        vertices.putIfAbsent(id, new Vertex(id));
    }

    public void addEdge(int u, int v) {
        addVertex(u);
        addVertex(v);
        Vertex vU = vertices.get(u);
        Vertex vV = vertices.get(v);
        vU.addNeighbor(vV);
        vV.addNeighbor(vU);
    }

    public List<List<Integer>> findConnectivityComponents() {
        List<List<Integer>> components = new ArrayList<>();

        for (Vertex vertex : vertices.values()) {
            if (!vertex.isVisited()) {
                List<Integer> currentComponent = new ArrayList<>();
                dfs(vertex, currentComponent);
                Collections.sort(currentComponent);
                components.add(currentComponent);
            }
        }

        components.sort(Comparator.comparingInt(List::getFirst));
        return components;
    }

    public void dfs(Vertex vertex, List<Integer> component) {
        vertex.setVisited(true);
        component.add(vertex.getId());

        for (Vertex neighbor : vertex.getNeighbors()) {
            if (!neighbor.isVisited()) {
                dfs(neighbor, component);
            }
        }
    }
}
