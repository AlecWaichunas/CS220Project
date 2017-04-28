package edu.siu.sortingalgorithms;

import edu.siu.google.query.DomainDetails;

import java.util.Comparator;

/**
 * Created by Alec on 4/27/2017.
 */
public class QuickSort {

    //MIN_SIZE: insertion sort takes over
    // as quick sort is useless below this number
    private final static int MIN_SIZE = 5;

    private static Comparator<?> Comparator;

    /**
     *
     * Partitions the array and puts smaller elements in the left half
     * and bigger elements to the right relative to the pivot
     *
     * @param myObjects a list of comparable objects
     * @param first the starting element to sort in the partition
     * @param last the ending element to sort in the partition
     * @param <T> extends comparable so it can be sorted
     */

    public static <T extends Comparable<? super T>> void Sort(Comparator comparator, T[] myObjects, int first, int last){
        QuickSort.Comparator = comparator;
        //4 or less items else quick sort it
        if(last - first + 1 < MIN_SIZE) {
            //uses insertion sort
            InserstionSort.sort(comparator, myObjects, first, last);
        }else{
            int pivot = Partition(comparator, myObjects, first, last);

            Sort(comparator, myObjects, first, pivot - 1);
            Sort(comparator, myObjects, pivot + 1, last);
        }

    }

    /**
     *
     * @param myObjects a list of comparable objects
     * @param first the starting element to sort in the partition
     * @param last the ending element to sort in the partition
     * @param <T> extends comparable so it can be sorted
     * @return the pivot index in the partition
     */
    private static <T extends Comparable<? super T>> int Partition(Comparator comparator, T[] myObjects, int first, int last){
        int mid = (first + last)/2;
        //order first, middle and last
        Order(comparator, myObjects, first, mid);
        Order(comparator, myObjects, mid, last);
        Order(comparator, myObjects, first, mid);

        Swap(myObjects, mid, last - 1); //why below
        int pivotIndex = last - 1; // store the index of the original mid point
        T pivot = myObjects[pivotIndex]; //store the original mid point;

        int indexFromLeft = first + 1;
        int indexFromRight = pivotIndex - 1;

        //anything less than the pivot it puts to the left, otherwise it goes to the right
        boolean done = false;
        while(!done){
            //checks if everything on the left is less than the pivot;
            //if not then stop
            while(comparator.compare(myObjects[indexFromLeft], pivot) < 0)
                indexFromLeft++;
            //checks if everything on the right is less than the pivot;
            //if not then stop
            while(comparator.compare(myObjects[indexFromRight], pivot) > 0)
                indexFromRight--;
            //makes sure that the indexes doesn't surpass boundaries
            assert comparator.compare(myObjects[indexFromLeft], pivot) >= 0 &&
                    comparator.compare(myObjects[indexFromRight], pivot) <= 0;

            //if the while loop did stop then switch the elements
            //otherwise end loop
            if(indexFromLeft < indexFromRight){
                Swap(myObjects, indexFromLeft, indexFromRight);
                indexFromLeft++;
                indexFromRight--;
            }else
                done = true;
        }

        //swap the pivot and the left index;
        //left will be bigger than right now, ending condition for the if condition @line 62
        Swap(myObjects, pivotIndex, indexFromLeft);
        pivotIndex = indexFromLeft;

        return pivotIndex;
    }

    /**
     *
     * @param myObjects a list of comparable objects
     * @param i an element index in question of order
     * @param j an element index in question of order
     * @param <T> extends Comparable so it can be sorted
     */

    private static <T extends Comparable<? super T>> void Order(Comparator comparator, T[] myObjects, int i, int j){
        if(comparator.compare(myObjects[i], myObjects[j]) > 0)
            Swap(myObjects, i, j);

    }

    /**
     *
     * @param myObjects a list of comparable objects
     * @param i an element index in question of order
     * @param j an element index in question of order
     * @param <T> extends Comparable so it can be sorted
     */

    private static <T extends Comparable<? super T>> void Swap(T[] myObjects, int i, int j){
        T temp = myObjects[i];
        myObjects[i] = myObjects[j];
        myObjects[j] = temp;
    }

}
