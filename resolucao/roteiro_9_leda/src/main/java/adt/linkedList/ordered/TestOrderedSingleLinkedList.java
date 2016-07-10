package adt.linkedList.ordered;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import adt.linkedList.LinkedList;


public class TestOrderedSingleLinkedList {

	private OrderedSingleLinkedList<Integer> lista1;
	private OrderedSingleLinkedList<Integer> lista2;
	
	@Before
	public void setUp() throws Exception {
		
		getImplementations();
		
		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);
		
	}
	
	private void getImplementations(){
		Comparator<Integer> ascedente = new Ascedente();
		Comparator<Integer> descedente = new Descedente();
				
		lista1 = new  OrderedSingleLinkedListImpl<Integer>(ascedente);
		lista2 = new  OrderedSingleLinkedListImpl<Integer>(ascedente);
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty());
		Assert.assertFalse(lista1.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
	}

	@Test
	public void testSearch() {
		Assert.assertEquals(3, lista1.search(3).intValue());
	}

	@Test
	public void testInsert() {
		lista2.insert(5);
		Assert.assertEquals(1,lista2.size() );
		Object[] array =  (Object[]) lista2.toArray();
		Assert.assertEquals(5, array[0]);
	}

	@Test
	public void testRemove() {
		lista1.remove(1);
		Assert.assertEquals(2,lista1.size() );
	}

	@Test
	public void testToArray() {
		Object[] array =  (Object[])(new Comparable[]{1,2,3});
		Assert.assertArrayEquals(array, lista1.toArray());
	}
	
	@Test
	public void testMaximum() {
		Assert.assertEquals(3, lista1.maximum().intValue());
		Assert.assertEquals(null, lista2.maximum());

	}
	
	@Test
	public void testMinimum() {
		Assert.assertEquals(1, lista1.minimum().intValue());
		Assert.assertEquals(null, lista2.minimum());

	}
}
