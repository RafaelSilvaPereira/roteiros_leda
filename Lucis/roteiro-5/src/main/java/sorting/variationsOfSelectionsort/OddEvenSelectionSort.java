package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import util.Util;

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

	private static final int TWO = 2;

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex){
		if (isInvalid(array, leftIndex, rightIndex)) return;
		pacedSelectionSort(array, leftIndex, rightIndex, TWO);
		pacedSelectionSort(array, leftIndex+1, rightIndex, TWO);
		pacedMerge(array, leftIndex, rightIndex, TWO);
	}
	
	private void pacedMerge(T[] array, int leftIndex, int rightIndex, int pace) {
		int firstPointer = leftIndex;
		int secondPointer = leftIndex+1;
		T[] auxArray = (T[]) new Comparable[rightIndex-leftIndex+1];
		int finalPointer = 0;
		
		while (firstPointer <= rightIndex && secondPointer <= rightIndex){
			if (array[firstPointer].compareTo(array[secondPointer]) < 0){
				auxArray[finalPointer++] = array[firstPointer];
				firstPointer+= pace;
			}else {
				auxArray[finalPointer++] = array[secondPointer];
				secondPointer += pace;
			}
		}
		
		while (firstPointer <= rightIndex){
			auxArray[finalPointer++] = array[firstPointer];
			firstPointer += pace;
		}
		
		while (secondPointer <= rightIndex){
			auxArray[finalPointer++] = array[secondPointer];
			secondPointer += pace;
		}
		cloneArrays(array, auxArray, leftIndex, rightIndex);
	}
	
	private void pacedSelectionSort(T[] array, int leftIndex, int rightIndex, int pace){
		int firstIndex, secondIndex;
		for (firstIndex = leftIndex; firstIndex <= rightIndex - 2; firstIndex += pace){
			int minIndex = firstIndex;
			for (secondIndex = firstIndex + pace; secondIndex <= rightIndex; secondIndex += pace) {
				if (array[secondIndex].compareTo(array[minIndex]) < 0){
					minIndex = secondIndex;
				}
			}
			Util.swap(array, minIndex, firstIndex);
		}
	}
	
	private void cloneArrays(T[] a, T[] b, int from, int to ){
		int cloneIndex;
		for (cloneIndex = 0; cloneIndex <= to-from; cloneIndex++) {
			a[cloneIndex+from] = b[cloneIndex];
		}
	}
	
	private boolean isInvalid(T[] array, int leftIndex, int rightIndex) {
		return (array == null || array.length < 2 || leftIndex < 0 || rightIndex >= array.length || leftIndex == rightIndex);
	}
}
