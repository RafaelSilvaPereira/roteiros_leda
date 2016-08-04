package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	@Override
	public void remove(T element) {
		if (!isEmpty() && element != null) {
			if (head.getData().equals(element)) {
				head = head.getNext();
			} else if (size() > ONE) {
				SingleLinkedListNode<T> aux = head.getNext();
				while (aux.getNext() != null && !aux.getData().equals(element))
					aux = aux.getNext();
				
				if (aux.getData().equals(element)) {
					DoubleLinkedListNode<T> toRemove = (DoubleLinkedListNode<T>) aux,
											previous = toRemove.getPrevious(),
											next = (DoubleLinkedListNode<T>)toRemove.getNext();
					if (next != null)
						next.setPrevious(previous);
					// we don't need to check because it'll be the second at least
					previous.setNext(next);
				}
			}
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newElem = new DoubleLinkedListNode<>(element, null, last);
			if (isEmpty()) {
				head = last = newElem;
			} else {
				last.setNext(newElem);
				last = newElem;
			}
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> oldHead = (DoubleLinkedListNode<T>) this.head;
			DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<>(element, oldHead, null);
			if (isEmpty()) {
				last = newHead;
			} else {
				oldHead.setPrevious(newHead);
			}
			this.head = newHead;
		}
	}

	@Override
	public void removeFirst() {
		if (!isEmpty()) {
			if (size() > ONE) {
				DoubleLinkedListNode<T> secondElement = (DoubleLinkedListNode<T>) this.head.getNext();
				secondElement.setPrevious(null);
				this.head = secondElement;
			} else {
				this.head = null;
				this.last = null;
			}
		}
	}

	@Override
	public void removeLast() {
		if (!isEmpty()) {
			if (size() > ONE) {
				DoubleLinkedListNode<T> newLast = this.last.getPrevious();
				newLast.setNext(null);
				this.last = newLast;
			} else {
				this.head = null;
				this.last = null;
			}
		}
	}
}
