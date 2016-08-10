package adt.rbtree;

import java.util.ArrayList;
import java.util.List;

import adt.avltree.AVLTreeImpl;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends AVLTreeImpl<T> 
	implements RBTree<T> {

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}
	
	protected int blackHeight() {
		if (this.root.isEmpty())
			return 0;
		RBNode<T> node = (RBNode<T>) this.root;
		int height = 0;
		while (!node.isEmpty()) {
			if (node.getColour().equals(Colour.BLACK)) {
				height++;
			}
			node = (RBNode<T>) node.getLeft();
		}
		return height;
	}

	protected boolean verifyProperties(){
		boolean resp = verifyNodesColour()
						&& verifyNILNodeColour()
						&& verifyRootColour()
						&& verifyChildrenOfRedNodes()
						&& verifyBlackHeight();
		
		return resp;
	}
	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed by the type Colour.
	 */
	private boolean verifyNodesColour(){
		return true; //already implemented
	}
	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return ((RBNode<T>)root).getColour() == Colour.BLACK; //already implemented
	}
	
	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour(){
		return true; //already implemented
	}
	
	/**
	 * Verifies the property for all RED nodes: the children of a red node must be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes(){
		return verifyChildrenOfRedNodes((RBNode<T>) this.root);
	}
	
	
	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		if (!node.isEmpty()) {
			if (node.getColour().equals(Colour.RED)) {
				RBNode<T> left = (RBNode<T>) node.getLeft();
				RBNode<T> right = (RBNode<T>) node.getRight();
				if (left.getColour().equals(Colour.RED) || right.getColour().equals(Colour.RED)) {
					return false;
				}
			}
			return this.verifyChildrenOfRedNodes((RBNode<T>) node.getLeft()) &&
					this.verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
		}
		return true;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (this.root == null || this.root.isEmpty()) {
			return true;
		}
		int leftBlackHeight = verifyBlackHeight((RBNode<T>) this.root.getLeft(), 0);
		int rightBlackHeight = verifyBlackHeight((RBNode<T>) this.root.getRight(), 0);
		if (leftBlackHeight != rightBlackHeight || leftBlackHeight == -1 || rightBlackHeight == -1) {
			return false;
		}
		return true;
	}
	
	private int verifyBlackHeight(RBNode<T> node, int lenght) {
		if (lenght == -1) 
			return lenght;
		int leftBlackHeight = 0;
		int rightBlackHeight = 0;
		if (node.getColour().equals(Colour.BLACK)) {
			lenght++;
		}
		if (node != null && node.getLeft() != null && !node.getLeft().isEmpty()) {
			leftBlackHeight = verifyBlackHeight((RBNode<T>) node.getLeft(), lenght);
		} else {
			leftBlackHeight++;
		}
		if (node != null && node.getRight() != null && !node.getRight().isEmpty()) {
			rightBlackHeight = verifyBlackHeight((RBNode<T>) node.getRight(), lenght);
		} else {
			rightBlackHeight++;
		}
		if (leftBlackHeight != rightBlackHeight || leftBlackHeight == -1 || rightBlackHeight == -1) {
			return -1;
		}
		return leftBlackHeight;
	}

	@Override
	public void insert(T value) {
		RBNode<T> node = this.insert((RBNode<T>) this.root, new RBNode<T>(), value);
		this.fixUpCase1(node);
	}
	
	private RBNode<T> insert(RBNode<T> node, RBNode<T> parent, T element) {
		if (node.isEmpty()) {
			node.setData(element);
			node.setLeft(new RBNode<T>());
			node.setRight(new RBNode<T>());
			node.setParent(parent);
			node.setColour(Colour.RED);
			return node;
		} else if (element.compareTo(node.getData()) < 0) {
			return this.insert((RBNode<T>) node.getLeft(), node, element);
		} else if (element.compareTo(node.getData()) > 0) {
			return this.insert((RBNode<T>) node.getRight(), node, element);
		}
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public RBNode<T>[] extendedPreOrder() {
		List<RBNode<T>> list = new ArrayList<RBNode<T>>();
		this.extendedPreOrder((RBNode<T>) this.root, list);
		RBNode<T>[] rb = new RBNode[list.size()];
		// return (RBNode<T>[]) list.toArray();
		return list.toArray(rb);
	}
	
	private void extendedPreOrder(RBNode<T> node, List<RBNode<T>> list) {
		if (!node.isEmpty()) {
			list.add(node);
			this.extendedPreOrder((RBNode<T>) node.getLeft(), list);
			this.extendedPreOrder((RBNode<T>) node.getRight(), list);
		}
	}

		//FIXUP methods
		protected void fixUpCase1(RBNode<T> node) {
			if (node == this.root) {
				node.setColour(Colour.BLACK);
			} else {
				fixUpCase2(node);
			}
		}
		
		protected void fixUpCase2(RBNode<T> node){
			if (!((RBNode<T>) node.getParent()).getColour().equals(Colour.BLACK)) {
				fixUpCase3(node);
			}
		}
		
		protected void fixUpCase3(RBNode<T> node){
			RBNode<T> parent = (RBNode<T>) node.getParent();
			RBNode<T> grandFather = (RBNode<T>) parent.getParent();
			RBNode<T> uncle = null;
			if (grandFather.getLeft() == parent) {
				uncle = (RBNode<T>) grandFather.getRight();
			} else {
				uncle = (RBNode<T>) grandFather.getLeft();
			}
			if (uncle.getColour().equals(Colour.RED)) {
				parent.setColour(Colour.BLACK);
				uncle.setColour(Colour.BLACK);
				grandFather.setColour(Colour.RED);
				fixUpCase1(grandFather);
			} else {
				this.fixUpCase4(node);
			}
		}
		
		protected void fixUpCase4(RBNode<T> node) {
			RBNode<T> parent = (RBNode<T>) node.getParent();
			RBNode<T> grandFather = null;
			RBNode<T> aux = node;
			if (parent != this.root) {
				grandFather = (RBNode<T>) parent.getParent();
			}
			if (grandFather != null && grandFather.getLeft() == parent && parent.getRight() == node) {
				this.leftRotation(parent);
				aux = (RBNode<T>) node.getLeft();
			} else if (grandFather != null && grandFather.getRight() == parent && parent.getLeft() == node) {
				this.rightRotation(parent);
				aux = (RBNode<T>) node.getRight();
			}
			this.fixUpCase5(aux);
		}
		
		protected void fixUpCase5(RBNode<T> node){
			RBNode<T> parent = (RBNode<T>) node.getParent();
			RBNode<T> grandFather = null;
			if (parent != this.root) {
				grandFather = (RBNode<T>) parent.getParent();
			}
			parent.setColour(Colour.BLACK);
			grandFather.setColour(Colour.RED);
			if (parent.getLeft() == node) {
				this.rightRotation(grandFather);
			} else {
				this.leftRotation(grandFather);
			}
		}
}
