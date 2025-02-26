package com.wycode.datastructures.set;

import org.junit.jupiter.api.Test;

import java.util.Iterator;

/**
 * @author WY
 * @version 1.0
 **/

public class ArraySetTest {
    @Test
    public void testArraySet() {
        ArraySet<Integer> set = new ArraySet<>();
        set.add(1);
        set.add(2);
        set.add(3);
        set.add(3);

        Iterator<Integer> iterator = set.iterator();

        while(iterator.hasNext()) {
            System.out.println(iterator.next());
        }

        for(Integer i : set) {
            System.out.println(i);
        }
    }

}
