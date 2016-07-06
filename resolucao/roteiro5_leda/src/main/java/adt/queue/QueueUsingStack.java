package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;
	
	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}
	
	@Override
	public void enqueue(T element) throws QueueOverflowException {
		try {
			stack1.push(element);
		} catch (StackOverflowException e) {
			throw new QueueOverflowException();
		}
		

	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		try{
		   stackFor2();
		   T elemento = stack2.pop();
		   stackFor1();
		   return elemento;
		}catch(StackUnderflowException e){
			throw new  QueueUnderflowException();
			
		}
	}

	private void stackFor2(){
		while (!stack1.isEmpty()){
			try{
				stack2.push(stack1.pop());
			}catch(Exception e){
				
			}
		}
	}
	
	private void stackFor1(){
		while (!stack2.isEmpty()){
			try{
				stack1.push(stack2.pop());
			}catch(Exception e){
				
			}
		}
	}
	@Override
	public T head() {
		stackFor2();
		T elemento = stack2.top();
		stackFor1();
		return elemento;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
