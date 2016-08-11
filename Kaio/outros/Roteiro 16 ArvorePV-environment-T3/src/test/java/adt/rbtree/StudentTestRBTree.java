package adt.rbtree;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.Before;

import adt.rbtree.RBNode.Colour;

public class StudentTestRBTree {

	public RBTreeImpl<Integer> myRB;
	
	@Before
	public void initialize() {
		myRB = new RBTreeImpl<Integer>();
	}
	
	@Test
	public void testInsert0() {
		Integer[] preOrder = {11};
		Colour[] preOrderColour = {Colour.BLACK};
		
		myRB.insert(11);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	public void testInsert1() {
		Integer[] preOrder = {11, 2};
		Colour[] preOrderColour = {	Colour.BLACK, 
									Colour.RED};
		
		myRB.insert(11);
		myRB.insert(2);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	public void testInsert2() {
		Integer[] preOrder = {11, 2, 14};
		Colour[] preOrderColour = {	Colour.BLACK, 
									Colour.RED, 
									Colour.RED};
		
		myRB.insert(11);
		myRB.insert(2);
		myRB.insert(14);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	public void testInsert3() {
		Integer[] preOrder = {11, 2, 1, 14};
		Colour[] preOrderColour = {	Colour.BLACK, 
									Colour.BLACK, 
									Colour.RED, 
									Colour.BLACK};

		myRB.insert(11);
		myRB.insert(2);
		myRB.insert(14);
		myRB.insert(1);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	public void testInsert4() {
		Integer[] preOrder = {11, 2, 1, 14, 15};
		Colour[] preOrderColour = {	Colour.BLACK, 
									Colour.BLACK, 
									Colour.RED, 
									Colour.BLACK, 
									Colour.RED};

		myRB.insert(11);
		myRB.insert(2);
		myRB.insert(14);
		myRB.insert(1);
		myRB.insert(15);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	public void testInsert5() {
		Integer[] preOrder = {11, 2, 1, 7, 14, 15};
		Colour[] preOrderColour = {	Colour.BLACK, 
									Colour.BLACK, 
									Colour.RED, 
									Colour.RED, 
									Colour.BLACK, 
									Colour.RED};

		myRB.insert(11);
		myRB.insert(2);
		myRB.insert(14);
		myRB.insert(1);
		myRB.insert(15);
		myRB.insert(7);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	public void testInsert6() {
		Integer[] preOrder = {11, 2, 1, 7, 5, 14, 15};
		Colour[] preOrderColour = {	Colour.BLACK, 
									Colour.RED, 
									Colour.BLACK, 
									Colour.BLACK, 
									Colour.RED, 
									Colour.BLACK, 
									Colour.RED};

		myRB.insert(11);
		myRB.insert(2);
		myRB.insert(14);
		myRB.insert(1);
		myRB.insert(15);
		myRB.insert(7);
		myRB.insert(5);
		
		int len = myRB.size();
		RBNode<Integer>[] result = myRB.extendedPreOrder();
		for (int i = 0 ; i < len ; i++) {
			assertEquals(preOrder[i], result[i].getData());
			assertEquals(preOrderColour[i], result[i].getColour());
		}
		assertTrue(myRB.verifyProperties());
	}
	
	/**
	 * Testes além
	 */
	
	@Test
	public void testHeight() {
		assertEquals(0, myRB.blackHeight());
		myRB.insert(10);
		myRB.insert(9);
		myRB.insert(11);
		myRB.insert(12);
		/*				10p
		 * 		9p|>>>>>><<<<<<|11p
		 * 	p|>>><<<|p		p|>>><<<|12v
		 * 						  p|>><<|p
		 */
		assertEquals(2, myRB.blackHeight());
		assertTrue(myRB.verifyProperties());
	}
	
	@Test
	/**
	 * @author Anarco Quaresma in 
	 *  - https://github.com/anarcoqr/RBTrees/blob/master/src/test/java/adt/rbtree/StudentTestRBTree.java
	 */
	public void testHeightAnarco(){
		final int MIN_VALUE = 50;
		final int MAX_VALUE = 100;
		assertEquals(0, myRB.size());
		for(int i = 1, j = MAX_VALUE; i <= MIN_VALUE; i++,j--){
			myRB.insert(i);
			myRB.insert(j);
			assertEquals(i*2, myRB.size());
			assertTrue(myRB.height() <= 2*logBase2(myRB.size()));
		}
		assertTrue(myRB.verifyProperties());
	}
	
	private double logBase2(int num){
		return (Math.log10(num)/Math.log10(2));
	}
	
}
