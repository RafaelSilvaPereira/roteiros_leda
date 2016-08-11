package adt.queueStack;

import java.util.ArrayList;

import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

/**
 * Classe que implementa uma fila e uma pilha ao mesmo tempo usando apenas um arraylist sobrejacente.
 * A pilha é implementada no início do array e a fila é implementada no final do array. Assim, uma mesma estrutura 
 * (arraylist) serve para acomodar duas estruturas (pilha e fila) ao mesmo tempo. Cada estrutura (pilha e fila) 
 * pode ter um tamanho arbitrário, desde que a soma dos tamanhos delas seja no máximo o tamanho do array sobrejacente.
 *
 * @param <T> o tipo to dado armazenado na estrutura.
 */
public class QueueStackArrayListImpl<T> implements QueueStack<T> {

	/**
	 * Embora ArrayList seja uma estrutura que pode crescer dinamicamente, não será permitido esse crescimento
	 * e o tamanho inicial deverá ser respeitado.
	 */
	private ArrayList<T> array;
	private int size;
	private int top; //indica o topo da pilha
	private int tail; //indica o final da fila 
	
	public QueueStackArrayListImpl(int size) {
		this.array = new ArrayList<T>(size);
		this.size = size;
		this.top = -1;
		this.tail = -1;
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull())
			throw new StackOverflowException();
		this.array.add(++top, element);
	}

	@Override
	public T pop() throws StackUnderflowException {
		if (isEmpty())
			throw new StackUnderflowException();
		return this.array.remove(top--);
	}

	@Override
	public T top() {
		if (top <= -1)
			return null;
		return this.array.get(top);
	}

	@Override
	public boolean isEmpty() {
		return this.array.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.array.size() == size;
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()) 
			throw new QueueOverflowException();
		this.array.add(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if (isEmpty()) 
			throw new QueueUnderflowException();
		return this.array.remove(this.getHeadIndex());
	}

	@Override
	public T head() {
		if (this.getHeadIndex() >= this.array.size())
			return null;
		return this.array.get(this.getHeadIndex());
	}

	private int getHeadIndex() {
		return this.top + 1;
	}

}
