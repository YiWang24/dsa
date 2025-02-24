package com.wycode.datastructures.deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/


public class ArrayDequeTest {
    @Test
    public void testForEach() {
        ArrayDeque<Integer> deque = new ArrayDeque<>();
        ArrayDeque<Integer> deque2 = new ArrayDeque<>();
        for (int i = 0; i < 3; i++) {
            deque.addFirst(i);
            deque2.addFirst(i);
        }
        for (Integer i : deque) {
            System.out.println(i);
        }
        for (Integer i : deque2) {
            System.out.println(i);
        }
    }

    @Test
    public void normalTest() {
        Deque<Integer> arrayDeque = new ArrayDeque<>();
        Deque<Integer> linkedListDeque = new DoubleLinkedListDeque<>();
        for (int i = 0; i < 1000; i++) {
            arrayDeque.addFirst(i);
            linkedListDeque.addFirst(i);
        }
        for (int i = 0; i < 1000; i++) {
            arrayDeque.addLast(i);
            linkedListDeque.addLast(i);
        }
        for (int i = 0; i < arrayDeque.size(); i++) {
            assertEquals(arrayDeque.get(i), linkedListDeque.get(i));
            assertEquals(arrayDeque.removeFirst(), linkedListDeque.removeFirst());
            assertEquals(arrayDeque.removeLast(), linkedListDeque.removeLast());
        }

    }



    @Test
    public void getTest() {
        Deque<String> lld1 = new ArrayDeque<String>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");
        lld1.addLast("d");
        assertEquals("a", lld1.get(0));
        assertEquals("b", lld1.get(1));
        assertEquals("c", lld1.get(2));
        assertEquals("d", lld1.get(3));
        assertNull(lld1.get(4));
    }


    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        Deque<String> lld1 = new ArrayDeque<String>();

        assertTrue( lld1.isEmpty());
        lld1.addFirst("front");

        // The && operator is the same as "and" in Python.
        // It's a binary operator that returns true if both arguments true, and false otherwise.
        assertEquals(1, lld1.size());
        assertFalse(lld1.isEmpty());

        lld1.addLast("middle");
        assertEquals(2, lld1.size());

        lld1.addLast("back");
        assertEquals(3, lld1.size());

        System.out.println("Printing out deque: ");

    }

    @Test
    /** Adds an item, then removes an item, and ensures that dll is empty afterwards. */
    public void addRemoveTest() {

        Deque<Integer> lld1 = new ArrayDeque<Integer>();
        // should be empty
        assertTrue(lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse( lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue( lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {

        Deque<Integer> lld1 = new ArrayDeque<Integer>();
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


        Deque<String> lld1 = new ArrayDeque<String>();
        Deque<Double> lld2 = new ArrayDeque<Double>();
        Deque<Boolean> lld3 = new ArrayDeque<Boolean>();

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

        Deque<Integer> lld1 = new ArrayDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertEquals(null, lld1.removeFirst());
        assertEquals( null, lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        Deque<Integer> lld1 = new ArrayDeque<Integer>();
        for (int i = 0; i < 1000000; i++) {
            lld1.addLast(i);
        }

        for (double i = 0; i < 500000; i++) {
            assertEquals(i, (double) lld1.removeFirst(), 0.0);
        }

        for (double i = 999999; i > 500000; i--) {
            assertEquals(i, (double) lld1.removeLast(), 0.0);
        }


    }
}
