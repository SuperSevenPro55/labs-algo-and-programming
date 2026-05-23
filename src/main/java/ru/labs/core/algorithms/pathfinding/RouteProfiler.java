package ru.labs.core.algorithms.pathfinding;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;

import java.util.ArrayList;
import java.util.List;

public class RouteProfiler {
    public static RouteResult profile(RouteBuilder algorithm, Node start, Node finish) {
        List<Node> exploredNodes = new ArrayList<>();

        Long startTime = System.currentTimeMillis();

        List<Edge> finalPath = algorithm.createRoute(start, finish, exploredNodes::add);

        Long endTime = System.currentTimeMillis();

        return RouteResult.builder()
                .finalPath(finalPath)
                .exploredNodes(exploredNodes)
                .executionTimeMs(endTime - startTime)
                .visitedCount(exploredNodes.size())
                .build();
    }
}
