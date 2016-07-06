package sorting.linearSorting;

import java.util.Arrays;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure evitar desperdicio de 
 * memoria alocando o array de contadores com o tamanho sendo o máximo inteiro presente no array 
 * a ser ordenado.  
 * 
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array,int leftIndex, int rightIndex) {
		if (leftIndex<rightIndex){
			int maiorValor =maiorValor(array)+1;
	        int[] c = new int[maiorValor];
	        Integer [] b = Arrays.copyOf(array, array.length);
	
	        for (int i = leftIndex; i <= rightIndex; i++) {
	            c[b[i]]++;
	
	        }
	
	        for (int i = 1; i < c.length; i++) {
	            c[i] = c[i - 1] + c[i];
	        }
	
	        for (int i = rightIndex; i >= leftIndex; i--) {
	            array[c[b[i]]-1] = b[i];
	            c[b[i]]--;
	        }

		}
	}
	
    public int maiorValor(Integer[] array) {
        int maiorValor = array[0];
        for (int i = 1; i < array.length; i++) {
            if (maiorValor < array[i]){
            	maiorValor = array[i];
            }
        }
        return maiorValor;
    }

}
