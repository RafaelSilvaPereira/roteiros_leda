package adt.splaytree;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> implements
		SplayTree<T> {

	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (!node.isEmpty()) {
			this.splay(node);
		} else {
			this.splay((BSTNode<T>) node.getParent());
		}
		return node;
	}
	
	/**
	 * BST is a set, so duplicates elements not acceptable.
	 */
	@Override
	public void insert(T element) {
		BSTNode<T> node = this.insert(this.root, new BSTNode<T>(), element);
		this.splay(node);
	}

	protected BSTNode<T> insert(BTNode<T> node, BTNode<T> parent, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
			return (BSTNode<T>) node;
		} else if (element.compareTo(node.getData()) < 0) {
			return this.insert(node.getLeft(), node, element);
		} else if (element.compareTo(node.getData()) > 0) {
			return this.insert(node.getRight(), node, element);
		}
		return new BSTNode<T>();
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = super.search(element);
		BSTNode<T> parent = (BSTNode<T>) node.getParent();
		if (!node.isEmpty()) {
			super.remove(node.getData());
		}
		this.splay(parent);
	}

	private void splay(BSTNode<T> node){
		if (node == null || node.isEmpty() || node == this.root)
			return ;
		BSTNode<T> parent = null;
		while (node != this.root && !node.isEmpty()) {
			parent = (BSTNode<T>) node.getParent();
			// caso 1: filho do root à esquerda
			if (node == this.root.getLeft()) {
				this.rightRotation(parent);
			// filho do root à direita
			} else if (node == this.root.getRight()) {
				this.leftRotation(parent);
			// caso 2: zig zig à esqueda
			} else if (parent.getParent().getLeft() == parent && parent.getLeft() == node) {
				this.rightRotation((BSTNode<T>) parent.getParent());
				this.rightRotation(parent);
			// zig zig à direita
			} else if (parent.getParent().getRight() == parent && parent.getRight() == node) {
				this.leftRotation((BSTNode<T>) parent.getParent());
				this.leftRotation(parent);
			// caso 3: zig zag
			} else if (parent.getParent().getLeft() == parent && parent.getRight() == node) {
				this.leftRotation(parent);
				this.rightRotation((BSTNode<T>) parent.getParent());
			} else if (parent.getParent().getRight() == parent && parent.getLeft() == node) {
				this.rightRotation(parent);
				this.leftRotation((BSTNode<T>) parent.getParent());
			} else {
				break;
			}
		}
	}
}
