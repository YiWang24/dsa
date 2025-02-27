package com.wycode.datastructures.map;

import com.wycode.datastructures.Map.BinarySearchTreeMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/

public class BinarySearchTreeMapTest {
    private BinarySearchTreeMap<Integer, String> bst;

    @BeforeEach
    public void setUp() {
        bst = new BinarySearchTreeMap<>();
    }


    @Test
    public void testPutAndGet() {
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");
        bst.put(2, "D");
        bst.put(4, "E");

        assertEquals("A", bst.get(5));
        assertEquals("B", bst.get(3));
        assertEquals("C", bst.get(7));
        assertEquals("D", bst.get(2));
        assertEquals("E", bst.get(4));
        assertNull(bst.get(10));
    }

    @Test
    public void testContainsKey() {
        bst.put(5, "A");
        bst.put(3, "B");

        assertTrue(bst.containsKey(5));
        assertTrue(bst.containsKey(3));
        assertFalse(bst.containsKey(10)); // 不存在
    }


    @Test
    public void testSize() {
        assertEquals(0, bst.size());
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");

        assertEquals(3, bst.size());
        bst.put(3, "Z");
        assertEquals(3, bst.size());
    }


    @Test
    public void testRemove() {
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");
        bst.put(2, "D");
        bst.put(4, "E");

        assertEquals("B", bst.remove(3));
        assertNull(bst.get(3));
        assertEquals(4, bst.size());

        assertNull(bst.remove(10));
        assertEquals(4, bst.size());
    }


    @Test
    public void testRemoveWithValue() {
        bst.put(5, "A");
        bst.put(3, "B");

        assertNull(bst.remove(3, "X"));
        assertEquals(2, bst.size());

        assertEquals("B", bst.remove(3, "B"));
        assertNull(bst.get(3));
        assertEquals(1, bst.size());
    }


    @Test
    public void testKeySet() {
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");

        Set<Integer> keys = bst.keySet();
        assertTrue(keys.contains(5));
        assertTrue(keys.contains(3));
        assertTrue(keys.contains(7));
        assertEquals(3, keys.size());
    }


    @Test
    public void testIterator() {
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");
        bst.put(2, "D");
        bst.put(4, "E");

        Iterator<Integer> iterator = bst.iterator();
        assertTrue(iterator.hasNext());
        assertEquals((Integer) 2, iterator.next());
        assertEquals((Integer) 3, iterator.next());
        assertEquals((Integer) 4, iterator.next());
        assertEquals((Integer) 5, iterator.next());
        assertEquals((Integer) 7, iterator.next());
        assertFalse(iterator.hasNext());
    }


    @Test
    public void testClear() {
        bst.put(5, "A");
        bst.put(3, "B");
        assertEquals(2, bst.size());

        bst.clear();
        assertEquals(0, bst.size());
        assertNull(bst.get(5));
        assertFalse(bst.containsKey(3));
    }


    @Test
    public void testPrintInOrder() {
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");
        bst.put(2, "D");
        bst.put(4, "E");
        bst.printInOrder();


    }

    @Test
    public void sanityGenericsTest() {
        try {
            BinarySearchTreeMap<String, String> a = new BinarySearchTreeMap<String, String>();
            BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
            BinarySearchTreeMap<Integer, String> c = new BinarySearchTreeMap<Integer, String>();
            BinarySearchTreeMap<Boolean, Integer> e = new BinarySearchTreeMap<Boolean, Integer>();
        } catch (Exception e) {
            fail();
        }
    }

