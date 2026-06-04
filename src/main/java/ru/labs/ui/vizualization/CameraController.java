package ru.labs.ui.vizualization;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputAdapter;
import com.badlogic.gdx.graphics.OrthographicCamera;
import lombok.Getter;

/**
 * Класс-контроллер камеры
 */

@Getter
public class CameraController extends InputAdapter {
    private final OrthographicCamera camera;

    public CameraController(float mapWidth, float centerX, float centerY) {
        float aspectRatio = (float) Gdx.graphics.getWidth() / Gdx.graphics.getHeight();

        camera = new OrthographicCamera(mapWidth * 1.1f, (mapWidth * 1.1f / aspectRatio));
        camera.position.set(centerX, centerY, 0);
        camera.update();
    }

    public void update() {
        camera.update();
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        float zoomSensitivity = 0.1f;
        camera.zoom += amountY * zoomSensitivity * camera.zoom;

        float minZoom = 0.01f;
        float maxZoom = 1.5f;
        if (camera.zoom < minZoom) camera.zoom = minZoom;
        if (camera.zoom > maxZoom) camera.zoom = maxZoom;

        camera.update();
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        float deltaX = Gdx.input.getDeltaX();
        float deltaY = Gdx.input.getDeltaY();

        float moveX = -deltaX * camera.zoom * 0.00015f;
        float moveY = deltaY * camera.zoom * 0.00015f;

        camera.translate(moveX, moveY);
        camera.update();
        return true;
    }
}
