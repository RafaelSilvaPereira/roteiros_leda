package adt.splaytree;

import org.junit.Test;

import adt.bst.BSTNode;

public class SplayTreeTest {

	@Test
	public void birl() {
		SplayTreeImpl<Integer> arvere = new SplayTreeImpl<>();
		Integer[] arr = { 1, 2, 4, 5, 9, 6 };

		for (Integer i : arr) {
			arvere.insert(i);
		}
		((BSTNode<?>) arvere.getRoot()).printTree();

		arvere.search(1);
		((BSTNode<?>) arvere.getRoot()).printTree();
	}
}
