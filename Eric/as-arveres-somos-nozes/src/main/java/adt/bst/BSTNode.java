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
		} else if (getData().compareTo(element) < 0) {
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

	public BSTNode<T> maximum() {
		BSTNode<T> result = null;
		if (!isEmpty()) {
			result = this;
		}
		if (result != null && !getRight().isEmpty()) {
			result = getRight().maximum();
		}
		return result;
	}

	public BSTNode<T> minimum() {
		BSTNode<T> result = null;
		if (!isEmpty()) {
			result = this;
		}
		if (result != null && !getLeft().isEmpty()) {
			result = getLeft().minimum();
		}
		return result;
	}

	public void remove(T element) {
		if (!this.isEmpty()) {
			int compareResult = this.data.compareTo(element);
			if (compareResult < 0) {
				this.getRight().remove(element);
			} else if (compareResult > 0) {
				this.getLeft().remove(element);
			} else {
				BSTNode<T> thisNewValue = getRight().minimum();
				BSTNode<T> parentOfRemoved = thisNewValue.getParent();
				BSTNode<T> child = thisNewValue.getRight();
				this.data = thisNewValue.getData();
				parentOfRemoved.setLeft(child);
				child.setParent(parentOfRemoved);
			}
		}
	}
	
	@Override
	public BSTNode<T> getParent() {
		return (BSTNode<T>) super.getParent();
	}
}
