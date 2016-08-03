package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	DoubleLinkedListNode<T> last;

	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newElement = new DoubleLinkedListNode<>();
		newElement.setData(element);
		newElement.setNext(head);
		this.head = newElement;
		if (last == null) {
			last = new DoubleLinkedListNode<>();
			last.setPrevious(newElement);
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			this.head = this.head.getNext();
		}
	}

	@Override
	public void removeLast() {
		if (size > 2) {
			
		}
	}
}
