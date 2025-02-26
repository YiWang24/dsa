package com.wycode.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A dynamic array implementation of the List interface.
 * Supports adding, removing, retrieving, and resizing operations.
 *
 * @param <T> the type of elements stored in this list
 * @author WY
 * @version 1.0
 * <p>
 * Complexity Summary:
 * - Access (get/set): O(1)
 * - Insert at end (addLast): O(1) amortized
 * - Insert at beginning (addFirst): O(n)
 * - Insert at arbitrary position: O(n)
 * - Remove last element (removeLast): O(1)
 * - Remove first element (removeFirst): O(n)
 * - Remove arbitrary element: O(n)
 * - Contains check: O(n)
 * - Resize operation: O(n)
 * - Space Complexity: O(n)
 **/

public class ArrayList<T> implements List<T>, Iterable<T> {

    private class ArrayListIterator implements Iterator<T> {
        int index = 0;

        @Override
        public boolean hasNext() {
            return index < size;
        }

        @Override
        public T next() {
            return items[index++];
        }
    }

    private static final int DEFAULT_CAPACITY = 10; // Default initial capacity
    private T[] items; // Array to store elements
    private int size; // Number of elements currently in the list

    @Override
    public Iterator<T> iterator() {
        return new ArrayListIterator();
    }

    /**
     * Default constructor initializing the list with a default capacity.
     * Time Complexity: O(1)
     * Space Complexity: O(n), where n = DEFAULT_CAPACITY
     */
    public ArrayList() {
        items = (T[]) new Object[DEFAULT_CAPACITY];
        size = 0;
    }

    /**
     * Constructor that initializes the list with a specified capacity.
     * Throws an exception if the capacity is negative.
     * Time Complexity: O(1)
     * Space Complexity: O(n), where n = capacity
     *
     * @param capacity the initial capacity of the list
     */
    public ArrayList(int capacity) {
        if (capacity < 0) {
            throw new NoSuchElementException("Capacity must be greater than 0:" + capacity);
        }
        items = (T[]) new Object[capacity];
        this.size = 0;
    }

    /**
     * Resizes the internal array to the specified capacity.
     * Time Complexity: O(n), where n is the current size
     * Space Complexity: O(n)
     *
     * @param capacity the new capacity of the list
     */
    private void resize(int capacity) {
        T[] newItems = (T[]) new Object[capacity];
        for (int i = 0; i < size; i++) {
            newItems[i] = items[i];
        }
        items = newItems;
    }

    /**
     * Ensures that the array has enough space to accommodate new elements.
     * Doubles the capacity if the array is full.
     * Time Complexity: O(n) when resizing, O(1) otherwise (amortized O(1))
     */
    private void ensureCapacity() {
        if (size == items.length) {
            resize(items.length * 2);
        }
    }

    /**
     * Reduces the size of the array if the number of elements is less than 1/4 of its capacity.
     * Ensures that the capacity never goes below DEFAULT_CAPACITY.
     * Time Complexity: O(n) when resizing, O(1) otherwise
     */
    private void trimToSize() {
        if (size < items.length / 4 && items.length > DEFAULT_CAPACITY) {
            int newCapacity = Math.max(items.length / 2, DEFAULT_CAPACITY);
            resize(newCapacity);
        }
    }

    /**
     * Returns the number of elements in the list.
     * Time Complexity: O(1)
     *
     * @return the size of the list
     */
    @Override
    public int size() {
        return size;
    }


    /**
     * Checks if the list contains the specified element.
     * Time Complexity: O(n) in worst case
     *
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        if (size == 0) {
            return false;
        }
        for (int i = 0; i < size; i++) {
            if (items[i].equals(element)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Inserts an element at the specified index.
     * Shifts elements to the right if necessary.
     * Time Complexity: O(n) in worst case (insertion at the beginning)
     *
     * @param index   the position to insert the element
     * @param element the element to be inserted
     */
    @Override
    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        ensureCapacity();
        for (int i = size; i > index; i--) {
            items[i] = items[i - 1];
        }
        items[index] = element;
        size++;

    }

    /**
     * Removes the element at the specified index and returns it.
     * Shifts elements to the left if necessary.
     * Time Complexity: O(n) in worst case (removal at the beginning)
     *
     * @param index the index of the element to remove
     * @return the removed element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        T item = items[index];
        for (int i = index; i < size - 1; i++) {
            items[i] = items[i + 1];
        }
        items[--size] = null;
        trimToSize();
        return item;

    }

    /**
     * Appends an element to the beginning of the list.
     * Time Complexity: O(n) in worse case (adding at the beginning)
     *
     * @param element the element to add
     */
    @Override
    public void addFirst(T element) {
        ensureCapacity();
        for (int i = size; i > 0; i--) {
            items[i] = items[i - 1];
        }
        items[0] = element;
        size++;

    }

    /**
     * Appends an element to the end of the list.
     * Time Complexity: O(1) (amortized)
     *
     * @param element the element to add
     */
    @Override
    public void addLast(T element) {
        ensureCapacity();
        items[size++] = element;
    }


    /**
     * Removes and returns the first element of the list.
     * Shifts all elements to the left to maintain order.
     * If the number of elements is less than 1/4 of the capacity, it triggers a size reduction.
     * <p>
     * Time Complexity: O(n) (due to shifting elements)
     * Space Complexity: O(1)
     *
     * @return the removed first element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        T item = items[0];
        for (int i = 1; i < size; i++) {
            items[i - 1] = items[i];
        }
        items[--size] = null;
        trimToSize();
        return item;
    }

    /**
     * Removes and returns the last element of the list.
     * Simply reduces the size and removes the last reference.
     * If the number of elements is less than 1/4 of the capacity, it triggers a size reduction.
     * <p>
     * Time Complexity: O(1)
     * Space Complexity: O(1)
     *
     * @return the removed last element
     * @throws NoSuchElementException if the list is empty
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        T item = items[size - 1];
        items[--size] = null;
        trimToSize();
        return item;
    }

    /**
     * Returns the element at the specified index.
     * Time Complexity: O(1)
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return items[index];
    }

    /**
     * Returns the first element in the list.
     * Time Complexity: O(1)
     *
     * @return the first element, or null if empty
     */
    @Override
    public T getFirst() {
        return isEmpty() ? null : get(0);
    }

    /**
     * Returns the last element in the list.
     * Time Complexity: O(1)
     *
     * @return the last element, or null if empty
     */
    @Override
    public T getLast() {
        return isEmpty() ? null : get(size - 1);
    }

    /**
     * Method to create an ArrayList from an argument list
     *
     * @param elements argument list
     * @param <T>      type of argument
     * @return list
     */
    public static <T> ArrayList<T> of(T... elements) {
        ArrayList<T> list = new ArrayList<>(elements.length);
        for (T element : elements) {
            list.addLast(element);
        }
        return list;
    }


}
