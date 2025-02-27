package com.wycode.datastructures.queue;

/**
 * @author WY
 * @version 1.0
 **/

public class ArrayMinHeapPQ<T> implements PriorityQueue<T> {
    private Node[] items;
    private int size;

    private class Node {
        T item;
        double priority;

        Node(T item, double priority) {
            this.item = item;
            this.priority = priority;
        }
    }

    public ArrayMinHeapPQ() {
        items = new ArrayMinHeapPQ.Node[16];
        items[0] = null;
        size = 0;
    }


    @Override
    public void insert(T x, double priority) {
        if (size + 1 == items.length) {
            resize(items.length * 2);
        }

        size = size + 1;
        items[size] = new Node(x, priority);
        swim(size);
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public T peek() {
        if (size == 0) {
            return null;
        }
        return items[1].item;
    }

    @Override
    public T remove() {
        if (size == 0) {
            return null;
        }
        T remove = items[1].item;
        swap(1, size);
        items[size] = null;
        size = size - 1;
        if(size == 0){
            return remove;
        }
        sink(1);
        return remove;
    }

    @Override
    public void changePriority(T x, double priority) {
        if (size == 0) {
            return;
        }
        int i;
        for (i = 1; i <= size; i++) {
            if (items[i].item.equals(x)) {
                break;
            }
        }
        if (i > size) {
            return;
        }
        Node n = getNode(i);
        double oldPriority = n.priority;
        n.priority = priority;
        if (priority < oldPriority) {
            swim(i);
        } else {
            sink(i);
        }

    }

    private static int leftIndex(int i) {
        return 2 * i;
    }

    private static int rightIndex(int i) {
        return 2 * i + 1;
    }

    private static int parentIndex(int i) {
        return (int) i / 2;
    }

    private Node getNode(int index) {
        if (index < 1 || index > size) {
            return null;
        }
        return items[index];
    }

    private void swap(int i, int j) {
        Node temp = items[i];
        items[i] = items[j];
        items[j] = temp;
    }

    private void resize(int capacity) {
        Node[] temp = new ArrayMinHeapPQ.Node[capacity];
        System.arraycopy(items, 1, temp, 1, size);
        items = temp;
    }

    private void swim(int i) {
        validate(i);
        if (i == 1) {
            return;
        }
        Node current = getNode(i);
        int parentIndex = parentIndex(i);
        Node parent = items[parentIndex];
        if (current.priority < parent.priority) {
            swap(i, parentIndex);
        }
        swim(parentIndex);
    }

    private void sink(int i) {
        validate(i);
        Node current = getNode(i);
        int leftIndex = leftIndex(i);
        int rightIndex = rightIndex(i);
        int minIndex = min(leftIndex, rightIndex);
        Node n = getNode(minIndex);
        if (n == null) {
            return;
        }
        if (current.priority > n.priority) {
            swap(i, minIndex);
        }
        sink(minIndex);
    }


    private int min(int i, int j) {
        Node node1 = items[i];
        Node node2 = items[j];
        if (node1 == null) {
            return j;
        } else if (node2 == null) {
            return i;
        } else if (node1.priority < node2.priority) {
            return i;
        } else {
            return j;
        }
    }

    private void validate(int index) {
        if (index < 1 || index > size || items[index] == null) {
            throw new IllegalArgumentException("Index out of bounds");
        }
    }

}

