package ru.labs.ui.vizualization;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import lombok.Getter;

/**
 * Класс-контроллер интерактивных элементов карты
 */
@Getter
public class ControlsHUD {
    private final Stage stage;
    private final BitmapFont font;

    public interface ControlListener {
        void onAlgorithmChanged(String algoName);
        void onMapChanged(String mapPath);
    }

    public ControlsHUD(ControlListener listener) {
        this.stage = new Stage(new ScreenViewport());
        this.font = new BitmapFont();

        TextButton.TextButtonStyle btnStyle = createButtonStyle(font);

        TextButton btnDijkstra = new TextButton("Algo: Dijkstra", btnStyle);
        TextButton btnAStar = new TextButton("Algo: A*", btnStyle);
        TextButton btnMapOmsk = new TextButton("Map: Omsk", btnStyle);
        TextButton btnMapSimple = new TextButton("Map: Simple", btnStyle);

        btnDijkstra.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) { listener.onAlgorithmChanged("DIJKSTRA"); }
        });
        btnAStar.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) { listener.onAlgorithmChanged("ASTAR"); }
        });
        btnMapOmsk.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) { listener.onMapChanged("/maps/omsk/"); }
        });
        btnMapSimple.addListener(new ClickListener() {
            @Override public void clicked(InputEvent event, float x, float y) { listener.onMapChanged("/maps/simple/"); }
        });

        Table table = new Table();
        table.top().right();
        table.setFillParent(true);
        table.pad(20);

        table.add(btnDijkstra).padRight(10);
        table.add(btnAStar).row();
        table.add(btnMapOmsk).padTop(10).padRight(10);
        table.add(btnMapSimple).padTop(10).row();

        stage.addActor(table);
    }

    public void render(float deltaTime) {
        stage.act(deltaTime);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }

    private TextButton.TextButtonStyle createButtonStyle(BitmapFont font) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(new Color(0.2f, 0.2f, 0.3f, 1));
        pixmap.fill();
        Texture bgTex = new Texture(pixmap);
        pixmap.dispose();

        TextButton.TextButtonStyle style = new TextButton.TextButtonStyle();
        style.up = new TextureRegionDrawable(new TextureRegion(bgTex));
        style.font = font;
        style.fontColor = Color.WHITE;
        return style;
    }
}