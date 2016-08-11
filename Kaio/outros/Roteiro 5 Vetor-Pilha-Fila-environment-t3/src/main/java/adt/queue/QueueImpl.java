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
		//TODO Implement your code here
		return null;
		
	}

	@Override
	public boolean isEmpty() {
		//TODO Implement your code here
		return false;
	}

	@Override
	public boolean isFull() {
		//TODO Implement your code here
		return false;
	}
	
	private void shiftLeft(){
		//TODO Implement your code here
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		//TODO Implement your code here
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		//TODO Implement your code here
		return null;
	}


}
