package sorting.divideAndConquer.hybridMergesort;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes: - Ter
 * contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 * que essa informação possa ser capturada pelo teste. - A cada chamado do
 * método de sort(T[] array) esses contadores são resetados. E a cada chamada
 * interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 * INSERTIONSORT_APPLICATIONS são incrementados. - O InsertionSort utilizado no
 * algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	private T[] array_auxiliar;
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array) {
		this.MERGESORT_APPLICATIONS = 0;
		this.INSERTIONSORT_APPLICATIONS = 0;
		super.sort(array);
	}

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (leftIndex < rightIndex) {
			if (rightIndex+1 - leftIndex  <= SIZE_LIMIT) {
				insertionSort(array, leftIndex, rightIndex);
				INSERTIONSORT_APPLICATIONS++;
			} else {
				int middle = (leftIndex + rightIndex) / 2;
				sort(array, leftIndex, middle);
				sort(array, middle + 1, rightIndex);
				merge(array, leftIndex, middle, rightIndex);
				MERGESORT_APPLICATIONS++;

			}
		}
	}

	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int i = leftIndex + 1; i < rightIndex + 1; i++) {
			T key = array[i];
			int j = i - 1;
			while (j >= leftIndex && (array[j].compareTo(key) == 1)) {
				array[j + 1] = array[j];
				j--;
			}
			array[j + 1] = key;
		}
	}

	public void merge(T[] array, int leftIndex, int middle, int rightIndex) {
		this.array_auxiliar = Arrays.copyOf(array, array.length);

		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;

		while (i <= middle && j <= rightIndex) {
			if (array_auxiliar[i].compareTo(array_auxiliar[j]) <= 0) {
				array[k] = array_auxiliar[i];
				i++;
			} else {
				array[k] = array_auxiliar[j];
				j++;
			}
			k++;
		}
		while (i <= middle) {
			array[k] = array_auxiliar[i];
			k++;
			i++;
		}
	}
}
