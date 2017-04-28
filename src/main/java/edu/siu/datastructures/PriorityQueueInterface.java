package edu.siu.datastructures;

/**
 * Created by Ryan
 */

public interface PriorityQueueInterface<T extends Comparable<? super T>>{

	//methods used in priorityqueue

	public void add(T newEntry);
	public T remove();
	public T peek();
	public boolean isEmpty();
	public int getSize();
	public void clear();
	public T[] toArray(Class c);
	
}
