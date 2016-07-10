package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import sorting.Util;

import java.util.Arrays;

/**
 * This selection sort variation has two internal iterations. In the first, it takes the
 * smallest elements from the array, and puts it in the first position. In the second,
 * the iteration is done backwards, that is, from right to left, and this time the biggest
 * element is selected and stored in the last position. Then it repeats the process,
 * excluding the positions already filled in, until the whole array is ordered.
 */
public class BidirectionalSelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	private static final int LOWER = -1;
	private static final int BIGGER = 1;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int done = 0; // Represents the number of elements correctly placed at the end of the array

		/*
		 * We only need to go until (rightIndex - done), because
		 * the algorithm correctly places (done) elements at the end
		 * of the array. So, we don't need to check the entire array.
		 */
		for (int index = leftIndex; index <= rightIndex - done; index++) {
			int smallestIndex = index;
			for (int analysisIndex = index + 1; analysisIndex <= rightIndex - done; analysisIndex++) {
				if (array[analysisIndex].compareTo(array[smallestIndex]) == LOWER) {
					smallestIndex = analysisIndex;
				}
			}
			Util.swap(array, index, smallestIndex);

			int biggestIndex = index;
			for (int analysisIndex = index + 1; analysisIndex <= rightIndex - done; analysisIndex++) {
				if (array[analysisIndex].compareTo(array[biggestIndex]) == BIGGER) {
					biggestIndex = analysisIndex;
				}
			}
			Util.swap(array, rightIndex - done, biggestIndex);
			done++;
		}
	}
}
