package ru.labs.core.algorithms.pathfinding;

import org.junit.jupiter.api.Test;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class DijkstraRouteBuilderTest {

    private Map<Long, Node> createNodes(long... ids) {
        Map<Long, Node> nodes = new HashMap<>();
        for (long id : ids) {
            nodes.put(id, new Node(id, 0.0, 0.0));
        }
        return nodes;
    }

    private void addEdge(long u, long v, long distance, Map<Long, List<Edge>> adjacencyList) {
        Edge edge = new Edge(u, v);
        edge.setDistance(distance);

        adjacencyList.computeIfAbsent(u, k -> new ArrayList<>()).add(edge);
        adjacencyList.computeIfAbsent(v, k -> new ArrayList<>()).add(edge);
    }

    @Test
    public void createRoute_optimalBypass_findsShorterIndirectPath() {
        Map<Long, Node> nodes = createNodes(1, 2, 3);
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        addEdge(1, 2, 10, adjacencyList);
        addEdge(1, 3, 2, adjacencyList);
        addEdge(3, 2, 2, adjacencyList);

        RouteBuilder algorithm = new DijkstraRouteBuilder(nodes, adjacencyList);

        List<Edge> result = algorithm.createRoute(nodes.get(1L), nodes.get(2L), null);

        assertEquals(2, result.size(), "Должен выбрать путь в обход через 2 ребра");

        assertTrue(result.get(0).getU() == 3 || result.get(0).getV() == 3);
        assertTrue(result.get(1).getU() == 3 || result.get(1).getV() == 3);
    }

    @Test
    public void createRoute_straightPath_findsCorrectPath() {
        Map<Long, Node> nodes = createNodes(1, 2, 3, 4);
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        addEdge(1, 2, 5, adjacencyList);
        addEdge(2, 3, 5, adjacencyList);
        addEdge(3, 4, 5, adjacencyList);

        RouteBuilder algorithm = new DijkstraRouteBuilder(nodes, adjacencyList);

        List<Edge> result = algorithm.createRoute(nodes.get(1L), nodes.get(4L), null);

        assertEquals(3, result.size(), "Должен пройти через все 3 ребра по прямой");
    }

    @Test
    public void createRoute_unreachableTarget_returnsEmptyList() {
        Map<Long, Node> nodes = createNodes(1, 2);
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        RouteBuilder algorithm = new DijkstraRouteBuilder(nodes, adjacencyList);
        List<Edge> result = algorithm.createRoute(nodes.get(1L), nodes.get(2L), null);

        assertTrue(result.isEmpty(), "Если пути нет, должен вернуться пустой список");
    }
}