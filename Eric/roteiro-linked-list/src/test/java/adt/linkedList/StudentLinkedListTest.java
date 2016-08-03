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
		lista1 = null;
		lista2 = null;
	}

	@Test
	public void testIsEmpty() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testSize() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testSearch() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testInsert() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testRemove() {
		Assert.fail("Not implemented!");
	}

	@Test
	public void testToArray() {
		Assert.fail("Not implemented!");
	}
}