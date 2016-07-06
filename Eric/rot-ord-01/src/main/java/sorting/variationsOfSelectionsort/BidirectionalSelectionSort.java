package sorting.variationsOfSelectionsort;

import sorting.AbstractSorting;
import sorting.Util;

/**
 * This selection sort variation has two internal iterations. In the first, it takes the
 * smallest elements from the array, and puts it in the first position. In the second,
 * the iteration is done backwards, that is, from right to left, and this time the biggest
 * element is selected and stored in the last position. Then it repeats the process,
 * excluding the positions already filled in, until the whole array is ordered.
 */
public class BidirectionalSelectionSort<T extends Comparable<T>> extends AbstractSorting<T>{

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		int descontoInicio = 0, 
			descontoFim = 0;
		
		for (int aux = 0; aux <= rightIndex; aux++) {
			int comeco = leftIndex + descontoInicio, 
				fim = rightIndex - descontoFim;
			if (aux % 2 == 0) {
				int posMenor = fim;
				for (int pointer = fim; pointer >= comeco; pointer--) {
					if (array[posMenor].compareTo(array[pointer]) > 0) {
						posMenor = pointer;
					}
				}
				Util.swap(array, posMenor, comeco);
				descontoInicio++;
			} else {
				int posMaior = comeco;
				for (int pointer = comeco; pointer <= fim; pointer++) {
					if (array[posMaior].compareTo(array[pointer]) < 0) {
						posMaior = pointer;
					}
				}
				Util.swap(array, posMaior, fim);
				descontoFim++;
			}
		}
	}
}
