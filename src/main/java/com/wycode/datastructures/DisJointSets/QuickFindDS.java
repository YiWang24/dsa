package com.wycode.datastructures.DisJointSets;

/**
 * @author WY
 * @version 1.0
 **/

public class QuickFindDS implements DIsJointSets {
    private int[] id;

    public QuickFindDS(int size) {
        id = new int[size];
        for (int i = 0; i < size; i++) {
            id[i] = i;
        }
    }


    @Override
    public void connect(Object p, Object q) {
        int pid = id[(int) p];
        int qid = id[(int) q];
        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid) {
                id[i] = qid;
            }
        }
    }

    @Override
    public boolean isConnected(Object p, Object q) {
        return (id[(int) p] == id[(int) q]);
    }
}
