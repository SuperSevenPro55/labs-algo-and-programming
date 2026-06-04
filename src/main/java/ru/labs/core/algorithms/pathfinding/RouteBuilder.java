package ru.labs.core.algorithms.pathfinding;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.List;
import java.util.function.Consumer;

/**
 * Интерфейс для всех алгоритмов обхода графа
 */

public interface RouteBuilder {
    /**
     * Метод определения кратчайшего пути между двумя Node с возможностью подключить "наблюдателей"
     * @param start стартовая Node
     * @param finish конечная Node
     * @param onNodeExplored параметр для "наблюдателей"
     * @return кратчайший путь
     */
    List<Edge> createRoute(Node start, Node finish, Consumer<Node> onNodeExplored);
}
