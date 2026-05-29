package ru.labs.ui.vizualization;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;
import ru.labs.core.models.graph.RouteResult;
import ru.labs.util.graph.GraphMapper.GraphContext;

public class MapVisualizerApplication extends ApplicationAdapter {
    private final GraphContext graphContext;
    private final RouteResult routeResult;

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;

    public MapVisualizerApplication(GraphContext graphContext, RouteResult routeResult) {
        this.graphContext = graphContext;
        this.routeResult = routeResult;
    }

    @Override
    public void create() {
        shapeRenderer = new ShapeRenderer();

        float minX = Float.POSITIVE_INFINITY, maxX = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY;

        for (Node node : graphContext.nodesById().values()) {
            if (node.getX() < minX) {
                minX = node.getX().floatValue();
            }
            if (node.getX() > maxX) {
                maxX = node.getX().floatValue();
            }
            if (node.getY() < minY) {
                minY = node.getY().floatValue();
            }
            if (node.getY() > maxY) {
                maxY = node.getY().floatValue();
            }
        }

        float centerX = (minX + maxX) / 2f;
        float centerY = (minY + maxY) / 2f;

        float mapWidth = maxX - minX;
//        float mapHeight = maxY - minY;

        float aspectRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();

        camera = new OrthographicCamera(mapWidth * 1.1f, (mapWidth * 1.1f) / aspectRatio);
        camera.position.set(centerX, centerY, 0);
        camera.update();
    }

    @Override
    public void render() {
        handleBasicInput();

        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        shapeRenderer.setProjectionMatrix(camera.combined);

        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);

        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);

        shapeRenderer.setColor(new Color(0.5f, 0.5f, 0.5f, 0.3f));
        for (Long nodeId : graphContext.adjacencyList().keySet()) {
            for (Edge edge : graphContext.adjacencyList().get(nodeId)) {
                shapeRenderer.line(edge.getUX().floatValue(), edge.getUY().floatValue(),
                        edge.getVX().floatValue(), edge.getVY().floatValue());
            }
        }

        if (routeResult != null && routeResult.getExploredNodes() != null) {
            shapeRenderer.setColor(new Color(0.8f, 0.8f, 0.2f, 0.5f));
            for (Node node : routeResult.getExploredNodes()) {
                shapeRenderer.circle(node.getX().floatValue(), node.getY().floatValue(), camera.viewportWidth * 0.001f * camera.zoom);
            }
        }

        if (routeResult != null && routeResult.getFinalPath() != null) {
            shapeRenderer.setColor(Color.RED);
            for (Edge edge : routeResult.getFinalPath()) {
                shapeRenderer.line(edge.getUX().floatValue(), edge.getUY().floatValue(),
                        edge.getVX().floatValue(), edge.getVY().floatValue());
            }
        }

        shapeRenderer.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    @Override
    public void dispose() {
        shapeRenderer.dispose();
    }

    private void handleBasicInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
            camera.zoom += 0.02f;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
            camera.zoom -= 0.02f;
        }

        if (camera.zoom < 0.1f) {
            camera.zoom = 0.1f;
        }
        if (camera.zoom > 3.0f) {
            camera.zoom = 3.0f;
        }

        camera.update();
    }
}
