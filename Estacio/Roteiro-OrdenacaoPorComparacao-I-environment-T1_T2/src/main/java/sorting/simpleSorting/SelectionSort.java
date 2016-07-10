package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

import java.util.Arrays;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	private final static int LOWER = -1;

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		for(int index = leftIndex; index <= rightIndex; index++){
			int smallestIndex = index;
			for(int analysisIndex = index + 1; analysisIndex <= rightIndex; analysisIndex++){
				if(array[analysisIndex].compareTo(array[smallestIndex]) == LOWER){
					smallestIndex = analysisIndex;
				}
			}
			Util.swap(array, index, smallestIndex);
		}
	}
}
