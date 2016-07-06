package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * The bubble sort algorithm iterates over the array multiple times, pushing big
 * elements to the right by swapping adjacent elements, until the array is sorted.
 */
public class BubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private final static int MAIOR = 1;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean sorted;
		do {
			sorted = true;

			for (int i = leftIndex; i < rightIndex ; i++) {
				if (array[i].compareTo(array[i+1]) == MAIOR) {
					Util.swap(array, i, i+1);
					sorted = false;
				}
			}
		} while (!sorted);
	}
}
