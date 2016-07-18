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
		if (leftIndex < rightIndex) {
            int middle = leftIndex + (rightIndex - leftIndex) / 2;
            sort(array, leftIndex, middle);
            sort(array, middle + 1, rightIndex);
            merge(array, leftIndex, middle, rightIndex);
        }
	}

	private void merge(T[] array, int from, int middle, int to) {
		T[] auxiliarArray = (T[]) new Comparable[array.length];
		int populateIndex;
        for (populateIndex = from; populateIndex <= to; populateIndex++) {
            auxiliarArray[populateIndex] = array[populateIndex];
        }
        int firstIndex = from;
        int secondIndex = middle + 1;
        int finalIndex = from;
        while (firstIndex <= middle && secondIndex <= to) {
            if (auxiliarArray[firstIndex].compareTo(auxiliarArray[secondIndex]) < 0) {
                array[finalIndex] = auxiliarArray[firstIndex];
                firstIndex++;
            } else {
                array[finalIndex] = auxiliarArray[secondIndex];
                secondIndex++;
            }
            finalIndex++;
        }
        while (firstIndex <= middle) {
            array[finalIndex++] = auxiliarArray[firstIndex++];

        }
        while (secondIndex <= to){
        	array[finalIndex++] = auxiliarArray[secondIndex++];
        }
 
    }
}
