package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

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
		return data == null;
	}

	@Override
	public int size() {
		if(isEmpty()){
			return 0;
		}return 1 + next.size();
	}

	@Override
	public T search(T element) {
		if(isEmpty()){
			return null;
		}else if (data.equals(element)){
			return data;
		}else{
			return next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveSingleLinkedListImpl<>();

		} else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (!isEmpty()) {
			if (this.data.equals(element)) {
				data = this.next.getData();
				next = this.next.getNext();
			} else {
				next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		int n =1;
		T[] array = (T[]) new Object[size()];
		if (!isEmpty()) {
			array[0] = data;
		}else{
			arrayAux(array, next, n);
		}
		return array;
		
	}

	private void arrayAux(T[] array,
			RecursiveSingleLinkedListImpl<T> node, int n) {
		if (n < size()) {
			array[n] = node.data;
			n++;
			arrayAux(array, node.next, n);
		}
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
