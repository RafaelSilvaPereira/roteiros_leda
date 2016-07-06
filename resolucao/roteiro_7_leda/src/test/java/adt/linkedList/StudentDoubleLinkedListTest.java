package adt.linkedList;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentDoubleLinkedListTest {
	
	private DoubleLinkedList<Integer> lista1;
	private DoubleLinkedList<Integer> lista2;
	private DoubleLinkedList<Integer> lista3;
	
	@Before
	public void setUp() throws Exception {

		getImplementations();
		
		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

		
		// Lista com 1 elemento.
		lista3.insert(1);
	}
	
	private void getImplementations(){
		//TODO O aluno deve ajustar aqui para instanciar sua implementação
		lista1 = new RecursiveDoubleLinkedListImpl<>();
		lista2 = new RecursiveDoubleLinkedListImpl<>();
		lista3 = new RecursiveDoubleLinkedListImpl<>();
	}

	@Test
	public void testIsEmpty() {
	    Assert.assertTrue(lista2.isEmpty());
	    Assert.assertFalse(lista1.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(0, lista2.size());
		Assert.assertEquals(3, lista1.size());
	}

	@Test
	public void testSearch() {
		Assert.assertEquals(2, lista1.search(2).intValue());
	}

	@Test
	public void testInsert() {
		lista2.insert(2);
		Object[] array =  (Object[]) lista2.toArray();
		Assert.assertEquals(2, array[0]);
		
	}

	@Test
	public void testRemove() {
		lista1.remove(2);
		Assert.assertEquals(2, lista1.size());
	}

	@Test
	public void testToArray() {
		Object[] array =  (Object[]) lista3.toArray();
		Assert.assertEquals(1, array[0]);
	}
	
	// Métodos de DoubleLinkedList
	
	@Test
	public void testInsertFirst(){
		lista1.insertFirst(2);
		Object[] array =  (Object[]) lista1.toArray();
		Assert.assertEquals(2, array[0]);
	}

	@Test
	public void testRemoveFirst(){
		lista1.removeFirst();
		Object[] array =  (Object[]) lista1.toArray();
		Assert.assertEquals(2, array[0]);
	}
	
	@Test
	public void testRemoveLast(){
		lista1.removeLast();
		Object[] array =  (Object[]) lista1.toArray();
		Assert.assertEquals(null, array[1]);
	}
}