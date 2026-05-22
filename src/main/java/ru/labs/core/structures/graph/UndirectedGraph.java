package ru.labs.core.structures.graph;

import ru.labs.core.structures.vertex.Vertex;

public class UndirectedGraph extends CommonGraph {
    @Override
    public void addEdge(int u, int v) {
        addVertex(u);
        addVertex(v);
        Vertex vU = vertices.get(u);
        Vertex vV = vertices.get(v);
        vU.addNeighbor(vV);
        vV.addNeighbor(vU);
    }
}
