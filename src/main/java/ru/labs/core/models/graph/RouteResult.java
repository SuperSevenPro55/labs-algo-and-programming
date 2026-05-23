package ru.labs.core.models.graph;

import lombok.Builder;
import lombok.Getter;

import java.util.List;

/**
 * finalPath - итоговый путь
 * exploredNodes - узлы, посещенные алгоритмом
 * executionTimeMs - время выполнения (мс)
 * visitedCount - количество посещенных узлов
 */

@Getter
@Builder
public class RouteResult {
    private final List<Edge> finalPath;
    private final List<Node> exploredNodes;
    private final Long executionTimeMs;
    private final Integer visitedCount;
}
