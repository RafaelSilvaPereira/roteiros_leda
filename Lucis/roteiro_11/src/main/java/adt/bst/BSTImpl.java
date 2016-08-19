package adt.bst;

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
		return height(root);
	}

	private int height(BSTNode<T> node) {
		if (node.isEmpty()) return -1;
		else return Math.max(height((BSTNode<T>) node.getRight()), height((BSTNode<T>) node.getLeft())) + 1;
	}

	@Override
	public BSTNode<T> search(T element) {
		return search(root, element);
	}

	private BSTNode<T> search(BSTNode<T> node, T element) {
		if (node.isEmpty() || element == null) return new BSTNode<T>();
		if (element.equals(node.getData())) return node;
		if (element.compareTo(node.getData()) > 0)
			return search((BSTNode<T>) node.getRight(),element);
		else
			return search((BSTNode<T>) node.getLeft(),element);
	}

	@Override
	public void insert(T element) {
		if (element == null) return;
		this.insert(root, element, null);
	}

	
	private void insert(BSTNode<T> root2, T element, BSTNode<T> parent) {
		if (root2.isEmpty()) {
			root2.setParent(parent);
			root2.setData(element);
			root2.setLeft(new BSTNode<T>());
			root2.setRight(new BSTNode<T>());
		}else{
			if (element.compareTo(root2.getData()) > 0){
				insert((BSTNode<T>) root2.getRight(), element, root2);
			}else{
				insert((BSTNode<T>) root2.getLeft(), element, root2);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		return maximum(root);
	}

	private BSTNode<T> maximum(BSTNode<T> node) {
		if (node.isEmpty()) return null;
		if (node.getRight().isEmpty()) return node;
		return maximum((BSTNode<T>) node.getRight());
	}

	@Override
	public BSTNode<T> minimum() {
		return minimum(root);
	}
	
	private BSTNode<T> minimum(BSTNode<T> node) {
		if (node.isEmpty()) return null;
		if (node.getLeft().isEmpty()) return node;
		return minimum((BSTNode<T>) node.getLeft());
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) return null;
		if (!node.getRight().isEmpty()){
			return minimum((BSTNode<T>) node.getRight());
		}else{
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
	        while (parent != null && node.equals(parent.getRight())) {
	            node = parent;
	            parent = (BSTNode<T>) parent.getParent();
	        }
	        return parent;
		}
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> node = search(element);
		if (node.isEmpty()) return null;
		if (!node.getLeft().isEmpty()){
			return maximum((BSTNode<T>) node.getLeft());
		}else{
			BSTNode<T> parent = (BSTNode<T>) node.getParent();
	        while (parent != null && node.equals(parent.getLeft())) {
	            node = parent;
	            parent = (BSTNode<T>) parent.getParent();
	        }
	        return parent;
		}
	}

	@Override
	public void remove(T element) {
		BSTNode<T> toBeRemoved = search(element);
		if (toBeRemoved.isEmpty()) return;
		remove(toBeRemoved);
	}

	private void remove(BSTNode<T> toBeRemoved) {
		if (toBeRemoved.isEmpty()) return;
		if (!toBeRemoved.getLeft().isEmpty() && !toBeRemoved.getRight().isEmpty()){
			BSTNode<T> suc = sucessor(toBeRemoved.getData());
			toBeRemoved.setData(suc.getData());
			remove(suc);
		}else{
			BSTNode<T> newNode = (BSTNode<T>) toBeRemoved.getLeft();
			if (newNode.isEmpty()){
				newNode = (BSTNode<T>) toBeRemoved.getRight();
			}
			toBeRemoved.setData(newNode.getData());
			toBeRemoved.setRight(newNode.getRight());
			toBeRemoved.setLeft(newNode.getLeft());
			if (toBeRemoved.getRight() != null) toBeRemoved.getRight().setParent(toBeRemoved);
			if (toBeRemoved.getLeft() != null) toBeRemoved.getLeft().setParent(toBeRemoved);
		}
	}

	@Override
	public T[] preOrder() {
		T[] preO = (T[]) new Comparable[this.size()];
		if (this.isEmpty()) return preO;
		preOrder(preO, root, 0);
		return preO;
	}

	private int preOrder(T[] preO, BSTNode<T> node, int index) {
		preO[index++] = node.getData();
		if (!node.getLeft().isEmpty()) index = preOrder(preO,(BSTNode) node.getLeft(), index);
		if (!node.getRight().isEmpty()) index = preOrder(preO,(BSTNode) node.getRight(), index);
		return index;
	}

	@Override
	public T[] order() {
		T[] inO  = (T[]) new Comparable[this.size()];
		if (this.isEmpty()) return inO;
		order(inO, root, 0);
		return inO;
	}

	private int order(T[] inO, BSTNode<T> node, int index) {
		if (!node.getLeft().isEmpty()) index = order(inO,(BSTNode) node.getLeft(), index);
		inO[index++] = node.getData();
		if (!node.getRight().isEmpty()) index = order(inO,(BSTNode) node.getRight(), index);
		return index;
	}

	@Override
	public T[] postOrder() {
		T[] posO  = (T[]) new Comparable[this.size()];
		if (this.isEmpty()) return posO;
		postOrder(posO, root, 0);
		return posO;
	}

	private int postOrder(T[] posO, BSTNode<T> node, int index) {
		if (!node.getLeft().isEmpty()) index = postOrder(posO,(BSTNode) node.getLeft(), index);
		if (!node.getRight().isEmpty()) index = postOrder(posO,(BSTNode) node.getRight(), index);
		posO[index++] = node.getData();
		return index;
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
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
