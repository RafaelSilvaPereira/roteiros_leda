package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The combsort algoritm.
 */
public class CombSort<T extends Comparable<T>> extends AbstractSorting<T> {
    private static final int IGUAL = 0;
    private static final float FACTOR = 1.25f;
    private static final int MINIMUM_GAP = 1;

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (!isValid(array, leftIndex, rightIndex)) {
            return;
        }

        int gap = rightIndex + MINIMUM_GAP;
        boolean swapped = false;

        while (gap > MINIMUM_GAP || swapped) {
            if (gap > MINIMUM_GAP) {
                gap /= FACTOR;
            }
            swapped = false;

            for (int index = leftIndex; index + gap <= rightIndex; index++) {
                if (array[index].compareTo(array[index + gap]) > IGUAL) {
                    Util.swap(array, index, index + gap);
                    swapped = true;
                }
            }
        }
    }

    private boolean isValid(T[] array, int leftIndex, int rightIndex) {
        if (array == null || leftIndex < 0 || rightIndex >= array.length
                || leftIndex >= rightIndex || array.length <= 1) {
            return false;
        }
        return true;
    }
}
