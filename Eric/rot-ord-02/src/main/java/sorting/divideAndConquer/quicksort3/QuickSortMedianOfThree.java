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
 * 2.	Ordenar os elemento, tal que: A[left] < A[center] < A[right].
 * 3.	Adotar o A[center] como pivô.
 * 4.	Colocar o pivô na penúltima posição A[right-1].
 * 5.	Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6.	Aplicar o algoritmo na metade a esquerda e na metade a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T>{
    
	public void sort(T[] array, int leftIndex, int rightIndex){
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			int middle = sortPivots(array, leftIndex, rightIndex);
			Util.swap(array, middle, rightIndex - 1);
			int posPivot = rightIndex - 1;
			posPivot = moveElems(array, posPivot, leftIndex + 1, rightIndex - 1);
			sort(array, leftIndex, posPivot - 1);
			sort(array, posPivot + 1, rightIndex);
		}
	}
	
	private int moveElems(T[] array, int posPivot, int start, int end) {
		T[] aux = (T[]) new Comparable[end - start + 1];
		T pivot = array[posPivot];
		int indexAux = 0;

		for (int i = start; i <= end; i++) {
			if (array[i].compareTo(pivot) < 0) {
				aux[indexAux] = array[i];
				indexAux++;
			}
		}
		posPivot = start + indexAux;
		for (int i = start; i <= end; i++) {
			if (array[i].compareTo(pivot) == 0) {
				aux[indexAux] = array[i];
				indexAux++;
			}
		}
		for (int i = start; i <= end; i++) {
			if (array[i].compareTo(pivot) > 0) {
				aux[indexAux] = array[i];
				indexAux++;
			}
		}
		for (int i = 0; i < aux.length; i++) {
			array[start + i] = aux[i];
		}
		return posPivot;
	}

	private int sortPivots(T[] array, int start, int end) {
		int middle = (start + end) / 2;
		for (int i = 0; i < 2; i++) {
			if (array[start].compareTo(array[middle]) > 0) {
				Util.swap(array, start, middle);
			}
			if (array[middle].compareTo(array[end]) > 0) {
				Util.swap(array, middle, end);
			}
		}
		return middle;
	}
	
}
