package adt.queue;

public class CircularQueue<T> implements Queue<T> {

	private static final int NOT_INITIALIZED = -1;

	private static final int ZERO = 0;

	private static final int ONE = 1;

	private T[] array;

	private int tail;

	private int head;

	private int elements;

	private int capacity;

	@SuppressWarnings("unchecked")
	public CircularQueue(int size) {
		array = (T[]) new Object[size];
		capacity = size;
		head = NOT_INITIALIZED;
		tail = NOT_INITIALIZED;
		elements = ZERO;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();

		if (element != null) {
			if (tail == NOT_INITIALIZED)
				head = tail = ZERO;
			else
				tail = (tail + ONE) % capacity;
			array[tail] = element;
			elements++;
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		T element = array[head];
		elements--;
		if (head == tail)
			head = tail = NOT_INITIALIZED;
		else
			head = (head + ONE) % capacity;
		return element;
	}

	@Override
	public T head() {
		T elem = null;
		if (head != NOT_INITIALIZED)
			elem = array[head];
		return elem;
	}

	@Override
	public boolean isEmpty() {
		return elements == ZERO;
	}

	@Override
	public boolean isFull() {
		return elements == capacity;
	}
}
