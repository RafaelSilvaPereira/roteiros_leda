package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements AVLTree<T> {

	private static final int ZERO = 0;
	private static final int UNBALANCED_RIGHT = 1;
	private static final int UNBALANCED_LEFT = -1;

	@Override
	public void remove(T element) {
		if (!isNull(element)) {
			BSTNode<T> node = search(element);
			node = super.remove(node);
			rebalanceUp(node);
		}
	}

	@Override
	public void insert(T element) {
		if (!isNull(element)) {
			BSTNode<T> node = super.insert(root, element);
			rebalanceUp(node);
		}
	}

	// AUXILIARY
	/**
	 * Calculates the balance of the node. Returns a positive integer if
	 * unbalanced to right or a negative integer if unbalanced to the left, zero
	 * otherwise.
	 */
	protected int calculateBalance(BSTNode<T> node) {
		return isNull(node) || node.isEmpty() ? ZERO : height(getRight(node)) - height(getLeft(node));
	}

	// AUXILIARY
	/**
	 * Rebalances the node, rotating it to left or right.
	 * 
	 * @param node
	 *            Node to be balanced.
	 */
	protected void rebalance(BSTNode<T> node) {
		int balance = calculateBalance(node);
		if (balance < UNBALANCED_LEFT) {
			if (calculateBalance(getLeft(node)) > ZERO) {
				leftRotation(getLeft(node));
			}
			rightRotation(node);
		} else if (balance > UNBALANCED_RIGHT) {
			if (calculateBalance(getRight(node)) < ZERO) {
				rightRotation(getRight(node));
			}
			leftRotation(node);
		}
	}

	// AUXILIARY
	/**
	 * Recursive method that balances all the nodes from this to the root.
	 * 
	 * @param node
	 *            Node to start the rebalance.
	 */
	protected void rebalanceUp(BSTNode<T> node) {
		if (!isNull(node)) {
			rebalance(node);
			rebalanceUp(getParent(node));
		}
	}

	// AUXILIARY
	/**
	 * Rotates the node to left.
	 * 
	 * @param node
	 *            Node to be rotated.
	 */
	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (isNull(newNode.getParent())) {
			root = newNode;
		}
	}

	// AUXILIARY
	/**
	 * Rotates the node to right.
	 * 
	 * @param node
	 *            Node to be rotated.
	 */
	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (isNull(newNode.getParent())) {
			root = newNode;
		}
	}
}