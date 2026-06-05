package ru.labs.ui.labs;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import ru.labs.ui.vizualization.MapVisualizerApplication;

public class Lab_11 implements LabRunner{

    @Override
    public void start() {
        System.out.println("Запуск Интерактивного Симулятора Доставки Пиццы...");
        System.out.println("Закройте окно карты, чтобы вернуться в меню.");

        new Lwjgl3Application(new MapVisualizerApplication(), configuration());
    }

    private Lwjgl3ApplicationConfiguration configuration() {
        Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
        config.setTitle("Доставка пиццы - интерактивное издание");
        config.setWindowedMode(1280, 720);
        config.useVsync(true);
        config.setForegroundFPS(60);

        return config;
    }
}