package QueueStackArrayListImpl;
import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Test;

public class ArrayListTest {

	@Test
	public void addTest() throws Exception {
		// podemos inserir quando o final � igual ao atual tamanho da lista?
		ArrayList<Integer> a = new ArrayList<>();
		assertEquals(0, a.size());
		a.add(0, 2);
		assertEquals(1, a.size());
		a.add(1, 3);
		assertEquals(2, a.size());
		Integer[] expect = {2,3};
		assertArrayEquals(expect, a.toArray());
		// sim! podemos!
	}
	
	@Test
	public void sizeTest() throws Exception {
		ArrayList<Integer> a = new ArrayList<>(30);
		assertEquals(0, a.size());
	}
	
}
