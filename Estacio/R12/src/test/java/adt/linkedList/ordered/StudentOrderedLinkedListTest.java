package adt.linkedList.ordered;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StudentOrderedLinkedListTest {

	private OrderedLinkedList<Integer> lista1;
	private OrderedLinkedList<Integer> lista2;

	@Before
	public void setUp() throws Exception {

		getImplementations();

		// Lista com 3 elementos.
		lista1.insert(3);
		lista1.insert(2);
		lista1.insert(1);

	}

	private void getImplementations() {
		// TODO O aluno deve ajustar aqui para instanciar sua implementação
		Comparator<Integer> comparador = (a, b) -> a.compareTo(b);
		lista1 = new OrderedDoubleLinkedListImpl<>();
		lista2 = new OrderedDoubleLinkedListImpl<>();
	}

	@Test
	public void testDoubleImpl() {

		OrderedDoubleLinkedListImpl<Integer> list = new OrderedDoubleLinkedListImpl<>((a, b) -> b.compareTo(a));
		Assert.assertEquals(0, list.size());
		Assert.assertTrue(list.isEmpty());

		list.insertFirst(1);
		Assert.assertEquals(1, list.size());
		Assert.assertFalse(list.isEmpty());

		list.insertFirst(2);
		Assert.assertEquals(2, list.size());
		Assert.assertNotNull(list.search(2));
		Assert.assertNotNull(list.search(1));
		Assert.assertNull(list.search(0));

		Assert.assertEquals(new Integer(2), list.minimum());
		Assert.assertEquals(new Integer(1), list.maximum());

		list.insertFirst(3);
		Assert.assertEquals(3, list.size());

		list.insertFirst(10);
		Assert.assertEquals(4, list.size());

		list.insertFirst(4);
		Assert.assertEquals(4, list.size());

		list.removeFirst();
		Assert.assertEquals(3, list.size());

		list.insertFirst(4);
		Assert.assertEquals(4, list.size());

		list.removeFirst();
		Assert.assertEquals(3, list.size());

		list.removeFirst();
		Assert.assertEquals(2, list.size());

		list.removeFirst();
		Assert.assertEquals(1, list.size());

		list.removeFirst();
		Assert.assertEquals(0, list.size());

		list.removeFirst();
		Assert.assertEquals(0, list.size());

		list.insert(10);
		list.insert(12);
		list.insert(14);
		list.insert(16);
		list.insert(18);
		list.insert(20);
		list.insert(22);
		list.insert(24);

		Assert.assertEquals(new Integer(24), list.minimum());
		Assert.assertEquals(new Integer(10), list.maximum());
		list.removeLast();
		Assert.assertEquals(new Integer(12), list.maximum());
		Assert.assertNull(list.search(10));

		list.remove(16);
		Assert.assertNull(list.search(16));

		Assert.assertEquals(6, list.size());

		Integer[] array = {24, 22, 20, 18, 14, 12};
		Assert.assertArrayEquals(array, list.toArray());
		list.setComparator((a, b) -> a.compareTo(b));

		Arrays.sort(array);
		Assert.assertArrayEquals(array, list.toArray());
	}
	@Test
	public void testIsEmpty() {
		Assert.assertFalse(lista1.isEmpty());
		Assert.assertTrue(lista2.isEmpty());
	}

	@Test
	public void testSize() {
		Assert.assertEquals(3, lista1.size());
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testSearch() {
		Assert.assertTrue(2 == lista1.search(2));
		Assert.assertNull(lista1.search(4));
		Assert.assertFalse(3 == lista1.search(2));
	}

	@Test
	public void testInsert() {
		Assert.assertEquals(3, lista1.size());
		lista1.insert(5);
		lista1.insert(7);
		Assert.assertEquals(5, lista1.size());

		Assert.assertEquals(0, lista2.size());
		lista2.insert(4);
		lista2.insert(7);
		Assert.assertEquals(2, lista2.size());
	}

	@Test
	public void testRemove() {
		Assert.assertEquals(3, lista1.size());
		lista1.remove(2);
		lista1.remove(1);
		Assert.assertEquals(1, lista1.size());

	}

	@Test
	public void testToArray() {
		Assert.assertArrayEquals(new Integer[] {}, lista2.toArray());
		Assert.assertArrayEquals(new Integer[] { 1, 2, 3 }, lista1.toArray());
	}
	
	@Test
	public void testMinimum() {
		Assert.assertNull(lista2.minimum());
		Assert.assertEquals(new Integer(1), lista1.minimum());
	}
	@Test
	public void testMaximum() {
		Assert.assertNull(lista2.maximum());
		Assert.assertEquals(new Integer(3), lista1.maximum());
	}
}