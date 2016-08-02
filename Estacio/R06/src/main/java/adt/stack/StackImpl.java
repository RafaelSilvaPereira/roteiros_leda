package adt.stack;

public class StackImpl<T> implements Stack<T> {

	private static final int ZERO = 0;

	private T[] array;
	private int top;

	@SuppressWarnings("unchecked")
	public StackImpl(int size) {
		if(size < ZERO){
			size = ZERO;
		}
		array = (T[]) new Object[size];
		top = -1;
	}

	@Override
	public T top() {
		if(this.isEmpty()){
			return null;
		}else{
			return this.array[this.top];
		}
	}

	@Override
	public boolean isEmpty() {
		return this.top == -1;
	}

	@Override
	public boolean isFull() {
		return this.top == this.array.length - 1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if(this.isFull()){
			throw new StackOverflowException();
		}else if(element != null){
			this.array[++this.top] = element;
		}
	}

	@Override
	public T pop() throws StackUnderflowException {
		if(this.isEmpty()){
			throw new StackUnderflowException();
		}

		return this.array[this.top--];
	}

}
