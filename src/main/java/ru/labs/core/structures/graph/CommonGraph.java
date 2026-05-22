package ru.labs.core.structures.graph;

import ru.labs.core.structures.vertex.Vertex;
import ru.labs.core.structures.vertex.VertexState;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

public abstract class CommonGraph {
    protected final Map<Integer, Vertex> vertices = new HashMap<>();

    public void addVertex(int id) {
        vertices.putIfAbsent(id, new Vertex(id));
    }

    public Vertex getVertex(int id) {
        return vertices.get(id);
    }

    public Collection<Vertex> getVertices() {
        return vertices.values();
    }

    public void resetVisited() {
        for (Vertex vertex : vertices.values()) {
            vertex.setState(VertexState.NOT_VISITED);
        }
    }

    public abstract void addEdge(int u, int v);
}
