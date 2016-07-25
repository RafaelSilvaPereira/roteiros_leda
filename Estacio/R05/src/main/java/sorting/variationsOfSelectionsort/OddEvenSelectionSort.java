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

    private static final int SAME = 0;
    private static final int JUMP = 2;

    @Override
    public void sort(T[] array, int leftIndex, int rightIndex) {
        if (!isValid(array, leftIndex, rightIndex)) {
            return;
        }

        selectionSort(array, leftIndex, rightIndex, JUMP);
        selectionSort(array, leftIndex + 1, rightIndex, JUMP);
        merge(array, leftIndex, rightIndex);
    }

    private void selectionSort(T[] array, int leftIndex, int rightIndex, int jump){
        for(int index = leftIndex; index <= rightIndex; index += jump){
            int smallestIndex = index;
            for(int analysisIndex = index + jump; analysisIndex <= rightIndex; analysisIndex += jump){
                if(array[analysisIndex].compareTo(array[smallestIndex]) < SAME){
                    smallestIndex = analysisIndex;
                }
            }
            Util.swap(array, index, smallestIndex);
        }
    }


    private void merge(T[] array, int leftIndex, int rightIndex) {
        T[] aux = (T[]) new Comparable[rightIndex - leftIndex + 1];
        int firstIndex = leftIndex, secondIndex = leftIndex + 1, index = 0;

        while (firstIndex <= rightIndex && secondIndex <= rightIndex) {
            if (array[firstIndex].compareTo(array[secondIndex]) > SAME) {
                aux[index++] = array[secondIndex];
                secondIndex += 2;
            } else {
                aux[index++] = array[firstIndex];
                firstIndex += 2;
            }
        }

        while (firstIndex <= rightIndex) {
            aux[index++] = array[firstIndex];
            firstIndex += 2;
        }
        while (secondIndex <= rightIndex) {
            aux[index++] = array[secondIndex];
            secondIndex += 2;
        }

        copyArray(aux, 0, aux.length, array, leftIndex, rightIndex);
    }

    private void copyArray(T[] aux, int index, int length, T[] array, int leftIndex, int rightIndex) {
        while (index < length && leftIndex <= rightIndex) {
            array[leftIndex++] = aux[index++];
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
