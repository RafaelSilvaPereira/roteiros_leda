package adt.heap;

import java.util.Arrays;

public class MinHeapImpl<T extends Comparable<T>> implements MinHeap<T> {

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	private T[] arrayHeap;
	private int indice;

	public MinHeapImpl() {
		this.indice = -1;
		this.arrayHeap = (T[]) new Comparable[INITIAL_SIZE];
	}

	@Override
	public boolean isEmpty() {
		return indice == -1;
	}

	@Override
	public void insert(T element) {
		if (indice == arrayHeap.length -1) {
			arrayHeap = Arrays.copyOf(arrayHeap, arrayHeap.length
					+ INCREASING_FACTOR);
		}
		indice++;
		arrayHeap[indice] = element;
		int i = indice;
		while (i >= 1 && arrayHeap[parent(i)].compareTo(arrayHeap[i]) == 1) {
			Util.swap(arrayHeap, i, parent(i));
			i = parent(i);
		}
	}

	private int parent(int i) {
		return (i - 1) / 2;
	}

	private int left(int i) {
		return 2 * i + 1;
	}

	private int right(int i) {
		return 2 * (i + 1);
	}

	@Override
	public T extractRootElement() {
		if (isEmpty()) {
			return null;

		} else {
			T min = arrayHeap[0];
			arrayHeap[0] = arrayHeap[indice];
			indice--;
			heapify(0);
			return min;
		}
	}

	@Override
	public T rootElement() {
		if (isEmpty()) {
			return null;
		} else {
			return arrayHeap[0];
		}
	}

	@Override
	public T[] heapsort(T[] array) {
		buildHeap(array);
		T [] arrayNovo = (T[]) new Comparable[indice+1];
		for (int i = 0; i < arrayNovo.length; i++) {
			arrayNovo[i] =  extractRootElement();
		}
		return arrayNovo;
	}

	@Override
	public void buildHeap(T[] array) {
		arrayHeap = array;
		indice = array.length -1;
		for (int i = parent(indice); i >= 0; i--) {
			heapify(i);
		}
	}

	private void heapify(int position) {
		int left = left(position);
		int right = right(position);
		int min = position;
		if (left <= indice
				&& arrayHeap[left].compareTo(arrayHeap[position]) == -1) {
			min = left;
		}
		if (right <= indice 
				&& arrayHeap[right].compareTo(arrayHeap[min]) == -1) {
			min = right;
		}
		if (min != position) {
			Util.swap(arrayHeap, min, position);
			heapify(min);
		}

	}

	@Override
	public T[] toArray() {
		T[] array = (T[]) new Comparable [indice+1];
		for (int i = 0; i < array.length; i++) {
			array[i] = arrayHeap[i];
			
		}
		return array;
	}


}
