package com.wycode.datastructures.trie;

import java.util.HashMap;
import java.util.Map;

/**
 * @author WY
 * @version 1.0
 **/

public class MapTrie {
    private Node root;

    private class Node {
        private boolean isWord;
        private Map<Character, Node> children;

        public Node() {
            this.isWord = false;
            this.children = new HashMap<>();
        }
    }

    public MapTrie() {
        root = new Node();
    }

    public void insert(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            node.children.putIfAbsent(c, new Node());
            node = node.children.get(c);
        }
        node.isWord = true;
    }

    public boolean search(String word) {
        Node node = find(word);
        return node != null && node.isWord;
    }

    public boolean startsWith(String prefix) {
        return find(prefix) != null;
    }

    private Node find(String word) {
        Node node = root;
        for (char c : word.toCharArray()) {
            if (!node.children.containsKey(c)) {
                return null;
            }
            node = node.children.get(c);
        }
        return node;
    }

    public boolean delete(String word) {
        return delete(root, word, 0);
    }

    private boolean delete(Node node, String word, int index) {
        if (index == word.length()) {
            if (!node.isWord) {
                return false;
            }
            node.isWord = false;
            return node.children.isEmpty();
        }
        char c = word.charAt(index);
        Node child = node.children.get(c);
        if (child == null) {
            return false;
        }
        boolean shouldDelete = delete(child, word, index + 1);
        if (shouldDelete) {
            node.children.remove(c);
            return node.children.isEmpty() && !node.isWord;
        }
        return false;
    }

}

