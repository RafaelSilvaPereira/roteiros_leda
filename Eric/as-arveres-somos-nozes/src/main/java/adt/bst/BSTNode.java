package adt.bst;

import adt.bt.BTNode;

public class BSTNode<T extends Comparable<T>> extends BTNode<T> {

	public BSTNode() {
		super();
	}

	public BSTNode(BSTNode<T> parent) {
		super(null, null, null, parent);
	}

	public void add(T data) {
		if (data != null) {
			if (isEmpty()) {
				setData(data);
				setRight(new BSTNode<T>(this));
				setLeft(new BSTNode<T>(this));
			} else if (data.compareTo(getData()) > 0) {
				getRight().add(data);
			} else if (data.compareTo(getData()) < 0) {
				getLeft().add(data);
			}
		}
	}

	public int getThisHeight() {
		int underThis = 0;
		if (!isEmpty()) {
			int rightHeight = getRight().getThisHeight();
			int leftHeight = getLeft().getThisHeight();
			underThis = 1 + Math.max(leftHeight, rightHeight);
		}
		return underThis;
	}
	
	public BSTNode<T> search(T element) {
		BSTNode<T> result;
		if (isEmpty() || getData().equals(element)) {
			result = this;
		} else if (getData().compareTo(element) > 0) {
			result = getRight().search(element);
		} else {
			result = getLeft().search(element);
		}
		return result;
	}

	@Override
	public BSTNode<T> getRight() {
		return (BSTNode<T>) super.getRight();
	}

	@Override
	public BSTNode<T> getLeft() {
		return (BSTNode<T>) super.getLeft();
	}
}
