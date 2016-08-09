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
		int inFront = ZERO;
		if (!isEmpty()) {
			inFront = ONE + this.next.size();
		}
		return inFront;
	}

	@Override
	public T search(T element) {
		T searchResult = null;
		if (element != null && !isEmpty()) {
			if (this.data.equals(element)) {
				searchResult = this.data;
			} else {
				searchResult = this.next.search(element);
			}
		}
		return searchResult;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			if (isEmpty()) {
				this.data = element;
				this.next = new RecursiveDoubleLinkedListImpl<>();
			} else {
				this.next.insert(element);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (this.data.equals(element)) {
				this.data = this.next.getData();
				this.next = this.next.getNext();
			} else {
				this.next.remove(element);
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] thisArray = (T[]) new Object[ZERO];
		// well, i think it's supposed to work and be beautiful, not to be
		// efficient. Top-down strategy.
		if (!isEmpty()) {
			T[] subArray = this.next.toArray();
			thisArray = (T[]) new Object[ONE + subArray.length];
			thisArray[ZERO] = this.data;
			for (int pointer = ONE; pointer < thisArray.length; pointer++)
				thisArray[pointer] = subArray[pointer - ONE];
		}
		return thisArray;
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
