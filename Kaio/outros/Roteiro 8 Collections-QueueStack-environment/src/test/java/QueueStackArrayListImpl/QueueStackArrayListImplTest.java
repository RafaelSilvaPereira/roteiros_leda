package QueueStackArrayListImpl;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.queue.QueueOverflowException;
import adt.queue.QueueUnderflowException;
import adt.queueStack.QueueStackArrayListImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueStackArrayListImplTest {

	@Test
	public void test() throws Exception {
		
		QueueStackArrayListImpl<Integer> q = new QueueStackArrayListImpl<>(5);
		assertTrue(q.isEmpty());
		assertEquals(null, q.top());
		assertEquals(null, q.head());
		// [][]
		
		q.push(10);
		assertFalse(q.isEmpty());
		assertEquals(10, q.top().intValue());
		assertEquals(null, q.head());
		// [10][]
		
		q.push(20);
		assertEquals(20, q.top().intValue());
		assertEquals(null, q.head());
		// [10, 20][]
		
		assertEquals(20, q.pop().intValue());
		assertEquals(10, q.top().intValue());
		assertEquals(null, q.head());
		// [10][]
		
		q.enqueue(200);
		assertEquals(10, q.top().intValue());
		assertEquals(200, q.head().intValue());
		// [10][200]
		
		q.enqueue(300);
		assertEquals(10, q.top().intValue());
		assertEquals(200, q.head().intValue());
		// [10][200, 300]
		
		q.push(40);
		assertEquals(40, q.top().intValue());
		assertEquals(200, q.head().intValue());
		// [10, 40][200, 300]
		
		assertEquals(200, q.dequeue().intValue());
		assertEquals(300, q.head().intValue());
		assertEquals(40, q.top().intValue());
		// [10, 40][300]
		
		assertEquals(40, q.pop().intValue());
		assertEquals(10, q.pop().intValue());
		assertEquals(null, q.top());
		assertEquals(300, q.dequeue().intValue());
		// [][]
		
		try {
			q.dequeue();
			fail("Deveria gerar QueueUnderflowException");
		} catch (QueueUnderflowException que) {
		}
		
		try {
			q.pop();
			fail("Deveria gerar StackUnderflowException");
		} catch (StackUnderflowException sue) {
		}
		
		try {
			for (int i = 0; i <= 5; i++) {
				q.push(i);
			}
			fail("Deveria gerar StackOverflowException");
		} catch (StackOverflowException soe) {
		}
		
		try {
			q.enqueue(10);
			fail("Deveria gerar QueueOverflowException");
		} catch (QueueOverflowException qoe) {
		}
	}
	
}
