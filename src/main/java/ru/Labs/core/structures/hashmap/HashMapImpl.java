package ru.Labs.core.structures.hashmap;

import java.util.ArrayList;
import java.util.LinkedList;

public class HashMapImpl<V> implements HashMap<String, V> {
    private static final int TABLE_SIZE = 300007;
    private static final int A = 31;
    private final ArrayList<LinkedList<Node<String, V>>> table;

    public HashMapImpl() {
        table = new ArrayList<>(TABLE_SIZE);
        for (int i = 0; i < TABLE_SIZE; i++) {
            table.add(null);
        }
    }

    private static class Node<K, V> {
        K key;
        V value;

        Node(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private int hashByString(String value) {
        long hash = 0;
        for (Character ch : value.toCharArray()) {
            hash = (hash * A + ch) % TABLE_SIZE;
        }
        return (int) hash;
    }

    @Override
    public void put(String key, V value) {
        int index = hashByString(key);
        LinkedList<Node<String, V>> chamber = table.get(index);

        if (chamber == null) {
            chamber = new LinkedList<>();
            table.set(index, chamber);
        }

        for (Node<String, V> node : chamber) {
            if (node.key.equals(key)) {
                node.value = value;
                return;
            }
        }

        chamber.add(new Node<>(key, value));
    }

    @Override
    public V getValue(String key) {
        int index = hashByString(key);
        LinkedList<Node<String, V>> chamber = table.get(index);

        if (chamber == null) {
            return null;
        }

        for (Node<String, V> node : chamber) {
            if (node.key.equals(key)) {
                return node.value;
            }
        }

        return null;
    }

    @Override
    public V delete(String key) {
        int index = hashByString(key);
        LinkedList<Node<String, V>> chamber = table.get(index);

        if (chamber == null) {
            return null;
        }

        var iterator = chamber.iterator(); // Безопасное удаление во время перебора
        while (iterator.hasNext()) {
            Node<String, V> node = iterator.next();

            if (node.key.equals(key)) {
                V value = node.value;
                iterator.remove();
                return value;
            }
        }

        return null;
    }
}