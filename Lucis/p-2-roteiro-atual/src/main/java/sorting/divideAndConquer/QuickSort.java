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
		if (leftIndex >= rightIndex) return;
		int pivotIndex;
		pivotIndex = partition(array, leftIndex, rightIndex);
		sort(array, leftIndex, pivotIndex-1);
		sort(array, pivotIndex+1, rightIndex); 
	}
	
	private int partition(T[] array, int from, int to){
		T pivot = array[from];
		int firstPointer = from + 1;
		int secondPointer = to;
		while (firstPointer <= secondPointer){
			if (array[firstPointer].compareTo(pivot) <= 0) firstPointer++;
			else if (array[secondPointer].compareTo(pivot) > 0) secondPointer--;
			else Util.swap(array, firstPointer, secondPointer);
		}
		Util.swap(array, from, secondPointer);
		return secondPointer;
	}
}
