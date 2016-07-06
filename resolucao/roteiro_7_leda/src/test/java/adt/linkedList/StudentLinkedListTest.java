package adt.linkedList;

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
		lista1 = new RecursiveSingleLinkedListImpl<>();
		lista2 = new RecursiveSingleLinkedListImpl<>();
	}

	@Test
	public void testIsEmpty() {
		Assert.assertTrue(lista2.isEmpty());
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
		Assert.assertEquals(1, lista2.size());
	}

	@Test
	public void testRemove() {
		lista2.remove(5);
		Assert.assertEquals(0, lista2.size());
	}

	@Test
	public void testToArray() {
		Object[] array =  (Object[]) lista1.toArray();
		Assert.assertEquals(3, array[0]);
	}
}