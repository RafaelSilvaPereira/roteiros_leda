package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa do Counting Sort vista em sala. 
 * Desta vez este algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array == null || array.length == 0
				|| leftIndex < 0 || rightIndex >= array.length
				|| leftIndex > rightIndex) {
			return;
		}

		int biggestElement = getBiggestElement(array, leftIndex, rightIndex);
		int smallestElement = getSmallestElement(array, leftIndex, rightIndex);

		/* Esta modificação permite que ocorra a ordenação de números
		 * negativos e também a otimização do espaço extra utilizado.
		 *
		 * Nos dois casos, o array contador fica da distância do menor
		 * elemento para o maior.
		 */
		Integer[] countingVector = constructIntegerArray(biggestElement - smallestElement + 1);
		for (int index = leftIndex; index <= rightIndex; index++) {
			countingVector[array[index] - smallestElement]++; // Necessário para a indexação correta.
		}

		unstableSort(array, leftIndex, countingVector, smallestElement);
		//stableSort(array, leftIndex, rightIndex, countingVector, smallestElement);
	}

	private void unstableSort(Integer[] array, int leftIndex, Integer[] countingVector, int smallestElement) {

		int arrayIndex = leftIndex;
		for (int index = 0; index < countingVector.length; index++) {
			while (countingVector[index] > 0) {
				array[arrayIndex++] = index + smallestElement; // Necesário corrigir a indexação.
				countingVector[index]--;
			}
		}
	}

	private void stableSort(Integer[] array, int leftIndex, int rightIndex, Integer[] countingVector, int smallestElement) {
		countingVector[0]--;
		for (int index = 1; index < countingVector.length; index++) {
			countingVector[index] = countingVector[index] + countingVector[index - 1];
		}

		Integer[] sortedArray = new Integer[array.length];
		for (int index = rightIndex; index >= leftIndex; index--) {
			sortedArray[countingVector[array[index] - smallestElement]] = array[index];
			countingVector[array[index] - smallestElement]--;
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

	private int getSmallestElement(Integer[] array, int leftIndex, int rightIndex) {
		int smallest = Integer.MAX_VALUE;
		for (int index = leftIndex; index <= rightIndex; index++) {
			if (array[index] < smallest) {
				smallest = array[index];
			}
		}
		return smallest;
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
