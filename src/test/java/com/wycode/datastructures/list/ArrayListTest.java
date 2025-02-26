package com.wycode.datastructures.list;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.util.NoSuchElementException;

/**
 * Unit tests for the ArrayList implementation using JUnit 5.
 */
public class ArrayListTest {

    @Test
    public void testForEach() {
        ArrayList<Integer> list = new ArrayList<>();
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
        ArrayList<Integer> list = new ArrayList<>();
        assertTrue(list.isEmpty(), "List should be empty");
        list.addLast(1);
        assertFalse(list.isEmpty(), "List should not be empty");
    }

    @Test
    public void testOfMethod() {
        ArrayList<String> list = ArrayList.of("A", "B", "C");
        assertEquals(3, list.size(), "List size should be 3");
        assertEquals("A", list.getFirst(), "First element should be A");
        assertEquals("C", list.getLast(), "Last element should be C");
    }

    @Test
    public void testCustomCapacityConstructor() {
        ArrayList<Integer> list = new ArrayList<>(20);
        assertEquals(0, list.size(), "List should be empty initially");
        list.addLast(5);
        assertEquals(5, list.getLast(), "Last element should be 5");
        assertEquals(1, list.size(), "Size should be 1 after adding an element");
    }

    @Test
    public void testInsertLast() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addLast(1);
        list.addLast(2);
        list.addLast(3);
        assertEquals(3, list.size(), "Size should be 3");
        assertEquals(3, list.getLast(), "Last element should be 3");
        list.addLast(99);
        assertEquals(99, list.getLast());
        list.addLast(36);
        assertEquals(36, list.getLast());
    }

    @Test
    public void testInsertFirst() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addFirst(0);
        list.addFirst(1);
        assertEquals(1, list.getFirst(), "First element should be 1");
        assertEquals(0, list.getLast(), "Last element should be 0");
    }

    @Test
    public void testInsertAtIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addLast(1);
        list.addLast(2);
        list.insert(1, 99);
        assertEquals(99, list.get(1), "Element at index 1 should be 99");
    }

    @Test
    public void testInsert() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10000; i++) {
            list.insert(i, i);
        }
        for (int i = 0; i < 10000; i++) {
            assertEquals(i, list.get(i), "Element at index 1 should be " + i);
        }
        assertEquals(10000, list.size(), "Size should be 10000");
    }


    @Test
    public void testRemoveLast() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addLast(1);
        list.addLast(2);
        assertEquals(2, list.removeLast(), "Removed last element should be 2");
        assertEquals(1, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void testRemoveFirst() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addLast(1);
        list.addLast(2);
        assertEquals(1, list.removeFirst(), "Removed first element should be 1");
        assertEquals(1, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void testRemoveAtIndex() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            list.addLast(i);
        }
        assertEquals(100, list.size(), "Size should be 10000");
        for (int i = 0; i < 100; i++) {
            assertEquals(i, list.get(i), "Element at index 1 should be " + i);
        }
        for (int i = 0; i < 100; i++) {
            assertEquals(i, list.remove(0), "Element at index 1 should be " + i);
        }
        assertEquals(0, list.size(), "Size should be 0");
    }

    @Test
    public void testContains() {
        ArrayList<Integer> list = new ArrayList<>();
        list.addLast(2);
        assertTrue(list.contains(2), "List should contain 2");
        assertFalse(list.contains(99), "List should not contain 99");
    }


    @Test
    public void testLargeDataset() {
        ArrayList<Integer> largeList = new ArrayList<>();
        int largeSize = 100000;
        for (int i = 0; i < largeSize; i++) {
            largeList.addLast(i);
        }
        assertEquals(largeSize, largeList.size(), "Large list size should be " + largeSize);
        assertEquals(0, largeList.getFirst(), "First element should be 0");
        assertEquals(largeSize - 1, largeList.getLast(), "Last element should be " + (largeSize - 1));

        for (int i = 0; i < largeSize; i++) {
            assertEquals(i, largeList.removeFirst(), "Removed element should be " + i);
        }
        assertTrue(largeList.isEmpty(), "Large list should be empty after removals");
    }

    @Test
    public void testMegaInsert() {
        ArrayList<Integer> L = new ArrayList<>();
        int N = 1000000;
        for (int i = 0; i < N; i += 1) {
            L.addLast(i);
        }

        for (int i = 0; i < N; i += 1) {
            L.addLast(L.get(i));
        }
    }


    @Test
    public void testExceptionHandling() {
        ArrayList<Integer> list = new ArrayList<>();
        assertThrows(NoSuchElementException.class, list::removeFirst, "Should throw NoSuchElementException");
        assertThrows(NoSuchElementException.class, list::removeLast, "Should throw NoSuchElementException");
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0), "Should throw IndexOutOfBoundsException");
    }
}
