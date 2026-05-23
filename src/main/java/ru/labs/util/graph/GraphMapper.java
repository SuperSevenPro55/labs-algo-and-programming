package ru.labs.util.graph;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GraphMapper {
    public record GraphContext(Map<Long, Node> nodesById, Map<Long, List<Edge>> adjacencyList) {}

    public static GraphContext mapGraph(List<Node> rawNodes, List<Edge> rawEdges) {
        Map<Long, Node> nodesById = new HashMap<>();
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        for (Node node : rawNodes) {
            node.setX(node.getLongitude());
            node.setY(node.getLatitude());

            nodesById.put(node.getId(), node);
            adjacencyList.put(node.getId(), new ArrayList<>());
        }

        for (Edge edge : rawEdges) {
            Node u = nodesById.get(edge.getU());
            Node v = nodesById.get(edge.getV());

            if (u != null && v != null) {
                edge.setUX(u.getX());
                edge.setUY(u.getY());
                edge.setVX(v.getX());
                edge.setVY(v.getY());

                double distance = DistanceUtils.euclideanDistance(u.getX(), u.getY(), v.getX(), v.getY());
                edge.setDistance(Math.round(distance));

                adjacencyList.get(u.getId()).add(edge);
                adjacencyList.get(v.getId()).add(edge);
            }
        }

        return new GraphContext(nodesById, adjacencyList);
    }
}
