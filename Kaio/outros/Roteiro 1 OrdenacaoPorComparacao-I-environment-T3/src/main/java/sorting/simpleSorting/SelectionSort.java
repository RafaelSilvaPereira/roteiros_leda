package sorting.simpleSorting;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * The selection sort algorithm chooses the smallest element from the array and
 * puts it in the first position. Then chooses the second smallest element and
 * stores it in the second position, and so on until the array is sorted.
 */
public class SelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	// selection
	@Override
	    public void sort(T[] array, int leftIndex, int rightIndex) {
	        int index;
	        for (int i = leftIndex; i <= rightIndex - 1; i++) {
	            index = i;
	            for (int j = i + 1; j <= rightIndex; j++)
	                if (array[index].compareTo(array[j]) > 0)
	                    index = j;
	            Util.swap(array, i, index);
	        }
	    }
}
