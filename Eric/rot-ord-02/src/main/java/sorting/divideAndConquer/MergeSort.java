package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			int middle = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}
	}
	
	private void merge(T[] array, int start, int middle, int end) {
		int firstPart = start,
			secPart = middle + 1;
		T[] auxArray = (T[])new Comparable[end - start + 1];
		
		int indexAuxArr = 0;
		while (firstPart <= middle && secPart <= end) {
			if (array[firstPart].compareTo(array[secPart]) > 0) {
				auxArray[indexAuxArr] = array[secPart];
				secPart++;
			} else {
				auxArray[indexAuxArr] = array[firstPart];
				firstPart++;
			}
			indexAuxArr++;
		}
		
		while (firstPart <= middle) {
			auxArray[indexAuxArr] = array[firstPart];
			indexAuxArr++;
			firstPart++;
		}
		
		while (secPart <= end) {
			auxArray[indexAuxArr] = array[secPart];
			indexAuxArr++;
			secPart++;
		}
		
		for (int auxPointer = 0; auxPointer < auxArray.length; auxPointer++) {
			array[auxPointer + start] = auxArray[auxPointer];
		}
	}
}
