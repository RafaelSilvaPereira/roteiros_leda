package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	
	public RecursiveDoubleLinkedListImpl() {

	}

	public RecursiveDoubleLinkedListImpl(T data, RecursiveSingleLinkedListImpl<T> next, RecursiveDoubleLinkedListImpl<T> previous) {
		super(data, next);
		this.previous = previous;
	}

	@Override
	public void insertFirst(T element) {
		if(isEmpty()){
			next = new RecursiveDoubleLinkedListImpl<>();
			((RecursiveDoubleLinkedListImpl<T>) this.next).previous = this;
			previous = new RecursiveDoubleLinkedListImpl<>();
			previous.next = this;
			data = element;
		}else{
			RecursiveDoubleLinkedListImpl<T> novoNo = new RecursiveDoubleLinkedListImpl<>();
			((RecursiveDoubleLinkedListImpl<T>) this.next).previous = novoNo;
			novoNo.data =this.data;
			novoNo.next = this.next;
			this.next = novoNo;
			novoNo.previous = this;


			this.data = element;

			


			

			
		}
	}

	@Override
	public void removeFirst() {
		if(!isEmpty()){
			if(next.isEmpty()){
				data = next.data;
			}else{
				data = next.data;
				next = next.next;
				((RecursiveDoubleLinkedListImpl<T>) next).previous = this;
			}
			
		}
	}

	@Override
	public void removeLast() {
		if(!isEmpty()){
			if(next.isEmpty()){
				data = next.data;
			}else{
				((RecursiveDoubleLinkedListImpl<T>) next).removeLast();
			}
			
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}
	
	@Override
	public void insert(T element) {
		if (isEmpty()) {
			this.data = element;
			this.next = new RecursiveDoubleLinkedListImpl<T>();
			((RecursiveDoubleLinkedListImpl<T>) this.next).previous = new RecursiveDoubleLinkedListImpl<T>();

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
				this.previous.next = this.next;
			} else {
				next.remove(element);
			}
		}
	}
	
}
