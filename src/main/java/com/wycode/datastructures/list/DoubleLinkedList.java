package com.wycode.datastructures.list;

import java.util.Iterator;

/**
 * A doubly linked list implementation of the List interface.
 * Supports insertion, deletion, retrieval, and search operations.
 * <p>
 * Complexity Summary:
 * - `size()`: O(1)
 * - `isEmpty()`: O(1)
 * - `contains(T element)`: O(n)
 * - `insert(int index, T element)`: O(n)
 * - `addFirst(T element)`: O(1)
 * - `addLast(T element)`: O(1)
 * - `get(int index)`: O(n)
 * - `getFirst()`: O(1)
 * - `getLast()`: O(1)
 * - `remove(int index)`: O(n)
 * - `removeFirst()`: O(1)
 * - `removeLast()`: O(1)
 *
 * @author WY
 * @version 1.0
 **/

public class DoubleLinkedList<T> implements List<T>, Iterable<T> {

    /**
     * Internal class representing a node in the doubly linked list.
     */
    private class Node {
        public T item;
        public Node prev;
        public Node next;

        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private class DoubleLinkedListIterator implements Iterator<T> {
        Node current = head.next;

        @Override
        public boolean hasNext() {
            return current != head;
        }

        @Override
        public T next() {
            T item = current.item;
            current = current.next;
            return item;
        }
    }

    private Node head;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new DoubleLinkedListIterator();
    }

    public DoubleLinkedList() {
        head = new Node(null, null, null);
        head.next = head;
        head.prev = head;
        size = 0;
    }

    public DoubleLinkedList(T item) {
        head = new Node(null, null, null);
        Node newNode = new Node(item, head, head);
        head.prev = newNode;
        head.next = newNode;
        size = 1;
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * Checks whether the list contains a specific element.
     * Time Complexity: O(n)
     *
     * @param element the element to search for
     * @return true if the element is found, false otherwise
     */
    @Override
    public boolean contains(T element) {
        Node p = head;
        while (p.next != head) {
            if (p.next.item.equals(element)) {
                return true;
            }
            p = p.next;
        }
        return false;
    }

    /**
     * Inserts an element at a specific position in the list.
     * Time Complexity: O(n)
     *
     * @param index   the index to insert the element at
     * @param element the element to be inserted
     */
    @Override
    public void insert(int index, T element) {
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node p = head;
        while (index > 0) {
            p = p.next;
            index--;
        }
        Node newNode = new Node(element, p, p.next);
        p.next.prev = newNode;
        p.next = newNode;
        size++;
    }

    /**
     * Adds an element to the beginning of the list.
     * Time Complexity: O(1)
     */
    @Override
    public void addFirst(T element) {
        Node newNode = new Node(element, head, head.next);
        head.next.prev = newNode;
        head.next = newNode;
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * Time Complexity: O(1)
     */
    @Override
    public void addLast(T element) {
        Node newNode = new Node(element, head.prev, head);
        head.prev.next = newNode;
        head.prev = newNode;
        size++;
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
        return p.item;
    }

    /**
     * Retrieves the first element of the list.
     * Time Complexity: O(1)
     */
    @Override
    public T getFirst() {
        return isEmpty() ? null : head.next.item;
    }

    /**
     * Retrieves the last element of the list.
     * Time Complexity: O(1)
     */
    @Override
    public T getLast() {
        return isEmpty() ? null : head.prev.item;
    }

    /**
     * Removes an element at a specific index.
     * Time Complexity: O(n)
     *
     * @param index the index to remove the element from
     * @return the removed element
     */
    @Override
    public T remove(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
        }
        Node p = head.next;
        while (index > 0) {
            p = p.next;
            index--;
        }
        p.prev.next = p.next;
        p.next.prev = p.prev;
        size--;
        return p.item;

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
        Node removedNode = head.next;
        head.next = removedNode.next;
        head.next.prev = head;
        size--;
        return removedNode.item;
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
        Node removedNode = head.prev;
        head.prev = removedNode.prev;
        head.prev.next = head;
        size--;
        return removedNode.item;
    }
}
