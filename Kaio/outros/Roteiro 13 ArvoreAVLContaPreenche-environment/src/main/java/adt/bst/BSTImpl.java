package adt.bst;

import java.util.ArrayList;
import java.util.List;

import adt.bt.BTNode;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return this.height(this.root);
	}

	protected int height(BTNode<T> node) {
		if (node.isEmpty())
			return -1;
		return Math.max(this.height(node.getLeft()), this.height(node.getRight())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return this.search(element, this.root);
	}

	private BSTNode<T> search(T element, BSTNode<T> node) {
		if (node.isEmpty())
			return new BSTNode<T>();
		if (element.compareTo(node.getData()) < 0)
			return this.search(element, (BSTNode<T>) node.getLeft());
		if (element.compareTo(node.getData()) > 0)
			return this.search(element, (BSTNode<T>) node.getRight());
		else
			return node;
	}

	/**
	 * BST is a set, so duplicates elements not acceptable.
	 */
	@Override
	public void insert(T element) {
		this.insert(element, this.root, new BSTNode<T>());
	}

	protected void insert(T element, BTNode<T> node, BTNode<T> parent) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new BSTNode<T>());
			node.setRight(new BSTNode<T>());
			node.setParent(parent);
		} else if (element.compareTo(node.getData()) < 0) {
			this.insert(element, node.getLeft(), node);
		} else if (element.compareTo(node.getData()) > 0) {
			this.insert(element, node.getRight(), node);
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return this.maximum(this.root);
	}

	private BSTNode<T> maximum(BTNode<T> node) {
		if (node.isEmpty())
			return null;
		while (!node.getRight().isEmpty()) 
			node = node.getRight();
		return (BSTNode<T>) node;
	}

	@Override
	public BSTNode<T> minimum() {
		return this.minimum(this.root);
	}

	private BSTNode<T> minimum(BTNode<T> node) {
		if (node.isEmpty())
			return null;
		while (!node.getLeft().isEmpty()) 
			node = node.getLeft();
		return (BSTNode<T>) node;
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty())
			return null;
		if (!node.getRight().isEmpty())
			return this.minimum(node.getRight());
		if (node.getParent().getLeft() == node)
			return (BSTNode<T>) node.getParent();
		while (!node.isEmpty() && this.isSonRight(node, node.getParent()))
			node = (BSTNode<T>) node.getParent();
		if (node == this.root)
			return null;
		return (BSTNode<T>) node.getParent();
	}

	private boolean isSonRight(BTNode<T> node, BTNode<T> parent) {
		if (node == null || node.isEmpty())
			return false;
		return parent.getRight() == node;
	}
	
	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = this.search(element);
		if (node.isEmpty())
			return null;
		if (!node.getLeft().isEmpty())
			return this.maximum(node.getLeft());
		if (node.getParent().getRight() == node)
			return (BSTNode<T>) node.getParent();
		while (!node.isEmpty() && this.isSonLeft(node, node.getParent()))
			node = (BSTNode<T>) node.getParent();
		if (node == this.root)
			return null;
		return (BSTNode<T>) node.getParent();
	}
	
	protected boolean isSonLeft(BTNode<T> node, BTNode<T> parent) {
		if (node == null || node.isEmpty())
			return false;
		return parent.getLeft() == node;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> node = this.search(element);
		this.remove(node);
	}

	protected void remove(BSTNode<T> node) {
		if (node == null || node.isEmpty())
			return;
		if (this.isSheet(node)) {
			if (node == this.root)
				this.root = new BSTNode<T>();
			else if (this.isSonLeft(node, node.getParent()))
				node.getParent().setLeft(new BSTNode<T>());
			else
				node.getParent().setRight(new BSTNode<T>());
		} else if (this.justRightSon(node)) {
			if (node == this.root)
				this.root = (BSTNode<T>) node.getRight();
			else {
				if (this.isSonLeft(node, node.getParent()))
					node.getParent().setLeft(node.getRight());
				else
					node.getParent().setRight(node.getRight());
				node.getRight().setParent(node.getParent());
			}
		} else if (this.justLeftSon(node)) {
			if (node == this.root)
				this.root = (BSTNode<T>) node.getLeft();
			else {
				if (this.isSonLeft(node, node.getParent()))
					node.getParent().setLeft(node.getLeft());
				else
					node.getParent().setRight(node.getLeft());
				node.getLeft().setParent(node.getParent());
			}
		} else {
			BSTNode<T> newElement = this.sucessor(node.getData());
			if (newElement == null)
				newElement = this.predecessor(node.getData());
			T aux = node.getData();
			node.setData(newElement.getData());
			newElement.setData(aux);
			this.remove(newElement);
		}
	}

	protected boolean isSheet(BTNode<T> node) {
		if (node == null || node.isEmpty())
			throw new RuntimeException("Node is empty!");
		return node.getRight().isEmpty() && node.getLeft().isEmpty();
	}

	protected boolean justRightSon(BTNode<T> node) {
		if (node == null || node.isEmpty())
			return false;
		return !node.getRight().isEmpty() && node.getLeft().isEmpty();
	}
	
	protected boolean justLeftSon(BTNode<T> node) {
		if (node == null || node.isEmpty())
			return false;
		return !node.getLeft().isEmpty() && node.getRight().isEmpty();
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T[] preOrder() {
		int treeSize = this.size();
		List<T> list = new ArrayList<>(treeSize);
		this.constructListPreOrder(this.root, list);
		return list.toArray((T[]) new Comparable[treeSize]);
	}

	private void constructListPreOrder(BTNode<T> node, List<T> list) {
		if (node.isEmpty())
			return ;
		list.add(node.getData());
		this.constructListPreOrder(node.getLeft(), list);
		this.constructListPreOrder(node.getRight(), list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] order() {
		int treeSize = this.size();
		List<T> list = new ArrayList<>(treeSize);
		this.constructListOrder(this.root, list);
		return list.toArray((T[]) new Comparable[treeSize]);
	}
	

	private void constructListOrder(BTNode<T> node, List<T> list) {
		if (node.isEmpty())
			return ;
		this.constructListOrder(node.getLeft(), list);
		list.add(node.getData());
		this.constructListOrder(node.getRight(), list);
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] postOrder() {
		int treeSize = this.size();
		List<T> list = new ArrayList<>(treeSize);
		this.constructListPostOrder(this.root, list);
		return list.toArray((T[]) new Comparable[treeSize]);
	}

	private void constructListPostOrder(BTNode<T> node, List<T> list) {
		if (node.isEmpty())
			return ;
		this.constructListPostOrder(node.getLeft(), list);
		this.constructListPostOrder(node.getRight(), list);		
		list.add(node.getData());
	}

	/**
	 * This method is already implemented using recursion. You must understand
	 * how it work and use similar idea with the other methods.
	 */
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft()) + size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}