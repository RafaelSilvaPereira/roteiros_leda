package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import sorting.Util;

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
    
	@SuppressWarnings("unchecked")
	private T[] helper = (T[]) new Comparable[6000000];
	
	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;
	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if ((rightIndex - leftIndex + 1) <= SIZE_LIMIT) {
			insertionSort(array, leftIndex, rightIndex);
		} else {
			mergeSort(array, leftIndex, rightIndex);
		}
	}
	
	public void insertionSort(T[] array, int leftIndex, int rightIndex) {
		int index;
		for (int i = leftIndex + 1; i <= rightIndex; i++) {
			index = i;
			while ((index > leftIndex) && (array[index-1].compareTo(array[index]) > 0)) {
				Util.swap(array, index-1, index);
				index--;
			}
		}
	}
	
	public void mergeSort(T[] array, int leftIndex, int rightIndex) {
		int middle;
		if (leftIndex < rightIndex) {
			middle = (leftIndex + rightIndex) / 2;
			mergeSort(array, leftIndex, middle);
			mergeSort(array, middle + 1, rightIndex);
			merge_two_sorted_list(array, leftIndex, middle, rightIndex);
		}
	}
	
	private void merge_two_sorted_list(
			T[] array, int leftIndex, int middle, int rightIndex) {
		
		System.arraycopy(array, leftIndex, helper, leftIndex, (rightIndex - leftIndex + 1));
		int i = leftIndex;
		int j = middle + 1;
		int k = leftIndex;
		
		while ((i <= middle) && (j <= rightIndex)) {
			if (helper[i].compareTo(helper[j]) <= 0) {
				array[k] = helper[i];
				i++;
			} else {
				array[k] = helper[j];
				j++;
			}
			k++;
		}
		
		while (i <= middle) {
			array[k] = helper[i];
			i++; k++;
		}
		
		while (j <= rightIndex) {
			array[k] = helper[j];
			j++; k++;
		}
		
	}
	
}
