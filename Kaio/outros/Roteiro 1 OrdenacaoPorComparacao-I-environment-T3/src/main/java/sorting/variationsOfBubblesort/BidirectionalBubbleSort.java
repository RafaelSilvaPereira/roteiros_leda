package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * This bubble sort variation has two internal iterations. In the first, it pushes big
 * elements to the right, like the normal bubble sort does. Then in the second, iterates the
 * array backwards, that is, from right to left, while pushing small elements to the left.
 * This process is repeated until the array is sorted.
 */
public class BidirectionalBubbleSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        boolean swapped;
        do {
            swapped = false;
            for (int i = leftIndex + 1; i <= rightIndex; i++) {
                if (array[i-1].compareTo(array[i]) > 0) {
                    Util.swap(array, i-1, i);
                    swapped = true;
                }
            }
            for (int i = rightIndex; i > leftIndex; i--) {
                if (array[i].compareTo(array[i-1]) < 0) {
                    Util.swap(array, i-1, i);
                    swapped = true;
                }
            }
        } while(swapped);
    }
}
