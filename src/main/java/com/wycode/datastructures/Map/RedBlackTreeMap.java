package com.wycode.datastructures.Map;

import java.util.Iterator;
import java.util.Set;

/**
 * @author WY
 * @version 1.0
 **/

public class RedBlackTreeMap<K extends Comparable<K>, V> implements Map<K, V> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;

    private Node root;
    private int size;

    private class Node {
        private K key;
        private V value;
        private Node left, right;
        private boolean color;
        public Node(K k,V v,boolean color) {
            this.key = k;
            this.value = v;
            this.color = color;
        }
    }


    @Override
    public V get(K key){
        return get(root,key);
    }

    private V get(Node n,K key){
        while (n != null) {
            int cmp = key.compareTo(n.key);
            if (cmp < 0) {
                n = n.left;
            }else if (cmp > 0) {
                n = n.right;
            } else {
                return n.value;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    @Override
    public boolean containsKey(K key) {
        return get(key) != null;
    }


    @Override
    public int size() {
        return size;
    }

    private boolean isRed(Node n) {
        if(n == null) {return false;}
        return n.color == RED;
    }
    private Node rotateRight(Node h) {
        assert (h!=null) && isRed(h.left);
        Node x = h.left;
        h.left = x.right;
        x.left = h;
        x.color = h.color;
        h.color = RED;
        return x;
    }


    @Override
    public void put(K key, V value) {

    }

    @Override
    public Set<K> keySet() {
        return Set.of();
    }

    @Override
    public V remove(K key) {
        return null;
    }

    @Override
    public V remove(K key, V value) {
        return null;
    }

    @Override
    public Iterator<K> iterator() {
        return null;
    }
}
