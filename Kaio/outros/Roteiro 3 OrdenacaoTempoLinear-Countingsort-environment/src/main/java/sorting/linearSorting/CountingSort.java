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
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		
		if (array == null || array.length <= 0 || (rightIndex - leftIndex) <= 0)
			return;
		
		int maiorElemento = procuraMaiorElemento(array, leftIndex, rightIndex);
		
		int[] count = new int[maiorElemento+1];
		
		// conta recorrência dos elementos de array
		for (int i = leftIndex; i <= rightIndex; i++)
			count[array[i]]++;
		
		// usa o vetor de contagem pra povoar array de maneira ordenada
		for (int i = 0, j = leftIndex; i < count.length; i++) 
			while (count[i]-- > 0) 
				array[j++] = i;
	
	}

	private int procuraMaiorElemento(Integer[] array, int leftIndex, int rightIndex) {
		int maior = array[leftIndex];
		for (int i = leftIndex + 1; i <= rightIndex; i++)
			if (array[i] > maior)
				maior = array[i];
		return maior;
	}

}