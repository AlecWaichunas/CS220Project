package edu.siu.datastructures;

/**
 * Created by Akec on 4/11/2017.
 */
public interface LinkedListIntereface<T> {

    int getCurrentSize();
    boolean isFull();
    boolean isEmpty();
    boolean add(T newEntry);
    T remove();
    boolean remove(T anEntry);
    void clear();
    int getFrequencyOf(T anEntry);
    boolean contains(T anEntry);
    Object[] toArray();

}
