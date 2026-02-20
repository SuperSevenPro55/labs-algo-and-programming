package ru.Labs.core.algorithms.sorting;

import java.util.List;

public interface Sorter<T extends Comparable<? super T>> {
    void sort(List<T> nums);
}
