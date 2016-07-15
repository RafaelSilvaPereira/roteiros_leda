package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que funciona 
 * de forma ligeiramente diferente. Relembre que quando o pivô escolhido divide o 
 * array aproximadamente na metade, o QuickSort tem um desempenho perto do ótimo. 
 * Para aproximar a entrada do caso ótimo, diversas abordagens podem ser utilizadas. 
 * Uma delas é usar a mediana de 3 para achar o pivô. Essa técnica consiste no seguinte:
 * 1.	Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2.	Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3.	Adotar o A[center] como pivô.
 * 4.	Colocar o pivô na penúltima posição A[right-1].
 * 5.	Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6.	Aplicar o algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T>{

	private static final int MAIOR = 1;

	public void sort(T[] array, int leftIndex, int rightIndex){

		if (leftIndex < rightIndex) {
			median(array, leftIndex, rightIndex);

			int middle = partition(array, leftIndex + 1, rightIndex - 1);
			sort(array, leftIndex, middle - 1);
			sort(array, middle + 1, rightIndex);
		}
	}

	private void median(T[] array, int leftIndex, int rightIndex) {
		int middle = (leftIndex + rightIndex) / 2;

		if(array[leftIndex].compareTo(array[middle]) == MAIOR){
			Util.swap(array, leftIndex, middle);
		}
		if(array[middle].compareTo(array[rightIndex]) == MAIOR){
			Util.swap(array, middle, rightIndex);
		}
		if(array[leftIndex].compareTo(array[middle]) == MAIOR){
			Util.swap(array, leftIndex, middle);
		}

		Util.swap(array, middle, rightIndex - 1);
	}

	private int partition(T[] array, int leftIndex, int rightIndex) {
		int pivotIndex = rightIndex;

		int analysisIndex = pivotIndex;
		for(int index = pivotIndex - 1; index >= leftIndex; index--){
			if(array[index].compareTo(array[pivotIndex]) == MAIOR){
				Util.swap(array, --analysisIndex, index);
			}
		}
		Util.swap(array, pivotIndex, analysisIndex);
		return analysisIndex;
	}
}
