package ru.labs.util.graph;

import ru.labs.core.algorithms.pathfinding.RouteBuilder;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;

import java.util.ArrayList;
import java.util.List;

/**
 * Профайлер для алгоритмов поиска пути.
 * Оборачивает алгоритм для замера времени выполнения и сбора статистики обхода графа.
 */

public class RouteProfiler {
    /**
     * Выполняет поиск пути с замером метрик
     * @param algorithm алгоритм поиска пути, релазованных через RouteBuilder
     * @param start начальный Node
     * @param finish конечный Node
     * @return RouteResult
     */
    public static RouteResult profile(RouteBuilder algorithm, Node start, Node finish) {
        List<Node> exploredNodes = new ArrayList<>();

        long startTime = System.currentTimeMillis();

        // Запуск алгоритма с передачей ссылки на метод add списка exploredNodes
        List<Edge> finalPath = algorithm.createRoute(start, finish, exploredNodes::add);

        long endTime = System.currentTimeMillis();

        return RouteResult.builder()
                .finalPath(finalPath)
                .exploredNodes(exploredNodes)
                .executionTimeMs(endTime - startTime)
                .visitedCount(exploredNodes.size())
                .build();
    }
}
