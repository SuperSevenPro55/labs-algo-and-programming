package ru.Labs.core.structures.stack;

public interface MinStack<T extends Number> {
    void push(T value);
    void pop();
    T top();
    T min();
    boolean isEmpty();
}
