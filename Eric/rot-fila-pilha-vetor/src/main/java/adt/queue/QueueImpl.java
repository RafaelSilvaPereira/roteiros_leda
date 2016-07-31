package adt.queue;

public class QueueImpl<T> implements Queue<T> {

	private T[] array;
	private int tail;
		
	
	@SuppressWarnings("unchecked")
	public QueueImpl(int size) {
		array = (T[])new Object[size];
		tail = -1;
	}

	@Override
	public T head() {
		T elem = null;
		if (tail >= 0)
			elem = array[0];
		return elem;
	}

	@Override
	public boolean isEmpty() {
		return tail == -1;
	}

	@Override
	public boolean isFull() {
		return tail + 1 == array.length;
	}
	
	private void shiftLeft(){
		int i = 1;
		while (i < array.length && array[i] != null) {
			array[i - 1] = array[i];
			i++;
		}
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull())
			throw new QueueOverflowException();
		tail++;
		array[tail] = element;
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty())
			throw new QueueUnderflowException();
		T elem = array[0];
		this.shiftLeft();
		tail--;
		return elem;
	}


}
