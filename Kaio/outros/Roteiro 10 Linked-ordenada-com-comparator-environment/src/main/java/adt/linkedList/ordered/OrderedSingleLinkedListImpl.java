package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 * 
 * @author Adalberto
 *
 * @param <T>
 */
public class OrderedSingleLinkedListImpl<T> implements
		OrderedSingleLinkedList<T> {

	private SingleLinkedListNode<T> head;
	private Comparator<T> comparator;

	/**
	 * Ao usar esse construtor, a ordem da lista não é garantida.
	 */
	public OrderedSingleLinkedListImpl() {
		this(null);
	}

	public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
		this.comparator = comparator;
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}

	@Override
	public int size() {
		int sum = 0;
		for (SingleLinkedListNode<T> aux = this.head; !aux.isNIL(); aux = aux.getNext())
			sum++;
		return sum;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> aux = this.head;
		while (!aux.isNIL()) {
			if (element.equals(aux.getData()))
				return aux.getData();
			aux = aux.getNext();
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			this.head.setData(element);
			this.head.setNext(new SingleLinkedListNode<T>());
		} else {
			SingleLinkedListNode<T> node = new SingleLinkedListNode<T>(element, new SingleLinkedListNode<T>());
			// se houver comparador, use-o
			if (this.comparator != null) {
				if (this.comparator.compare(element, this.head.getData()) < 0) {
					node.setNext(this.head);
					this.head = node;
				} else {
					SingleLinkedListNode<T> pred = this.head;
					SingleLinkedListNode<T> aux = this.head.getNext();
					while (aux != null && !aux.isNIL()) {
						if (this.comparator.compare(element, aux.getData()) < 0)
							break;
						pred = aux;
						aux = aux.getNext();
					}
					if (pred != null && !pred.isNIL()) {
						node.setNext(aux);
						pred.setNext(node);
					}
				}
			} else {
				// se não houver comparador, trate como descrito na interface
				// LinkedList
				SingleLinkedListNode<T> aux = this.head;
				while (!aux.getNext().isNIL())
					aux = aux.getNext();
				aux.setNext(node);
			}
		}
	}

	@Override
	public void remove(T element) {
		if (this.isEmpty())
			return;
		if (element.equals(this.head.getData())) {
			this.head = this.head.getNext();
		} else {
			SingleLinkedListNode<T> pred = this.head;
			SingleLinkedListNode<T> aux = this.head.getNext();
			while (aux != null && !aux.isNIL()) {
				if (element.equals(aux.getData()))
					pred.setNext(aux.getNext());
				pred = aux;
				aux = aux.getNext();
			}
//			if (pred != null && !pred.isNIL() && element.equals(pred.getData()))
//				pred.setNext(aux.getNext());
		}
	}

	@Override
	public T[] toArray() {
		@SuppressWarnings("unchecked")
		T[] array = ((T[]) new Comparable[this.size()]);
		SingleLinkedListNode<T> aux = this.head;
		for (int i = 0; !aux.isNIL(); i++, aux = aux.getNext())
			array[i] = aux.getData();
		return array;
	}

	@Override
	public T minimum() {
		if (this.comparator == null || this.isEmpty())
			return null;
		T min = this.head.getData();
		for (SingleLinkedListNode<T> aux = this.head.getNext(); aux != null && !aux.isNIL(); aux = aux.getNext()) {
			if (this.comparator.compare(min, aux.getData()) > 0)
				min = aux.getData();
		}
		return min;
	}

	@Override
	public T maximum() {
		if (this.comparator == null || this.isEmpty())
			return null;
		T max = this.head.getData();
		for (SingleLinkedListNode<T> aux = this.head.getNext(); aux != null && !aux.isNIL(); aux = aux.getNext()) {
			if (this.comparator.compare(max, aux.getData()) < 0)
				max = aux.getData();
		}
		return max;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
		// TODO: fazer isso dá certo. How?
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
