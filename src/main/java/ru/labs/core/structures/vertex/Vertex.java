package ru.labs.core.structures.vertex;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class Vertex {
    private final int id;
    private final List<Vertex> neighbors = new ArrayList<>();
    @Setter
    private VertexState state = VertexState.NOT_VISITED;

    public Vertex(int id) {
        this.id = id;
    }

    public void addNeighbor(Vertex neighbor) {
        if (!neighbors.contains(neighbor)) {
            neighbors.add(neighbor);
        }
    }
}
