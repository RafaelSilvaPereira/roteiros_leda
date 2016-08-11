package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
        RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

    protected RecursiveDoubleLinkedListImpl<T> previous;

    public RecursiveDoubleLinkedListImpl() {

    }

    public RecursiveDoubleLinkedListImpl(T data,
                                         RecursiveSingleLinkedListImpl<T> next,
                                         RecursiveDoubleLinkedListImpl<T> previous) {
        super(data, next);
        this.next = (RecursiveDoubleLinkedListImpl<T>) next;
        this.previous = previous;
    }

    @Override
    public void insertFirst(T element) {
        if (element != null) {
            RecursiveDoubleLinkedListImpl<T> node =
                    new RecursiveDoubleLinkedListImpl<>(this.getData(), this.getNext(), this);
            this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
            this.setData(element);
            this.setNext(node);
        }
    }

    @Override
    public void removeFirst() {
        if (!isEmpty()) {
            this.setPrevious(new RecursiveDoubleLinkedListImpl<T>());
            this.setData(this.getNext().getData());
            if (isNull()) {
                this.setNext(new RecursiveDoubleLinkedListImpl<T>());
            } else {
                this.setNext(this.getNext().getNext());
            }
        }
    }

    @Override
    public void removeLast() {
        if (!isEmpty()) {
            if (this.getNext().isNull()) {
                this.setData(this.getNext().getData());
                this.setNext(this.getNext().getNext());
            } else {
                this.getNext().removeLast();
            }
        }
    }

    @Override
    public void insert(T element) {
        if (isEmpty()) {
            this.insertFirst(element);
        } else {
            this.getNext().insert(element);
        }
    }

    public RecursiveDoubleLinkedListImpl<T> getPrevious() {
        return previous;
    }

    @Override
    public RecursiveDoubleLinkedListImpl<T> getNext() {
        return (RecursiveDoubleLinkedListImpl<T>) next;
    }

    public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
        this.previous = previous;
    }

}
