package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.BTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

	private static final Colour RED = RBNode.Colour.RED;
	
	private static final Colour BLACK = RBNode.Colour.BLACK;

	public RBTreeImpl() {
		this.root = new RBNode<T>();
	}

	protected int blackHeight() {
		return blackHeight(getRoot());
	}
	
	private int blackHeight(RBNode<T> node) {
		int height = node.getColour() == BLACK  && !node.isEmpty() ? 1 : 0;
		height += node.isEmpty() ? 0 : blackHeight(getRight(node));
		return height;
	}

	protected boolean verifyProperties() {
		boolean resp = verifyNodesColour() 
				&& verifyNILNodeColour() 
				&& verifyRootColour() 
				&& verifyChildrenOfRedNodes()
				&& verifyBlackHeight();
		return resp;
	}

	/**
	 * The colour of each node of a RB tree is black or red. This is guaranteed
	 * by the type Colour.
	 */
	private boolean verifyNodesColour() {
		return true; // already implemented
	}

	/**
	 * The colour of the root must be black.
	 */
	private boolean verifyRootColour() {
		return getRoot().getColour() == BLACK;
	}

	/**
	 * This is guaranteed by the constructor.
	 */
	private boolean verifyNILNodeColour() {
		return true; // already implemented
	}

	/**
	 * Verifies the property for all RED nodes: the children of a red node must
	 * be BLACK.
	 */
	private boolean verifyChildrenOfRedNodes() {
		return verifyChildrenOfRedNodes(getRoot());
	}

	private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
		boolean isValid = true;
		boolean isRedNode = node.getColour() == RED;
		if (node != null && !node.isEmpty() && isRedNode) {
			isValid = getLeft(node).getColour() == BLACK
					&& getRight(node).getColour() == BLACK;
		}
		if ((!isRedNode || isValid) && !node.isEmpty()) {
			isValid = verifyChildrenOfRedNodes(getLeft(node))
					&& verifyChildrenOfRedNodes(getRight(node));
		}
		return isValid;
	}

	/**
	 * Verifies the black-height property from the root. The method blackHeight
	 * returns an exception if the black heights are different.
	 */
	private boolean verifyBlackHeight() {
		if (!verifiBlackHeight(getRoot()))
			throw new RuntimeException("The black heights of the tree are invalid.");
		return true;
	}
	
	private boolean verifiBlackHeight(RBNode<T> node) {
		boolean heightsOk = node.isEmpty() || (blackHeight(getLeft(node)) == blackHeight(getRight(node)));
		boolean shouldCallAgain = !node.isEmpty();
		boolean result = heightsOk;
		if (heightsOk && shouldCallAgain) {
			result = verifiBlackHeight(getLeft(node)) && verifiBlackHeight(getRight(node));
		}
		return result;
	}

	@Override
	public void insert(T element) {
		if (element != null)
			insert(getRoot(), element);
	}

	protected RBNode<T> insert(RBNode<T> nodeAt, T element) {
		RBNode<T> added = nodeAt;
		if (nodeAt.isEmpty()) {
			nodeAt.setData(element);
			nodeAt.setColour(RED);
			nodeAt.setLeft(new RBNode<T>());
			nodeAt.getLeft().setParent(nodeAt);
			nodeAt.setRight(new RBNode<T>());
			nodeAt.getRight().setParent(nodeAt);
			fixUpCase1(nodeAt);
		} else {
			int comparation = nodeAt.getData().compareTo(element);
			if (comparation == 1)
				added = insert(getLeft(nodeAt), element);
			else if (comparation == -1)
				added = insert(getRight(nodeAt), element);
		}
		return added;
	}

	@Override
	public RBNode<T>[] rbPreOrder() {
		return walkByTree(getRoot());
	}

	@SuppressWarnings("unchecked")
	private RBNode<T>[] walkByTree(RBNode<T> nodeAt) {
		RBNode<T>[] array = (RBNode<T>[]) new RBNode[0];
		if (!nodeAt.isEmpty()) {
			RBNode<T>[] leftTree = walkByTree(getLeft(nodeAt));
			RBNode<T>[] rightTree = walkByTree(getRight(nodeAt));
			array = (RBNode<T>[]) new RBNode[1 + leftTree.length + rightTree.length];
			int start = 0;
			array[start++] = nodeAt;
			for (RBNode<T> e : leftTree)
				array[start++] = e;
			for (RBNode<T> e : rightTree)
				array[start++] = e;
		}
		return array;
	}

	// FIXUP methods
	protected void fixUpCase1(RBNode<T> node) {
		if (node.equals(getRoot())) {
			node.setColour(BLACK);
		} else {
			fixUpCase2(node);
		}
	}

	protected void fixUpCase2(RBNode<T> node) {
		if (getParent(node).getColour() != BLACK) {
			fixUpCase3(node);
		}
	}

	protected void fixUpCase3(RBNode<T> node) {
		RBNode<T> parent = getParent(node);
		RBNode<T> parentParent = getParent(parent);
		RBNode<T> aux = isLeftChild(parent) ? getRight(parentParent) : getLeft(parentParent);
		if (aux.getColour() == RED) {
			parent.setColour(BLACK);
			aux.setColour(BLACK);
			parentParent.setColour(RED);
			fixUpCase1(parentParent);
		} else {
			fixUpCase4(node);
		}
	}

	protected void fixUpCase4(RBNode<T> node) {
		RBNode<T> parent = getParent(node);
		boolean isZigZag = !((isLeftChild(node) && isLeftChild(parent)) 
						|| (!isLeftChild(node) && !isLeftChild(parent)));
		if (isZigZag) {
			if (isLeftChild(node)) {
				rightRotation(getParent(node));
			} else {
				leftRotation(getParent(node));
			}
			fixUpCase5(parent);
		} else {
			fixUpCase5(node);
		}
	}

	protected void fixUpCase5(RBNode<T> node) {
		RBNode<T> parent = getParent(node);
		RBNode<T> parentParent = getParent(parent);
		parent.setColour(BLACK);
		parentParent.setColour(RED);

		if (isLeftChild(node)) {
			rightRotation(parentParent);
		} else {
			leftRotation(parentParent);
		}
	}

	// Aux methods
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
	public RBNode<T> getRoot() {
		return (RBNode<T>) root;
	}

	protected RBNode<T> getParent(BSTNode<T> node) {
		return (RBNode<T>) node.getParent();
	}

	private boolean isLeftChild(BSTNode<T> node) {
		return node.equals(getParent(node).getLeft());
	}

	protected RBNode<T> getRight(BTNode<T> node) {
		return (RBNode<T>) node.getRight();
	}

	protected RBNode<T> getLeft(BTNode<T> node) {
		return (RBNode<T>) node.getLeft();
	}

	private RBNode<T> thisNode(BTNode<T> btNode) {
		return (RBNode<T>) btNode;
	}
}
