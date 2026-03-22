package ru.Labs.ui.menu;

public enum Labs {
    UNKNOWN(0, 0),
    BASE(1, 2),
    SORTINGS_1(2, 3),
    SORTINGS_2(3, 3),
    MINS(4, 2),
    HASH_MAP(5, 1),
    BINARY_SEARCH(6, 3),
    DYNAMIC_PROGRAMMING(7, 3),
    REQUESTS(8, 2),
    TREES(9, 2);

    private final int id;
    private final int subItemsCount;

    Labs(int id, int subItemsCount) {
        this.id = id;
        this.subItemsCount = subItemsCount;
    }

    public int getId() {
        return id;
    }
    public int getSubItemsCount() {
        return subItemsCount;
    }

    public static Labs getById(Integer id) {
        for (Labs lab : values()) {
            if (lab.id == id) {
                return lab;
            }
        }
        return UNKNOWN;
    }
}