package sorting.variationsOfSelectionsort;


import java.lang.reflect.Array;
import java.util.Arrays;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * This algorithm simulates a logical partitioning of the input array by considering 
 * different indexing, that is, the first sub-array is indexed by even elements and
 * the second sub-array is indexed by odd elements. Then, it applies a complete selectionsort
 * in the first sub-array considering neighbours (even). After that, 
 * it applies a complete selectionsort in the second sub-array considering
 * neighbours (odd).  After that, the algorithm performs a merge between elements indexed
 * by even and odd numbers.
 */
public class OddEvenSelectionSort<T extends Comparable<T>> extends AbstractSorting<T> {
	private T [] vetorAux;

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex){

		for (int i = leftIndex; i <= rightIndex; i+=2) {
			int min = i;
			for (int j=i+2; j<= rightIndex;j+=2){
				if (array[j].compareTo(array[min])==-1){
					min =j;
					
				}
			}
			Util.swap(array, min, i);
		}
		
		for (int i = leftIndex+1; i <= rightIndex; i+=2) {
			int min = i;
			for (int j=i+2; j<= rightIndex;j+=2){
				if (array[j].compareTo(array[min])==-1){
					min =j;
					
				}
			}
			Util.swap(array, min, i);
		}
		
		
		int i = leftIndex;
		int j = leftIndex+1;
		int k = leftIndex;
		this.vetorAux = Arrays.copyOf(array, array.length); 
		
		while (i<= rightIndex && j<= rightIndex){
			if (vetorAux[i].compareTo(vetorAux[j])==-1){
				array[k] = vetorAux[i];
				i+=2;
			}else{
				array[k] = vetorAux[j];
				j+=2;
			}
			k++;
		}
		while (i<= rightIndex){
			array[k] = vetorAux[i];
			i+=2;
			k++;

		}
 
		while (j<= rightIndex){
			array[k] = vetorAux[j];
			j+=2;
			k++;
		}

	}
	

}
