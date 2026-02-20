package HashMap;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapImpl<K, V> implements HashMap<String, V>{
    private static final int TABLE_SIZE = 300007;
    private static final int A = 31;
    private final ArrayList<LinkedList<Node<K, V>>> table;

    public static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    public HashMapImpl() {
        table = new ArrayList<>(TABLE_SIZE);
        for (int i = 0; i < TABLE_SIZE; i++) {
            table.add(null);
        }
    }

    private long hashByString(String value) {
        long hash = 0;
        for (Character ch : value.toCharArray()) {
            hash = (hash * A + ch) % TABLE_SIZE;
        }
        return hash;
    }

    @Override
    public void put(String key, V value) {
        long index = hashByString(key);

    }

    @Override
    public V getValue(String key) {
        //TODO
        return null;
    }

    @Override
    public V delete(String key) {

        return null;
    }
}
