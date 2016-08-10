package adt.heap;

import java.util.Arrays;

public class MinHeapImpl<T extends Comparable<T>> implements MinHeap<T> {

	private static final int MINIMUM_LENGHT_HEAPFY = 2;
	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;
	private T[] heap;
	private int tail;
	
	@SuppressWarnings("unchecked")
	public MinHeapImpl() {
		this.heap = (T[]) new Comparable[INITIAL_SIZE];
		this.tail = -1;
	}
	
	@Override
	public boolean isEmpty() {
		return this.tail == -1;
	}

	
	@Override
	public void insert(T element) {
		if (element == null)
			return ;
		if (this.isFull())
			this.resizing();
		this.heap[++this.tail] = element;
		int pai = this.tail;
		do {
			pai = this.getIndexParent(pai);
			this.heapify(pai);
		} while(pai != 0);
	}

	@Override
	public T extractRootElement() {
		if (this.isEmpty())
			return null;
		T element = this.heap[0];
		Util.swap(this.heap, 0, this.tail--);
		this.heapify(0);
		return element;
	}

	@Override
	public T rootElement() {
		if (this.isEmpty())
			return null;
		return this.heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		
		if (array == null || array.length == 0)
			return array;
		
		T[] reference = this.heap;
		int oldTail = this.tail;
		
		this.buildHeap(array);
		
		@SuppressWarnings("unchecked")
		T[] result = ((T[]) new Comparable[array.length]);
		for (int i = 0; i < array.length; i++)
			result[i] = this.extractRootElement();
		
		this.heap = reference;
		this.tail = oldTail;
		
		return result;
	}

	@Override
	public void buildHeap(T[] array) {
		// if (array == null || array.length == 0)
		if (array == null || array.length == 0)
			return ;
		
		this.heap = Arrays.copyOf(array, array.length);
		this.tail = array.length - 1;
		
		for (int i = this.getIndexParent(this.tail); i > -1; i--)
			this.heapify(i);
	}

	private void heapify(int position) {
		if (this.isEmpty() || this.isInvalidIndex(position))
			return;

		int left = this.getIndexLeft(position);
		int right = this.getIndexRight(position);
		
		int min_position = position;
		
		if (left != -1 && this.heap[min_position].compareTo(this.heap[left]) > 0) {
			min_position = left;
		}
		
		if (right != -1 && this.heap[min_position].compareTo(this.heap[right]) > 0) {
			min_position = right;
		}
		
		if (min_position != position) {
			Util.swap(this.heap, min_position, position);
			this.heapify(min_position);
		}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		if (this.isEmpty())
			return (T[]) new Comparable[0];
		return Arrays.copyOf(this.heap, this.tail + 1);
	}
	
	/*
	 *  funções internas
	 */
	
	private boolean isFull() {
		return (this.tail + 1) == this.heap.length;
	}
	
	private void resizing() {
		int lenght = this.heap.length + INCREASING_FACTOR;
		this.heap = Arrays.copyOf(this.heap, lenght);
	}

	private int getIndexParent(int index) {
		if (this.isInvalidIndex(index))
			return -1;
		int pos = (index - 1) / 2;
		return this.isInvalidIndex(pos) ? -1 : pos;
	}
	
	private int getIndexLeft(int index) {
		if (this.isInvalidIndex(index))
			return -1;
		int pos = 2 * index + 1;
		return this.isInvalidIndex(pos) ? -1 : pos;
	}
	
	private int getIndexRight(int index) {
		if (this.isInvalidIndex(index))
			return -1;
		int pos = (index + 1) * 2;
		return this.isInvalidIndex(pos) ? -1 : pos;
	}
	
	private boolean isInvalidIndex(int index) {
		return this.isEmpty() || index < 0 || index > this.tail;
	}

}
