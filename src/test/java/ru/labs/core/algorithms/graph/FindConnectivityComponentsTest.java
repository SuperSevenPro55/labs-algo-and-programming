package ru.labs.core.algorithms.graph;

import org.junit.jupiter.api.Test;
import ru.labs.core.structures.graph.UndirectedGraph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class FindConnectivityComponentsTest {
    @Test
    public void findConnectivityComponentsAlgorithm_emptyUndirectedGraph_isEmptyTrue() {
        UndirectedGraph graph = new UndirectedGraph();
        GraphAlgorithm<UndirectedGraph, List<List<Integer>>> algorithm = new FindConnectivityComponents();

        List<List<Integer>> components = algorithm.execute(graph);
        assertTrue(components.isEmpty(), "Empty graph should have 0 components");
    }

    @Test
    public void findConnectivityComponentsAlgorithm_filledCorrectUndirectedGraph_findCorrectConnectivityComponents() {
        UndirectedGraph graph = new UndirectedGraph();
        GraphAlgorithm<UndirectedGraph, List<List<Integer>>> algorithm = new FindConnectivityComponents();

        graph.addEdge(0, 1);
        graph.addEdge(1, 2);

        graph.addEdge(3, 4);

        graph.addVertex(5);

        List<List<Integer>> components = algorithm.execute(graph);

        assertEquals(3, components.size(), "Should have 3 connected components");

        assertTrue(components.contains(List.of(0, 1, 2)));
        assertTrue(components.contains(List.of(3, 4)));
        assertTrue(components.contains(List.of(5)));
    }

}
