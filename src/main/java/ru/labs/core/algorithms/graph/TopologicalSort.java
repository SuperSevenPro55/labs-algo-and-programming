package ru.labs.core.algorithms.graph;

import ru.labs.core.structures.graph.DirectedGraph;
import ru.labs.core.structures.vertex.Vertex;
import ru.labs.core.structures.vertex.VertexState;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TopologicalSort implements GraphAlgorithm<DirectedGraph, List<Integer>> {
    @Override
    public List<Integer> execute(DirectedGraph graph) {
        List<Integer> result = new ArrayList<>();
        graph.resetVisited();

        for (Vertex vertex : graph.getVertices()) {
            if (vertex.getState() == VertexState.NOT_VISITED) {
                if (hasCycle(vertex, result)) {
                    throw new IllegalStateException("Найден цикл. Сортировка невозможна.");
                }
            }
        }

        Collections.reverse(result);
        return result;
    }

    private boolean hasCycle(Vertex vertex, List<Integer> result) {
        vertex.setState(VertexState.VISITING);

        for (Vertex neighbor : vertex.getNeighbors()) {
            if (neighbor.getState() == VertexState.VISITING) {
                return true;
            }

            if (neighbor.getState() == VertexState.NOT_VISITED) {
                if (hasCycle(neighbor, result)) {
                    return true;
                }
            }
        }

        vertex.setState(VertexState.VISITED);
        result.add(vertex.getId());

        return false;
    }
}
