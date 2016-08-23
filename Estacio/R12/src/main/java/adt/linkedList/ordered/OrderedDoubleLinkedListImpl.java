package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListNode;

/**
 * Para testar essa classe voce deve implementar seu comparador. Primeiro
 * implemente todos os métodos requeridos. Depois implemente dois comparadores
 * (com idéias opostas) e teste sua classe com eles. Dependendo do comparador
 * que você utilizar a lista funcionar como ascendente ou descendente, mas a
 * implemntação dos métodos é a mesma.
 *
 * @param <T>
 * @author Adalberto
 */
public class OrderedDoubleLinkedListImpl<T> extends OrderedSingleLinkedListImpl<T> implements
        OrderedLinkedList<T>, DoubleLinkedList<T> {

    private static final int ONE = 1;
    private DoubleLinkedListNode<T> last;

    public OrderedDoubleLinkedListImpl() {
        super();
        DoubleLinkedListNode<T> head =
                new DoubleLinkedListNode<>(null, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
        this.setHead(head);
        this.last = head;
    }

    public OrderedDoubleLinkedListImpl(Comparator<T> comparator) {
        super(comparator);
    }

    /**
     * Este método faz sentido apenas se o elemento a ser inserido pode
     * realmente ficar na primeira posição (devido a ordem)
     */
    @Override
    public void insertFirst(T element) {
        if (element != null) {
            if (isEmpty()) {
                this.insert(element);
            } else if (this.getComparator().compare(element, this.getHead().getData()) <= EQUAL) {
                DoubleLinkedListNode<T> node =
                        new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) this.getHead(),
                                new DoubleLinkedListNode<T>());

                ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(node);
                this.setHead(node);
            }
        }
    }

    @Override
    public void removeFirst() {
        if (!this.isEmpty()) {
            this.setHead(this.getHead().getNext());
            ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<T>());

            if (this.getHead().isNIL()) {
                this.getHead().setNext(new DoubleLinkedListNode<T>());
                this.last = (DoubleLinkedListNode<T>) this.getHead();
            }
        }
    }

    @Override
    public void removeLast() {
        if (size() > ONE) {
            this.getLast().setData(null);
            this.getLast().setNext(null);
            this.setLast(this.getLast().getPrevious());
        } else if (size() == ONE) {
            DoubleLinkedListNode<T> head =
                    new DoubleLinkedListNode<>(null, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
            this.setHead(head);
            this.setLast(head);
        }
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
            if (isEmpty()) {
                this.setHead(newNode);
                this.setLast(newNode);
            } else {
                DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
                while (!node.getNext().isNIL() && this.getComparator().compare(element, node.getData()) > EQUAL) {
                    node = (DoubleLinkedListNode<T>) node.getNext();
                }

                if (node.getPrevious().isNIL()) {
                    ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(newNode);
                    newNode.setNext(this.getHead());
                    this.setHead(newNode);
                } else if (node.getNext().isNIL()) {
                    this.getLast().setNext(newNode);
                    this.setLast(newNode);
                } else {
                    newNode.setPrevious(node.getPrevious());
                    newNode.setNext(node);

                    node.getPrevious().setNext(newNode);
                    node.setPrevious(newNode);
                }
            }
        }
    }

    @Override
    public void remove(T element) {
        if (!isEmpty()) {
            if (this.getHead().getData().equals(element)) {
                this.removeFirst();
            } else if (this.getLast().getData().equals(element)) {
                this.removeLast();
            } else {
                DoubleLinkedListNode<T> node = (DoubleLinkedListNode<T>) this.getHead();
                while (!node.isNIL()
                        && !node.getData().equals(element)) {
                    node = (DoubleLinkedListNode<T>) node.getNext();
                }

                if (!node.isNIL() && node.getData().equals(element)) {
                    node.getPrevious().setNext(node.getNext());
                    ((DoubleLinkedListNode<T>) node.getNext()).setPrevious(node.getPrevious());
                }
            }
        }
    }

    private DoubleLinkedListNode<T> getLast() {
        return this.last;
    }

    private void setLast(DoubleLinkedListNode<T> node) {
        this.last = node;
    }
}
