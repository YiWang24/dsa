package com.wycode.datastructures.list;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.NoSuchElementException;

/**
 * Unit tests for the SingleLinkedList implementation using JUnit 5.
 */
public class SingleLinkedListTest {

    @Test
    public void testForEach() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        for (Integer i : list) {
            System.out.println(i);
        }
        list.print();
    }

    @Test
    public void testIsEmpty() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertTrue(list.isEmpty(), "List should be empty");
        list.addFirst(1);
        assertFalse(list.isEmpty(), "List should be empty");
        list.removeFirst();
        assertTrue(list.isEmpty(), "List should be empty");
        list.addLast(1);
        assertFalse(list.isEmpty(), "List should be empty");
        list.removeLast();
        assertTrue(list.isEmpty(), "List should be empty");
    }

    @Test
    public void testSingleElementConstructor() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>(10);
        assertEquals(1, list.size(), "List should have one element");
        assertEquals(10, list.getFirst(), "First element should be 10");
    }


    @Test
    public void testAddFirst() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addFirst(10);
        list.addFirst(20);
        assertEquals(20, list.getFirst(), "First element should be 20");
        assertEquals(10, list.getLast(), "last element should be 10 ");
        assertEquals(2, list.size(), "Size should be 2");
    }

    @Test
    public void testAddLast() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(20, list.getLast(), "Last element should be 20");
        assertEquals(2, list.size(), "Size should be 2");
    }

    @Test
    public void testInsert() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        list.insert(0, 99);
        assertEquals(99, list.get(0), "Element at index 0 should be 99");
        assertEquals(1, list.get(1), "Element at index 1 should be 1");
        assertEquals(4, list.size(), "Size should be 3");
    }

    @Test
    public void testRemoveFirst() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(10, list.removeFirst(), "Removed first element should be 10");
        assertEquals(20, list.removeLast(), "Removed first element should be 10");
        assertEquals(0, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void testRemoveLast() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(20, list.removeLast(), "Removed last element should be 20");
        assertEquals(1, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void testRemoveAtIndex() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        assertEquals(20, list.remove(1), "Removed element at index 1 should be 20");
        assertEquals(2, list.size(), "Size should be 2 after removal");
        assertEquals(30, list.remove(1), "Removed element at index 1 should be 30");
        assertEquals(1, list.size(), "Size should be 2 after removal");
    }

    @Test
    public void testContains() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertTrue(list.contains(20), "List should contain 20");
        assertFalse(list.contains(30), "List should not contain 30");
    }

    @Test
    public void testGet() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(10, list.get(0), "Element at index 0 should be 10");
        assertEquals(20, list.get(1), "Element at index 1 should be 20");
    }

    @Test
    public void testMegaInsert() {
        SingleLinkedList<Integer> L = new SingleLinkedList<>();
        int N = 100;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }

        for (int i = 0; i < N; i += 1) {
            L.addLast(L.get(i));
        }
    }
    @Test
    public void testExceptionHandling() {
        SingleLinkedList<Integer> list = new SingleLinkedList<>();
        assertThrows(NoSuchElementException.class, list::removeFirst, "Should throw NoSuchElementException");
        assertThrows(NoSuchElementException.class, list::removeLast, "Should throw NoSuchElementException");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0), "Should throw IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0), "Should throw IndexOutOfBoundsException");
    }
}
