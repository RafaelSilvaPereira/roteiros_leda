package adt.stack;

import adt.linkedList.DoubleLinkedList;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> list;
	protected int size;
	
	public StackRecursiveDoubleLinkedListImpl(int size) {
		this.size = size;
	}
	
	@Override
	public void push(T element) throws StackOverflowException {
		list.insertFirst(element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		Object[] array =  (Object[]) list.toArray();
		list.removeFirst();
		return (T) array[0];
	}

	@Override
	public T top() {
		Object[] array =  (Object[]) list.toArray();
		return (T) array[0];
	}

	@Override
	public boolean isEmpty() {
		return list.isEmpty();
	}

	@Override
	public boolean isFull() {
		return size==list.size();
	}

}
