package sorting.divideAndConquer.hybridMergeSort;

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
    
	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;
	
	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;
	
	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		MERGESORT_APPLICATIONS = 0;
		INSERTIONSORT_APPLICATIONS = 0;
		hybridSort(array, leftIndex, rightIndex);
	}
	
	private void hybridSort(T[] array,int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			int size = rightIndex - leftIndex;
			
			if (size > SIZE_LIMIT) {
				int middle = (leftIndex + rightIndex) / 2;
				hybridSort(array, leftIndex, middle);
				hybridSort(array, middle + 1, rightIndex);
				merge(array, leftIndex, middle, rightIndex);
			} else {
				insertionSort(array, leftIndex, rightIndex);
			}
		}
	}

	private void insertionSort(T[] array, int leftIndex, int rightIndex) {
		for (int aux = leftIndex; aux <= rightIndex; aux++) {
			int pointer = aux;
			while (pointer > leftIndex && array[pointer].compareTo(array[pointer - 1]) < 0) {
				Util.swap(array, pointer - 1, pointer);
				pointer--;
			}
		}
		INSERTIONSORT_APPLICATIONS++;
	}
	
	private void merge(T[] array, int start, int middle, int end) {
		int firstPart = start,
			secPart = middle + 1;
		T[] auxArray = (T[])new Comparable[end - start + 1];

		int indexAuxArr = 0;
		while (firstPart <= middle && secPart <= end) {
			if (array[firstPart].compareTo(array[secPart]) > 0) {
				auxArray[indexAuxArr] = array[secPart];
				secPart++;
			} else {
				auxArray[indexAuxArr] = array[firstPart];
				firstPart++;
			}
			indexAuxArr++;
		}
		
		if (firstPart <= middle) {
			copyArray(array, auxArray, firstPart, middle, indexAuxArr);
		}
		if (secPart <= end) {
			copyArray(array, auxArray, secPart, end, indexAuxArr);
		}
		copyArray(auxArray, array, 0, auxArray.length - 1, start);
		MERGESORT_APPLICATIONS++;
	}
	
	private void copyArray(T[] auxArray, T[] mainArray, int from, int to, int start) {
		for (int pointer = from; pointer <= to; pointer++, start++) {
			mainArray[start] = auxArray[pointer];
		}
	}
}
