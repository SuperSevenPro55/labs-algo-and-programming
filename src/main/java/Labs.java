import java.util.stream.Stream;

public enum Labs {
    UNKNOWN(0),
    BASE(1),
    SORTINGS_1(2),
    SORTINGS_2(3),
    MINS(4),
    HASH_MAP(5),
    BINARY_SEARCH(6),
    DYNAMIC_PROGRAMMING(7),
    REQUESTS(8),
    TREES(9);
    private int id;

    Labs(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public static Labs getById(Integer id) {
        return Stream.of(Labs.values())
                .filter(x -> x.id == id)
                .findFirst()
                .orElse(UNKNOWN);
    }
}
