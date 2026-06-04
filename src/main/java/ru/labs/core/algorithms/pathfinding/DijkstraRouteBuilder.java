package ru.labs.core.algorithms.pathfinding;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.*;
import java.util.function.Consumer;

/**
 * Реализация алгоритма Дейкстры для поиска кратчайшего пути в графе
 */

public class DijkstraRouteBuilder implements RouteBuilder {
    private final Map<Long, Node> nodesById;
    private final Map<Long, List<Edge>> adjacencyList;

    public DijkstraRouteBuilder(Map<Long, Node> nodesById, Map<Long, List<Edge>> adjacencyList) {
        this.nodesById = nodesById;
        this.adjacencyList = adjacencyList;
    }

    /**
     * Вспомогательный класс для хранения узла и его текущей стоимости в очереди с приоритетом
     * @param node хранимый узел
     * @param currentCost текущая стоимость хранимого узла
     */
    private record QueueItem(Node node, Double currentCost) implements Comparable<QueueItem> {
        @Override
        public int compareTo(QueueItem other) {
            return Double.compare(this.currentCost, other.currentCost);
        }
    }

    @Override
    public List<Edge> createRoute(Node start, Node finish, Consumer<Node> onNodeExplored) {
        Map<Long, Double> distances = new HashMap<>(); // Минимальное расстояние от старта до конкретного узла
        Map<Long, Edge> previousEdges = new HashMap<>(); // Пройденные грани
        PriorityQueue<QueueItem> queue = new PriorityQueue<>(); // Очередь, выдающая ближайший узел

        // Инициализация расстояний
        for (Long longId : nodesById.keySet()) {
            distances.put(longId, Double.MAX_VALUE);
        }
        distances.put(start.getId(), 0.0);
        queue.add(new QueueItem(start, 0.0));

        while (!queue.isEmpty()) {
            QueueItem currentItem = queue.poll();
            Node currentNode = currentItem.node();

            // Пропуск более длинных узлов
            if (currentItem.currentCost() > distances.get(currentNode.getId())) {
                continue;
            }

            // Callback в слушатель (узел обойден)
            if (onNodeExplored != null) {
                onNodeExplored.accept(currentNode);
            }

            // Найден путь до конечной точки
            if (currentNode.getId().equals(finish.getId())) {
                break;
            }

            // Анализ соседей
            List<Edge> neighbors = adjacencyList.getOrDefault(currentNode.getId(), Collections.emptyList());
            for (Edge edge : neighbors) {
                // Определение "соседа по ребру"
                Long neighborId = edge.getU().equals(currentNode.getId()) ? edge.getV() : edge.getU();
                Node neighborNode = nodesById.get(neighborId);

                // Вычисление новой стоимости пути до соседа через текущий узел
                Double newCost = distances.get(currentNode.getId()) + edge.getDistance();

                if (newCost < distances.get(neighborId)) {
                    distances.put(neighborId, newCost);
                    previousEdges.put(neighborId, edge);
                    queue.add(new QueueItem(neighborNode, newCost));
                }
            }
        }

        // Постройка финального маршрута (от конечной до начальной точки)
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
