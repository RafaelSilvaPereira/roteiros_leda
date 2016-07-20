package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place! 
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T>{
	
	public void sort(T[] array,int leftIndex, int rightIndex){
		if (isInvalid(array, leftIndex, rightIndex)) return;
		int pivotIndex = leftIndex;
		while (pivotIndex < rightIndex){
			if (pivotIndex < leftIndex || array[pivotIndex].compareTo(array[pivotIndex+1]) <= 0){
				pivotIndex++;
			}else{
				Util.swap(array, pivotIndex, pivotIndex+1);
				pivotIndex--;
			}
		}
	}

	private boolean isInvalid(T[] array, int leftIndex, int rightIndex) {
		return (array == null || array.length < 2 || leftIndex < 0 || rightIndex >= array.length || leftIndex == rightIndex);
	}

	
}
