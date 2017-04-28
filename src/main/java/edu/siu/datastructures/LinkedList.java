package edu.siu.datastructures;


/**
 * Data Structure that links each node to each other
 * Node 1 stores Node 2 stores Node 3 stor...
 *
 *
 * Created by Alec on 4/22/2017.
 */
public class LinkedList<E> implements LinkedListIntereface<E> {

    public static <T> LinkedList<T> ArrayToLinkedList(T[] a){
        LinkedList<T> linkedList = new LinkedList<T>(a.length);

        for(int i = 0; i < a.length; i++)
            linkedList.add(a[i]);

        return linkedList;
    }

    //properties of the linked list
    public int size;
    public int maxSize;
    public Node firstNode;

    /**
     * Specifies max size to a default
     */

    public LinkedList(){
        this(10);
    }

    /**
     *
     * @param maxSize the maximum nodes the linkedlist will hold
     */

    public LinkedList(int maxSize){
        this.maxSize = maxSize;
        this.size = 0;
    }

    /**
     *
     * @return the current size of the linkedlist
     */
    public int getCurrentSize() {
        return size;
    }

    /**
     *
     * @return if the linked list is full or not
     */

    public boolean isFull() {
        return size >= maxSize;
    }

    /**
     *
     * @return if the linked list is empty or not
     */
    public boolean isEmpty() {
        return firstNode == null;
    }

    /**
     *
     * @param newEntry the new data adding to the link list
     * @return if it is successful or not (i.e., linked list is full)
     */

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

    /**
     *
     * @return the removed Data
     */
    public E remove() {
        E removedNodeData = null;
        if(size > 0) {
            removedNodeData = firstNode.getData();
            firstNode = firstNode.next();
            size--;
        }
        return removedNodeData;
    }

    /**
     *
     * @param anEntry the data needed to be removed
     * @return if the data was removed or not found
     */
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

    public Object[] toArray() {
        Object[] detailsArray = new Object[size];
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
