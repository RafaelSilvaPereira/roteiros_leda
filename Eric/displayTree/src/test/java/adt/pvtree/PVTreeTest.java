package adt.pvtree;

import org.junit.Test;

public class PVTreeTest {
	
	@Test
	public void test() {
		PVTree<Integer> a = new PVTree<>();
		
		Integer[] n = new Integer[]{84,61,30,65,80,8,3,5,76,65};
		for (Integer i : n)
			a.insert(i);
		((PVNode<?>) a.getRoot()).printTree();
	}
}
