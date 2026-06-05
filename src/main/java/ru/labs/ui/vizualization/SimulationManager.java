package ru.labs.ui.vizualization;

import lombok.Getter;
import ru.labs.core.algorithms.pathfinding.AStarRouteBuilder;
import ru.labs.core.algorithms.pathfinding.DijkstraRouteBuilder;
import ru.labs.core.algorithms.pathfinding.RouteBuilder;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;
import ru.labs.util.graph.DistanceUtils;
import ru.labs.util.graph.GraphMapper;
import ru.labs.util.graph.MapFileReaderUtils;
import ru.labs.util.graph.RouteProfiler;

import java.util.List;

/**
 * Класс для управления состоянием симуляции
 */

@Getter
public class SimulationManager {
    private GraphMapper.GraphContext context;
    private RouteResult routeResult;
    private Node startNode;
    private Node finishNode;

    private String currentAlgoName = "DIJKSTRA";
    private long lastCalcTime = 0;

    public boolean loadMap(String mapDir) {
        List<Node> rawNodes = MapFileReaderUtils.readNodes(mapDir + "nodes.csv");
        List<Edge> rawEdges = MapFileReaderUtils.readEdges(mapDir + "edges.csv");

        if (rawNodes.isEmpty() || rawEdges.isEmpty()) {
            return false;
        }

        this.context = GraphMapper.mapGraph(rawNodes, rawEdges);
        this.startNode = null;
        this.finishNode = null;
        this.routeResult = null;
        return true;
    }

    public void setAlgorithm(String algoName) {
        this.currentAlgoName = algoName;
        recalculateRoute();
    }

    public void setStartNode(Node node) {
        this.startNode = node;
        recalculateRoute();
    }

    public void setFinishNode(Node node) {
        this.finishNode = node;
        recalculateRoute();
    }

    private void recalculateRoute() {
        if (startNode == null || finishNode == null || context == null) {
            return;
        }

        RouteBuilder algorithm = "ASTAR".equals(currentAlgoName)
                ? new AStarRouteBuilder(context.nodesById(), context.adjacencyList())
                : new DijkstraRouteBuilder(context.nodesById(), context.adjacencyList());

        long startTime = System.currentTimeMillis();
        routeResult = RouteProfiler.profile(algorithm, startNode, finishNode);
        lastCalcTime = System.currentTimeMillis() - startTime;
    }

    public Node findNearestNode(float x, float y) {
        if (context == null) return null;

        Node nearest = null;
        double minDistance = Double.MAX_VALUE;

        for (Node node : context.nodesById().values()) {
            double dist = DistanceUtils.euclideanDistance(x, y, node.getX(), node.getY());
            if (dist < minDistance) {
                minDistance = dist;
                nearest = node;
            }
        }
        return nearest;
    }
}
