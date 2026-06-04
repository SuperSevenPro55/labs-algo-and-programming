package ru.labs.ui.vizualization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;

import static ru.labs.util.graph.GraphMapper.GraphContext;

/**
 * Отрисовка геометрии графа
 */

public class GraphRenderer {
    private final ShapeRenderer shapeRenderer;
    private final GraphContext context;
    private final RouteResult routeResult;

    public GraphRenderer(GraphContext graphContext, RouteResult routeResult) {
        this.shapeRenderer = new ShapeRenderer();
        this.context = graphContext;
        this.routeResult = routeResult;
    }

    /**
     * Метод для отрисовки кадра
     * @param camera камера, матрица которой используется для отрисовки
     */
    public void render(OrthographicCamera camera) {
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        // Настройки полупрозрачности
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // Отрисовка всей сети
        shapeRenderer.setColor(new Color(0.5f, 0.5f, 0.5f, 0.3f));
        for (Long nodeId : context.adjacencyList().keySet()) {
            for (Edge edge : context.adjacencyList().get(nodeId)) {
                shapeRenderer.line(
                        edge.getUX().floatValue(),
                        edge.getUY().floatValue(),
                        edge.getVX().floatValue(),
                        edge.getVY().floatValue()
                );
            }
        }

        // Отрисовка исследованных алгоритмом узлов
        if (routeResult != null && routeResult.getExploredNodes() != null) {
            shapeRenderer.setColor(new Color(0.8f, 0.8f, 0.2f, 0.5f));
            for (Node node : routeResult.getExploredNodes()) {
                shapeRenderer.circle(
                        node.getX().floatValue(),
                        node.getY().floatValue(),
                        camera.viewportWidth * 0.001f * camera.zoom
                );
            }
        }

        // Отрисовка финального пути
        if (routeResult != null && routeResult.getFinalPath() != null) {
            shapeRenderer.setColor(Color.RED);
            for (Edge edge : routeResult.getFinalPath()) {
                shapeRenderer.line(
                        edge.getUX().floatValue(),
                        edge.getUY().floatValue(),
                        edge.getVX().floatValue(),
                        edge.getVY().floatValue()
                );
            }

        }

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    /**
     * Метод для нейтрализации GraphRenderer
     */
    public void dispose() {
        shapeRenderer.dispose();
    }
}
