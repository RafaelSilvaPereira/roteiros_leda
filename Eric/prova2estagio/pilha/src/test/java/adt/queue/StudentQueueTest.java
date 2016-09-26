package adt.queue;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentQueueTest {

	public Queue<Integer> queue1;
	public Queue<Integer> queue2;
	public Queue<Integer> queue3;

	@Before
	public void setUp() throws QueueOverflowException {
		queue1 = new QueueUsingStack<Integer>(6);
		queue1.enqueue(1);
		queue1.enqueue(2);
		queue1.enqueue(3);

		queue2 = new QueueUsingStack<Integer>(2);
		queue2.enqueue(1);
		queue2.enqueue(2);

		queue3 = new QueueUsingStack<Integer>(6);
	}

	// MÉTODOS DE TESTE
	@Test
	public void testNegativeSize() {
		Queue<Integer> q = new QueueImpl<>(0);
		Assert.assertTrue(q.isEmpty());
		Assert.assertTrue(q.isFull());
		try {
			q.enqueue(1);
			Assert.fail();
		} catch (QueueOverflowException e) {
		}
		try {
			q.dequeue();
			Assert.fail();
		} catch (QueueUnderflowException e) {
		}
		
		q = new QueueImpl<>(-5);
		Assert.assertTrue(q.isEmpty());
		Assert.assertTrue(q.isFull());
		try {
			q.enqueue(1);
			Assert.fail();
		} catch (QueueOverflowException e) {
		}
		try {
			q.dequeue();
			Assert.fail();
		} catch (QueueUnderflowException e) {
		}
	}
	
	@Test
	public void testHead() throws QueueUnderflowException {
		Assert.assertTrue(queue1.head().equals(new Integer(1)));
		Assert.assertTrue(queue2.head().equals(new Integer(1)));

		queue1.dequeue();
		Assert.assertTrue(queue1.head().equals(new Integer(2)));

		queue1.dequeue();
		Assert.assertTrue(queue1.head().equals(new Integer(3)));
	}

	@Test
	public void testIsEmpty() throws QueueUnderflowException {
		Assert.assertTrue(queue3.isEmpty());
		Assert.assertFalse(queue2.isEmpty());
		Assert.assertFalse(queue1.isEmpty());

		queue2.dequeue();
		Assert.assertFalse(queue2.isEmpty());
		queue2.dequeue();
		Assert.assertTrue(queue2.isEmpty());
	}

	@Test
	public void testIsFull() throws QueueOverflowException, QueueUnderflowException {
		Assert.assertFalse(queue1.isFull());
		Assert.assertTrue(queue2.isFull());
		Assert.assertFalse(queue3.isFull());

		queue1.enqueue(4);
		Assert.assertFalse(queue1.isFull());
		queue1.enqueue(5);
		Assert.assertFalse(queue1.isFull());
		queue1.enqueue(6);
		Assert.assertTrue(queue1.isFull());

		queue2.dequeue();
		Assert.assertFalse(queue2.isFull());
	}

	@Test
	public void testEnqueue() throws QueueOverflowException {
		Assert.assertNull(queue3.head());

		queue3.enqueue(1);
		Assert.assertTrue(queue3.head().equals(1));

		queue3.enqueue(2);
		Assert.assertTrue(queue3.head().equals(1));
	}

	@Test(expected = QueueOverflowException.class)
	public void testEnqueueComErro() throws QueueOverflowException {
		queue2.enqueue(10);
	}

	@Test
	public void testDequeue() throws QueueUnderflowException {
		Assert.assertTrue(queue1.dequeue().equals(1));

		Assert.assertTrue(queue2.dequeue().equals(1));
		Assert.assertTrue(queue2.dequeue().equals(2));
	}

	@Test(expected = QueueUnderflowException.class)
	public void testDequeueComErro() throws QueueUnderflowException {
		queue3.dequeue();
	}
}
