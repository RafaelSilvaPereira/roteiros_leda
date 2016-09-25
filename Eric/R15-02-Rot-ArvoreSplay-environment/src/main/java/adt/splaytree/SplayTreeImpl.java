package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements SplayTree<T> {

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> result = super.search(element);
		if (result.isEmpty())
			splay(getParent(result));
		else
			splay(result);
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			BSTNode<T> added = insert(this.root, element);
			splay(added);
		}
	}

	@Override
	public void remove(T element) {
		// O professor ensinou que se deve fazer o splay
		// no nó acima do removido.
		BSTNode<T> nodeToRemove = super.search(element);
		BSTNode<T> nodeToSplay = getParent(nodeToRemove);
		if ((element != null) && (nodeToRemove != null))
			super.remove(nodeToRemove);
		splay(nodeToSplay);
	}

	/**
	 * Splays the current node. Checking first if the node has a parent then if
	 * it's in a zig-zag then making the two rotations.
	 * 
	 * @param node
	 *            Node to be splayed and get to the root of the tree.
	 */
	private void splay(BSTNode<T> node) {
		if (node != null && node.getParent() == null) {
			this.root = node;
		} else if (node != null) {
			boolean hasParentOfParent = getParent(getParent(node)) != null;
			if (isLeftChild(node)) {
				boolean isParentLeftChild = hasParentOfParent && isLeftChild(getParent(node));
				if (isParentLeftChild) {
					Util.rightRotation(getParent(getParent(node)));
				}
				Util.rightRotation(getParent(node));
				if (hasParentOfParent && !isParentLeftChild) {
					Util.leftRotation(getParent(node));
				}
			} else {
				boolean isParentRightChild = hasParentOfParent && !isLeftChild(getParent(node));
				if (isParentRightChild) {
					Util.leftRotation(getParent(getParent(node)));
				}
				Util.leftRotation(getParent(node));
				if (hasParentOfParent && !isParentRightChild) {
					Util.rightRotation(getParent(node));
				}
			}
			splay(node);
		}
	}

	/**
	 * Checks if the current node is a left child or not, assuming it has a
	 * parent.
	 * 
	 * @return True if the node is a left child.
	 */
	private boolean isLeftChild(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node);
	}
}
