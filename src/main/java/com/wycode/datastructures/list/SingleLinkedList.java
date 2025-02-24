package com.wycode.datastructures.list;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * A singly linked list implementation of the List interface.
 * Supports adding, removing, retrieving, and searching elements.
 *
 * @author WY
 * @version 1.0
 * single linked list
 * <p>
 * Complexity Summary:
 * - size() : O(1)
 * - isEmpty() : O(1)
 * - contains(T element) : O(n)
 * - insert(int index, T element) : O(n)
 * - addFirst(T element) : O(1)
 * - addLast(T element) : O(n)
 * - get(int index) : O(n)
 * - getFirst() : O(1)
 * - getLast() : O(n)
 * - remove(int index) : O(n)
 * - removeFirst() : O(1)
 * - removeLast() : O(n)
 **/

public class SingleLinkedList<T> implements List<T> ,Iterable<T>{
    /**
     * Internal class representing a node in the linked list.
     */
    private class Node {
        public T item;
        public Node next;

        public Node(T item, Node next) {
            this.item = item;
            this.next = next;
        }
    }

    private class SingleLinkedListIterator implements Iterator<T>{
        Node current = head.next;
        @Override
        public boolean hasNext() {
            return current != null;
        }

        @Override
        public T next() {
            T t = current.item;
            current = current.next;
            return t;
        }
    }
    private Node head;
    private int size;

    @Override
    public Iterator<T> iterator() {
        return new SingleLinkedListIterator();
    }

    /**
     * Constructs an empty linked list.
     */
    public SingleLinkedList() {
        head = new Node(null, null);
        size = 0;
    }

    /**
     * Constructs a linked list with a single element.
     *
     * @param item the first item in the list
     */
    public SingleLinkedList(T item) {
        head = new Node(null, null);
        head.next = new Node(item, null);
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
        while (p.next != null) {
            p = p.next;
            if (p.item.equals(element)) {
                return true;
            }
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
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node p = head;
        while (index > 0) {
            index--;
            p = p.next;
        }
        p.next = new Node(element, p.next);
        size++;
    }

    /**
     * Adds an element to the beginning of the list.
     * Time Complexity: O(1)
     */
    @Override
    public void addFirst(T element) {
        head.next = new Node(element, head.next); // we need ensure head always at the beginning of the list
        size++;
    }

    /**
     * Adds an element to the end of the list.
     * Time Complexity: O(n)
     */
    @Override
    public void addLast(T element) {
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        p.next = new Node(element, null);
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
            throw new IndexOutOfBoundsException("Index out of bounds");
        }

        Node p = head.next;
        while (index > 0) {
            index--;
            p = p.next;
        }
        return p.item;
    }

    /**
     * Retrieves the first element of the list.
     * Time Complexity: O(1)
     */
    @Override
    public T getFirst() {

        return isEmpty() ? null :head.next.item;
    }

    /**
     * Retrieves the last element of the list.
     * Time Complexity: O(n)
     */
    @Override
    public T getLast() {
        Node p = head;
        while (p.next != null) {
            p = p.next;
        }
        return isEmpty() ? null : p.item;
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
            throw new IndexOutOfBoundsException("Index out of bounds");
        }
        Node p = head;
        while (index > 0 && p.next != null) {
            index--;
            p = p.next;
        }
        T item = p.next.item;
        p.next = p.next.next;
        size--;
        return item;
    }

    /**
     * Removes the first element of the list.
     * Time Complexity: O(1)
     */
    @Override
    public T removeFirst() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        T item = head.next.item;
        head.next = head.next.next;
        size--;
        return item;
    }

    /**
     * Removes the last element of the list.
     * Time Complexity: O(n)
     */
    @Override
    public T removeLast() {
        if (size == 0) {
            throw new NoSuchElementException("List is empty");
        }
        Node p = head;
        while (p.next.next != null) {
            p = p.next;
        }
        T item = p.next.item;
        p.next = null;
        size--;
        return item;

    }


}
