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
				this.setData(element);
				this.setNext(newNode(null, null, this));
				if (this.getPrevious() == null)
					this.setPrevious(newNode(null, this, null));
			} else {
				this.getNext().insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				this.data = getNext().getData();
				this.next = getNext().getNext();
				if (getNext() != null)
					getNext().setPrevious(this);
			} else {
				this.getNext().remove(element);
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> second = newNode(this.getData(), this.getNext(), this);
			this.setNext(second);
			this.setData(element);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> second = this.getNext();
			if (second.getNext() != null) {
				RecursiveDoubleLinkedListImpl<T> third = second.getNext();
				third.setPrevious(this);
			}
			this.setData(second.getData());
			this.setNext(second.getNext());
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (this.getNext().isEmpty()) {
				this.setData(null);
				this.setNext(null);
			} else {
				this.getNext().removeLast();
			}
		}
	}

	private RecursiveDoubleLinkedListImpl<T> newNode(T data, RecursiveDoubleLinkedListImpl<T> next,
			RecursiveDoubleLinkedListImpl<T> prev) {
		return new RecursiveDoubleLinkedListImpl<T>(data, next, prev);
	}

	@Override
	public RecursiveDoubleLinkedListImpl<T> getNext() {
		return (RecursiveDoubleLinkedListImpl<T>) super.getNext();
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
