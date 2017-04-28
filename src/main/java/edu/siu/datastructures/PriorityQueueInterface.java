package edu.siu.datastructures;

public interface PriorityQueueInterface<T extends Comparable<? super T>>{
	
	public void add(T newEntry);
	public T remove();
	public T peek();
	public boolean isEmpty();
	public int getSize();
	public void clear();
	public T[] toArray();
	
}
