package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	protected int size;

	public SingleLinkedListImpl() {
		this.size = 0;
		this.head = null;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		return this.size;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			while (aux.getNext() != null && !aux.getData().equals(element)) {
				aux = aux.getNext();
			}
			if (aux.getData().equals(element)) {
				result = aux.getData();
			}
		}
		return result;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> newElement = new SingleLinkedListNode<>(element, null);
		if (isEmpty()) {
			head = newElement;
		} else {
			SingleLinkedListNode<T> aux = head;
			while (aux.getNext() != null)
				aux = aux.getNext();
			aux.setNext(newElement);
		}
		size++;
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (head.getData().equals(element)) {
				head = head.getNext();
				size--;
			} else if (size > 1) {
				SingleLinkedListNode<T> frontPointer = head.getNext();
				SingleLinkedListNode<T> backPointer = head;
				while (frontPointer.getNext() != null && !frontPointer.getData().equals(element)) {
					backPointer = frontPointer;
					frontPointer = frontPointer.getNext();
				}
				if (frontPointer.getData().equals(element)) {
					backPointer.setNext(frontPointer.getNext());
					size--;
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		SingleLinkedListNode<T> aux = this.head;
		for (int index = 0; aux != null; index++) {
			array[index] = aux.getData();
			aux = aux.getNext();
		}
		return array;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}
}
