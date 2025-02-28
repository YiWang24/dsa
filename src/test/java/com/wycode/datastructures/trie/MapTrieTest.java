package com.wycode.datastructures.trie;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * @author WY
 * @version 1.0
 **/

public class MapTrieTest {
    private MapTrie trie;

    @BeforeEach
    void setUp() {
        trie = new MapTrie();
    }

    @Test
    void testInsertAndSearch() {
        trie.insert("apple");
        assertTrue(trie.search("apple"));
        assertFalse(trie.search("app"));
    }

    @Test
    void testStartsWith() {
        trie.insert("apple");
        assertTrue(trie.startsWith("app"));
        assertFalse(trie.startsWith("b"));
    }

    @Test
    void testInsertMultipleWords() {
        trie.insert("apple");
        trie.insert("app");
        assertTrue(trie.search("apple"));
        assertTrue(trie.search("app"));
    }

    @Test
    void testNonExistingWord() {
        trie.insert("hello");
        assertFalse(trie.search("hell"));
        assertTrue(trie.startsWith("hell"));
        assertFalse(trie.search("world"));
    }

    @Test
    void testDeleteExistingWord() {
        trie.insert("apple");
        assertTrue(trie.search("apple"));

        trie.delete("apple");
        assertFalse(trie.search("apple"));
        assertFalse(trie.startsWith("app"));
    }

    @Test
    void testDeletePartialPrefix() {
        trie.insert("apple");
        trie.insert("app");

        trie.delete("apple");
        assertFalse(trie.search("apple"));
        assertTrue(trie.search("app"));
    }

    @Test
    void testDeleteNonExistingWord() {
        trie.insert("hello");
        assertFalse(trie.delete("world"));
        assertTrue(trie.search("hello"));
    }

    @Test
    void testDeleteWordWithSharedPrefix() {
        trie.insert("car");
        trie.insert("cart");
        trie.insert("cat");

        trie.delete("cart");
        assertFalse(trie.search("cart"));
        assertTrue(trie.search("car"));
        trie.delete("cat");
        assertFalse(trie.search("cat"));
    }
    @Test
    void testDeleteCarButKeepCart() {
        trie.insert("car");
        trie.insert("cart");

        trie.delete("car");

        assertFalse(trie.search("car"));
        assertTrue(trie.search("cart"));
    }
}

