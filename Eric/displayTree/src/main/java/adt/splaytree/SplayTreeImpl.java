package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T> {

	@Override
	public BSTNode<T> search(T element) {
		BSTNode<T> node = super.search(element);
		if (!node.isEmpty())
			birl(node);
		return node;
	}
	
	@Override
	public void insert(T element) {
		BSTNode<T> added = super.insert(this.root, element);
		birl(added);
	}

	private void birl(BSTNode<T> node) {
		if (node.getParent() == null) {
			this.root = node;
			System.out.println("num vai dar nao");
		} else {
			if (isLeftChild(node)) {
				System.out.println("Girando o pai pa esquerda");
				Util.rightRotation(getParent(node));
			} else {
				Util.leftRotation(getParent(node));
			}
			System.out.println("Bora cumpadi");
			birl(node);
		}
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.getParent().getLeft().equals(node);
	}
}
