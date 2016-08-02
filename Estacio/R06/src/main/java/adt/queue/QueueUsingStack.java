package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

    private static final int ZERO = 0;

    private Stack<T> enqueueStack;
    private Stack<T> dequeueStack;

    public QueueUsingStack(int size) {
        if (size < ZERO) {
            size = ZERO;
        }
        enqueueStack = new StackImpl<T>(size);
        dequeueStack = new StackImpl<T>(size);
    }

    @Override
    public void enqueue(T element) throws QueueOverflowException {
        this.moveElements(this.dequeueStack, this.enqueueStack);
        try {
            this.enqueueStack.push(element);
        } catch (StackOverflowException e) {
            throw new QueueOverflowException();
        }
    }

    @Override
    public T dequeue() throws QueueUnderflowException {
        this.moveElements(this.enqueueStack, this.dequeueStack);
        try {
            return this.dequeueStack.pop();
        } catch (StackUnderflowException e) {
            throw new QueueUnderflowException();
        }
    }

    @Override
    public T head() {
        this.moveElements(this.enqueueStack, this.dequeueStack);
        return this.dequeueStack.top();
    }

    /**
     * Fill the second Stack with the elements of the first one.
     *
     * @param firstStack  Stack to have elements removed.
     * @param secondStack Stack to be filled.
     */
    private void moveElements(Stack<T> firstStack, Stack<T> secondStack) {
        try {
            while (!firstStack.isEmpty()) {
                secondStack.push(firstStack.pop());
            }
        } catch (StackUnderflowException | StackOverflowException exception) {
            // This is a private method... Trust me... I know what I'm doing...
        }
    }

    @Override
    public boolean isEmpty() {
        return this.dequeueStack.isEmpty() && this.enqueueStack.isEmpty();
    }

    @Override
    public boolean isFull() {
        return this.dequeueStack.isFull() || this.enqueueStack.isFull();
    }

}
