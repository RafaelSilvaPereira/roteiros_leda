package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	protected int size;

	public SingleLinkedListImpl() {
		this.size = 0;
		this.head = new SingleLinkedListNode<T>();
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
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			while (aux.getNext() != null && !aux.getData().equals(element)) {
				aux = aux.getNext();
			}
			if (!aux.getData().equals(element))
				element = null;
		} else {
			element = null;
		}
		return element;
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> newElement = new SingleLinkedListNode<>();
		if (isEmpty()) {
			head = newElement;
			size++;
		} else {
			SingleLinkedListNode<T> aux = head;
			while (aux.getNext() != null)
				aux = aux.getNext();
			newElement.setData(element);
			aux.setNext(newElement);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			SingleLinkedListNode<T> aux = head;
			if (head.getData().equals(element)) {
				head = head.getNext();
				size--;
			} else if (size > 1) {
				SingleLinkedListNode<T> backPointer = aux;
				aux = aux.getNext();
				while (aux.getNext() != null && !aux.getData().equals(element)) {
					backPointer = aux;
					aux = aux.getNext();
				}
				if (aux.getData().equals(element)) {
					backPointer.setNext(aux.getNext());
					size--;
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size];
		SingleLinkedListNode<T> aux = head;
		for (int index = 0; aux.getNext() != null; index++, aux = aux.getNext()) {
			array[index] = aux.getData();
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
