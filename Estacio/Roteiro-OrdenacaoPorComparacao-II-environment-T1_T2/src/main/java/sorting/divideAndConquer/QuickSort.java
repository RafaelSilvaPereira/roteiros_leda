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

	private static final int MENOR = -1;

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex < rightIndex){
			int pivotIndex = partition(array, leftIndex, rightIndex);
			sort(array, leftIndex, pivotIndex - 1);
			sort(array, pivotIndex + 1, rightIndex);
		}
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int pivotIndex = leftIndex;

		int analysisIndex = pivotIndex;
		for(int index = pivotIndex + 1; index <= rightIndex; index++){
			if(array[index].compareTo(array[pivotIndex]) == MENOR){
				Util.swap(array, ++analysisIndex, index);
			}
		}
		Util.swap(array, pivotIndex, analysisIndex);
		return analysisIndex;
	}
}
