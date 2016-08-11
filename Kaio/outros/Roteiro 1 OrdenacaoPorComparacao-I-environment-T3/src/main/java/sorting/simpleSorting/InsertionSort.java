package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * As the insertion sort algorithm iterates over the array, it makes the
 * assumption that the visited positions are already sorted in ascending order,
 * which means it only needs to find the right position for the current element
 * and insert it there.
 */
public class InsertionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        int index;
        for (int i = leftIndex + 1; i <= rightIndex; i++) {
            index = i;
            while ((index > leftIndex) && (array[index-1].compareTo(array[index]) > 0)) {
                Util.swap(array, index-1, index);
                index--;
            }
        }
    }
}
