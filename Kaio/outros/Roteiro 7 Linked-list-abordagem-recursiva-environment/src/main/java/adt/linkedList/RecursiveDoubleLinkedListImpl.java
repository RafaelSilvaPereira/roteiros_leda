package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;
	
	public RecursiveDoubleLinkedListImpl() {
		this(null, null, null);
	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		// copia antigo primeiro elemento
		RecursiveDoubleLinkedListImpl aux = new RecursiveDoubleLinkedListImpl<>();
		aux.data = this.data;
		aux.next = this.next;
		aux.previous = this;
		
		// faz a cabeça ser o primeiro elemento
		this.data = element;
		this.next = aux;
	}

	@Override
	public void removeFirst() {
		this.data = next.data;
		RecursiveDoubleLinkedListImpl aux = (RecursiveDoubleLinkedListImpl<T>) this.next.next;
		aux.previous = this;
		this.next = aux;
	}

	@Override
	public void removeLast() {
		if (isEmpty()) {
			RecursiveDoubleLinkedListImpl aux = (RecursiveDoubleLinkedListImpl<T>) this.previous;
			if (aux.previous != null)
				aux = aux.previous;
			aux.next = this;
			this.previous = aux;
		}
	}

	@Override
	public void insert(T element) {
		// trata primeiro elemento
		if (isEmpty()) {
			this.data = element;
			this.previous = new RecursiveDoubleLinkedListImpl<>();
			this.next = new RecursiveDoubleLinkedListImpl<>();
		} else {
			RecursiveDoubleLinkedListImpl aux = (RecursiveDoubleLinkedListImpl) this.next;
			aux.insert(element, this);
		}
	}

	public void insert(T element, RecursiveDoubleLinkedListImpl<T> previous) {
		if (isEmpty()) {
			this.data = element;
			this.previous = previous;
			this.next = new RecursiveDoubleLinkedListImpl<>();
		} else {
			RecursiveDoubleLinkedListImpl aux = (RecursiveDoubleLinkedListImpl) this.next;
			aux.insert(element, this);
		}
	}
	
	@Override
	public void remove(T element) {
		// tratar primeiro elemento
		if (data.equals(element)) {
			this.data = next.data;
			RecursiveDoubleLinkedListImpl aux = new RecursiveDoubleLinkedListImpl<>();
			aux = (RecursiveDoubleLinkedListImpl) this.next.next;
			aux.previous = this;
			this.next = aux;
		} else
			this.next.remove(element);
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
}
