package sorting.linearSorting;

import sorting.AbstractSorting;

import java.util.Arrays;

import javax.management.RuntimeErrorException;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de 
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array 
 * a ser ordenado.  
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	private static final Integer BASE_COUNT = 0;
	private static final Integer ONE_COUNT = 1;

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		int biggest, lowest, firstIndex, populateIndex, secondIndex, thirdIndex, finalIndex;
		if (leftIndex >= rightIndex) return;
		biggest = lowest = array[leftIndex];
		for (firstIndex = leftIndex+1; firstIndex <= rightIndex; firstIndex++){
			if (array[firstIndex] > biggest) biggest = array[firstIndex];
			if (array[firstIndex] < lowest) lowest = array[firstIndex];
		}
		Integer[] counted = new Integer[biggest - lowest + 1];
		for (populateIndex = leftIndex; populateIndex <= rightIndex; populateIndex++){
			if (counted[array[populateIndex]-lowest] == null) counted[array[populateIndex]-lowest] = ONE_COUNT;
			else counted[array[populateIndex]-lowest]++;
		}
		for (secondIndex = 1; secondIndex < counted.length; secondIndex++){
			if (counted[secondIndex-1] == null) counted[secondIndex-1] = BASE_COUNT;
			if (counted[secondIndex] == null) counted[secondIndex] = BASE_COUNT;
			counted[secondIndex] += counted[secondIndex-1];
		}
		Integer[] ordered = new Integer[array.length];
		for (thirdIndex = rightIndex; thirdIndex >= leftIndex; thirdIndex--){
			finalIndex = counted[array[thirdIndex]-lowest]--;
			ordered[finalIndex-1] = array[thirdIndex];
		}
		cloneArrays(array, ordered);
	}
	
	private void cloneArrays(Integer[] a, Integer[] b){
		if (a.length != b.length) 
			throw new RuntimeException("Both arrays must be of same size");
		int cloneIndex;
		for (cloneIndex = 0; cloneIndex < b.length; cloneIndex++) {
			a[cloneIndex] = b[cloneIndex];
		}
	}
}
