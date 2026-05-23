package ru.labs.core.models.graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Edge {
    private final Long u;
    private final Long v;

    private Double uX;
    private Double uY;
    private Double vX;
    private Double vY;

    private Long distance;
}
