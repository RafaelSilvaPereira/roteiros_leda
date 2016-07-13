package sorting.divideAndConquer.quicksort3;

import java.awt.SecondaryLoop;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que funciona 
 * de forma ligeiramente diferente. Relembre que quando o pivô escolhido divide o 
 * array aproximadamente na metade, o QuickSort tem um desempenho perto do ótimo. 
 * Para aproximar a entrada do caso ótimo, diversas abordagens podem ser utilizadas. 
 * Uma delas é usar a mediana de 3 para achar o pivô. Essa técnica consiste no seguinte:
 * 1.	Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2.	Ordenar os elemento, tal que: A[left] < A[center] < A[right].
 * 3.	Adotar o A[center] como pivô.
 * 4.	Colocar o pivô na penúltima posição A[right-1].
 * 5.	Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6.	Aplicar o algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T>{
    
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			sortPivots(array, leftIndex, rightIndex);
			int posPivot = moveElems(array, leftIndex + 1, rightIndex - 1);
			if (rightIndex - leftIndex > 2) {
				sort(array, leftIndex, posPivot - 1);
				sort(array, posPivot + 1, rightIndex);
			} else {
				sortThree(array, leftIndex, rightIndex);
			}
		}
	}

	private void sortThree(T[] array, int leftIndex, int rightIndex) {
		int size = rightIndex - leftIndex;
		if (size > 0 && array[leftIndex].compareTo(array[rightIndex]) > 0) {
			Util.swap(array, leftIndex, rightIndex);
		}
		if (size > 1) {
			int middle = (leftIndex + rightIndex) / 2;
			if (array[leftIndex].compareTo(array[middle]) > 0)
				Util.swap(array, leftIndex, middle);
			if (array[middle].compareTo(array[rightIndex]) > 0)
				Util.swap(array, middle, rightIndex);
		}
	}

	private int moveElems(T[] array, int start, int end) {
		int backPointer = start - 1;
		int frontPointer = start;
		T pivot = array[end];

		while (frontPointer < end && backPointer < end) {
			if (array[frontPointer].compareTo(pivot) < 0 && frontPointer > backPointer) {
				backPointer++;
				Util.swap(array, backPointer, frontPointer);
			} else {
				frontPointer++;
			}
		}
		backPointer++;
		while (end > backPointer) {
			Util.swap(array, end, end - 1);
			end--;
		}
		// Util.swap(array, backPointer, end);
		return backPointer;
	}

	private void sortPivots(T[] array, int start, int end) {
		int middle = (start + end) / 2;
		if (array[start].compareTo(array[middle]) > 0) {
			Util.swap(array, start, middle);
		}
		if (array[start].compareTo(array[end]) > 0) {
			Util.swap(array, start, end);
		}
		if (array[middle].compareTo(array[end]) > 0) {
			Util.swap(array, middle, end);
		}
		Util.swap(array, middle, end - 1);
	}

}
