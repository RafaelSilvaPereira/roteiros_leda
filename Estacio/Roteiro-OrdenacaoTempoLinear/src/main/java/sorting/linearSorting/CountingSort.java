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

        int biggestElement = getBiggestElement(array, leftIndex, rightIndex);

        Integer[] countingVector = constructIntegerArray(biggestElement + 1);
        for (int index = leftIndex; index <= rightIndex; index++) {
            countingVector[array[index]]++;
        }

        sortArray(array, leftIndex, countingVector);
    }

    private Integer[] constructIntegerArray(int size) {
        Integer[] array = new Integer[size];

        for(int index = 0; index < size; index++){
            array[index] = 0;
        }

        return array;
    }

    private void sortArray(Integer[] array, int leftIndex, Integer[] countingVector) {

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
        for (int index = leftIndex + 1; index <= rightIndex; index++) {
            if (array[index] > biggest) {
                biggest = array[index];
            }
        }
        return biggest;
    }

}
