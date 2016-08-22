package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

    protected static final int ZERO = 0;

    protected SingleLinkedListNode<T> head;

    public SingleLinkedListImpl() {
        this.head = new SingleLinkedListNode<T>();
    }

    @Override
    public boolean isEmpty() {
        return this.head.isNIL();
    }

    @Override
    public int size() {
        int counter = ZERO;
        if (this.isEmpty()) return counter;

        SingleLinkedListNode<T> node = this.getHead();
        while (!node.isNIL()) {
            node = node.getNext();
            counter++;
        }
        return counter;
    }

    @Override
    public T search(T element) {
        SingleLinkedListNode<T> node = this.getHead();
        while (!node.isNIL()
                && !node.getData().equals(element)) {
            node = node.getNext();
        }
        return (!node.isNIL() && node.getData().equals(element)) ? node.getData() : null;
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            SingleLinkedListNode<T> node = this.getHead();
            while (!node.isNIL()) {
                node = node.getNext();
            }

            node.setData(element);
            node.setNext(new SingleLinkedListNode<T>());
        }
    }

    @Override
    public void remove(T element) {
        if (!isEmpty() && element != null) {
            if (this.getHead().getData().equals(element)) {
                if (this.getHead().getNext().isNIL()) {
                    this.setHead(new SingleLinkedListNode<T>());
                    this.getHead().setNext(null);
                } else {
                    this.setHead(this.getHead().getNext());
                }
            } else {
                SingleLinkedListNode<T> node = this.getHead();
                SingleLinkedListNode<T> previous = new SingleLinkedListNode<>(null, node);

                while (!node.isNIL()
                        && !node.getData().equals(element)) {
                    previous = node;
                    node = node.getNext();
                }

                if (!node.isNIL() && node.getData().equals(element)) {
                    previous.setNext(node.getNext());
                }
            }
        }
    }

    @Override
    public T[] toArray() {
        T[] array = (T[]) new Object[this.size()];
        SingleLinkedListNode<T> node = this.getHead();

        int index = ZERO;
        while (!node.isNIL()) {
            array[index++] = node.getData();
            node = node.getNext();
        }

        return array;
    }

    public SingleLinkedListNode<T> getHead() {
        return head;
    }

    public void setHead(SingleLinkedListNode<T> head) {
        this.head = head;
    }

}