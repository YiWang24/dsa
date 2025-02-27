package com.wycode.datastructures.queue;

public interface PriorityQueue<T> {
     void insert(T x,double priority);
     int size();
     T peek();
     T remove();
     void changePriority(T x,double priority);

}
