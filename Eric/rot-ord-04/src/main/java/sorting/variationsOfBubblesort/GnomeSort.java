package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean isValid = array != null && array.length > 1 && rightIndex < array.length;
		isValid &= leftIndex >= 0 && rightIndex > leftIndex;

		if (isValid) {
			gnomeSort(array, leftIndex, rightIndex);
		}
	}

	private void gnomeSort(T[] array, int leftIndex, int rightIndex) {
		int indexPtr = leftIndex;

		while (indexPtr < rightIndex) {
			int nextIndex = indexPtr + 1;
			if (indexPtr < leftIndex || array[indexPtr].compareTo(array[nextIndex]) <= 0) {
				indexPtr++;
			} else {
				Util.swap(array, indexPtr, nextIndex);
				indexPtr--;
			}
		}
	}
}
