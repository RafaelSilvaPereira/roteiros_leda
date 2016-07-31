package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private static final int NOT_INITIALIZED = -1;

	private static final int ZERO_INDEX = 0;

	private static final int ONE = 1;

	private T[] array;

	private int tail;
	
	private int capacity;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[]) new Object[size];
		capacity = size;
		tail = NOT_INITIALIZED;
	}

	@Override
	public T head() {
		T elem = null;
		if (tail != NOT_INITIALIZED)
			elem = array[ZERO_INDEX];
		return elem;
	}

	@Override
	public boolean isEmpty() {
		return tail == NOT_INITIALIZED;
	}

	@Override
	public boolean isFull() {
		return tail + ONE == capacity;
	}

	private void shiftLeft() {
		int pointer = ONE;
		while (pointer < capacity && array[pointer] != null) {
			array[pointer - ONE] = array[pointer];
			pointer++;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		if (element != null) {
			tail++;
			array[tail] = element;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		T elem = array[ZERO_INDEX];
		this.shiftLeft();
		tail--;
		return elem;
	}
}
