package ru.Labs.core.structures.hashmap;

public interface HashMap<K, V> {
    void put(K key, V value);
    V getValue(K key);
    V delete(K key);
}
