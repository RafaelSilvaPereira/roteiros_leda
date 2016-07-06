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

	private static final int LOWER = -1;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int index = leftIndex; index <= rightIndex; index++){
			int elementIndex = index;
			for(int analysisIndex = elementIndex - 1; analysisIndex >= leftIndex; analysisIndex--){
				if(array[elementIndex].compareTo(array[analysisIndex]) == LOWER){
					Util.swap(array, elementIndex--, analysisIndex);
				}
			}
		}
	}
}
