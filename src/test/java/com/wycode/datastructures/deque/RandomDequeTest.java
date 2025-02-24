package com.wycode.datastructures.deque;

import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author WY
 * @version 1.0
 **/

public class RandomDequeTest {
    @Test
    public void randomTest() {
        Random random = new Random();
        ArrayDeque<Integer> arrayDeque = new ArrayDeque<>();
        Deque<Integer> linkedListDeque = new DoubleLinkedListDeque<>();
        int N = 10000;
        for (int i = 0; i < N; i++) {
            int operationNumber = random.nextInt(4);
            if (operationNumber == 0) {
                int randVal = random.nextInt(N);
                arrayDeque.addFirst(randVal);
                linkedListDeque.addFirst(randVal);
            } else if (operationNumber == 1) {
                int randVal = random.nextInt(N);
                arrayDeque.addLast(randVal);
                linkedListDeque.addLast(randVal);
            } else if (operationNumber == 2) {
                if (arrayDeque.isEmpty()) {
                    continue;
                }
                int remove = arrayDeque.removeFirst();
                int remove2 = linkedListDeque.removeFirst();
                assertEquals(remove, remove2);
            } else if (operationNumber == 3) {
                if (arrayDeque.isEmpty()) {
                    continue;
                }
                int remove = arrayDeque.removeLast();
                int remove2 = linkedListDeque.removeLast();
                assertEquals(remove, remove2);
            }
        }

        for (int i = 0; i < arrayDeque.size(); i++) {
            assertEquals(arrayDeque.get(i), linkedListDeque.get(i));
        }
    }

}
