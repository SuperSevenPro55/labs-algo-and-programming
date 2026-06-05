package ru.labs.ui.vizualization;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.GL20;
import ru.labs.core.models.graph.Node;

/**
 * Класс-приложение libGDX
 */

public class MapVisualizerApplication extends ApplicationAdapter implements ControlsHUD.ControlListener {
    private SimulationManager simManager;

    private CameraController cameraController;
    private GraphRenderer graphRenderer;
    private MetricsHUD metricsHUD;
    private ControlsHUD controlsHUD;

    @Override
    public void create() {
        simManager = new SimulationManager();
        graphRenderer = new GraphRenderer();
        metricsHUD = new MetricsHUD();
        controlsHUD = new ControlsHUD(this);

        loadMapAndResetCamera("/maps/omsk/");
    }

    @Override
    public void onMapChanged(String mapPath) {
        loadMapAndResetCamera(mapPath);
    }

    @Override
    public void onAlgorithmChanged(String algoName) {
        simManager.setAlgorithm(algoName);
        updateMetricsDisplay();
    }

    private void loadMapAndResetCamera(String mapPath) {
        if (!simManager.loadMap(mapPath)) {
            System.err.println("Не удалось загрузить карту: " + mapPath);
            return;
        }

        centerCameraOnMap();
        setupInputProcessor();
        updateMetricsDisplay();
    }

    private void centerCameraOnMap() {
        float minX = Float.POSITIVE_INFINITY, maxX = Float.NEGATIVE_INFINITY;
        float minY = Float.POSITIVE_INFINITY, maxY = Float.NEGATIVE_INFINITY;

        for (Node node : simManager.getContext().nodesById().values()) {
            if (node.getX() < minX) minX = node.getX().floatValue();
            if (node.getX() > maxX) maxX = node.getX().floatValue();
            if (node.getY() < minY) minY = node.getY().floatValue();
            if (node.getY() > maxY) maxY = node.getY().floatValue();
        }

        float centerX = (minX + maxX) / 2f;
        float centerY = (minY + maxY) / 2f;
        float mapWidth = maxX - minX;

        cameraController = new CameraController(mapWidth, centerX, centerY);
    }

    private void setupInputProcessor() {
        InputMultiplexer multiplexer = new InputMultiplexer();

        multiplexer.addProcessor(controlsHUD.getStage());

        MapInputHandler mapInput = new MapInputHandler(cameraController, simManager, this::updateMetricsDisplay);
        multiplexer.addProcessor(mapInput);

        multiplexer.addProcessor(cameraController);

        Gdx.input.setInputProcessor(multiplexer);
    }

    private void updateMetricsDisplay() {
        metricsHUD.updateMetrics(simManager.getRouteResult(), simManager.getCurrentAlgoName(), simManager.getLastCalcTime());
    }

    @Override
    public void render() {
        Gdx.gl.glClearColor(0.1f, 0.1f, 0.15f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (cameraController != null) cameraController.update();

        graphRenderer.render(
                cameraController.getCamera(),
                simManager.getContext(),
                simManager.getRouteResult(),
                simManager.getStartNode(),
                simManager.getFinishNode()
        );

        metricsHUD.render(Gdx.graphics.getDeltaTime());
        controlsHUD.render(Gdx.graphics.getDeltaTime());
    }

    @Override
    public void resize(int width, int height) {
        if (metricsHUD != null) {
            metricsHUD.getStage().getViewport().update(width, height, true);
        }
        if (controlsHUD != null) {
            controlsHUD.getStage().getViewport().update(width, height, true);
        }
    }

    @Override
    public void dispose() {
        graphRenderer.dispose();
        metricsHUD.dispose();
        controlsHUD.dispose();
    }
}
