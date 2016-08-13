package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected  BSTNode<T> root;
	
	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot(){
		return this.root;
	}
	
	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		int height  = root.getThisHeight();
		return height == 0 ? -1 : height;
	}

	@Override
	public BSTNode<T> search(T element) {
		return root.search(element);
	}

	@Override
	public void insert(T element) {
		root.add(element);
	}

	@Override
	public BSTNode<T> maximum() {
		return root.maximum();
	}

	@Override
	public BSTNode<T> minimum() {
		return root.minimum();
	}

	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> elementNode = root.search(element);
		if (elementNode != null) {
			result = elementNode.sucessor(elementNode);
		}
		return result;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> result = null;
		BSTNode<T> elementNode = root.search(element);
		if (elementNode != null) {
			result = elementNode.predecessor(elementNode);
		}
		return result;
	}

	@Override
	public void remove(T element) {
		root.remove(element);
	}

	@Override
	public T[] preOrder() {
		return root.preOrder();
	}

	@Override
	public T[] order() {
		return root.order();
	}

	@Override
	public T[] postOrder() {
		return root.postOrder();
	}

	/**
	 * This method is already implemented using recursion. You must understand how it work and 
	 * use similar idea with the other methods. 
	 */
	@Override
	public int size() {
		return size(root);
	}
	private int size(BSTNode<T> node){
		int result = 0;
		//base case means doing nothing (return 0)
		if(!node.isEmpty()){ //indusctive case
			result = 1 + size((BSTNode<T>)node.getLeft()) + size((BSTNode<T>)node.getRight());
		}
		return result;
	}

}
