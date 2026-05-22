package ru.labs.core.algorithms.graph;

import ru.labs.core.structures.graph.UndirectedGraph;
import ru.labs.core.structures.vertex.Vertex;
import ru.labs.core.structures.vertex.VertexState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class FindConnectivityComponents implements GraphAlgorithm<UndirectedGraph, List<List<Integer>>>{
    @Override
    public List<List<Integer>> execute(UndirectedGraph graph) {
        List<List<Integer>> components = new ArrayList<>();
        graph.resetVisited();

        for (Vertex vertex : graph.getVertices()) {
            if (vertex.getState() == VertexState.NOT_VISITED) {
                List<Integer> currentComponent = new ArrayList<>();
                dfs(vertex, currentComponent);
                Collections.sort(currentComponent);
                components.add(currentComponent);
            }
        }

        components.sort(Comparator.comparingInt(List::getFirst));
        return components;
    }

    private void dfs(Vertex vertex, List<Integer> component) {
        vertex.setState(VertexState.VISITED);
        component.add(vertex.getId());

        for (Vertex neighbor : vertex.getNeighbors()) {
            if (neighbor.getState() == VertexState.NOT_VISITED) {
                dfs(neighbor, component);
            }
        }
    }
}
