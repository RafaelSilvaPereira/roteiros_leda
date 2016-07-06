package adt.rbtree;

import java.awt.Color;
import java.util.ArrayList;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T>
		implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight((RBNode<T>) root);

	}

	private int blackHeight(RBNode<T> node) {
		if (!node.isEmpty()) {
			int leftBlackHeight = blackHeight((RBNode<T>) node.getLeft());
			int rightBlackHeight = blackHeight((RBNode<T>) node.getRight());
			if (leftBlackHeight == rightBlackHeight) {
				if (node.getColour().equals(Colour.BLACK))
					return 1 + leftBlackHeight;
				else
					return leftBlackHeight;
			} else
				return -1;
		} else
			return 0;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() && verifyNILNodeColour()
				&& verifyRootColour() && verifyChildrenOfRedNodes()
				&& verifyBlackHeight();

		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
																// implemented
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenRedNodes((RBNode<T>) root);
	}

	private boolean verifyChildrenRedNodes(RBNode<T> node) {
		boolean correctChilds = true;
		boolean correctLeft = true;
		boolean correctRight = true;
		if (node.getColour().equals(Colour.RED))
			correctChilds = ((RBNode<T>) node.getLeft()).getColour().equals(
					Colour.BLACK)
					&& ((RBNode<T>) node.getRight()).getColour().equals(
							Colour.BLACK);
		if (!node.getLeft().isEmpty())
			correctLeft = verifyChildrenRedNodes((RBNode<T>) node.getLeft());
		if (!node.getRight().isEmpty())
			correctRight = verifyChildrenRedNodes((RBNode<T>) node.getRight());
		return correctChilds && correctLeft && correctRight;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (blackHeight() == -1) {
			throw new RuntimeException();
		} else {
			return true;
		}
	}

	@Override
	public void insert(T value) {
		insert(value, this.root);
	}

	protected void insert(T element, BTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<T>());
			node.getLeft().setParent(node);
			((RBNode<T>) node).setColour(Colour.RED);

			node.setRight(new RBNode<T>());
			node.getRight().setParent(node);

			if (node.getParent() == null) {
				RBNode<T> father = new RBNode<T>();
				node.setParent(father);
			}
			fixUpCase1((RBNode<T>) node);

		} else {
			if (node.getData().compareTo(element) == -1) {
				insert(element, node.getRight());
			} else {
				insert(element, node.getLeft());
			}

		}

	}

	@Override
	public RBNode<T>[] extendedPreOrder() {

		ArrayList<RBNode<T>> list = new ArrayList<RBNode<T>>();
		preOrder((RBNode<T>) this.root, list);
		RBNode<T>[] array = (RBNode<T>[]) new RBNode[size()];
		return (RBNode<T>[]) list.toArray(array);
	}

	public void preOrder(RBNode<T> no, ArrayList<RBNode<T>> array) {
		if (!no.isEmpty()) {
			array.add(no);
			preOrder((RBNode<T>) no.getLeft(), array);
			preOrder((RBNode<T>) no.getRight(), array);
		}
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(root)) {
			node.setColour(Colour.BLACK);
		} else {
			fixUpCase2(node);
		}

	}

	protected void fixUpCase2(RBNode<T> node) {
		if (((RBNode<T>) node.getParent()).getColour() == Colour.BLACK) {

		} else {
			fixUpCase3(node);
		}

	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> granParent = (RBNode<T>) parent.getParent();
		RBNode<T> uncle = null;
		if (parent.equals(granParent.getLeft()))
			uncle = (RBNode<T>) granParent.getRight();
		else
			uncle = (RBNode<T>) granParent.getLeft();
		if (uncle.getColour().equals(Colour.RED)) {
			parent.setColour(Colour.BLACK);
			uncle.setColour(Colour.BLACK);
			granParent.setColour(Colour.RED);
			fixUpCase1(granParent);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> next = node;
		RBNode<T> parent = (RBNode<T>) node.getParent();
		if (node.equals(parent.getRight())
				&& parent.equals(parent.getParent().getLeft())) {
			leftRotation(parent);
			next = (RBNode<T>) node.getLeft();
		} else if (node.equals(parent.getLeft())
				&& parent.equals(parent.getParent().getRight())) {
			rightRotation(parent);
			next = (RBNode<T>) node.getRight();
		}
		fixUpCase5(next);

	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = (RBNode<T>) node.getParent();
		RBNode<T> father = (RBNode<T>) node.getParent().getParent();

		parent.setColour(Colour.BLACK);
		father.setColour(Colour.RED);
		if (node.equals(parent.getLeft())) {
			rightRotation(father);
		} else {
			leftRotation(father);
		}

	}
}
