package com.wycode.datastructures.map;

import com.wycode.datastructures.Map.BinarySearchTreeMap;
import com.wycode.datastructures.Map.HashMap;
import com.wycode.datastructures.Map.UnorderedLinkedListMap;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Objects;
import java.util.Random;

/**
 * @author WY
 * @version 1.0
 **/

public class RandomMapTest {
    @Test
    public void RandomMapTest() {

        HashMap<Integer, String> myMap = new HashMap<>();

        Map<Integer, String> javaMap = new java.util.HashMap<>();

        BinarySearchTreeMap<Integer, String> bstMap = new BinarySearchTreeMap<>();

        UnorderedLinkedListMap<Integer, String> ullMap = new UnorderedLinkedListMap<>();

        Random random = new Random();
        int testSize = 1000;

        for (int i = 0; i < testSize; i++) {
            int key = random.nextInt(500);
            String value = "Value" + random.nextInt(1000);


            int operation = random.nextInt(3);
            switch (operation) {
                case 0:
                    myMap.put(key, value);
                    javaMap.put(key, value);
                    bstMap.put(key, value);
                    ullMap.put(key, value);
                    break;
                case 1:
                    if (!Objects.equals(myMap.get(key), javaMap.get(key)) ||
                            !Objects.equals(myMap.get(key), bstMap.get(key))) {
                        System.out.println("Mismatch in get operation for key " + key);
                    }
                    break;
                case 2:
                    if (!Objects.equals(myMap.remove(key), javaMap.remove(key)) ||
                            !Objects.equals(myMap.remove(key), bstMap.remove(key))) {
                        System.out.println("Mismatch in remove operation for key " + key);
                    }
                    break;
            }
        }


        if (!myMap.keySet().equals(javaMap.keySet()) || !myMap.keySet().equals(bstMap.keySet())) {
            System.out.println("Mismatch in keySet after operations");
        }

        System.out.println("Random test completed");
    }
}

