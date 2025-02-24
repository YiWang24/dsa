package com.wycode.datastructures.deque;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/

public class DoubleLinkedListDequeTest {

    @Test
    public void getTest() {
        DoubleLinkedListDeque<String> lld1 = new DoubleLinkedListDeque<String>();
        lld1.addLast("a");
        lld1.addLast("b");
        lld1.addLast("c");
        lld1.addLast("d");
        assertEquals("a", lld1.get(0));
        assertEquals("b", lld1.get(1));
        assertEquals("c", lld1.get(2));
        assertEquals("d", lld1.get(3));

        assertEquals("a", lld1.getRecursive(0));
        assertEquals("b", lld1.getRecursive(1));
        assertEquals("c", lld1.getRecursive(2));
        assertEquals("d", lld1.getRecursive(3));
        for (String s : lld1) {
            System.out.println(s);
        }

    }


    @Test
    /** Adds a few things to the list, checking isEmpty() and size() are correct,
     * finally printing the results.
     *
     * && is the "and" operation. */
    public void addIsEmptySizeTest() {

        DoubleLinkedListDeque<String> lld1 = new DoubleLinkedListDeque<String>();

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

        DoubleLinkedListDeque<Integer> lld1 = new DoubleLinkedListDeque<Integer>();
        // should be empty
        assertTrue(lld1.isEmpty());

        lld1.addFirst(10);
        // should not be empty
        assertFalse(lld1.isEmpty());

        lld1.removeFirst();
        // should be empty
        assertTrue(lld1.isEmpty());

    }

    @Test
    /* Tests removing from an empty deque */
    public void removeEmptyTest() {
        DoubleLinkedListDeque<Integer> lld1 = new DoubleLinkedListDeque<>();
        lld1.addFirst(3);

        lld1.removeLast();
        lld1.removeFirst();
        lld1.removeLast();
        lld1.removeFirst();

        int size = lld1.size();
        assertEquals(0, size);

    }

    @Test
    /* Check if you can create LinkedListDeques with different parameterized types*/
    public void multipleParamTest() {


        DoubleLinkedListDeque<String> lld1 = new DoubleLinkedListDeque<String>();
        DoubleLinkedListDeque<Double> lld2 = new DoubleLinkedListDeque<Double>();
        DoubleLinkedListDeque<Boolean> lld3 = new DoubleLinkedListDeque<Boolean>();

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


        DoubleLinkedListDeque<Integer> lld1 = new DoubleLinkedListDeque<Integer>();

        boolean passed1 = false;
        boolean passed2 = false;
        assertNull(lld1.removeFirst());
        assertNull(lld1.removeLast());


    }

    @Test
    /* Add large number of elements to deque; check if order is correct. */
    public void bigLLDequeTest() {

        DoubleLinkedListDeque<Integer> lld1 = new DoubleLinkedListDeque<Integer>();
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
