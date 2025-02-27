package com.wycode.datastructures.queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

/**
 * @author WY
 * @version 1.0
 **/

public class ArrayMinHeapPQTest {
    private ArrayMinHeapPQ<String> pq;

    @BeforeEach
    void setUp() {
        pq = new ArrayMinHeapPQ<>();
    }


    @Test
    void testInsertAndSize() {
        assertEquals(0, pq.size());

        pq.insert("task1", 5.0);
        assertEquals(1, pq.size());

        pq.insert("task2", 3.0);
        pq.insert("task3", 7.0);
        assertEquals(3, pq.size());
    }


    @Test
    void testPeek() {
        pq.insert("A", 4.0);
        pq.insert("B", 2.0);
        pq.insert("C", 6.0);

        assertEquals("B", pq.peek());
        assertEquals(3, pq.size());
    }

    @Test
    void testRemove() {
        pq.insert("X", 10.0);
        pq.insert("Y", 1.0);
        pq.insert("Z", 5.0);

        assertEquals("Y", pq.remove());
        assertEquals(2, pq.size());

        assertEquals("Z", pq.remove());
        assertEquals(1, pq.size());

        assertEquals("X", pq.remove());
        assertEquals(0, pq.size());

        assertNull(pq.remove()); // 空队列
    }


    @Test
    void testChangePriority() {
        pq.insert("A", 5.0);
        pq.insert("B", 2.0);
        pq.insert("C", 8.0);

        assertEquals("B", pq.peek());

        pq.changePriority("C", 1.0);
        assertEquals("C", pq.peek());

        pq.changePriority("C", 10.0);
        assertEquals("B", pq.peek());
    }

    @Test
    void testEmptyQueue() {
        assertNull(pq.peek());
        assertNull(pq.remove());
    }

    @Test
    void testChangePriorityNonExistent() {
        pq.insert("Task1", 3.0);
        pq.changePriority("Task2", 1.0);
        assertEquals("Task1", pq.peek());
    }
}
