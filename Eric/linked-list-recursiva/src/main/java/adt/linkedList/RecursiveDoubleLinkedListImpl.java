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
			RecursiveDoubleLinkedListImpl<T> lastNode = (RecursiveDoubleLinkedListImpl<T>) super.searchNode(null);
			lastNode.setData(element);
			RecursiveDoubleLinkedListImpl<T> newLast = new RecursiveDoubleLinkedListImpl<T>();
			newLast.setPrevious(lastNode);
			lastNode.setNext(newLast);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			RecursiveDoubleLinkedListImpl<T> newSecond = new RecursiveDoubleLinkedListImpl<T>();
			newSecond.setData(this.data);
			newSecond.setNext(this.next);
			newSecond.setPrevious(this);
			this.data = element;
			this.next = newSecond;
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
		RecursiveDoubleLinkedListImpl<T> lastNode = (RecursiveDoubleLinkedListImpl<T>) super.searchNode(null);
		lastNode = lastNode.getPrevious();
		if (lastNode != null) {
			lastNode.setData(null);
			lastNode.setNext(null);
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
}
