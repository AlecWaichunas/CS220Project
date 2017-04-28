package edu.siu.datastructures;

import java.lang.reflect.Array;
import java.util.Vector;

public class PriorityQueue<E extends Comparable<? super E>> implements PriorityQueueInterface<E> {
	
	private int size;
	//private int priorityIndex;
	private Vector<E> queue;
	private boolean added = false;
	
	public PriorityQueue(){
		queue = new Vector<E>();
	}
	
	public PriorityQueue(int initialCapacity){
		queue = new Vector<E>(initialCapacity);
		size = initialCapacity;
	}
	
	public void add(E newEntry) {
		if(this.getSize() == 0){
			queue.add(newEntry);
		} else {
			for(int i = 0; i < queue.size(); i++){
				if(newEntry.compareTo(queue.get(i)) > 0){
					queue.add(i, newEntry);
					added = true;
					break;
				}
			}
			if(!added){
				queue.add(newEntry);
			}
		}
		size++;
		
	}
	
	public E remove() {
		E result = null;
		if(!isEmpty()){
			result = queue.remove(0);
			size--;
		}
		return result;
	}
	
	public E peek() {
		return queue.get(0);
	}
	
	public boolean isEmpty() {
		if(size <= 0){
			return true;
		}
		return false;
	}
	
	public int getSize() {
		return size;
	}
	
	public void clear() {
		queue.clear();
	}
	
	public int compare(E entry1, E entry2){
		return entry1.compareTo(entry2);
	}
	
	@SuppressWarnings("unchecked")
	public E[] toArray(Class c){
		E[] newArray = (E[]) Array.newInstance(c, queue.size());
		for(int i = 0; i < queue.size(); i++){
			newArray[i] = queue.remove(0);
		}
		return newArray;
	}

}
