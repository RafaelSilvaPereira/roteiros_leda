package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> addStack;

	private Stack<T> removeStack;

	public QueueUsingStack(int size) {
		addStack = new StackImpl<T>(size);
		removeStack = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		prepareToAdd();
		try {
			addStack.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		prepareToRemove();
		try {
			return removeStack.pop();
		} catch (StackUnderflowException e) {
			throw new QueueUnderflowException();
		}
	}

	@Override
	public T head() {
		prepareToRemove();
		return removeStack.top();
	}

	@Override
	public boolean isEmpty() {
		return addStack.isEmpty() && removeStack.isEmpty();
	}

	@Override
	public boolean isFull() {
		return addStack.isFull() || removeStack.isFull();
	}

	/**
	 * Fill up the removeStack if there are available elements
	 */
	private void prepareToRemove() {
		if (!addStack.isEmpty() && removeStack.isEmpty()) {
			getElemsFromTo(addStack, removeStack);
		}
	}

	/**
	 * Fill up the addStack if there are available elements
	 */
	private void prepareToAdd() {
		if (addStack.isEmpty() && !removeStack.isEmpty()) {
			getElemsFromTo(removeStack, addStack);
		}
	}

	/**
	 * It'll move all the elements from the fromStack to the toStack.
	 * 
	 * @param stackFrom
	 *            Stack with the elements
	 * @param stackTo
	 *            Empty stack
	 */
	private void getElemsFromTo(Stack<T> stackFrom, Stack<T> stackTo) {
		try {
			while (!stackFrom.isEmpty()) {
				T element = stackFrom.pop();
				stackTo.push(element);
			}
		} catch (StackOverflowException | StackUnderflowException e) {
			// let's just suppress this, it's gonna be alright, trust me
			// still, i'm not an engineer
			// throw new RuntimeException(e);
		}
	}
}
