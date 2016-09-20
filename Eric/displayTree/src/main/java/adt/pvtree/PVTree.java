package adt.pvtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class PVTree<T extends Comparable<T>> extends BSTImpl<T> {

	public PVTree() {
		this.root = new PVNode<T>();
	}

	@Override
	public void insert(T element) {
		if (element != null)
			insert(this.root, element);
	}

	protected BSTNode<T> insert(BSTNode<T> nodeAt, T element) {
		BSTNode<T> added = nodeAt;
		if (nodeAt.isEmpty()) {
			nodeAt.setData(element);
			nodeAt.setLeft(new PVNode<T>());
			nodeAt.getLeft().setParent(nodeAt);
			nodeAt.setRight(new PVNode<T>());
			nodeAt.getRight().setParent(nodeAt);
			fixUpCaseOne(thisNode(nodeAt));
		} else {
			int comparation = nodeAt.getData().compareTo(element);
			if (comparation == GREATER)
				added = insert(getLeft(nodeAt), element);
			else if (comparation == LESSER)
				added = insert(getRight(nodeAt), element);
		}
		return added;
	}

	private void fixUpCaseOne(PVNode<T> node) {
		if (node.equals(getRoot())) {
			node.setCor(Color.BLACK);
		} else {
			fixUpCaseTwo(node);
		}
	}

	private void fixUpCaseTwo(PVNode<T> node) {
		if (getParent(node).getCor() != Color.BLACK) {
			fixUpCaseThree(node);
		}
	}

	private void fixUpCaseThree(PVNode<T> node) {
		PVNode<T> parent = getParent(node);
		PVNode<T> parentParent = getParent(parent);
		PVNode<T> aux;
		if (isLeftChild(parent)) {
			aux = thisNode(parentParent.getRight());
		} else {
			aux = thisNode(parentParent.getLeft());
		}
		if (aux.getCor() == Color.RED) {
			parent.setCor(Color.BLACK);
			aux.setCor(Color.BLACK);
			parentParent.setCor(Color.RED);
			fixUpCaseOne(parentParent);
		} else {
			boolean isZigZag = !(isLeftChild(node) && isLeftChild(parent)
					|| !isLeftChild(node) && !isLeftChild(parent));
			fixUpCaseFour(node, isZigZag);
		}
	}

	private void fixUpCaseFour(PVNode<T> node, boolean isZigZag) {
		if (isZigZag) {
			PVNode<T> parent = getParent(node);
			if (isLeftChild(node)) {
				rightRotation(getParent(node));
			} else {
				leftRotation(getParent(node));
			}
			fixUpCaseFive(parent);
		} else {
			fixUpCaseFive(node);
		}
	}

	private void fixUpCaseFive(PVNode<T> node) {
		PVNode<T> parent = getParent(node);
		PVNode<T> parentParent = getParent(parent);
		parent.setCor(Color.BLACK);
		parentParent.setCor(Color.RED);

		if (isLeftChild(node)) {
			rightRotation(parentParent);
		} else {
			leftRotation(parentParent);
		}
	}

	protected void leftRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.leftRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	protected void rightRotation(BSTNode<T> node) {
		BSTNode<T> newNode = Util.rightRotation(node);
		if (newNode.getParent() == null) {
			root = newNode;
		}
	}

	@Override
	public PVNode<T> getRoot() {
		return (PVNode<T>) root;
	}

	@Override
	protected PVNode<T> getParent(BSTNode<T> node) {
		return (PVNode<T>) node.getParent();
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.equals(getParent(node).getLeft());
	}

	private PVNode<T> thisNode(BSTNode<T> node) {
		return (PVNode<T>) node;
	}
}
