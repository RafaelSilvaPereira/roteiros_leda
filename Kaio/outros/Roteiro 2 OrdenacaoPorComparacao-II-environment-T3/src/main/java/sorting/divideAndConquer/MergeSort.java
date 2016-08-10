package sorting.divideAndConquer;

import sorting.AbstractSorting;

/**
 * Merge sort is based on the divide-and-conquer paradigm.  
 * The algorithm consists of recursively dividing the unsorted list in the middle,
 * sorting each sublist, and then merging them into one single sorted list.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@SuppressWarnings("unchecked")
	private T[] helper = (T[]) new Comparable[6000000];
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int middle;
		if (leftIndex < rightIndex) {
			middle = (leftIndex + rightIndex) / 2;
			sort(array, leftIndex, middle);
			sort(array, middle + 1, rightIndex);
			merge_two_sorted_list(array, leftIndex, middle, rightIndex);
		}
	}

	private void merge_two_sorted_list(
			T[] array, int leftIndex, int middle, int rightIndex) {
		
		System.arraycopy(array, leftIndex, helper, leftIndex, (rightIndex - leftIndex + 1));
		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;
		
		while ((i <= middle) && (j <= rightIndex)) {
			if (helper[i].compareTo(helper[j]) <= 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while (i <= middle) {
			array[k] = helper[i];
			i++; k++;
		}
		
		while (j <= rightIndex) {
			array[k] = helper[j];
			j++; k++;
		}
		
	}
}
