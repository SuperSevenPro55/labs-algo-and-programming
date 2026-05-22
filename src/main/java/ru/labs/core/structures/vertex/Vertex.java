package ru.labs.core.structures.vertex;

import java.util.ArrayList;
import java.util.List;

public class Vertex {
    private final int id;
    private final List<Vertex> neighbors = new ArrayList<>();
    private VertexState state = VertexState.NOT_VISITED;

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

    public VertexState getState() {
        return state;
    }

    public void setState(VertexState state) {
        this.state = state;
    }
}
