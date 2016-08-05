package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {
	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<T>();
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).insert(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> second = new RecursiveDoubleLinkedListImpl<>(this.data, this.next, this);
			this.next = second;
			this.data = element;
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> second = (RecursiveDoubleLinkedListImpl<T>) this.next;
			this.data = second.getData();
			this.next = second.getNext();
			if (second.getNext() != null) {
				RecursiveDoubleLinkedListImpl<T> third = (RecursiveDoubleLinkedListImpl<T>) second.getNext();
				third.setPrevious(null);
			}
		}
	}

	@Override
	public void removeLast() {
		if (this.next != null) {
			if (this.next.isEmpty()) {
				this.data = null;
				this.next = null;
			} else {
				((RecursiveDoubleLinkedListImpl<T>) this.next).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
