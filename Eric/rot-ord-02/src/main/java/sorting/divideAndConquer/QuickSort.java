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
			int posPivot = (leftIndex + rightIndex) / 2;
			posPivot = moveElems(array, posPivot, leftIndex, rightIndex);
			sort(array, leftIndex, posPivot);
			sort(array, posPivot + 1, rightIndex);
		}
	}
	
	private int moveElems(T[] array, int posPivot, int start, int end) {
		T pivot = array[posPivot];
		Util.swap(array, posPivot, end);
		posPivot = end;
		
		for (int i = end - 1; i >= start; i--) {
			if (array[i].compareTo(pivot) >= 0) {
				T elem = array[i];
				int aux = i;
				while(aux < end) {
					array[aux] = array[aux + 1];
					aux++;
				} 
				array[end] = elem;
				posPivot--;
			}
		}
		return posPivot;
	}
}
