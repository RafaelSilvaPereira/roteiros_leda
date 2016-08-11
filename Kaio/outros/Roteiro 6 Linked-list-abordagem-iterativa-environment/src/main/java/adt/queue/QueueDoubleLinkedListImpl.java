package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	
	protected DoubleLinkedList<T> list;
	protected int size;
	
	public QueueDoubleLinkedListImpl(int size) {
		list = new DoubleLinkedListImpl<T>();
		this.size = size;
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (list.size() < size) {
			list.insert(element);
		}
		throw new QueueOverflowException();
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (!list.isEmpty()) {
			T element = ((DoubleLinkedListImpl<T>) list).getHead().getData();
			list.removeFirst();
			return element;
		}
		throw new QueueUnderflowException();
	}

	@Override
	public T head() {
		if (!list.isEmpty()) {
			return ((DoubleLinkedListImpl<T>) list).getHead().getData();
		}
		return null;
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
