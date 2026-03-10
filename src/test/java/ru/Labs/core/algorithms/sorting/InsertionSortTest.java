package ru.Labs.core.algorithms.sorting;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InsertionSortTest {

    private final InsertionSort sorter = new InsertionSort();

    @Test
    void sort_nullList_doesNotThrowException() {
        // Проверяем, что метод корректно обрабатывает null, как и написано в коде
        assertDoesNotThrow(() -> sorter.sort(null));
    }

    @Test
    void sort_emptyList_doesNothing() {
        List<Integer> list = new ArrayList<>();
        sorter.sort(list);
        assertTrue(list.isEmpty());
    }

    @Test
    void sort_alreadySortedList_remainsSame() {
        List<Integer> list = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5));
        sorter.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    void sort_reverseSortedList_sortsCorrectly() {
        List<Integer> list = new ArrayList<>(Arrays.asList(5, 4, 3, 2, 1));
        sorter.sort(list);
        assertEquals(Arrays.asList(1, 2, 3, 4, 5), list);
    }

    @Test
    void sort_listWithDuplicates_sortsCorrectly() {
        List<Integer> list = new ArrayList<>(Arrays.asList(3, 1, 2, 3, 1));
        sorter.sort(list);
        assertEquals(Arrays.asList(1, 1, 2, 3, 3), list);
    }

    @Test
    void sort_listWithNegativeNumbers_sortsCorrectly() {
        List<Integer> list = new ArrayList<>(Arrays.asList(0, -5, 10, -2, 3));
        sorter.sort(list);
        assertEquals(Arrays.asList(-5, -2, 0, 3, 10), list);
    }
}