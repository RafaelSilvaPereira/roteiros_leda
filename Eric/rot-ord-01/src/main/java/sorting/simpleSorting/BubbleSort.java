package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < 0) 
			leftIndex = 0;
		if (rightIndex >= array.length)
			rightIndex = array.length;
		
		for (int fPointer = leftIndex; fPointer <= rightIndex; fPointer++) {
			for (int sPointer = leftIndex; sPointer <= rightIndex; sPointer++) {
				if (array[fPointer].compareTo(array[sPointer]) < 0) {
					Util.swap(array, fPointer, sPointer);
				}
			}
		}
	}
}
