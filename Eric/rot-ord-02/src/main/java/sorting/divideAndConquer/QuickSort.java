package sorting.divideAndConquer;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm.
 * The algorithm chooses a pivot element and rearranges the elements of the
 * interval in such a way that all elements lesser than the pivot go to the
 * left part of the array and all elements greater than the pivot, go to the
 * right part of the array. Then it recursively sorts the left and the right parts.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {
	
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			int posPivot = moveElems(array, leftIndex, rightIndex);
			sort(array, posPivot + 1, rightIndex);
			sort(array, leftIndex, posPivot - 1);
		}
	}

	private int moveElems(T[] array, int start, int end) {
		int backPointer = start -1;
		int frontPointer = start;
		T pivot = array[end];

		while (frontPointer < end && backPointer < end) {
			if (array[frontPointer].compareTo(pivot) < 0 && frontPointer > backPointer) {
				backPointer++;
				Util.swap(array, backPointer, frontPointer);
			} else {
				frontPointer++;
			}
		}
		backPointer++;
		while (end > backPointer) {
			Util.swap(array, end, end - 1);
			end--;
		}
//		Util.swap(array, backPointer, end);
		return backPointer;
	}
}
