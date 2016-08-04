package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

    private static int ZERO = 0;
    protected DoubleLinkedList<T> list;
    protected int size;

    public QueueDoubleLinkedListImpl(int size) {
        if(size < ZERO){
            size = ZERO;
        }
        this.size = size;
        this.list = new DoubleLinkedListImpl<T>();
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        if (!isFull()) {
            this.list.insert(element);
        } else {
            throw new QueueOverflowException();
        }
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (!isEmpty()) {
            T element = this.head();
            this.list.removeFirst();
            return element;
        } else {
            throw new QueueUnderflowException();
        }
    }

    @Override
    public T head() {
        return isEmpty() ? null : ((DoubleLinkedListImpl<T>) this.list).getHead().getData();
    }

    @Override
    public boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean isFull() {
        return this.list.size() == this.size;
    }

}
