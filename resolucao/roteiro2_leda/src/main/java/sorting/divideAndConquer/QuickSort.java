package sorting.divideAndConquer;

import java.util.Random;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm.
 * The algorithm chooses a pivot element and rearranges the elements of the
 * interval in such a way that all elements lesser than the pivot go to the
 * left part of the array and all elements greater than the pivot, go to the
 * right part of the array. Then it recursively sorts the left and the right parts.
 * Notice that if the list has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {
	private static Random escolhePivot = new Random();

	@Override
	public void sort(T[] array,int leftIndex, int rightIndex) {
		  if (leftIndex<rightIndex){
	          int posicaoPivot = separar(array, leftIndex, rightIndex);
	          sort(array, leftIndex, posicaoPivot-1);
	          sort(array, posicaoPivot+1, rightIndex);

	          
	    }
	    }
	       
	       public  int separar (T[] array, int leftIndex, int rightIndex){
	    	   int indice = escolhePivot.nextInt(rightIndex-leftIndex +1) + leftIndex;
	    	   Util.swap(array, indice, leftIndex);
	           T pivot = array[leftIndex];
	           int i =leftIndex+ 1;
	           int f = rightIndex;
	           while (i <= f)
	              {
	                 if (array[i].compareTo(pivot)<=0){
	                    i++;
	                 }
	                 else if (pivot.compareTo(array[f])<0){
	                    f--;
	                 }
	                 else
	                 {
	                    Util.swap(array, i, f);
	                    i++;
	                    f--;
	                 }
	              }
	              array[leftIndex] = array[f];
	              array[f] = pivot;
	              return f;
	       }

	
}
