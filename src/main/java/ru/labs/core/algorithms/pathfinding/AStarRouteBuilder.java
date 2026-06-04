package ru.labs.core.algorithms.pathfinding;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.util.graph.DistanceUtils;

import java.util.*;
import java.util.function.Consumer;

/**
 * Реализация алгоритма А* для поиска кратчайшего пути в графе
 */

public class AStarRouteBuilder implements RouteBuilder {
    private final Map<Long, Node> nodesById;
    private final Map<Long, List<Edge>> adjacencyList;

    public AStarRouteBuilder(Map<Long, Node> nodesById, Map<Long, List<Edge>> adjacencyList) {
        this.nodesById = nodesById;
        this.adjacencyList = adjacencyList;
    }

    /**
     * Контейнер для очереди
     * @param node узел
     * @param gScore реальная стоимость пути от старта до этого узла
     * @param fScore сумма gScope и примерного расстояния до финиша
     */
    private record QueueItem(Node node, Double gScore, Double fScore) implements Comparable<QueueItem> {
        @Override
        public int compareTo(QueueItem other) {
            return Double.compare(this.fScore, other.fScore);
        }
    }

    @Override
    public List<Edge> createRoute(Node start, Node finish, Consumer<Node> onNodeExplored) {
        Map<Long, Double> gScores = new HashMap<>(); // Реальная цена пути
        Map<Long, Edge> previousEdges = new HashMap<>(); // // Пройденные грани
        PriorityQueue<QueueItem> queue = new PriorityQueue<>(); // Очередь, выдающая ближайший узел

        // Инициализация
        for (Long nodeId : nodesById.keySet()) {
            gScores.put(nodeId, Double.MAX_VALUE);
        }
        gScores.put(start.getId(), 0.0);

        // Эвристика для начальной точки
        double startH = DistanceUtils.euclideanDistance(
                start.getX(),
                start.getY(),
                finish.getX(),
                finish.getY()
        );
        queue.add(new QueueItem(start, 0.0, startH));

        while (!queue.isEmpty()) {
            QueueItem currentItem = queue.poll();
            Node currentNode = currentItem.node();

            if (currentItem.gScore() > gScores.get(currentNode.getId())) {
                continue;
            }

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

                // Проверка на проход к соседу через текущий узел
                double tentativeGScore = gScores.get(currentNode.getId()) + edge.getDistance();

                if (tentativeGScore < gScores.get(neighborId)) {
                    gScores.put(neighborId, tentativeGScore);
                    previousEdges.put(neighborId, edge);

                    // Расчет эвристики h(x) от соседа до финиша
                    double hScore = DistanceUtils.euclideanDistance(
                            neighborNode.getX(), neighborNode.getY(),
                            finish.getX(), finish.getY()
                    );

                    double fScore = tentativeGScore + hScore;

                    queue.add(new QueueItem(neighborNode, tentativeGScore, fScore));
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
