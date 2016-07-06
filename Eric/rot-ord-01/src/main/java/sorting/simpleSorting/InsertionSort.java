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
		for (int fPointer = leftIndex; fPointer <= rightIndex; fPointer++) {
			for (int sPointer = fPointer; sPointer > leftIndex; sPointer--) {
				if (array[sPointer - 1].compareTo(array[sPointer]) > 0) {
					Util.swap(array, sPointer, sPointer - 1);
				}
			}
		}
	}
}
