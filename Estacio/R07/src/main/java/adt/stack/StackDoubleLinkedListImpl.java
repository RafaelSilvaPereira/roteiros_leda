package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

    private static int ZERO = 0;
    protected DoubleLinkedList<T> list;
    protected int size;

    public StackDoubleLinkedListImpl(int size) {
        if(size < ZERO){
            size = ZERO;
        }
        this.size = size;
        this.list = new DoubleLinkedListImpl<T>();
    }

    @Override
    public void push(T element) throws StackOverflowException {
        if (!isFull()) {
            this.list.insert(element);
        } else {
            throw new StackOverflowException();
        }
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (!isEmpty()) {
            T element = this.top();
            this.list.removeLast();
            return element;
        } else {
            throw new StackUnderflowException();
        }
    }

    @Override
    public T top() {
        return (isEmpty()) ? null : ((DoubleLinkedListImpl<T>) this.list).getLast().getData();
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
