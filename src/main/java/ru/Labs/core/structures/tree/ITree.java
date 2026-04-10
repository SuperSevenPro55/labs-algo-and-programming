package ru.Labs.core.structures.tree;

public interface ITree {
    void insert(int value);

    void delete(int value);

    boolean hasValue(int value);

    Integer next(int value);

    Integer prev(int value);
}
