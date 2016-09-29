package adt.btree;

import static org.junit.Assert.*;
import org.junit.Test;

public class BTreeTest {
	
	@Test
	public void testAdd() {
		BTree<Integer> arvere = new BTree<>();
		
		ArrayList<T> 
		
		assertEquals(arvere.size(), 0);
		
		arvere.insert(1);
		assertEquals(arvere.size(), 1);
		assertNotNull(arvere.search(1));
		assertFalse(arvere.search(1).isEmpty());
		assertEquals(arvere.height(), 1);
		
		arvere.insert(2);
		assertEquals(arvere.size(), 2);
		assertNotNull(arvere.search(2));
		assertFalse(arvere.search(2).isEmpty());
		assertEquals(arvere.height(), 1);
		
		arvere.insert(0);
		assertEquals(arvere.size(), 3);
		assertNotNull(arvere.search(0));
		assertFalse(arvere.search(0).isEmpty());
		assertEquals(arvere.height(), 1);
		
		arvere.insert(4);
		assertEquals(arvere.size(), 4);
		assertNotNull(arvere.search(4));
		assertFalse(arvere.search(4).isEmpty());
		assertEquals(arvere.height(), 2);
		
		arvere.insert(5);
		assertEquals(arvere.size(), 5);
		assertEquals(arvere.height(), 2);
		
		arvere.insert(6);
		assertEquals(arvere.size(), 6);
		assertEquals(arvere.height(), 2);
	}
}
