package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private static final int INITIAL_INDEX = 0;
	private static final int SAME_COMPARE = INITIAL_INDEX;

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if(leftIndex >= rightIndex){
			return;
		}

		int middle = (leftIndex + rightIndex) / 2;
		sort(array, leftIndex, middle);
		sort(array, middle + 1, rightIndex);
		merge(array, leftIndex, middle, rightIndex);
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		T[] temporaryArray = (T[]) new Comparable[array.length];

		copyArray(array, leftIndex, rightIndex, temporaryArray, leftIndex);

		int firstIndex = leftIndex, secondIndex = middle + 1, index = leftIndex;
		while(firstIndex <= middle && secondIndex <= rightIndex){
			if(temporaryArray[firstIndex].compareTo(temporaryArray[secondIndex]) <= SAME_COMPARE){
				array[index++] = temporaryArray[firstIndex++];
			}else{
				array[index++] = temporaryArray[secondIndex++];
			}
		}

		if(firstIndex <= middle){
			copyArray(temporaryArray, firstIndex, middle, array, index);
		}else if(secondIndex <= rightIndex){
			copyArray(temporaryArray, secondIndex, rightIndex, array, index);
		}
	}

	private void copyArray(T[] modelArray, int leftIndex, int rightIndex, T[] copy, int initialIndex) {
		for(int index = leftIndex; index <= rightIndex; index++, initialIndex++){
			copy[initialIndex] = modelArray[index];
		}
	}
}
