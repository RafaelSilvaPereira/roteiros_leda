package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		boolean isValid = array != null && array.length > 1 && rightIndex < array.length;
		isValid &= leftIndex >= 0 && rightIndex > leftIndex;

		if (isValid) {
			combSort(array, leftIndex, rightIndex);
		}
	}

	private void combSort(T[] array, int leftIndex, int rightIndex) {
		int gap = rightIndex - leftIndex + 1;
		boolean swapped = true;

		while (gap > 1 || swapped) {
			if (gap > 1)
				gap = (int) (gap / 1.25);
			int aux = leftIndex;
			swapped = false;
			while (aux + gap <= rightIndex) {
				if (array[aux].compareTo(array[aux + gap]) > 0) {
					Util.swap(array, aux, aux + gap);
					swapped = true;
				}
				aux++;
			}
		}
	}
}
