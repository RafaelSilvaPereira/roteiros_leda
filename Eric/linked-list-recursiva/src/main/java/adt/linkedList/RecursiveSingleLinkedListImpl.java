package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	private static final int ZERO = 0;

	private static final int ONE = 1;

	protected T data;

	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		int inFront = 0;
		if (!isEmpty())
			inFront++;
		if (this.next != null)
			inFront += this.next.size();
		return inFront;
	}

	@Override
	public T search(T element) {
		return this.searchNode(element).getData();
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			// This search will return the last node of the list
			RecursiveSingleLinkedListImpl<T> lastNode = this.searchNode(null);
			lastNode.setData(element);
			lastNode.setNext(new RecursiveSingleLinkedListImpl<T>());
		}
	}

	@Override
	public void remove(T element) {
		RecursiveSingleLinkedListImpl<T> node = this.searchNode(element);
		if (!node.isEmpty()) {
			// If the node isn't already the last it'll have a next one
			node.setData(node.getNext().getData());
			node.setNext(node.getNext().getNext());
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] thisArray = (T[]) new Object[ZERO];
		// well, i think it's supposed to work and be beautiful, not to be
		// efficient
		if (!isEmpty()) {
			T[] subArray = this.next.toArray();
			thisArray = (T[]) new Object[ONE + subArray.length];
			thisArray[ZERO] = this.data;
			for (int pointer = ONE; pointer < thisArray.length; pointer++)
				thisArray[pointer] = subArray[pointer - ONE];
		}
		return thisArray;
	}

	/**
	 * Search and return the node with specific data. If no node with the data
	 * is found it returns an empty node, which is the last node of the list.
	 */
	protected final RecursiveSingleLinkedListImpl<T> searchNode(T element) {
		RecursiveSingleLinkedListImpl<T> searchResult = null;
		// Return will be the last node or the node with data
		if (isEmpty() || this.data.equals(element)) {
			searchResult = this;
		} else {
			searchResult = this.next.searchNode(element);
		}
		return searchResult;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}
}
