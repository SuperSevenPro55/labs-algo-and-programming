package ru.labs.core.models.graph;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@RequiredArgsConstructor
public class Node {
    private final Long id;
    private final Double longitude;
    private final Double latitude;

    private Double x;
    private Double y;
}
