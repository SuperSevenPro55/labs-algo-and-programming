package ru.labs.util;

import org.junit.jupiter.api.Test;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.util.graph.GraphMapper.GraphContext;
import ru.labs.util.graph.GraphMapper;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class GraphMapperTest {

    @Test
    public void mapGraph_validData_buildsCorrectContext() {
        Node n1 = new Node(1L, 73.1, 54.1);
        Node n2 = new Node(2L, 73.2, 54.2);
        Edge e1 = new Edge(1L, 2L);

        List<Node> rawNodes = List.of(n1, n2);
        List<Edge> rawEdges = List.of(e1);

        GraphContext context = GraphMapper.mapGraph(rawNodes, rawEdges);

        assertEquals(2, context.nodesById().size());
        assertEquals(73.1, context.nodesById().get(1L).getX());
        assertEquals(54.1, context.nodesById().get(1L).getY());

        assertEquals(2, context.adjacencyList().size());
        assertEquals(1, context.adjacencyList().get(1L).size());
        assertEquals(1, context.adjacencyList().get(2L).size());

        Edge mappedEdge = context.adjacencyList().get(1L).getFirst();
        assertNotNull(mappedEdge.getDistance(), "Дистанция должна быть рассчитана");
        assertEquals(73.1, mappedEdge.getUX());
        assertEquals(54.1, mappedEdge.getUY());
    }

    @Test
    public void mapGraph_edgeWithMissingNode_ignoresInvalidEdge() {
        Node n1 = new Node(1L, 10.0, 10.0);
        Edge invalidEdge = new Edge(1L, 2L);

        GraphContext context = GraphMapper.mapGraph(List.of(n1), List.of(invalidEdge));

        assertTrue(context.adjacencyList().get(1L).isEmpty(), "Ребро с несуществующим узлом должно быть проигнорировано");
    }
}