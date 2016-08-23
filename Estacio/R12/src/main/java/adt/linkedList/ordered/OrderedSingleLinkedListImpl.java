package adt.linkedList.ordered;

import java.util.Comparator;

import adt.linkedList.SingleLinkedListImpl;
import adt.linkedList.SingleLinkedListNode;

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
public class OrderedSingleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
        OrderedLinkedList<T> {

    protected static final int BIGGER = 1;
    protected static final int EQUAL = 0;
    protected static final int SMALLER = -1;

    private Comparator<T> comparator;

    public OrderedSingleLinkedListImpl() {
        this.comparator = (a, b) -> (a == null) ? BIGGER :
                                    (b == null) ? SMALLER :
                                    (a.hashCode() > b.hashCode()) ? BIGGER :
                                    (a.hashCode() == b.hashCode()) ? EQUAL : SMALLER;
    }

    public OrderedSingleLinkedListImpl(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    @Override
    public T minimum() {
        return (isEmpty()) ? null : this.getHead().getData();
    }

    protected SingleLinkedListNode<T> getLastNode() {
        SingleLinkedListNode<T> node;
        for (node = this.getHead();
             node.isNIL() || !node.getNext().isNIL(); node = node.getNext())
            ;
        return node;
    }

    @Override
    public T maximum() {
        return (isEmpty()) ? null : this.getLastNode().getData();
    }

    @Override
    public void insert(T element) {
        if (isEmpty()) {
            super.insert(element);
        } else {
            SingleLinkedListNode<T> node = this.getHead();
            SingleLinkedListNode<T> previous = null;
            while (this.comparator.compare(element, node.getData()) > EQUAL) {
                previous = node;
                node = node.getNext();
            }
            SingleLinkedListNode<T> newNode = new SingleLinkedListNode<T>(element, node);
            if (previous == null) {
                this.setHead(newNode);
            } else {
                previous.setNext(newNode);
            }
        }
    }

    public Comparator<T> getComparator() {
        return comparator;
    }

    public void setComparator(Comparator<T> comparator) {
        this.comparator = comparator;
        this.sort();
    }

    private void sort() {
        boolean swapped;
        do {
            swapped = false;
            for (SingleLinkedListNode<T> node = this.getHead();
                 !node.isNIL() && !node.getNext().isNIL();
                 node = node.getNext()) {
                if (this.comparator.compare(node.getData(), node.getNext().getData()) > EQUAL) {
                    this.swap(node, node.getNext());
                    swapped = true;
                }
            }

        } while (swapped);
    }

    private void swap(SingleLinkedListNode<T> node, SingleLinkedListNode<T> next) {
        T aux = node.getData();
        node.setData(next.getData());
        next.setData(aux);
    }
}
