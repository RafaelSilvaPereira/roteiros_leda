package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.RecursiveDoubleLinkedListImpl;

public class StackRecursiveDoubleLinkedListImpl<T> implements Stack<T> {

    private static final int ZERO = 0;
    protected DoubleLinkedList<T> list;
    protected int size;

    public StackRecursiveDoubleLinkedListImpl(int size) {
        if(size < ZERO){
            size = ZERO;
        }
        this.size = size;
        this.list = new RecursiveDoubleLinkedListImpl<>();
    }

    @Override
    public void push(T element) throws StackOverflowException {
        if (!isFull()) {
            this.list.insertFirst(element);
        } else {
            throw new StackOverflowException();
        }
    }

    @Override
    public T pop() throws StackUnderflowException {
        if (!isEmpty()) {
            T element = this.top();
            this.list.removeFirst();
            return element;
        } else {
            throw new StackUnderflowException();
        }
    }

    @Override
    public T top() {
        return ((RecursiveDoubleLinkedListImpl<T>) this.list).getData();
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
