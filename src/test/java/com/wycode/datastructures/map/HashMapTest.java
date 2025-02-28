package com.wycode.datastructures.map;

import com.wycode.datastructures.Map.HashMap;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/

public class HashMapTest {
    @Test
    public void testRemove() {
        HashMap<String, String> q = new HashMap<>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a"); // a b c d e
        assertTrue(null != q.remove("c"));
        assertFalse(q.containsKey("c"));
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        assertTrue(q.containsKey("e"));
    }

    /**
     * Remove Test 2
     * Test the 3 different cases of remove
     */
    @Test
    public void testRemoveThreeCases() {
        HashMap<String, String> q = new HashMap<>();
        q.put("c", "a");
        q.put("b", "a");
        q.put("a", "a");
        q.put("d", "a");
        q.put("e", "a");                         // a b c d e
        assertTrue(null != q.remove("e"));      // a b c d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("c"));
        assertTrue(q.containsKey("d"));
        assertTrue(null != q.remove("c"));      // a b d
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("d"));
        q.put("f", "a");                         // a b d f
        assertTrue(null != q.remove("d"));      // a b f
        assertTrue(q.containsKey("a"));
        assertTrue(q.containsKey("b"));
        assertTrue(q.containsKey("f"));
    }
    @Test
    public void sanityGenericsTest() {
        HashMap<String, String> a = new HashMap<>();
        HashMap<String, Integer> b = new HashMap<>();
        HashMap<Integer, String> c = new HashMap<>();
        HashMap<Boolean, Integer> d = new HashMap<>();
    }

    @Test
    public void forEachTest(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("Alice", 10);
        map.put("Bob", 20);
        map.put("Charlie", 30);

        // 🟢 Create a set of expected keys
        Set<String> expectedKeys = map.keySet();
        Set<String> actualKeys = new HashSet<>();

        // 🟢 Iterate using for-each loop
        for (String key : map) {
            System.out.println(key);
            actualKeys.add(key);
        }

        // ✅ Check if actual keys match expected keys
        assertEquals(expectedKeys, actualKeys);
    }


    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        sanityClearTest(new HashMap<>());
    }

    public static void sanityClearTest(HashMap<String, Integer> b) {
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i)
                    && b.containsKey("hi" + i));
        }
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        sanityContainsKeyTest(new HashMap<>());
    }

    public static void sanityContainsKeyTest(HashMap<String, Integer> b) {
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        sanityGetTest(new HashMap<>());
    }

    public static void sanityGetTest(HashMap<String, Integer> b) {
        assertEquals(null, b.get("starChild"));
        b.put("starChild", 5);
        assertNotEquals(null, b.get("starChild"));
        b.put("KISS", 5);
        assertNotEquals(null, b.get("KISS"));
        assertNotEquals(null, b.get("starChild"));
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        sanitySizeTest(new HashMap<>());
    }

    public static void sanitySizeTest(HashMap<String, Integer> b) {
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
        }
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        sanityPutTest(new HashMap<>());
    }

    public static void sanityPutTest(HashMap<String, Integer> b) {
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    @Test
    public void sanityKeySetTest() {
        sanityKeySetTest(new HashMap<>());
    }

    public static void sanityKeySetTest(HashMap<String, Integer> b) {
        HashSet<String> values = new HashSet<String>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1);
            values.add("hi" + i);
        }
        assertEquals(455, b.size()); //keys are there
        Set<String> keySet = b.keySet();
        assertTrue(values.containsAll(keySet));
        assertTrue(keySet.containsAll(values));
    }

    // Test for general functionality and that the properties of Maps hold.
    @Test
    public void functionalityTest() {
        functionalityTest(new HashMap<>(), new HashMap<>());
    }

    public static void functionalityTest(HashMap<String, String> dictionary,
                                         HashMap<String, Integer> studentIDs) {
        assertEquals(0, dictionary.size());

        // can put objects in dictionary and get them
        dictionary.put("hello", "world");
        assertTrue(dictionary.containsKey("hello"));
        assertEquals("world", dictionary.get("hello"));
        assertEquals(1, dictionary.size());

        // putting with existing key updates the value
        dictionary.put("hello", "kevin");
        assertEquals(1, dictionary.size());
        assertEquals("kevin", dictionary.get("hello"));

        // putting key in multiple times does not affect behavior
        studentIDs.put("sarah", 12345);
        assertEquals(1, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("alan", 345);
        assertEquals(2, studentIDs.size());
        assertEquals(12345, studentIDs.get("sarah").intValue());
        assertEquals(345, studentIDs.get("alan").intValue());
        assertTrue(studentIDs.containsKey("sarah"));
        assertTrue(studentIDs.containsKey("alan"));

        // handle values being the same
        assertEquals(345, studentIDs.get("alan").intValue());
        studentIDs.put("evil alan", 345);
        assertEquals(345, studentIDs.get("evil alan").intValue());
        assertEquals(studentIDs.get("evil alan"), studentIDs.get("alan"));
    }
}
