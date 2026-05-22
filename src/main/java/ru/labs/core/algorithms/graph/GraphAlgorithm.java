package ru.labs.core.algorithms.graph;

import ru.labs.core.structures.graph.CommonGraph;

public interface GraphAlgorithm<G extends CommonGraph, R>{
    R execute(G graph);
}
