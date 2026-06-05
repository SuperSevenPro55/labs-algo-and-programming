package ru.labs.ui.vizualization;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import lombok.Getter;
import ru.labs.core.models.graph.RouteResult;

/**
 * Класс-отрисовки метрик алгоритмов
 */
@Getter
public class MetricsHUD {
    private final Stage stage;
    private final BitmapFont font;
    private final Label metricsLabel;

    public MetricsHUD() {
        this.stage = new Stage(new ScreenViewport());
        this.font = new BitmapFont();

        Label.LabelStyle labelStyle = new Label.LabelStyle(font, Color.WHITE);
        metricsLabel = new Label("Status: Loading map...", labelStyle);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        table.pad(20);

        table.add(metricsLabel).left();
        stage.addActor(table);
    }

    public void updateMetrics(RouteResult result, String currentAlgo, long calculationTime) {
        if (result == null) {
            metricsLabel.setText("Algorithm: " + currentAlgo +
                    "\nStatus: Select Start (L-Click) and Finish (R-Click) points.");
            return;
        }
        metricsLabel.setText("Algorithm: " + currentAlgo + "\n" +
                "Calculation Time: " + calculationTime + " ms\n" +
                "Visited Nodes: " + result.getVisitedCount() + "\n" +
                "Path Length: " + result.getFinalPath().size() + " edges");
    }

    public void render(float deltaTime) {
        stage.act(deltaTime);
        stage.draw();
    }

    public void dispose() {
        stage.dispose();
        font.dispose();
    }
}