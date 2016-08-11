package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;
	
	public StackRecursiveDoubleLinkedListImpl(int size) {
		list = new RecursiveDoubleLinkedListImpl<>();
		this.size = size;
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		if (list.size() < size) {
			list.insertFirst(element);
			size++;
		}
		throw new StackOverflowException();
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (list.size() > 0) {
			list.removeFirst();
			size--;
		}
		throw new StackUnderflowException();
	}

	@SuppressWarnings("rawtypes")
	@Override
	public T top() {
		if (!list.isEmpty())
			return ((RecursiveDoubleLinkedListImpl<T>) list).getData();
		return null;
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return list.size() == size;
	}

}
