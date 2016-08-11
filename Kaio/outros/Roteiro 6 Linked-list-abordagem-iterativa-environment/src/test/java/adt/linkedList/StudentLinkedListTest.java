package adt.linkedList;

import static org.junit.Assert.*;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentLinkedListTest {
	
	private LinkedList<Integer> lista1;
	private LinkedList<Integer> lista2;
	
	@Before
	public void setUp() throws Exception {
		
		getImplementations();
		
		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);
		
	}
	
	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new SingleLinkedListImpl<Integer>();
		lista2 = new SingleLinkedListImpl<Integer>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty());
		Assert.assertFalse(lista1.isEmpty());
	}

	@Test
	public void testSize() {
		assertEquals(3, lista1.size());
		assertEquals(0, lista2.size());
		
	}

	@Test
	public void testSearch() {
		assertEquals(null, lista2.search(9));
		assertEquals((Integer)3, lista1.search(3));
		assertEquals(null, lista1.search(9));
		assertEquals(null, lista2.search(3));
	}

	@Test
	public void testInsert() {
		assertEquals(null, lista2.search(9));
		lista2.insert(9);
		assertEquals((Integer)9, lista2.search(9));
	}

	@Test
	public void testRemove() {
		int sizeExpect = lista1.size();
		lista1.remove(100);
		assertEquals(sizeExpect, lista1.size());
		
		assertEquals((Integer)3, lista1.search(3));
		lista1.remove(3);
		assertEquals(null, lista1.search(3));
		assertEquals(sizeExpect-1, lista1.size());
		
		sizeExpect = lista2.size();
		lista2.remove(4);
		assertEquals(sizeExpect, lista2.size());
	}

	@Test
	public void testToArray() {
		Integer[] expected = new Integer[]{3,2,1};
		assertArrayEquals(expected, lista1.toArray());
		
		expected = new Integer[0];
		assertArrayEquals(expected, lista2.toArray());
		
	}
}