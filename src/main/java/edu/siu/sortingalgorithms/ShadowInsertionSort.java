package edu.siu.sortingalgorithms;

import java.util.Comparator;

/**
 * Created by alecwaichunas on 4/28/2017.
 */
public class ShadowInsertionSort {

    /**
     * Goes through each element and sees if the element next to it is
     * smaller or larger
     *
     * @param shadowArray the array that follows the compare objects
     * @param compareObjects a list of comparable objects
     * @param first the starting element to sort in the partition
     * @param last the ending element to sort in the partition
     * @param <T> extends comparable so it can be sorted
     * @param <E> declaration of the shadow array
     */
    public static <T extends Comparable<? super T>, E> void sort(Comparator comparator, E[] shadowArray,
                                                                 T[] compareObjects, int first, int last){

        //loops through 2nd element to the last
        for(int i = first + 1; i < last; i++){
            //stores the smallest element and the index
            T min = compareObjects[i];
            E shadMin = shadowArray[i];
            int pos = i;

            //moves this element through the sorted part and inserts it
            //in the middle
            while(pos > first && comparator.compare(min, compareObjects[pos - 1]) < 0){
                compareObjects[pos] = compareObjects[pos - 1];
                shadowArray[pos] = shadowArray[pos - 1];
                pos--;
            }
            //inserts object where sorted
            compareObjects[pos] = min;
            shadowArray[pos] = shadMin;
        }

    }

}
