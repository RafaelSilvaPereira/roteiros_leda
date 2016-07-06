package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private final static int BIGGER = 1;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean sorted;
		do {
			sorted = true;

			for (int elementIndex = leftIndex; elementIndex < rightIndex ; elementIndex++) {
				if (array[elementIndex].compareTo(array[elementIndex+1]) == BIGGER) {
					Util.swap(array, elementIndex, elementIndex+1);
					sorted = false;
				}
			}
		} while (!sorted);
	}
}
