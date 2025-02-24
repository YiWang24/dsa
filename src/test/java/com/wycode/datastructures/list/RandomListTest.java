package com.wycode.datastructures.list;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Randomized test to compare SingleLinkedList, DoubleLinkedList, and Java's ArrayList.
 * Ensures consistency across all implementations.
 *
 * @author WY
 * @version 1.0
 **/


public class RandomListTest {
    @Test
    public void testRandomOperations() {
        Random random = new Random();
        SingleLinkedList<Integer> singleList = new SingleLinkedList<>();
        DoubleLinkedList<Integer> doubleList = new DoubleLinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        int operations = 10000; // Number of operations to test
        for (int i = 0; i < operations; i++) {
            int op = random.nextInt(6); // Randomly select an operation
            int value = random.nextInt(1000); // Random value for testing

            switch (op) {
                case 0: // addFirst
                    singleList.addFirst(value);
                    doubleList.addFirst(value);
                    arrayList.insert(0, value);
                    break;
                case 1: // addLast
                    singleList.addLast(value);
                    doubleList.addLast(value);
                    arrayList.insert(arrayList.size(), value);
                    break;
                case 2: // removeFirst
                    if (!singleList.isEmpty() && !doubleList.isEmpty() && !arrayList.isEmpty()) {
                        int list1 = singleList.remove(0);
                        int list2 = doubleList.remove(0);
                        int list3 = arrayList.removeFirst();
                        assertEquals(list1, list2, "Mismatch in removeFirst (SingleLinkedList)");
                        assertEquals(list2, list3, "Mismatch in removeFirst (DoubleLinkedList)");
                    }
                    break;
                case 3: // removeLast
                    if (!singleList.isEmpty() && !doubleList.isEmpty() && !arrayList.isEmpty()) {
                        int list1 = singleList.remove(Math.max(0, arrayList.size() - 1));
                        int list2 = doubleList.remove(Math.max(0, arrayList.size() - 1));
                        int list3 = arrayList.removeLast();
                        assertEquals(list1, list2, "Mismatch in removeLast (SingleLinkedList)");
                        assertEquals(list2, list3, "Mismatch in removeLast (DoubleLinkedList)");
                    }
                    break;
                case 4: // get
                    if (!singleList.isEmpty() && !doubleList.isEmpty() && !arrayList.isEmpty()) {
                        int index = random.nextInt(singleList.size());
                        assertEquals(arrayList.get(index), singleList.get(index), "Mismatch in get(" + index + ") (SingleLinkedList)");
                        assertEquals(arrayList.get(index), doubleList.get(index), "Mismatch in get(" + index + ") (DoubleLinkedList)");
                    }
                    break;
                case 5: // remove at index
                    if (!singleList.isEmpty() && !doubleList.isEmpty() && !arrayList.isEmpty()) {
                        int index = random.nextInt(singleList.size());
                        int list1 = singleList.remove(index);
                        int list2 = doubleList.remove(index);
                        int list3 = arrayList.remove(index);
                        assertEquals(list1, list2, "Mismatch in remove(" + index + ") (SingleLinkedList)");
                        assertEquals(list2, list3, "Mismatch in remove(" + index + ") (DoubleLinkedList)");
                    }
                    break;
            }

            // Verify size consistency across all lists
            assertEquals(arrayList.size(), singleList.size(), "Size mismatch in SingleLinkedList");
            assertEquals(arrayList.size(), doubleList.size(), "Size mismatch in DoubleLinkedList");
        }
    }

    @Test
    public void testBatchOperations() {
        SingleLinkedList<Integer> singleList = new SingleLinkedList<>();
        DoubleLinkedList<Integer> doubleList = new DoubleLinkedList<>();
        List<Integer> arrayList = new ArrayList<>();

        // Batch insert 1000 elements
        for (int i = 0; i < 1000; i++) {
            singleList.addLast(i);
            doubleList.addLast(i);
            arrayList.insert(i, i);
        }

        assertEquals(arrayList.size(), singleList.size(), "Size mismatch after batch insert (SingleLinkedList)");
        assertEquals(arrayList.size(), doubleList.size(), "Size mismatch after batch insert (DoubleLinkedList)");

        // Batch remove first 500 elements
        for (int i = 0; i < 500; i++) {
            assertEquals(arrayList.remove(0), i, "Mismatch in batch removeFirst (SingleLinkedList)");
            assertEquals(doubleList.removeFirst(), i, "Mismatch in batch removeFirst (DoubleLinkedList)");
            assertEquals(singleList.removeFirst(), i, "Mismatch in batch removeFirst (DoubleLinkedList)");
        }

        assertEquals(arrayList.size(), singleList.size(), "Size mismatch after batch removeFirst (SingleLinkedList)");
        assertEquals(arrayList.size(), doubleList.size(), "Size mismatch after batch removeFirst (DoubleLinkedList)");

        // Batch remove last 500 elements
        while (!doubleList.isEmpty()) {
            assertEquals(doubleList.remove(doubleList.size() - 1), singleList.removeLast(), "Mismatch in batch removeLast (SingleLinkedList)");
        }

        assertTrue(singleList.isEmpty(), "SingleLinkedList should be empty after batch remove");
        assertTrue(doubleList.isEmpty(), "DoubleLinkedList should be empty after batch remove");
    }
}
