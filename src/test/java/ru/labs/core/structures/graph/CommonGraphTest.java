package ru.labs.core.structures.graph;

import org.junit.jupiter.api.Test;
import ru.labs.core.structures.vertex.Vertex;
import ru.labs.core.structures.vertex.VertexState;

import static org.junit.jupiter.api.Assertions.*;


public class CommonGraphTest {

    @Test
    public void extendingMethods_AddVertexAndGetVertex_correctAddAndGetVertex() {
        CommonGraph graph = new DirectedGraph();
        graph.addVertex(1);
        graph.addVertex(2);

        assertNotNull(graph.getVertex(1));
        assertNotNull(graph.getVertex(2));
        assertNull(graph.getVertex(3));
        assertEquals(2, graph.getVertices().size());
    }

    @Test
    public void extendingMethod_resetVisited_stateShouldBeReset() {
        CommonGraph graph = new DirectedGraph();
        graph.addVertex(1);

        Vertex vertex = graph.getVertex(1);
        vertex.setState(VertexState.VISITED);

        graph.resetVisited();

        assertEquals(VertexState.NOT_VISITED, vertex.getState());
    }
}
