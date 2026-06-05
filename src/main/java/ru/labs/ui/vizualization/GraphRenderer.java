package ru.labs.ui.vizualization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;
import ru.labs.util.graph.GraphMapper.GraphContext;

public class GraphRenderer {
    private final ShapeRenderer shapeRenderer;

    public GraphRenderer() {
        this.shapeRenderer = new ShapeRenderer();
    }

    public void render(OrthographicCamera camera, GraphContext context, RouteResult routeResult, Node startNode, Node finishNode) {
        if (context == null) {
            return;
        }

        shapeRenderer.setProjectionMatrix(camera.combined);
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        // Отрисовка всей сети
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(new Color(0.5f, 0.5f, 0.5f, 0.3f));
        for (Long nodeId : context.adjacencyList().keySet()) {
            for (Edge edge : context.adjacencyList().get(nodeId)) {
                shapeRenderer.line(
                        edge.getUX().floatValue(), edge.getUY().floatValue(),
                        edge.getVX().floatValue(), edge.getVY().floatValue()
                );
            }
        }
        shapeRenderer.end();

        // Отрисовка алгоритма
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        float pointRadius = camera.viewportWidth * 0.001f * camera.zoom;

        // Исследованные узлы
        if (routeResult != null && routeResult.getExploredNodes() != null) {
            shapeRenderer.setColor(new Color(0.9f, 0.9f, 0.1f, 0.6f));
            for (Node node : routeResult.getExploredNodes()) {
                shapeRenderer.circle(node.getX().floatValue(), node.getY().floatValue(), pointRadius, 8);
            }
        }

        // Отрисовка финального пути
        if (routeResult != null && routeResult.getFinalPath() != null) {
            shapeRenderer.setColor(Color.RED);
            float lineWidth = camera.viewportWidth * 0.0015f * camera.zoom;
            for (Edge edge : routeResult.getFinalPath()) {
                shapeRenderer.rectLine(
                        edge.getUX().floatValue(), edge.getUY().floatValue(),
                        edge.getVX().floatValue(), edge.getVY().floatValue(),
                        lineWidth
                );
            }
        }

        // Выделение старта и финиша
        float markerRadius = pointRadius * 2.5f;
        if (startNode != null) {
            shapeRenderer.setColor(Color.GREEN);
            shapeRenderer.circle(startNode.getX().floatValue(), startNode.getY().floatValue(), markerRadius, 16);
        }
        if (finishNode != null) {
            shapeRenderer.setColor(Color.CYAN);
            shapeRenderer.circle(finishNode.getX().floatValue(), finishNode.getY().floatValue(), markerRadius, 16);
        }

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}