package com.wycode.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/


public class DoubleLinkedListTest {

    @Test
    public void testForEach() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addFirst(1);
        list.addFirst(2);
        list.addFirst(3);
        for (Integer i : list) {
            System.out.println(i);
        }
        list.print();
    }

    @Test
    public void getTest() {
        DoubleLinkedList<String> lld1 = new DoubleLinkedList<String>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");
        lld1.addLast("d");
        assertEquals("a", lld1.get(0));
        assertEquals("b", lld1.get(1));
        assertEquals("c", lld1.get(2));
        assertEquals("d", lld1.get(3));
        assertEquals("a", lld1.get(0));
        assertEquals("b", lld1.get(1));
        assertEquals("c", lld1.get(2));
        assertEquals("d", lld1.get(3));
    }


    @Test
    public void testCustomCapacityConstructor() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>(20);
        assertEquals(1, list.size(), "List should be 1 initially");
        list.addLast(5);
        assertEquals(5, list.getLast(), "Last element should be 5");
        assertEquals(2, list.size(), "Size should be 1 after adding an element");
    }


    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    @Test
    public void addIsEmptySizeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        DoubleLinkedList<String> lld1 = new DoubleLinkedList<String>();

		assertTrue(lld1.isEmpty());
		lld1.addFirst("front");

		// The && operator is the same as "and" in Python.
		// It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse(lld1.isEmpty());

		lld1.addLast("middle");
		assertEquals(2, lld1.size());

		lld1.addLast("back");
		assertEquals(3, lld1.size());


    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        DoubleLinkedList<Integer> lld1 = new DoubleLinkedList<Integer>();
		// should be empty
		assertTrue(lld1.isEmpty());

		lld1.addFirst(10);
		// should not be empty
		assertFalse(lld1.isEmpty());

		lld1.removeFirst();
		// should be empty
		assertTrue( lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        DoubleLinkedList<Integer> lld1 = new DoubleLinkedList<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        assertEquals( 0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        DoubleLinkedList<String>  lld1 = new DoubleLinkedList<String>();
        DoubleLinkedList<Double>  lld2 = new DoubleLinkedList<Double>();
        DoubleLinkedList<Boolean> lld3 = new DoubleLinkedList<Boolean>();

        lld1.addFirst("string");
        lld2.addFirst(3.14159);
        lld3.addFirst(true);

        String s = lld1.removeFirst();
        double d = lld2.removeFirst();
        boolean b = lld3.removeFirst();

    }

    @Test
    /* check if null is return when removing from an empty LinkedListDeque. */
    public void emptyNullReturnTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        DoubleLinkedList<Integer> lld1 = new DoubleLinkedList<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull(lld1.removeFirst());
        assertNull(lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        System.out.println("Make sure to uncomment the lines below (and delete this print statement).");

        DoubleLinkedList<Integer> lld1 = new DoubleLinkedList<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals( i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals(i, (double) lld1.removeLast(), 0.0);
        }


    }

    @Test
    public void testIsEmpty() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertTrue(list.isEmpty(), "List should be empty initially");
    }

    @Test
    public void testSize() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertEquals(0, list.size(), "Initial size should be 0");
        list.addFirst(10);
        assertEquals(1, list.size(), "Size should be 1 after adding one element");
    }

    @Test
    public void testAddFirst() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addFirst(10);
        list.addFirst(20);
        assertEquals(20, list.getFirst(), "First element should be 20");
        assertEquals(2, list.size(), "Size should be 2");
    }

    @Test
    public void testAddLast() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(20, list.getLast(), "Last element should be 20");
        assertEquals(2, list.size(), "Size should be 2");
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
    public void testRemoveFirst() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(10, list.removeFirst(), "Removed first element should be 10");
        assertEquals(1, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void testRemoveLast() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(20, list.removeLast(), "Removed last element should be 20");
        assertEquals(1, list.size(), "Size should be 1 after removal");
    }

    @Test
    public void testRemoveAtIndex() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        list.addLast(30);
        assertEquals(20, list.remove(1), "Removed element at index 1 should be 20");
        assertEquals(2, list.size(), "Size should be 2 after removal");
    }

    @Test
    public void testContains() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertTrue(list.contains(20), "List should contain 20");
        assertFalse(list.contains(30), "List should not contain 30");
    }

    @Test
    public void testGet() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        list.addLast(10);
        list.addLast(20);
        assertEquals(10, list.get(0), "Element at index 0 should be 10");
        assertEquals(20, list.get(1), "Element at index 1 should be 20");
    }

    @Test
    public void testExceptionHandling() {
        DoubleLinkedList<Integer> list = new DoubleLinkedList<>();
        assertThrows(IndexOutOfBoundsException.class, () -> list.get(0), "Should throw IndexOutOfBoundsException");
        assertThrows(IndexOutOfBoundsException.class, () -> list.remove(0), "Should throw IndexOutOfBoundsException");
    }


}
