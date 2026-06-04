package ru.labs.util.graph;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Утилиты для парсинга данных из файлов
 */

public class MapFileReaderUtils {
    /**
     * Парсер данных из файлов в Node
     * @param filePath путь к файлу с данными Node
     * @return List из Node
     */
    public static List<Node> readNodes(String filePath) {
        List<Node> nodes = new ArrayList<>();

        try (InputStream is = MapFileReaderUtils.class.getResourceAsStream(filePath)) {
            if (is == null) {
                throw new NullPointerException("Файл не найден: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                br.readLine();
                String line;

                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }

                    // Колонки (ID, Longitude, Latitude)
                    String[] cells = line.split(",");

                    if (cells.length >= 3) {
                        Long id = Long.parseLong(cells[0].trim());
                        Double longitude = Double.parseDouble(cells[1].trim());
                        Double latitude = Double.parseDouble(cells[2].trim());
                        nodes.add(new Node(id, longitude, latitude));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[MapFileReaderUtils] Ошибка readNodes: " + e.getMessage());
        }

        return nodes;
    }

    /**
     * Парсер данных из файлов в Edge
     * @param filePath путь к файлу с данными Edge
     * @return List из Edge
     */
    public static List<Edge> readEdges(String filePath) {
        List<Edge> edges = new ArrayList<>();

        try (InputStream is = MapFileReaderUtils.class.getResourceAsStream(filePath)) {
            if (is == null) {
                throw new NullPointerException("Файл не найден: " + filePath);
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                br.readLine();
                String line;

                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }

                    // Колонки (u, v)
                    String[] cells = line.split(",");

                    if (cells.length >= 2) {
                        Long u = Long.parseLong(cells[0].trim());
                        Long v = Long.parseLong(cells[1].trim());
                        edges.add(new Edge(u, v));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("[MapFileReaderUtils] Ошибка readEdges: " + e.getMessage());
        }

        return edges;
    }
}
