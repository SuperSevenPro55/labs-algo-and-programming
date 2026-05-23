package ru.labs.core.algorithms.pathfinding;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.*;
import java.util.function.Consumer;

public class DijkstraRouteBuilder implements RouteBuilder {
    private final Map<Long, Node> nodesById;
    private final Map<Long, List<Edge>> adjacencyList;

    public DijkstraRouteBuilder(Map<Long, Node> nodesById, Map<Long, List<Edge>> adjacencyList) {
        this.nodesById = nodesById;
        this.adjacencyList = adjacencyList;
    }

    private record QueueItem(Node node, Double currentCost) implements Comparable<QueueItem> {
        @Override
        public int compareTo(QueueItem other) {
            return Double.compare(this.currentCost, other.currentCost);
        }
    }

    @Override
    public List<Edge> createRoute(Node start, Node finish, Consumer<Node> onNodeExplored) {
        Map<Long, Double> distances = new HashMap<>(); // Мин. расстояние до узла
        Map<Long, Edge> previousEdges = new HashMap<>(); // Откуда пришли
        PriorityQueue<QueueItem> queue = new PriorityQueue<>();

        // Инициализация
        for (Long nodeId : nodesById.keySet()) {
            distances.put(nodeId, Double.MAX_VALUE);
        }
        distances.put(start.getId(), 0.0);
        queue.add(new QueueItem(start, 0.0));

        while (!queue.isEmpty()) {
            QueueItem currentItem = queue.poll();
            Node currentNode = currentItem.node();

            // Пропуск устаревших записей
            if (currentItem.currentCost() > distances.get(currentNode.getId())) {
                continue;
            }

            // Callback в декоратор (профайлер), если навешан
            if (onNodeExplored != null) {
                onNodeExplored.accept(currentNode);
            }

            if (currentNode.getId().equals(finish.getId())) {
                break;
            }

            List<Edge> neighbors = adjacencyList.getOrDefault(currentNode.getId(), Collections.emptyList());

            for (Edge edge : neighbors) {
                Long neighborId = edge.getU().equals(currentNode.getId()) ? edge.getV() : edge.getU();
                Node neighborNode = nodesById.get(neighborId);

                Double newCost = distances.get(currentNode.getId()) + edge.getDistance();

                if (newCost < distances.get(neighborId)) {
                    distances.put(neighborId, newCost);
                    previousEdges.put(neighborId, edge);
                    queue.add(new QueueItem(neighborNode, newCost));
                }
            }
        }

        List<Edge> finalPath = new ArrayList<>();
        Long currentId = finish.getId();

        while (previousEdges.containsKey(currentId)) {
            Edge edge = previousEdges.get(currentId);
            finalPath.add(edge);
            currentId = edge.getU().equals(currentId) ? edge.getV() : edge.getU();
        }

        Collections.reverse(finalPath);

        return finalPath;
    }
}
