package adt.stack;


public class StackImpl<T> implements Stack<T> {
	
	private static final int ONE = 1;
	private static final int NOT_INITIALIZED = -1;
	private T[] array;
	private int top;
	private int capacity;
	
	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		array = (T[])new Object[size];
		capacity = size;
		top = NOT_INITIALIZED;
	}
	
	@Override
	public T top() {
		T element = null;
		if (isEmpty())
			element = array[top];
		return element;
	}

	@Override
	public boolean isEmpty() {
		return top == NOT_INITIALIZED;
	}

	@Override
	public boolean isFull() {
		return top + ONE == capacity;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		
		if (element != null) {
			top++;
			array[top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		
		T element = array[top];
		top--;
		return element;
	}
}
