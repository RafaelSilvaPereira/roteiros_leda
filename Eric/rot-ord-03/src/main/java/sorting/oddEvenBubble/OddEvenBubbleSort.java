package sorting.oddEvenBubble;

import sorting.AbstractSorting;
import sorting.Util;

public class OddEvenBubbleSort<T extends Comparable<T>> extends AbstractSorting<T> {

	private static final int DEFAULT_STEP = 1;
	
	private static final int BUBBLE_STEP = 2;
	
	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array.length > 1 && rightIndex - leftIndex >= 1) {
			int evenStart, oddStart;
			if (leftIndex % 2 == 0) {
				evenStart = leftIndex;
				oddStart = evenStart + 1;
			} else {
				oddStart = leftIndex;
				evenStart = oddStart + 1;
			}
			customBubble(array, evenStart, rightIndex);
			customBubble(array, oddStart, rightIndex);
			merge(array, leftIndex, rightIndex);
		}
	}

	private void customBubble(T[] array, int start, int end) {
		customBubble(array, start, end, BUBBLE_STEP);
	}

	private void customBubble(T[] array, int start, int end, int step) {
		int discount = 0;
		for (int counter = start; counter <= end; counter += step) {
			for (int pointer = start; pointer <= end - step - discount; pointer += step) {
				if (array[pointer].compareTo(array[pointer + step]) > 0) {
					Util.swap(array, pointer, pointer + step);
				}
			}
			discount += step;
		}
	}

	private void merge(T[] array, int start, int end) {
		@SuppressWarnings("unchecked")
		T[] aux = (T[]) new Comparable[end - start + 1];

		int firstPointer = start, secPointer = start + 1, auxIndex = 0;
		while (firstPointer <= end && secPointer <= end) {
			if (array[firstPointer].compareTo(array[secPointer]) > 0) {
				aux[auxIndex] = array[secPointer];
				secPointer += BUBBLE_STEP;
			} else {
				aux[auxIndex] = array[firstPointer];
				firstPointer += BUBBLE_STEP;
			}
			auxIndex++;
		}
		if (firstPointer <= end)
			copyArray(array, aux, firstPointer, end, auxIndex, BUBBLE_STEP);
		if (secPointer <= end)
			copyArray(array, aux, secPointer, end, auxIndex, BUBBLE_STEP);
		copyArray(aux, array, 0, aux.length - 1, start, DEFAULT_STEP);
	}

	private void copyArray(T[] from, T[] to, int startFrom, int endFrom, int startTo, int fromStep) {
		for (; startFrom <= endFrom; startFrom += fromStep, startTo++) {
			to[startTo] = from[startFrom];
		}
	}
}
