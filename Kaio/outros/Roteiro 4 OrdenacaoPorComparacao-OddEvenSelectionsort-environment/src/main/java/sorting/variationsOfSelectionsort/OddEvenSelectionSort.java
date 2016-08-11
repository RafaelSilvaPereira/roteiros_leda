package sorting.variationsOfSelectionsort;


import java.util.Arrays;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * This algorithm simulates a logical partitioning of the input array by considering 
 * different indexing, that is, the first sub-array is indexed by even elements and
 * the second sub-array is indexed by odd elements. Then, it applies a complete selectionsort
 * in the first sub-array considering neighbours (even). After that, 
 * it applies a complete selectionsort in the second sub-array considering
 * neighbours (odd).  After that, the algorithm performs a merge between elements indexed
 * by even and odd numbers.
 */
public class OddEvenSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		selection(array, inicioPar(leftIndex), rightIndex);
		selection(array, inicioImpar(leftIndex), rightIndex);
		mergeTwoSortedList(array, leftIndex, rightIndex);
	}

	private void selection(T[] array, int leftIndex, int rightIndex) {
		int aux;
		for (int i = leftIndex; i <= rightIndex; i += 2) {
			aux = i;
			for (int j = i + 2; j <= rightIndex; j += 2)
				if (array[aux].compareTo(array[j]) > 0)
					aux = j;
			Util.swap(array, aux, i);
		}
	}
	
	private int inicioPar(int leftIndex) {
		return (leftIndex % 2 == 0) ? leftIndex : leftIndex + 1;
	}
	
	private int inicioImpar(int leftIndex) {
		return (leftIndex % 2 != 0) ? leftIndex : leftIndex + 1;
	}
	
	private void mergeTwoSortedList(T[] array, int leftIndex, int rightIndex) {
		T[] helper = Arrays.copyOf(array, array.length);
		int i = inicioPar(leftIndex);
		int j = inicioImpar(leftIndex);
		int k = leftIndex;
		
		while (i <= rightIndex && j <= rightIndex) {
			if (helper[i].compareTo(helper[j]) <= 0) {
				array[k] = helper[i];
				i += 2;
			} else {
				array[k] = helper[j];
				j += 2;
			}
			k++;
		}
		
		while (i <= rightIndex) {
			array[k] = helper[i];
			i += 2;
			k++;
		}
		
		while (j <= rightIndex) {
			array[k] = helper[j];
			j += 2;
			k++;
		}
		
	}

}
