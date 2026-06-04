package ru.labs.util.graph;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Утилита для сборки графа из сырых данных - списков Node и Edge
 */

public class GraphMapper {
    /**
     * DTO-контейнер для возврата собранного контекста в граф
     * @param nodesById словарь узлов графа
     * @param adjacencyList список смежности
     */
    public record GraphContext(
            Map<Long, Node> nodesById,
            Map<Long, List<Edge>> adjacencyList
    ) {}

    /**
     * Преобразует сырые списки Node и Edge в структуру данных графа, готовую для обхода алгоритмами
     * @param rawNodes спсиок из Node
     * @param rawEdges список из Edge
     * @return GraphContext
     */
    public static GraphContext mapGraph(List<Node> rawNodes, List<Edge> rawEdges) {
        Map<Long, Node> nodesById = new HashMap<>();
        Map<Long, List<Edge>> adjacencyList = new HashMap<>();

        // Индексация узлов и инициализация корзин списка смежности
        for (Node node : rawNodes) {

            node.setX(node.getLongitude());
            node.setY(node.getLatitude());

            nodesById.put(node.getId(), node);
            adjacencyList.put(node.getId(), new ArrayList<>());
        }

        // Заполнение ребер и постройка списка смежности
        for (Edge edge : rawEdges) {
            Node u = nodesById.get(edge.getU());
            Node v = nodesById.get(edge.getV());

            if (u != null && v != null) {
                edge.setUX(u.getX());
                edge.setUY(u.getY());
                edge.setVX(v.getX());
                edge.setVY(v.getY());

                double distance = DistanceUtils.euclideanDistance(u.getX(), u.getY(), v.getX(), v.getY());
                edge.setDistance(distance);

                // Добавление ребра к обоим узлам для двунаправленности
                adjacencyList.get(u.getId()).add(edge);
                adjacencyList.get(v.getId()).add(edge);
            }
        }

        return new GraphContext(nodesById, adjacencyList);
    }
}
