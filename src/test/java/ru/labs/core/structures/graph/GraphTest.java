package ru.labs.core.structures.graph;

import org.junit.jupiter.api.Test;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

public class GraphTest {

    @Test
    public void testFindConnectivityComponents() {
        Graph graph = new Graph();
        
        // Component 1: 0-1-2
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        
        // Component 2: 3-4
        graph.addEdge(3, 4);
        
        // Component 3: 5
        graph.addVertex(5);

        List<List<Integer>> components = graph.findConnectivityComponents();

        assertEquals(3, components.size(), "Should have 3 connected components");
        
        // Verify contents
        assertTrue(components.contains(List.of(0, 1, 2)));
        assertTrue(components.contains(List.of(3, 4)));
        assertTrue(components.contains(List.of(5)));
    }

    @Test
    public void testEmptyGraph() {
        Graph graph = new Graph();
        List<List<Integer>> components = graph.findConnectivityComponents();
        assertTrue(components.isEmpty(), "Empty graph should have 0 components");
    }

    @Test
    public void testSingleVertex() {
        Graph graph = new Graph();
        graph.addVertex(1);
        List<List<Integer>> components = graph.findConnectivityComponents();
        assertEquals(1, components.size());
        assertEquals(List.of(1), components.getFirst());
    }
}
