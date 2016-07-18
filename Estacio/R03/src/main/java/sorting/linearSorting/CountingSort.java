package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array
 * a ser ordenado.
 */
public class CountingSort extends AbstractSorting<Integer> {

    @Override
    public void sort(Integer[] array, int leftIndex, int rightIndex) {
        if (array == null || array.length == 0
                || leftIndex < 0 || rightIndex >= array.length
                || leftIndex > rightIndex) {
            return;
        }

        int biggestElement = getBiggestElement(array, leftIndex, rightIndex);

        Integer[] countingVector = constructIntegerArray(biggestElement + 1);
        for (int index = leftIndex; index <= rightIndex; index++) {
            countingVector[array[index]]++;
        }

        unstableSort(array, leftIndex, countingVector);
        //stableSort(array, leftIndex, rightIndex, countingVector);
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

    private void stableSort(Integer[] array, int leftIndex, int rightIndex, Integer[] countingVector) {
        countingVector[0]--;
        for (int index = 1; index < countingVector.length; index++) {
            countingVector[index] = countingVector[index] + countingVector[index - 1];
        }

        Integer[] sortedArray = new Integer[array.length];
        for (int index = rightIndex; index >= leftIndex; index--) {
            sortedArray[countingVector[array[index]]] = array[index];
            countingVector[array[index]]--;
        }

        copyArray(sortedArray, 0, array, leftIndex, rightIndex);
    }

    private void copyArray(Integer[] sortedArray, int index, Integer[] array, int leftIndex, int rightIndex) {
        while (index < sortedArray.length && leftIndex <= rightIndex) {
            if (sortedArray[index] != null) {
                array[leftIndex++] = sortedArray[index];
            }
            index++;
        }
    }

    private int getBiggestElement(Integer[] array, int leftIndex, int rightIndex) {
        int biggest = Integer.MIN_VALUE;
        for (int index = leftIndex; index <= rightIndex; index++) {
            if (array[index] > biggest) {
                biggest = array[index];
            }
        }
        return biggest;
    }

    private Integer[] constructIntegerArray(int size) {
        Integer[] array = new Integer[size];

        for (int index = 0; index < size; index++) {
            array[index] = 0;
        }

        return array;
    }

}
