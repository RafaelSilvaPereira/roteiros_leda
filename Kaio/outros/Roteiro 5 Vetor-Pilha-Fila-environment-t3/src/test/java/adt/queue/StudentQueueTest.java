package adt.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException{
		queue1 = new QueueImpl<Integer>(6);
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);
		
		queue2 = new QueueImpl<Integer>(2);
		queue2.enqueue(1);
		queue2.enqueue(2);
		
		queue3 = new QueueImpl<Integer>(6);
	}
	
	//MÉTODOS DE TESTE
	@Test
	public void testHead() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testIsEmpty() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testIsFull() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testEnqueue() {
		Assert.fail("Not implemented!");
	}
	
	@Test(expected=QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testDequeue() {
		Assert.fail("Not implemented!");
	}
	
	@Test(expected=QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		Assert.fail("Not implemented!");
	}
}