    //assumes put/size/containsKey/get work
    @Test
    public void sanityClearTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
        for (int i = 0; i < 455; i++) {
            b.put("hi" + i, 1 + i);
            //make sure put is working via containsKey and get
            assertTrue(null != b.get("hi" + i) && (b.get("hi" + i).equals(1 + i))
                    && b.containsKey("hi" + i));
        }
        assertEquals(455, b.size());
        b.clear();
        assertEquals(0, b.size());
        for (int i = 0; i < 455; i++) {
            assertTrue(null == b.get("hi" + i) && !b.containsKey("hi" + i));
        }
    }

    // assumes put works
    @Test
    public void sanityContainsKeyTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
        assertFalse(b.containsKey("waterYouDoingHere"));
        b.put("waterYouDoingHere", 0);
        assertTrue(b.containsKey("waterYouDoingHere"));
    }

    // assumes put works
    @Test
    public void sanityGetTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
        assertEquals(null, b.get("starChild"));
        assertEquals(0, b.size());
        b.put("starChild", 5);
        assertTrue(((Integer) b.get("starChild")).equals(5));
        b.put("KISS", 5);
        assertTrue(((Integer) b.get("KISS")).equals(5));
        assertNotEquals(null, b.get("starChild"));
        assertEquals(2, b.size());
    }

    // assumes put works
    @Test
    public void sanitySizeTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
        assertEquals(0, b.size());
        b.put("hi", 1);
        assertEquals(1, b.size());
        for (int i = 0; i < 455; i++)
            b.put("hi" + i, 1);
        assertEquals(456, b.size());
    }

    //assumes get/containskey work
    @Test
    public void sanityPutTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
        b.put("hi", 1);
        assertTrue(b.containsKey("hi") && b.get("hi") != null);
    }

    //assumes put works
    @Test
    public void containsKeyNullTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
        b.put("hi", null);
        assertTrue(b.containsKey("hi"));
    }

    @Test
    public void printInOrderTest() {
        BinarySearchTreeMap<Integer, String> bst = new BinarySearchTreeMap<>();
        bst.put(5, "A");
        bst.put(3, "B");
        bst.put(7, "C");
        bst.put(2, "D");
        bst.put(4, "E");
        bst.put(6, "F");
        bst.put(8, "G");
        bst.printInOrder();
        for (int i : bst) {
            System.out.println(i);
        }
    }

    /*
     * Sanity test for keySet, only here because it's optional
     */
    @Test
    public void sanityKeySetTest() {
        BinarySearchTreeMap<String, Integer> b = new BinarySearchTreeMap<String, Integer>();
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

    /* Remove Test
     *
     * Note for testRemoveRoot:
     *
     * Just checking that c is gone (perhaps incorrectly)
     * assumes that remove is BST-structure preserving.
     *
     * More exhaustive tests could be done to verify
     * implementation of remove, but that would require doing
     * things like checking for inorder vs. preorder swaps,
     * and is unnecessary in this simple BST implementation.
     */
    @Test
    public void testRemoveRoot() {
        BinarySearchTreeMap<String, String> q = new BinarySearchTreeMap<String, String>();
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

    /* Remove Test 2
     * test the 3 different cases of remove
     */
    @Test
    public void testRemoveThreeCases() {
        BinarySearchTreeMap<String, String> q = new BinarySearchTreeMap<String, String>();
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

    /* Remove Test 3
     *  Checks that remove works correctly on root nodes
     *  when the node has only 1 or 0 children on either side. */
    @Test
    public void testRemoveRootEdge() {
        BinarySearchTreeMap rightChild = new BinarySearchTreeMap();
        rightChild.put('A', 1);
        rightChild.put('B', 2);
        Integer result = (Integer) rightChild.remove('A');
        assertTrue(result.equals(new Integer(1)));
        for (int i = 0; i < 10; i++) {
            rightChild.put((char) ('C' + i), 3 + i);
        }
        rightChild.put('A', 100);
        assertTrue(((Integer) rightChild.remove('D')).equals(new Integer(4)));
        assertTrue(((Integer) rightChild.remove('G')).equals(new Integer(7)));
        assertTrue(((Integer) rightChild.remove('A')).equals(new Integer(100)));
        assertTrue(rightChild.size() == 9);

        BinarySearchTreeMap leftChild = new BinarySearchTreeMap();
        leftChild.put('B', 1);
        leftChild.put('A', 2);
        assertTrue(((Integer) leftChild.remove('B')).equals(1));
        assertEquals(1, leftChild.size());
        assertEquals(null, leftChild.get('B'));

        BinarySearchTreeMap noChild = new BinarySearchTreeMap();
        noChild.put('Z', 15);
        assertTrue(((Integer) noChild.remove('Z')).equals(15));
        assertEquals(0, noChild.size());
        assertEquals(null, noChild.get('Z'));
    }



}
