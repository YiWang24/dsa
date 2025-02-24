package com.wycode.datastructures.deque;

/**
 * A generic Deque (double-ended queue) interface that supports operations
 * for adding, removing, and retrieving elements from both ends.
 *
 * @param <T> the type of elements stored in this deque
 */
public interface Deque<T> {

    /**
     * Adds an element to the front of the deque.
     * Time Complexity: O(1)
     *
     * @param item the element to be added
     */
    void addFirst(T item);

    /**
     * Adds an element to the end of the deque.
     * Time Complexity: O(1)
     *
     * @param item the element to be added
     */
    void addLast(T item);

    /**
     * Checks if the deque is empty.
     * Time Complexity: O(1)
     *
     * @return true if the deque is empty, false otherwise
     */
    default boolean isEmpty() {
        return size() == 0;
    }

    /**
     * Returns the number of elements in the deque.
     * Time Complexity: O(1)
     *
     * @return the size of the deque
     */
    int size();

    /**
     * Removes the first element from the deque.
     * Time Complexity: O(1)
     *
     * @return the first element of the deque
     */
    T removeFirst();

    /**
     * Removes the last element from the deque.
     * Time Complexity: O(1)
     *
     * @return the last item of the deque
     */
    T removeLast();

    /**
     * Retrieves the element at the specified index.
     * Time Complexity: O(n) (as it may require traversal) or O(1)
     *
     * @param index the index of the element to retrieve
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    T get(int index);
}
