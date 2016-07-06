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

	private static final int MENOR = -1;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int index = leftIndex; index <= rightIndex; index++){
			int i = index;
			for(int j = i - 1; j >= leftIndex; j--){
				if(array[i].compareTo(array[j]) == MENOR){
					Util.swap(array, i--, j);
				}
			}
		}
	}
}
