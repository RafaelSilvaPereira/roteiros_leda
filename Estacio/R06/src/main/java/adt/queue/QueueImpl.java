package adt.queue;

import java.util.Arrays;

public class QueueImpl<T> implements Queue<T> {

    private static final int ZERO = 0;

    private T[] array;
    private int tail;


    @SuppressWarnings("unchecked")
    public QueueImpl(int size) {
        if (size < ZERO) {
            size = ZERO;
        }
        array = (T[]) new Object[size];
        tail = -1;
    }

    @Override
    public T head() {
        if (this.isEmpty()) {
            return null;
        } else {
            return this.array[ZERO];
        }
    }

    @Override
    public boolean isEmpty() {
        return this.tail == -1;
    }

    @Override
    public boolean isFull() {
        return this.tail == array.length - 1;
    }

    private void shiftLeft() {
        this.tail--;
        for (int index = ZERO; index <= this.tail; index++) {
            this.array[index] = this.array[index + 1];
        }
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        if (this.isFull()) {
            throw new QueueOverflowException();
        } else if (element != null) {
            this.array[++this.tail] = element;
        }
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        if (this.isEmpty()) {
            throw new QueueUnderflowException();
        } else {
            T element = this.head();
            this.shiftLeft();
            return element;
        }
    }


}
