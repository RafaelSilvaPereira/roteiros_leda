package adt.splaytree;

import adt.avltree.AVLTreeImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;

public class SplayTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T>
		implements SplayTree<T> {

	private void splay(BSTNode<T> node) {
		while (!node.equals(root)) {
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
			BSTNode<T> grandParent = (BSTNode<T>) node.getParent().getParent();
			if (parent.equals(root)) {
				if (node.equals(parent.getLeft())) {
					rightRotation(parent);
				} else {
					leftRotation(parent);
				}
			} else {
				if (node.equals(parent.getLeft())) {
					if (parent.equals(grandParent.getLeft())) {
						rightRotation(grandParent);
						rightRotation(parent);
					} else {
						rightRotation(parent);
						leftRotation(grandParent);

					}
				} else {
					if (parent.equals(grandParent.getLeft())) {
						leftRotation(parent);
						rightRotation(grandParent);
					} else {
						leftRotation(grandParent);
						leftRotation(parent);
					}
				}
			}

		}
	}

	@Override
	protected void insert(T element, BTNode<T> node) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.getLeft().setParent(node);

			node.setRight(new BSTNode<T>());
			node.getRight().setParent(node);

			if (node.getParent() == null) {
				BSTNode<T> father = new BSTNode<T>();
				node.setParent(father);
			}
			splay((BSTNode<T>) node);
		} else {
			if (node.getData().compareTo(element) == -1) {
				insert(element, node.getRight());
			} else {
				insert(element, node.getLeft());
			}
		}

	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = search(root, element);
		if (!node.isEmpty()) {
			if (node.getLeft().isEmpty() && node.getRight().isEmpty()) {
				node.setData(null);
				splay((BSTNode<T>) node.getParent());
			} else if (node.getLeft().isEmpty() || node.getRight().isEmpty()) {
				if (node.equals(root)) {
					if (!node.getLeft().isEmpty()) {
						root = (BSTNode<T>) node.getLeft();
					} else {
						root = (BSTNode<T>) node.getRight();
					}
					root.setParent(new BSTNode<T>());
				} else if (node.equals(node.getParent().getLeft())) {
					if (!node.getLeft().isEmpty()) {
						node.getParent().setLeft(node.getLeft());
						node.getLeft().setParent(node.getParent());
					} else {
						node.getParent().setLeft(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				} else {
					if (!node.getLeft().isEmpty()) {
						node.getParent().setRight(node.getLeft());
						node.getLeft().setParent(node.getParent());
					} else {
						node.getParent().setRight(node.getRight());
						node.getRight().setParent(node.getParent());
					}
				}
				if (!node.getParent().isEmpty()) {
					splay((BSTNode<T>) node.getParent());
				}

			} else {
				T sucessor = sucessor(element).getData();
				remove(sucessor);
				node.setData(sucessor);
			}
		}
	}

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = search(root, element);
		if (node.isEmpty()) {
			splay((BSTNode<T>) node.getParent());
		} else {
			splay(node);
		}
		return node;
	}
}
