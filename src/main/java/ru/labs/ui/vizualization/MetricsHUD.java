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
 * Отрисовка интерфейса поверх карты
 */

@Getter
public class MetricsHUD {
    private final Stage stage;
    private final BitmapFont font;

    public MetricsHUD(RouteResult routeResult) {
        this.stage = new Stage(new ScreenViewport());
        this.font = new BitmapFont();

        Label.LabelStyle style = new Label.LabelStyle(font, Color.WHITE);

        String text = "Algorithm: Dijkstra\n";
        if (routeResult != null) {
            text += "Execution time: " + routeResult.getExecutionTimeMs() + "ms\n" +
                    "Visited Nodes: " + routeResult.getVisitedCount() + "\n" +
                    "Path Length: " + routeResult.getFinalPath().size() + " edges";
        } else {
            text += "Status: waiting for route...";
        }

        Label metricsLabel = new Label(text, style);

        Table table = new Table();
        table.top().left();
        table.setFillParent(true);
        table.pad(20);
        table.add(metricsLabel).left();

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
}
