package com.wycode.datastructures.Map;

import java.util.*;

/**
 * @author WY
 * @version 1.0
 **/

public class HashMap<K extends Comparable<K>, V> implements Map<K, V> {
    private int capacity;
    private int size;
    private double loadFactor = 0.75;
    private LinkedList<Entry>[] buckets;

    private class Entry {
        K key;
        V value;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    private class HashMapIterator implements Iterator<K> {
        private int bucketIndex = 0;
        private Iterator<Entry> bucketIterator;

        public HashMapIterator() {
            moveToNext();
        }

        @Override
        public boolean hasNext() {
            return bucketIterator != null && bucketIterator.hasNext();
        }

        @Override
        public K next() {
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            K key = bucketIterator.next().key;
            if (!bucketIterator.hasNext()) {
                moveToNext();
            }
            return key;
        }

        private void moveToNext() {
            while (bucketIndex < capacity && (buckets[bucketIndex] == null || buckets[bucketIndex].isEmpty())) {
                bucketIndex = bucketIndex + 1;
            }
            if (bucketIndex < capacity) {
                bucketIterator = buckets[bucketIndex].iterator();
                bucketIndex = bucketIndex + 1;
            } else {
                bucketIterator = null;
            }
        }
    }

    public HashMap() {
        this(16);
    }

    public HashMap(int capacity) {
        this.capacity = capacity;
        buckets = createTable(capacity);
        size = 0;
    }

    public HashMap(int capacity, double loadFactor) {
        this(capacity);
        this.loadFactor = loadFactor;
    }

    @Override
    public void clear() {
        this.capacity = 16;
        buckets = createTable(16);
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        return get(key) != null;
    }

    @Override
    public V get(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (entry.key.equals(key)) {
                    return entry.value;
                }
            }
        }
        return null;


    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        if ((double) size / capacity > loadFactor) {
            resize(capacity * 2);
        }
        int index = hash(key);
        if (buckets[index] == null) {
            Entry entry = createEntry(key, value);
            buckets[index].add(entry);
            size = size + 1;
            return;
        }
        for (Entry entry : buckets[index]) {
            if (entry.key.equals(key)) {
                entry.value = value;
                return;
            }
        }
        buckets[index].add(createEntry(key, value));
        size = size + 1;

    }

    @Override
    public Set<K> keySet() {
        HashSet<K> set = new HashSet<K>();
        for (LinkedList<Entry> bucket : buckets) {
            if (bucket != null) {
                for (Entry entry : bucket) {
                    if (entry.key != null) {
                        set.add(entry.key);
                    }
                }
            }
        }
        return set;
    }

    @Override
    public V remove(K key) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (key.equals(entry.key)) {
                    V value = entry.value;
                    buckets[index].remove(entry);
                    size = size - 1;
                    return value;
                }
            }
        }
        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (key == null) {
            throw new IllegalArgumentException("key is null");
        }
        int index = hash(key);
        if (buckets[index] != null) {
            for (Entry entry : buckets[index]) {
                if (key.equals(entry.key) && value.equals(entry.value)) {
                    V remove = entry.value;
                    buckets[index].remove(entry);
                    size = size - 1;
                    return remove;
                }
            }
        }
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new HashMapIterator();
    }

    private void resize(int capacity) {
        LinkedList<Entry>[] table = createTable(capacity);
        for (LinkedList<Entry> bucket : buckets) {
            if (bucket != null) {
                for (Entry entry : bucket) {
                    int newIndex = hash(entry.key);
                    table[newIndex].add(entry);
                }
            }
        }
        this.capacity = capacity;
        this.buckets = table;
    }

    private int hash(K key) {
        int h = key.hashCode();
        h ^= (h >>> 20) ^ (h >>> 12) ^ (h >>> 7) ^ (h >>> 4);
        return h & (capacity - 1);
    }

    private Entry createEntry(K key, V value) {
        return new Entry(key, value);
    }

    private LinkedList<Entry> createBucket() {
        return new LinkedList<>();
    }

    private LinkedList<Entry>[] createTable(int tableSize) {
        LinkedList<Entry>[] table = new LinkedList[tableSize];
        for (int i = 0; i < tableSize; i++) {
            table[i] = createBucket();
        }
        return table;
    }
}
