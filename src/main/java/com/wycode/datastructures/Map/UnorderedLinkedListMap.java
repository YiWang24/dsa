package com.wycode.datastructures.Map;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 * @author WY
 * @version 1.0
 **/

public class UnorderedLinkedListMap<K extends Comparable<K>, V> implements Map<K, V> {

    private class Entry {
        private K key;
        private V value;
        private Entry next;

        Entry(K k, V v, Entry n) {
            key = k;
            value = v;
            next = n;
        }

        Entry get(K k) {
            if (k != null && k.equals(key)) {
                return this;
            }
            if (next == null) {
                return null;
            }
            return next.get(k);
        }

    }

    private class EntryIterator implements Iterator<K> {
        private Entry current;

        public EntryIterator() {
            current = list;
        }

        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public K next() {
            K key = current.key;
            current = current.next;
            return key;
        }
    }

    int size = 0;
    private Entry list;


    @Override
    public void clear() {
        size = 0;
        list = null;
    }

    @Override
    public boolean containsKey(K key) {
        if (list == null) {
            return false;
        }
        return list.get(key) != null;
    }

    @Override
    public V get(K key) {
        if (list == null) {
            return null;
        }
        Entry entry = list.get(key);
        if (entry == null) {
            return null;
        }
        return entry.value;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        if (list != null) {
            Entry lookup = list.get(key);
            if (lookup == null) {
                list = new Entry(key, value, list);
                size = size + 1;
            } else {
                lookup.value = value;
            }
        } else {
            list = new Entry(key, value, list);
            size = size + 1;
        }
    }

    @Override
    public Set<K> keySet() {
        Entry current = list;
        Set<K> KeySet = new HashSet<K>();
        while (current != null) {
            KeySet.add(current.key);
            current = current.next;
        }
        return KeySet;
    }

    @Override
    public V remove(K key) {
        if (list == null) {
            return null;
        }

        if (list.key.equals(key)) {
            V remove = list.value;
            list = list.next;
            size = size - 1;
            return remove;
        }

        Entry prev = list;
        Entry current = list.next;
        while (current != null) {
            if (current.key.equals(key)) {
                prev.next = current.next;
                size = size - 1;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }

    @Override
    public V remove(K key, V value) {
        if (list == null) {
            return null;
        }

        if (list.key.equals(key) && list.value.equals(value)) {
            V remove = list.value;
            list = list.next;
            size = size - 1;
            return remove;
        }

        Entry prev = list;
        Entry current = list.next;
        while (current != null) {
            if (current.key.equals(key) && value.equals(current.value)) {
                prev.next = current.next;
                size = size - 1;
                return current.value;
            }
            prev = current;
            current = current.next;
        }

        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return new EntryIterator();
    }
}
