package ru.Labs.core.algorithms.search;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class RogueLikeSearchTest {

    @Test
    void solve_1x1Grid_returnsCorrectResult() {
        int[][] grid = {{5}};
        RogueLikeSearch.SearchResults result = RogueLikeSearch.solve(1, 1, grid);

        assertEquals(5, result.maxMoney());
        assertEquals("", result.path()); // Путь пуст, так как мы уже в целевой ячейке
    }

    @Test
    void solve_2x2Grid_returnsCorrectPathAndMoney() {
        // 1 2
        // 3 4
        // Макс путь: 1 -> 3 -> 4 = 8 (Вниз, Вправо / DR)
        int[][] grid = {
                {1, 2},
                {3, 4}
        };
        RogueLikeSearch.SearchResults result = RogueLikeSearch.solve(2, 2, grid);

        assertEquals(8, result.maxMoney());
        assertEquals("DR", result.path());
    }

    @Test
    void solve_horizontalOnly_returnsCorrectResult() {
        int[][] grid = {{1, 10, 100}};
        RogueLikeSearch.SearchResults result = RogueLikeSearch.solve(1, 3, grid);

        assertEquals(111, result.maxMoney());
        assertEquals("RR", result.path());
    }

    @Test
    void solve_verticalOnly_returnsCorrectResult() {
        int[][] grid = {
                {1},
                {10},
                {100}
        };
        RogueLikeSearch.SearchResults result = RogueLikeSearch.solve(3, 1, grid);

        assertEquals(111, result.maxMoney());
        assertEquals("DD", result.path());
    }

    @Test
    void solve_withNegativeNumbers_handlesCorrectly() {
        // 0  1
        // -5 2
        // Макс путь: 0 -> 1 -> 2 = 3 (Вправо, Вниз / RD)
        int[][] grid = {
                {0, 1},
                {-5, 2}
        };
        RogueLikeSearch.SearchResults result = RogueLikeSearch.solve(2, 2, grid);

        assertEquals(3, result.maxMoney());
        assertEquals("RD", result.path());
    }
}