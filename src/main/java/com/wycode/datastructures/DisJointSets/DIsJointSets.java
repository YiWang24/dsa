package com.wycode.datastructures.DisJointSets;

public interface DIsJointSets<T> {

    void connect(T p, T q);
    boolean isConnected(T p, T q);
}
