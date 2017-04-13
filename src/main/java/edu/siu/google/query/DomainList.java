package edu.siu.google.query;

import edu.siu.datastructures.LinkedListIntereface;

/**
 * Created by Alec on 4/11/2017.
 */
public class DomainList implements LinkedListIntereface<DomainDetails> {

    public int size;
    public int maxSize;
    public Node firstNode;

    public DomainList(){
        this(10);
    }

    public DomainList(int maxSize){
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

    public boolean add(DomainDetails newEntry) {
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

    public DomainDetails remove() {
        DomainDetails removedNodeData = null;
        if(size > 0) {
            removedNodeData = firstNode.getData();
            firstNode = firstNode.next();
            size--;
        }
        return removedNodeData;
    }

    public boolean remove(DomainDetails anEntry) {
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

    public int getFrequencyOf(DomainDetails anEntry) {
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

    public boolean contains(DomainDetails anEntry) {
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

    public DomainDetails[] toArray() {
        DomainDetails[] detailsArray = new DomainDetails[size];
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

        private DomainDetails data;
        private Node nextNode;

        public Node(DomainDetails data, Node nextNode){
            this.data = data;
            this.nextNode = nextNode;
        }

        public void setData(DomainDetails data){
            this.data = data;
        }

        public DomainDetails getData(){
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
