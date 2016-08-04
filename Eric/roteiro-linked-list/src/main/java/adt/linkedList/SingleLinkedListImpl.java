package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected static final int ZERO = 0;
	
	protected static final int ONE = 1;

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = null;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> aux = head;
		while (aux != null) {
			size++;
			aux = aux.getNext();			
		}
		return size;
	}

	@Override
	public T search(T element) {
		T result = null;
		if (!isEmpty() && element != null) {
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
		if (element != null) {
			SingleLinkedListNode<T> newElement = new SingleLinkedListNode<>(element, null);
			if (isEmpty()) {
				head = newElement;
			} else {
				SingleLinkedListNode<T> aux = head;
				while (aux.getNext() != null)
					aux = aux.getNext();
				aux.setNext(newElement);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (head.getData().equals(element)) {
				head = head.getNext();
			} else if (size() > ONE) {
				SingleLinkedListNode<T> frontPointer = head.getNext();
				SingleLinkedListNode<T> backPointer = head;
				while (frontPointer.getNext() != null && !frontPointer.getData().equals(element)) {
					backPointer = frontPointer;
					frontPointer = frontPointer.getNext();
				}
				if (frontPointer.getData().equals(element)) {
					backPointer.setNext(frontPointer.getNext());
				}
			}
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = (T[]) new Object[size()];
		SingleLinkedListNode<T> aux = this.head;
		for (int index = ZERO; aux != null; index++) {
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
