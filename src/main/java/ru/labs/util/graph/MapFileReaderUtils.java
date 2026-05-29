package ru.labs.util.graph;

import ru.labs.core.models.graph.Edge;
import ru.labs.core.models.graph.Node;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapFileReaderUtils {
    public static List<Node> readNodes(String filePath) {
        List<Node> nodes = new ArrayList<>();

        try (InputStream is = MapFileReaderUtils.class.getResourceAsStream(filePath)) {
            if (is == null) {
                System.out.println("Ошибка. Файл не найден в ресурсах");
                return nodes;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;

                br.readLine();

                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }

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
            System.out.println("Ошибка при чтении узлов: " + e.getMessage());
        }

        return nodes;
    }

    public static List<Edge> readEdges(String filePath) {
        List<Edge> edges = new ArrayList<>();

        try (InputStream is = MapFileReaderUtils.class.getResourceAsStream(filePath)) {
            if (is == null) {
                System.out.println("Ошибка. Файл не найден в ресурсах");
                return edges;
            }

            try (BufferedReader br = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8))) {
                String line;

                br.readLine();

                while ((line = br.readLine()) != null) {
                    if (line.isEmpty()) {
                        continue;
                    }

                    String[] cells = line.split(",");
                    if (cells.length >= 2) {
                        Long u = Long.parseLong(cells[0].trim());
                        Long v = Long.parseLong(cells[1].trim());
                        edges.add(new Edge(u, v));
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Ошибка при чтении граней: " + e.getMessage());
        }

        return edges;
    }
}
