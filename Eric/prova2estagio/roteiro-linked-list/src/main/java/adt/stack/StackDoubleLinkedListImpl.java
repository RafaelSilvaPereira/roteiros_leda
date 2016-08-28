package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	private static final int ZERO = 0;

	private final int SIZE;

	protected DoubleLinkedList<T> list;

	public StackDoubleLinkedListImpl(int size) {
		if (size < ZERO)
			size = ZERO;
		this.SIZE = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		list.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		T element = top();
		this.list.removeFirst();
		return element;
	}

	@Override
	public T top() {
		T element = null;
		if (!isEmpty())
			element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
		return element;
	}

	@Override
	public boolean isEmpty() {
		return this.list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.SIZE;
	}
}
