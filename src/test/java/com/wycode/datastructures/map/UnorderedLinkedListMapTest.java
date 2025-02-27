package com.wycode.datastructures.map;

import com.wycode.datastructures.Map.UnorderedLinkedListMap;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Iterator;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @author WY
 * @version 1.0
 **/

public class UnorderedLinkedListMapTest {
    private UnorderedLinkedListMap<String, Integer> map;

    @BeforeEach
    public void setUp() {
        map = new UnorderedLinkedListMap<>();
    }

    @Test
    public void testPutAndGet() {
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);

        assertEquals(10, (int) map.get("apple"));
        assertEquals(20, (int) map.get("banana"));
        assertEquals(30, (int) map.get("cherry"));
        assertNull(map.get("grape")); // key 不存在，应返回 null
    }


    @Test
    public void testPutOverwrite() {
        map.put("apple", 10);
        map.put("apple", 50); // 覆盖原值 10

        assertEquals(50, (int) map.get("apple"));
    }


    @Test
    public void testSize() {
        assertEquals(0, map.size()); // 初始大小应为 0

        map.put("apple", 10);
        assertEquals(1, map.size());

        map.put("banana", 20);
        assertEquals(2, map.size());

        map.put("apple", 50); // 重新插入相同 key，不应改变大小
        assertEquals(2, map.size());
    }


    @Test
    public void testContainsKey() {
        assertFalse(map.containsKey("apple"));

        map.put("apple", 10);
        assertTrue(map.containsKey("apple"));
        assertFalse(map.containsKey("banana"));
    }


    @Test
    public void testRemoveKey() {
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);

        assertEquals(10, (int) map.remove("apple")); // 删除 apple
        assertNull(map.get("apple")); // 确保 apple 被删除
        assertEquals(2, map.size());

        assertNull(map.remove("grape")); // 删除不存在的 key，应返回 null
    }

    @Test
    public void testRemoveKeyValue() {
        map.put("apple", 10);
        map.put("banana", 20);

        assertNull(map.remove("banana", 50)); // 值不匹配，不应删除
        assertEquals(20, (int) map.get("banana"));

        assertEquals(20, (int) map.remove("banana", 20)); // 正确匹配，删除
        assertNull(map.get("banana"));
    }


    @Test
    public void testClear() {
        map.put("apple", 10);
        map.put("banana", 20);
        assertEquals(2, map.size());

        map.clear();
        assertEquals(0, map.size());
        assertNull(map.get("apple"));
    }


    @Test
    public void testKeySet() {
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);

        Set<String> keys = map.keySet();
        assertTrue(keys.contains("apple"));
        assertTrue(keys.contains("banana"));
        assertTrue(keys.contains("cherry"));
        assertEquals(3, keys.size());
    }

    @Test
    public void testIterator() {
        map.put("apple", 10);
        map.put("banana", 20);
        map.put("cherry", 30);

        Iterator<String> iterator = map.iterator();
        assertTrue(iterator.hasNext());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertNotNull(iterator.next());
        assertFalse(iterator.hasNext());
    }


}
