package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
        DoubleLinkedList<T> {

    protected final int ONE = 1;

    protected DoubleLinkedListNode<T> last;

    public DoubleLinkedListImpl() {
        super();
        DoubleLinkedListNode<T> head =
                new DoubleLinkedListNode<>(null, new DoubleLinkedListNode<T>(), new DoubleLinkedListNode<T>());
        this.setHead(head);
        this.setLast(head);
    }

    @Override
    public void insertFirst(T element) {
        if (element != null) {
            DoubleLinkedListNode<T> node =
                    new DoubleLinkedListNode<>(element, (DoubleLinkedListNode<T>) this.getHead(),
                            new DoubleLinkedListNode<T>());

            ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(node);
            this.setHead(node);
        }
    }

    @Override
    public void removeFirst() {
        if (!this.isEmpty()) {
            this.setHead(this.getHead().getNext());
            ((DoubleLinkedListNode<T>) this.getHead()).setPrevious(new DoubleLinkedListNode<T>());

            if (this.getHead().isNIL()) {
                this.getHead().setNext(new DoubleLinkedListNode<T>());
                this.setLast((DoubleLinkedListNode<T>) this.getHead());
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
            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, new DoubleLinkedListNode<T>(), this.getLast());
            if (isEmpty()) {
                this.setHead(node);
                this.setLast(node);
            } else {
                this.getLast().setNext(node);
                this.setLast(node);
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

    public DoubleLinkedListNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleLinkedListNode<T> last) {
        this.last = last;
    }

}