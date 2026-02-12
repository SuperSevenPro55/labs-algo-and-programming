package Sorting;

import java.util.List;

public interface sorting <T extends Comparable<? super T>> {
    void sort(List<T> nums);
}
