package ru.labs.core.models.graph;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * Обертка для алгоритма с целью вывода метрик
 */

@Getter
@Builder
public class RouteResult {
    private final List<Edge> finalPath;
    private final List<Node> exploredNodes;
    private final Long executionTimeMs;
    private final Integer visitedCount;
}
