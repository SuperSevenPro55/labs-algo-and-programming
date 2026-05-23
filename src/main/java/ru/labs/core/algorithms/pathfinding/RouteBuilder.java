package ru.labs.core.algorithms.pathfinding;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.util.List;
import java.util.function.Consumer;

public interface RouteBuilder {
    List<Edge> createRoute(Node start, Node finish, Consumer<Node> onNodeExplored);
}
