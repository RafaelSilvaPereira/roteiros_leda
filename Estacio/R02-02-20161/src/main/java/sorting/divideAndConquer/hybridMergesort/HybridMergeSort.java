package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do MergeSort 
 * que pode fazer uso do InsertionSort (um algoritmo híbrido) da seguinte forma: 
 * o MergeSort é aplicado a entradas maiores a um determinado limite. Caso a entrada 
 * tenha tamanho menor ou igual ao limite o algoritmo usa o InsertionSort. 
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de 
 *   forma que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada
 *   chamada interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e 
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 *  - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends AbstractSorting<T> {
    
	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;
	private static final int MENOR = -1;
	private static final int SAME_COMPARE = 0;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = INSERTIONSORT_APPLICATIONS = 0;

		hybridMergeSort(array, leftIndex, rightIndex);
	}

	private void hybridMergeSort(T[] array, int leftIndex, int rightIndex) {
		int size = rightIndex - leftIndex + 1;

		if(size > SIZE_LIMIT) {
			int middle = (leftIndex + rightIndex) / 2;
			hybridMergeSort(array, leftIndex, middle);
			hybridMergeSort(array, middle + 1, rightIndex);
			merge(array, leftIndex, middle, rightIndex);
		}else{
			insertionSort(array, leftIndex, rightIndex);
		}
	}

	private void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		MERGESORT_APPLICATIONS++;
		T[] temporaryArray = (T[]) new Comparable[array.length];

		copyArray(array, leftIndex, rightIndex, temporaryArray, leftIndex);

		int firstIndex = leftIndex, secondIndex = middle + 1, index = leftIndex;
		while(firstIndex <= middle && secondIndex <= rightIndex){
			if(temporaryArray[firstIndex].compareTo(temporaryArray[secondIndex]) <= SAME_COMPARE){
				array[index++] = temporaryArray[firstIndex++];
			}else{
				array[index++] = temporaryArray[secondIndex++];
			}
		}

		if(firstIndex <= middle){
			copyArray(temporaryArray, firstIndex, middle, array, index);
		}else if(secondIndex <= rightIndex){
			copyArray(temporaryArray, secondIndex, rightIndex, array, index);
		}
	}

	private void copyArray(T[] modelArray, int leftIndex, int rightIndex, T[] copy, int initialIndex) {
		for(int index = leftIndex; index <= rightIndex; index++, initialIndex++){
			copy[initialIndex] = modelArray[index];
		}
	}

	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		INSERTIONSORT_APPLICATIONS++;
		for(int index = leftIndex; index <= rightIndex; index++){
			int elementIndex = index;
			for(int analysisIndex = elementIndex - 1; analysisIndex >= leftIndex; analysisIndex--){
				if(array[elementIndex].compareTo(array[analysisIndex]) == MENOR){
					Util.swap(array, elementIndex--, analysisIndex);
				}
			}
		}
	}
}
