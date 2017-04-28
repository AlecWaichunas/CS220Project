package edu.siu.sortingalgorithms;

/**
 * Created by Alec on 4/27/2017.
 */
public class InserstionSort {

    /**
     * Goes through each element and sees if the element next to it is
     * smaller or larger
     *
     * @param myObjects a list of comparable objects
     * @param first the starting element to sort in the partition
     * @param last the ending element to sort in the partition
     * @param <T> extends comparable so it can be sorted
     */
    public static <T extends Comparable<? super T>> void sort(T[] myObjects, int first, int last){

        //loops through 2nd element to the last
        for(int i = first + 1; i < last; i++){
            //stores the smallest element and the index
            T min = myObjects[i];
            int pos = i;

            //moves this element through the sorted part and inserts it
            //in the middle
            while(pos > first && min.compareTo(myObjects[pos - 1]) < 0){
                myObjects[pos] = myObjects[pos - 1];
                pos--;
            }
            //inserts object where sorted
            myObjects[pos] = min;
        }

    }

}
