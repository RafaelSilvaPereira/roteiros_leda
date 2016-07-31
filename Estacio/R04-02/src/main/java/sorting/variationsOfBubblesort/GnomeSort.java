package sorting.variationsOfBubblesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * The implementation of the algorithm must be in-place!
 */
public class GnomeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    private static final int IGUAL = 0;

    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (!isValid(array, leftIndex, rightIndex)) {
            return;
        }

        int index = leftIndex;
        while (index < rightIndex) {
            if (array[index].compareTo(array[index + 1]) > IGUAL) {
                Util.swap(array, index, index + 1);
                if (index > leftIndex) {
                    index--;
                }
            } else {
                index++;
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
