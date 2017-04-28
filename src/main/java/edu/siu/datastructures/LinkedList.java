package edu.siu.datastructures;

/**
 * Created by Alec on 4/22/2017.
 */
public class LinkedList<E> implements LinkedListIntereface<E> {


    public int size;
    public int maxSize;
    public Node firstNode;

    public LinkedList(){
        this(10);
    }

    public LinkedList(int maxSize){
        this.maxSize = maxSize;
        this.size = 0;
    }

    public int getCurrentSize() {
        return size;
    }

    public boolean isFull() {
        return size >= maxSize;
    }

    public boolean isEmpty() {
        return firstNode == null;
    }

    public boolean add(E newEntry) {
        boolean successful = false;
        if(size < maxSize){
            if(firstNode == null) {
                firstNode = new Node(newEntry, null);
            }else{
                Node newNode = new Node(newEntry, firstNode.next());
                firstNode.setNextNode(newNode);
            }
            size++;
            successful = true;
        }
        return successful;
    }

    public E remove() {
        E removedNodeData = null;
        if(size > 0) {
            removedNodeData = firstNode.getData();
            firstNode = firstNode.next();
            size--;
        }
        return removedNodeData;
    }

    public boolean remove(E anEntry) {
        boolean success = false;

        Node currentNode = firstNode;
        while(currentNode != null){
            if(currentNode.getData().equals(anEntry)){
                currentNode.setData(firstNode.data);
                remove();
                success = true;
                break;
            }
            currentNode = currentNode.next();
        }

        return success;
    }

    public void clear() {
        firstNode = null;
    }

    public int getFrequencyOf(E anEntry) {
        int frquency = 0;

        Node currentNode = firstNode;
        while(currentNode != null){
            if(currentNode.getData().equals(anEntry)){
                frquency++;
            }
            currentNode = currentNode.next();
        }

        return frquency;
    }

    public boolean contains(E anEntry) {
        boolean found = false;

        Node currentNode = firstNode;
        while(currentNode != null){
            if(currentNode.getData().equals(anEntry)){
                found = true;
                break;
            }
            currentNode = currentNode.next();
        }

        return found;
    }

    @SuppressWarnings("unchecked")
	public E[] toArray() {
        E[] detailsArray =  (E[]) new Object[size];
        Node currentNode = firstNode;
        int index = 0;
        while(currentNode != null){
            detailsArray[index] = currentNode.getData();
            index++;
            currentNode = currentNode.next();
        }
        return detailsArray;
    }

    private class Node{

        private E data;
        private Node nextNode;

        public Node(E data, Node nextNode){
            this.data = data;
            this.nextNode = nextNode;
        }

        public void setData(E data){
            this.data = data;
        }

        public E getData(){
            return data;
        }

        public void setNextNode(Node node){
            this.nextNode = node;
        }

        public boolean hasNext() {
            return (nextNode != null);
        }

        public Node next() {
            return nextNode;
        }
    }
}
