package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {
	
	protected DoubleLinkedList<T> list;
	protected int size;
	
	public StackDoubleLinkedListImpl(int size) {
		list = new DoubleLinkedListImpl<T>();
		this.size = size;
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		if (list.size() < size) {
			list.insertFirst(element);
		}
		throw new StackOverflowException();
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (!list.isEmpty()) {
			T element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
			list.removeFirst();
			return element;
		}
		throw new StackUnderflowException();
	}

	@Override
	public T top() {
		return ((DoubleLinkedListImpl<T>) list).getHead().getData();
	}

	@Override
	public boolean isEmpty() {
		return list.size() == 0;
	}

	@Override
	public boolean isFull() {
		return list.size() == size;
	}

}
