package ru.labs.core.algorithms.graph;

import org.junit.jupiter.api.Test;
import ru.labs.core.structures.graph.DirectedGraph;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class TopologicalSortTest {
    @Test
    public void topologicalSort_directedGraphWithoutCycles_successfulSort() {
        DirectedGraph graph = new DirectedGraph();
        GraphAlgorithm<DirectedGraph, List<Integer>> algorithm = new TopologicalSort();

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(1, 4);

        List<Integer> result = algorithm.execute(graph);

        assertTrue(result.indexOf(1) < result.indexOf(2));
        assertTrue(result.indexOf(1) < result.indexOf(4));
        assertTrue(result.indexOf(2) < result.indexOf(3));
        assertEquals(4, result.size());
    }

    @Test
    public void cycleDetection_directedGraphWithCycle_throwsException() {
        DirectedGraph graph = new DirectedGraph();
        GraphAlgorithm<DirectedGraph, List<Integer>> algorithm = new TopologicalSort();

        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        graph.addEdge(3, 1);

        assertThrows(IllegalStateException.class, () -> algorithm.execute(graph));
    }
}
