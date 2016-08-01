package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private static final int NOT_INITIALIZED = -1;

	private static final int ZERO = 0;

	private static final int ONE = 1;

	private final int CAPACITY;

	private T[] array;

	private int tail;

	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		// well, you're doing the same with a negative capacity stack and a zero
		// sized one :D
		if (size < ZERO)
			size = ZERO;
		array = (T[]) new Object[size];
		CAPACITY = size;
		tail = NOT_INITIALIZED;
	}

	@Override
	public T head() {
		T element = null;
		if (tail != NOT_INITIALIZED)
			element = array[ZERO];
		return element;
	}

	@Override
	public boolean isEmpty() {
		return tail == NOT_INITIALIZED;
	}

	@Override
	public boolean isFull() {
		return tail + ONE == CAPACITY;
	}

	private void shiftLeft() {
		int pointer = ONE;
		while (pointer < CAPACITY && array[pointer] != null) {
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
		T element = array[ZERO];
		this.shiftLeft();
		tail--;
		return element;
	}
}
