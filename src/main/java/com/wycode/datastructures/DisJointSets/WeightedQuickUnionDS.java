package com.wycode.datastructures.DisJointSets;

/**
 * Weighted Quick Union with path compression implementation of Disjoint Sets.
 *
 * This data structure supports efficient union and find operation while
 * maintaining a nearly constant time complexity using path compression and union by size.
 *
 * @author WY
 * @version 1.0
 *
 * ### Complexity Summary:
 * - `find(v1)`: **O(α(N))** (inverse Ackermann function, nearly constant time)
 * - `connect(p, q)`: **O(α(N))**
 * - `isConnected(p, q)`: **O(α(N))**
 * - `sizeOf(v1)`: **O(1)**
 * - `parent(v1)`: **O(1)**
 * - `validate(v1)`: **O(1)**
 *
 * In practical applications, **O(α(N))** is nearly O(1) for all realistic inputs.
 **/


public class WeightedQuickUnionDS implements DIsJointSets {
    private int[] parent;

    /**
     * Initializes a disjoint set with `size` elements, each as a separate set.
     * Initially, all elements are their own parent with a size of -1.
     *
     * @param size The number of elements in the disjoint set.
     * @throws IllegalArgumentException If `size` is non-positive.
     *
     * Time Complexity: **O(N)**
     */
    public WeightedQuickUnionDS(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException();
        }
        parent = new int[size];
        for (int i = 0; i < size; i++) {
            parent[i] = -1;
        }
    }

    public void validate(int v1) {
        if (v1 < 0 || v1 >= parent.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
    }

    /**
     * Returns the size of the set containing `v1`.
     *
     * @param v1 An element in the disjoint set.
     * @return The size of the set containing `v1`.
     *
     * Time Complexity: **O(1)** (Direct lookup from the root node)
     */
    public int sizeOf(int v1) {
        validate(v1);
        return -parent[find(v1)];
    }

    public int parent(int v1) {
        validate(v1);
        return parent[v1];
    }


    /**
     * Finds the root of the set containing `v1` with path compression.
     * Path compression flattens the structure, making future lookups faster.
     *
     * @param v1 The element to find.
     * @return The root of the set containing `v1`.
     *
     * Time Complexity: **O(α(N))**, nearly **O(1)**
     */
    public int find(int v1) {
        validate(v1);
        if (parent[v1] < 0) {
            return v1;
        }
        parent[v1] = find(parent[v1]);
        return parent[v1];


    }

    /**
     * Connects two elements `p` and `q` using union-by-size.
     * The smaller set is merged into the larger set to keep tree height small.
     *
     * @param p First element.
     * @param q Second element.
     *
     * Time Complexity:  **O(log N)**
     */
    @Override
    public void connect(Object p, Object q) {
        validate((int) p);
        validate((int) q);
        int root1 = find((int) p);
        int root2 = find((int) q);
        if (root1 == root2) {
            return;
        }
        if (sizeOf(root1) <= sizeOf(root2) && (parent((int) p) != -1 && parent((int) q) != -1)) {
            parent[root2] += parent[root1];
            parent[root1] = root2;
        } else {
            parent[root1] += parent[root2];
            parent[root2] = root1;
        }

    }

    /**
     * Checks if two elements `p` and `q` are in the same set.
     *
     * @param p First element.
     * @param q Second element.
     * @return `true` if `p` and `q` are connected, `false` otherwise.
     *
     * Time Complexity: **O(α(N))**, nearly **O(1)**
     */
    @Override
    public boolean isConnected(Object p, Object q) {
        validate((int) p);
        validate((int) q);
        return find((int) p) == find((int) q);
    }


}
