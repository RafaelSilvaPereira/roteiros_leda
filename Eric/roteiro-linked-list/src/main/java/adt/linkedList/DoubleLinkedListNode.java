package adt.linkedList;

public class DoubleLinkedListNode<T> extends SingleLinkedListNode<T> {
	
	private DoubleLinkedListNode<T> previous;

	public DoubleLinkedListNode() {
	}

	public DoubleLinkedListNode(T data, DoubleLinkedListNode<T> next, DoubleLinkedListNode<T> previous) {
		super(data, next);
		this.setPrevious(previous);
	}

	public DoubleLinkedListNode<T> getPrevious() {
		return previous;
	}

	public void setPrevious(DoubleLinkedListNode<T> previous) {
		this.previous = previous;
	}
}
