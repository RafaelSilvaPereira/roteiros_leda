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
		median(array, leftIndex, rightIndex);
		int pivotIndex = partition(array, leftIndex+1, rightIndex-1);
		sort(array, leftIndex, pivotIndex-1);
		sort(array, pivotIndex+1, rightIndex);
	}
	
	private T median(T[] array, int leftIndex, int rightIndex){
		int center = (leftIndex + rightIndex) / 2;
		if (array[leftIndex].compareTo(array[center]) > 0){
			Util.swap(array, center, leftIndex);
		}
		if (array[center].compareTo(array[rightIndex]) > 0){
			Util.swap(array, center, rightIndex);
		}
		if (array[leftIndex].compareTo(array[center]) > 0){
			Util.swap(array, center, leftIndex);
		}	
		return array[rightIndex-1];
	}
	
	private int partition(T[] array, int from, int to){
		T pivot = array[to];
		int firstPointer = to-1;
		int secondPointer = from;
		while (firstPointer <= secondPointer){
			if (array[firstPointer].compareTo(pivot) <= 0) firstPointer++;
			else if (array[secondPointer].compareTo(pivot) > 0) secondPointer--;
			else Util.swap(array, firstPointer, secondPointer);
		}
		Util.swap(array, to, secondPointer);
		return secondPointer;
	}
}
