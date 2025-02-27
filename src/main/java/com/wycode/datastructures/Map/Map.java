package com.wycode.datastructures.Map;

import java.util.Set;

public interface Map<K,V> extends Iterable<K> {

    void clear();

    boolean containsKey(K key);

    V get(K key);

    int size();

    void put(K key, V value);

    Set<K> keySet();

    V remove(K key);

    V remove(K key, V value);

}
