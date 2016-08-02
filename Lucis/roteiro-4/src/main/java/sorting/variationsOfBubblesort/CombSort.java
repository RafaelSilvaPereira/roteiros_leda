package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm. 
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
	private static final double FACTOR = 1.247330950103979;

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		if (isInvalid(array, leftIndex, rightIndex)) return;
		int size = rightIndex - leftIndex + 1;
		int gap = size;
	    boolean swapped = false;

	    do{
	        gap = (gap > 1) ? (int) (gap / FACTOR) : gap;
	        int firstIndex = leftIndex;
	        swapped = false;
	        while (firstIndex + gap < size) {	        	
	            if (array[firstIndex].compareTo(array[firstIndex + gap]) > 0) {
	                Util.swap(array, firstIndex, firstIndex + gap);
	                swapped = true;
	            }
	            firstIndex++;
	        }
	    } while (gap > 1 || swapped);
	    
	}
	
	private boolean isInvalid(T[] array, int leftIndex, int rightIndex) {
		return (array == null || array.length < 2 || leftIndex < 0 || rightIndex >= array.length || leftIndex == rightIndex);
	}
}
