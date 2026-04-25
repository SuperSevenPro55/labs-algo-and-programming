package ru.Labs.core.structures.graph;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final int id;
    private final List<Vertex> neighbors = new ArrayList<>();
    private boolean visited = false;

    public Vertex(int id) {
        this.id = id;
    }

    public void addNeighbor(Vertex neighbor) {
        if (!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
        }
    }

    public int getId() {
        return id;
    }

    public List<Vertex> getNeighbors() {
        return neighbors;
    }

    public boolean isVisited() {
        return visited;
    }

    public void setVisited(boolean visited) {
        this.visited = visited;
    }

}
