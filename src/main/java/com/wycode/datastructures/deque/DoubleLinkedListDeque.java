package com.wycode.datastructures.deque;

import java.util.Iterator;

/**
 * A doubly linked list implementation of the Deque interface.
 * Supports insertion, deletion, retrieval, and iteration operations.
 *
 * <p><b>Complexity Summary:</b></p>
 * - `size()`: O(1)
 * - `isEmpty()`: O(1)
 * - `addFirst(T item)`: O(1)
 * - `addLast(T item)`: O(1)
 * - `removeFirst()`: O(1)
 * - `removeLast()`: O(1)
 * - `get(int index)`: O(n)
 * - `getRecursive(int index)`: O(n)
 * - `iterator()`: O(1)
 *
 * @param <T> the type of elements stored in this deque
 * @author WY
 * @version 1.0
 */

public class DoubleLinkedListDeque<T> implements Deque<T>, Iterable<T> {

    /**
     * Internal class representing a node in the doubly linked list.
     */
    private class Node {
        private T data;
        private Node prev;
        private Node next;

        public Node(T data, Node prev, Node next) {
            this.data = data;
            this.prev = prev;
            this.next = next;
        }
    }

    /**
     * Iterator implementation for DoubleLinkedListDeque.
     * Time Complexity: O(1) per operation.
     */
    private class DoubleLinkedListDequeIterator implements Iterator<T> {
        private Node current = head.next;

        @Override
        public boolean hasNext() {
            return current != head;
        }

        @Override
        public T next() {
            T data = current.data;
            current = current.next;
            return data;
        }
    }


    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListDequeIterator();
    }

    private Node head;
    private int size;

    public DoubleLinkedListDeque() {
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public DoubleLinkedListDeque(T item) {
        head = new Node(null, null, null);
        Node newNode = new Node(item, head, head);
        head.next = newNode;
        head.prev = newNode;
        size++;
    }

    /**
     * Adds an element to the beginning of the list.
     * Time Complexity: O(1)
     */
    @Override
    public void addFirst(T item) {
        Node newNode = new Node(item, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * Time Complexity: O(1)
     */
    @Override
    public void addLast(T item) {
        Node newNode = new Node(item, head.prev, head);
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Removes the first element of the list.
     * Time Complexity: O(1)
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            return null;
        }
        Node removed = head.next;
        head.next = removed.next;
        head.next.prev = head;
        size--;
        return removed.data;
    }

    /**
     * Removes the last element of the list.
     * Time Complexity: O(1)
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            return null;
        }
        Node removed = head.prev;
        head.prev = removed.prev;
        removed.prev.next = head;
        size--;
        return removed.data;
    }

    /**
     * Retrieves an element at the specified index.
     * Time Complexity: O(n)
     *
     * @param index the index of the element
     * @return the element at the given index
     */
    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node p = head.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        return p.data;
    }

    /**
     * Helper method for recursive get operation.
     * Time Complexity: O(n)
     *
     * @param index the index of the element
     * @param node  the current node in recursion
     * @return the element at the given index
     */
    private T getRecursive(int index, Node node) {
        if (index == 0) {
            return node.data;
        }
        return getRecursive(index - 1, node.next);
    }

    /**
     * Recursively retrieves an element at the specified index.
     * Time Complexity: O(n)
     *
     * @param index the index of the element
     * @return the element at the given index
     * @throws IndexOutOfBoundsException if the index is out of range
     */
    public T getRecursive(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        return getRecursive(index, head.next);
    }
}
