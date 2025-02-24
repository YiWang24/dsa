package com.wycode.datastructures.list;

/**
 * List interface
 *
 * @param <T> the type of elements in the list
 */
public interface List<T> {

    /**
     * return the size of the list
     *
     * @return the number of elements in the list
     */
    int size();

    /**
     * checks if the list is empty
     *
     * @return ture if the list is empty, false otherwise
     */
    default boolean isEmpty(){
        return size() == 0;
    }

    /**
     * checks if the list contains a specific element
     *
     * @param element the element to check
     * @return true if the element is found, false otherwise
     */
    boolean contains(T element);

    /**
     * insert an element at a specific index
     *
     * @param index   the index at which to insert the element
     * @param element the element to insert
     */
    void insert(int index, T element);


    /**
     * Adds an element at the beginning of the list
     *
     * @param element the element to add
     */
    void addFirst(T element);

    /**
     * Adds an element at the end of the list
     *
     * @param element the element to add
     */
    void addLast(T element);

    /**
     * Gets the element at the specified index
     *
     * @param index the index of the element to retrieve
     * @return the element at the specified index
     */
    T get(int index);


    /**
     * Get and returns the first element of the list
     * @return the first element
     */
    T getFirst();

    /**
     * Get and returns the last element of the list
     * @return the last element
     */
    T getLast();


    /**
     * Remove an element at a specific index
     *
     * @param index the index of the element to remove
     * @return the removed element
     */
    T remove(int index);

    /**
     * remove and returns the first element of the list
     *
     * @return the removed element
     */
    T removeFirst();

    /**
     * remove and returns the last element of the list
     *
     * @return the removed element
     */
    T removeLast();




}
