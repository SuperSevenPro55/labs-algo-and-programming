package ru.labs.core.structures.graph;

import org.junit.jupiter.api.Test;
import ru.labs.core.structures.vertex.Vertex;

import static org.junit.jupiter.api.Assertions.*;

public class DirectedGraphTest {
    @Test
    public void uniqueMethod_addEdge_isUnidirectional() {
        DirectedGraph graph = new DirectedGraph();
        graph.addEdge(1, 2);

        Vertex v1 = graph.getVertex(1);
        Vertex v2 = graph.getVertex(2);

        assertTrue(v1.getNeighbors().contains(v2), "Vertex v1 should point to v2");
        assertFalse(v2.getNeighbors().contains(v1), "Vertex v2 should point to v1");
    }
}
