package ru.labs.ui.labs;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.labs.core.algorithms.pathfinding.AStarRouteBuilder;
import ru.labs.core.algorithms.pathfinding.RouteBuilder;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;
import ru.labs.ui.vizualization.MapVisualizerApplication;
import ru.labs.util.MessageManager;
import ru.labs.util.graph.GraphMapper;
import ru.labs.util.graph.MapFileReaderUtils;
import ru.labs.util.graph.RouteProfiler;

import java.util.List;

public class Lab_11 implements LabRunner {
    @Override
    public void start() {
        List<Node> rawNodes = MapFileReaderUtils.readNodes("/maps/omsk/nodes.csv");
        List<Edge> rawEdges = MapFileReaderUtils.readEdges("/maps/omsk/edges.csv");

        if (rawNodes.isEmpty() || rawEdges.isEmpty()) {
            System.out.println(MessageManager.get("error.map.read"));
            return;
        }

        GraphMapper.GraphContext context = GraphMapper.mapGraph(rawNodes, rawEdges);

        // Тестовые
        Node startNode = rawNodes.getFirst();
        Node finishNode = rawNodes.getLast();

        System.out.println("Старт: " + startNode.getId());
        System.out.println("Финиш: " + finishNode.getId());

        RouteBuilder algorithm = new AStarRouteBuilder(context.nodesById(), context.adjacencyList());

        RouteResult result = RouteProfiler.profile(algorithm, startNode, finishNode);

        System.out.println("Поиск завершен за " + result.getExecutionTimeMs() + "мс.");
        System.out.println("Посещено узлов: " + result.getVisitedCount());

        new Lwjgl3Application(new MapVisualizerApplication(context, result), configuration());
    }

    private Lwjgl3ApplicationConfiguration configuration() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Доставка пиццы.");
        config.setWindowedMode(1280, 720);
        config.useVsync(true);
        config.setForegroundFPS(60);

        return config;
    }
}
