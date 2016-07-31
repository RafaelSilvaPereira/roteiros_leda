package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array
 * a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {

        int biggestElement = getBiggestElement(array, leftIndex, rightIndex);

        Integer[] countingVector = constructIntegerArray(biggestElement + 1);
        for (int index = leftIndex; index <= rightIndex; index++) {
            countingVector[array[index]]++;
        }

        unstableSort(array, leftIndex, countingVector);
        //stableSort(array, leftIndex, rightIndex, countingVector);
    }

    private void stableSort(Integer[] array, int leftIndex, int rightIndex, Integer[] countingVector) {
        Integer[] cumulative = new Integer[countingVector.length];

        cumulative[0] = countingVector[0] - 1;
        for (int index = 1; index < cumulative.length; index++) {
            cumulative[index] = countingVector[index] + cumulative[index - 1];
        }

        Integer[] sortedArray = new Integer[array.length];
        for (int index = rightIndex; index >= leftIndex; index--) {
            sortedArray[cumulative[array[index]]] = array[index];
            cumulative[array[index]]--;
        }

        copyArray(sortedArray, 0, array, leftIndex, rightIndex);
    }

    private void copyArray(Integer[] sortedArray, int index, Integer[] array, int leftIndex, int rightIndex) {
        while (index < sortedArray.length && leftIndex <= rightIndex) {
            array[leftIndex++] = sortedArray[index++];
        }
    }

    private Integer[] constructIntegerArray(int size) {
        Integer[] array = new Integer[size];

        for (int index = 0; index < size; index++) {
            array[index] = 0;
        }

        return array;
    }

    private void unstableSort(Integer[] array, int leftIndex, Integer[] countingVector) {

        int arrayIndex = leftIndex;
        for (int index = 0; index < countingVector.length; index++) {
            while (countingVector[index] > 0) {
                array[arrayIndex++] = index;
                countingVector[index]--;
            }
        }
    }

    private int getBiggestElement(Integer[] array, int leftIndex, int rightIndex) {
        int biggest = 0;
        for (int index = leftIndex; index <= rightIndex; index++) {
            if (array[index] > biggest) {
                biggest = array[index];
            }
        }
        return biggest;
    }

}
