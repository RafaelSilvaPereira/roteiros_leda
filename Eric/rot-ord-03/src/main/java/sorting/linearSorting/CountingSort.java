package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de 
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array 
 * a ser ordenado.  
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			countingSort(array, leftIndex, rightIndex);
		}
	}
	
	private void countingSort(Integer[] array, int leftIndex, int rightIndex) {
		Integer upBound = array[leftIndex], lowBound = array[leftIndex];
		for (Integer elem : array) {
			if (elem > upBound) {
				upBound = elem;
			}
			if (elem < lowBound) {
				lowBound = elem;
			}
		}
		int auxSize = upBound - lowBound + 1;
		int diff = lowBound;
		int[] auxCounter = new int[auxSize];
		Integer[] aux = new Integer[rightIndex - leftIndex + 1];
		// Counting the occurrences
		for (int pointer = leftIndex; pointer <= rightIndex; pointer++)
			auxCounter[array[pointer] - diff]++;
		// Summing the occurrences
		for (int pointer = 1; pointer < auxSize; pointer++)
			auxCounter[pointer] += auxCounter[pointer - 1];
		for (int pointer = rightIndex; pointer >= leftIndex; pointer--) {
			int counterPos = array[pointer] - diff;
			aux[auxCounter[counterPos] - 1] = array[pointer];
			auxCounter[counterPos]--;
		}
		copyTo(aux, array, leftIndex);		
	}
	
	private void copyTo(Integer[] arrayFrom, Integer[] arrayTo, int start) {
		for (int pointer = 0; pointer < arrayFrom.length; pointer++)
			arrayTo[pointer + start] = arrayFrom[pointer];
	}
}
