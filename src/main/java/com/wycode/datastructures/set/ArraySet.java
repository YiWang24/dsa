package com.wycode.datastructures.set;

import java.util.Iterator;

/**
 * @author WY
 * @version 1.0
 **/

public class ArraySet<T> implements Set<T> ,Iterable<T>{

    private class ArraySetIterator implements Iterator<T> {
        private int index = 0;
        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return items[index++];
        }
    }
    private T[] items;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new ArraySetIterator();
    }

    public ArraySet() {
        items = (T[]) new Object[100];
        size = 0;
    }

    @Override
    public void add(T item) {
        if(item == null) {
            throw new IllegalArgumentException("cannot add null item");
        }
        if (contains(item)) {
            return;
        }
        items[size++] = item;
    }

    @Override
    public boolean contains(T item) {
        if(item == null) {
            throw new IllegalArgumentException("cannot contain null item");
        }
        for (int i = 0; i < size; i++) {
            if (items[i].equals(item)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public int size() {
        return size;
    }
}
