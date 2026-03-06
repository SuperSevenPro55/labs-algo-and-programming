package ru.Labs.core.algorithms.sorting;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
class InsertionSortTest {
    @Test
    void testInsertionSort_emptyList_shouldDoNothing() {
        List<Integer> list = new ArrayList<>();
        new InsertionSort().sort(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void testInsertionSort_normalArray_shouldSort() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 4));
        new InsertionSort().sort(list);

        List<Integer> expected = Arrays.asList(1, 3, 4);
        assertFalse(list.isEmpty());
        assertEquals(expected, list);
    }
}