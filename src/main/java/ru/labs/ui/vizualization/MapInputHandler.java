package ru.labs.ui.vizualization;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.math.Vector3;
import ru.labs.core.models.graph.Node;

public class MapInputHandler extends InputAdapter {
    private final CameraController cameraController;
    private final SimulationManager simManager;
    private final Runnable onRouteUpdatedCallback;

    public MapInputHandler(CameraController cameraController, SimulationManager simManager, Runnable onRouteUpdatedCallback) {
        this.cameraController = cameraController;
        this.simManager = simManager;
        this.onRouteUpdatedCallback = onRouteUpdatedCallback;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        if (simManager.getContext() == null) {
            return false;
        }

        Vector3 worldCoords = cameraController.getCamera().unproject(new Vector3(screenX, screenY, 0));

        Node nearest = simManager.findNearestNode(worldCoords.x, worldCoords.y);

        if (nearest != null) {
            if (button == Input.Buttons.LEFT) {
                simManager.setStartNode(nearest);
            } else if (button == Input.Buttons.RIGHT) {
                simManager.setFinishNode(nearest);
            }
            onRouteUpdatedCallback.run();
        }
        return true;
    }
}
