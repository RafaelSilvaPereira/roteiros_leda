package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * This bubble sort variation has two internal iterations. In the first, it pushes big
 * elements to the right, like the normal bubble sort does. Then in the second, iterates the
 * array backwards, that is, from right to left, while pushing small elements to the left.
 * This process is repeated until the array is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int j;
		int n = rightIndex + 1;
		int aux = -1;
		while (aux < n) {
			aux++;
			n--;
			for (j = aux; j < n; j++) {
				if (array[j].compareTo(array[j + 1]) == 1) {
					Util.swap(array, j, j + 1);
				}
			}
			for (j = n; --j >= aux;) {
				if (array[j].compareTo(array[j + 1]) == 1) {
					Util.swap(array, j, j + 1);
				}
			}
		}
	}
}
