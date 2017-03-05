package adt.bst;

import static org.junit.Assert.*;

import org.junit.Test;

import adt.bt.BTNode;

public class BSTVerifierTest {
	
	@Test
	public void simpleTest() {
		BST<Integer> arvere = new BSTImpl<>();
		BSTVerifier<Integer> v = new BSTVerifierImpl<Integer>(arvere);
		
		assertTrue(v.isBST());
		arvere.insert(0);
		assertTrue(v.isBST());
		arvere.insert(-5);
		assertTrue(v.isBST());
		arvere.insert(5);
		assertTrue(v.isBST());
		
		arvere.insert(10);
		arvere.insert(15);
		arvere.insert(20);
		arvere.insert(25);
		arvere.insert(30);
		assertTrue(v.isBST());
	}
	
	@Test
	public void testsFailure() {
		BST<Integer> arvere = new BSTImpl<>();
		BSTVerifier<Integer> v = new BSTVerifierImpl<Integer>(arvere);
		
		arvere.insert(0);
		arvere.insert(5);
		arvere.insert(-5);
		assertTrue(v.isBST());
		
		BTNode<Integer> node = arvere.search(5);
		BSTNode<Integer> smallAux = new BSTNode<Integer>();
		smallAux.setData(new Integer(-1000));
		BSTNode<Integer> greaterAux = new BSTNode<Integer>();
		greaterAux.setData(new Integer(1000));
		
		node.setLeft(greaterAux);
		assertFalse(v.isBST());
		
		node.setLeft(new BSTNode<Integer>());
		assertTrue(v.isBST());
		
		arvere.getRoot().setLeft(new BSTNode<Integer>());
		arvere.getRoot().setRight(new BSTNode<Integer>());
		assertTrue(v.isBST());
		
		node.setRight(smallAux);
		assertTrue(v.isBST());
	}
}
