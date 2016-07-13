package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Quicksort is based on the divide-and-conquer paradigm.
 * The algorithm chooses a pivot element and rearranges the elements of the
 * interval in such a way that all elements lesser than the pivot go to the
 * left part of the array and all elements greater than the pivot, go to the
 * right part of the array. Then it recursively sorts the left and the right parts.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		int firstIndex = leftIndex;
        int secondIndex = rightIndex;
        T[] pivot = array[leftIndex+(rightIndex-leftIndex)/2];
        // Divide into two arrays
        while (firstIndex <= secondIndex) {
            /**
             * In each iteration, we will identify a number from left side which
             * is greater then the pivot value, and also we will identify a number
             * from right side which is less then the pivot value. Once the search
             * is done, then we exchange both numbers.
             */
            while (array[firstIndex] < pivot) {
                firstIndex++;
            }
            while (array[secondIndex] > pivot) {
                secondIndex--;
            }
            if (firstIndex <= secondIndex) {
                exchangeNumbers(firstIndex, secondIndex);
                //move index to next position on both sides
                firstIndex++;
                secondIndex--;
            }
        }
        // call quickSort() method recursively
        if (lowerIndex < secondIndex)
            quickSort(lowerIndex, secondIndex);
        if (firstIndex < higherIndex)
            quickSort(firstIndex, higherIndex);
- See more at: http://www.java2novice.com/java-sorting-algorithms/quick-sort/#sthash.AmKLABRL.dpuf
	}
}
