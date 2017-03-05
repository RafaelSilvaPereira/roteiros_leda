package adt.avltree;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.bst.BSTNode;
import adt.bst.BSTVerifier;
import adt.bst.BSTVerifierImpl;
import adt.bt.BTNode;

public class AVLVerifierTest {
	
	@Test
	public void failureTest() {
		AVLTree<Integer> arvere = new AVLTreeImpl<>();
		AVLTreeVerifier<Integer> av = new AVLTreeVerifierImpl<Integer>(arvere);
		BSTVerifier<Integer> v = new BSTVerifierImpl<Integer>(arvere);
		
		assertTrue(av.isAVLTree());
		assertTrue(v.isBST());
		arvere.insert(0);
		assertTrue(av.isAVLTree());
		assertTrue(v.isBST());
		arvere.insert(-5);
		assertTrue(av.isAVLTree());
		assertTrue(v.isBST());
		arvere.insert(-10);
		assertTrue(av.isAVLTree());
		assertTrue(v.isBST());
		
		arvere.insert(-15);
		assertTrue(av.isAVLTree());
		assertTrue(v.isBST());
		
		BTNode<Integer> aux = arvere.search(0);
		aux.getParent().setRight(new BSTNode<Integer>());
		assertFalse(av.isAVLTree());
		assertTrue(v.isBST());
		
	}

}
