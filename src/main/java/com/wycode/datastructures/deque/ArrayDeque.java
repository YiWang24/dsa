package com.wycode.datastructures.deque;

import java.util.Iterator;

/**
 * A circular array implementation of the Deque interface.
 * This implementation supports efficient addition and removal at both ends.
 *
 * <p><b>Complexity Summary:</b></p>
 * - `size()`: O(1)
 * - `isEmpty()`: O(1)
 * - `addFirst(T item)`: O(1) amortized
 * - `addLast(T item)`: O(1) amortized
 * - `removeFirst()`: O(1) amortized
 * - `removeLast()`: O(1) amortized
 * - `get(int index)`: O(1)
 * - `resize(int capacity)`: O(n)
 *
 * @param <T> the type of elements stored in this deque
 * @author WY
 * @version 1.0
 */

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    /**
     * Iterator implementation for ArrayDeque.
     * Time Complexity: O(1) per operation.
     */
    private class ArrayDequeIterator implements Iterator<T> {
        int first = (nextFirst + 1) % items.length;

        @Override
        public boolean hasNext() {
            return items[first] != null;
        }

        @Override
        public T next() {
            T t = items[first];
            first = (first + 1) % items.length;
            return t;
        }
    }

    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T[]) new Object[8];
        nextFirst = items.length - 1;
        nextLast = 0;
        size = 0;
    }

    @Override
    public Iterator<T> iterator() {
        return new ArrayDequeIterator();
    }

    /**
     * Resizes the underlying array to the specified capacity.
     * Time Complexity: O(n)
     *
     * @param capacity the new capacity of the deque
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[(nextFirst + 1 + i) % items.length];
        }
        items = newItems;
        nextFirst = items.length - 1;
        nextLast = size;
    }

    /**
     * Shrinks the array size if the usage is too low.
     * Time Complexity: O(n) (if resizing happens), otherwise O(1).
     */
    private void trimToSize() {
        if (items.length > 16 && size < items.length / 4) {
            resize(items.length / 2);
        }
    }

    /**
     * Adds an element to the front of the deque.
     * Time Complexity: O(1) amortized
     */
    @Override
    public void addFirst(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst = nextFirst == 0 ? items.length - 1 : nextFirst - 1;
        size++;
    }

    /**
     * Adds an element to the end of the deque.
     * Time Complexity: O(1) amortized
     */
    @Override
    public void addLast(T item) {
        if (size == items.length) {
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast = (nextLast + 1) % items.length;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Removes and returns the first element from the deque.
     * Time Complexity: O(1) amortized
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        int first = (nextFirst + 1) % items.length;
        T item = items[first];
        items[first] = null;
        size--;
        nextFirst = first;
        trimToSize();
        return item;
    }

    /**
     * Removes and returns the last element from the deque.
     * Time Complexity: O(1) amortized
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        int last = nextLast == 0 ? items.length - 1 : nextLast - 1;
        T item = items[last];
        items[last] = null;
        size--;
        nextLast = last;
        trimToSize();
        return item;
    }

    /**
     * Retrieves an element at the specified index.
     * Time Complexity: O(1)
     *
     * @param index the index of the element
     * @return the element at the given index
     */
    @Override
    public T get(int index) {
        int i = (nextFirst + 1 + index) % items.length;
        return items[i];
    }
}
