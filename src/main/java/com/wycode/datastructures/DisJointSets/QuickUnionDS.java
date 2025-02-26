package com.wycode.datastructures.DisJointSets;

/**
 * @author WY
 * @version 1.0
 **/

public class QuickUnionDS implements DIsJointSets {
    private int[] parent;

    public QuickUnionDS(int num) {
        parent = new int[num];
        for (int i = 0; i < num; i++) {
            parent[i] = -1;
        }
    }

    private int find(int p) {
        while (parent[p] >= 0) {
            p = parent[p];
        }
        return p;
    }

    @Override
    public void connect(Object p, Object q) {
        int i = find((Integer) p);
        int j = find((Integer) q);
        parent[i] = j;
    }

    @Override
    public boolean isConnected(Object p, Object q) {
        return find((Integer) p) == find((Integer) q);
    }
}
