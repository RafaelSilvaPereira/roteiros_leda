package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;
	
		
	public RecursiveSingleLinkedListImpl() {
		this(null, null);
	}

	public RecursiveSingleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next) {
		this.data = data;
		this.next = next;
	}

	@Override
	public boolean isEmpty() {
		return data == null;
	}

	@Override
	public int size() {
		 if (next != null)
		      return next.size() + 1;
		 return 0;
	}

	@Override
	public T search(T element) {
		if (data.equals(element))
			return data;
		if (next == null)
			return null;
		return next.search(element);
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();
		}
		else
			next.insert(element);
	}

	@Override
	public void remove(T element) {
		// tratar primeiro elemento
		if (data.equals(element)) {
			this.data = next.data;
			this.next = next.next;
		}
		else this.remove(element);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {

		if (isEmpty())
			return null;
		
		T[] array = (T[]) new Object[this.size()];
		return toArray(this, array, 0);
	}
	
	private T[] toArray(RecursiveSingleLinkedListImpl<T> current, T[] array, int index) {
		if (isEmpty())
			return array;
		array[index] = current.data;
		return toArray(current.next, array, index + 1);
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
