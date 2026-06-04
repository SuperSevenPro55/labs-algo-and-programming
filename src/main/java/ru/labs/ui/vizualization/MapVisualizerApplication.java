package ru.labs.ui.vizualization;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;
import ru.labs.util.graph.GraphMapper;

/**
 * Класс-приложение libGDX
 */

public class MapVisualizerApplication extends ApplicationAdapter {
    private final GraphMapper.GraphContext context;
    private final RouteResult routeResult;

    private CameraController cameraController;
    private GraphRenderer graphRenderer;
    private MetricsHUD metricsHUD;

    public MapVisualizerApplication(GraphMapper.GraphContext context, RouteResult routeResult) {
        this.context = context;
        this.routeResult = routeResult;
    }

    @Override
    public void create() {
        float minX = Float.POSITIVE_INFINITY, maxX = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY;

        for (Node node : context.nodesById().values()) {
            if (node.getX() < minX) minX = node.getX().floatValue();
            if (node.getX() > maxX) maxX = node.getX().floatValue();
            if (node.getY() < minY) minY = node.getY().floatValue();
            if (node.getY() > maxY) maxY = node.getY().floatValue();
        }

        float centerX = (minX + maxX) / 2f;
        float centerY = (minY + maxY) / 2f;
        float mapWidth = maxX - minX;

        cameraController = new CameraController(mapWidth, centerX, centerY);
        graphRenderer = new GraphRenderer(context, routeResult);
        metricsHUD = new MetricsHUD(routeResult);

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(metricsHUD.getStage());
        multiplexer.addProcessor(cameraController);

        Gdx.input.setInputProcessor(multiplexer);
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        cameraController.update();
        graphRenderer.render(cameraController.getCamera());
        metricsHUD.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void dispose() {
        graphRenderer.dispose();
        metricsHUD.dispose();
    }
}
