package adt.btree;

import java.util.ArrayList;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.BTNode;

public class BNode<T extends Comparable<T>> extends BSTNode<T> {
	
	private T[] values;
	private BNode<T>[] children;
	private BNode<T> parent;
	
	@SuppressWarnings("unchecked")
	public BNode() {
		this.values = (T[]) new Object[3];
		this.children = (BNode<T>[]) new BNode[4];;
	}
	
	@Override
	public BTNode<T> getParent() {
		return parent;
	}
	
	public void setParent(BNode<T> parent) {
		this.parent = parent;
	}
	
	private T removeValue(int index) {
		T aux = values[index];
		values[index] = null;
		return aux;
	}
	
	private BNode<T> removeChild(int index) {
		BNode<T> aux = children[index];
		children[index] = new BNode<T>();
		return aux;
	}
	
	private void split() {
		BNode<T> preAux = new BNode<>();
		preAux.addValue(removeValue(0));
		preAux.setParent(this);
		BNode<T> postAux = new BNode<>();
		postAux.setParent(this);
		postAux.addValue(removeValue(2));
		swap(values, 1, 0);
		preAux.children[0] = removeChild(0);
		preAux.children[1] = removeChild(1);
		postAux.children[0] = removeChild(2);
		postAux.children[1] = removeChild(3);
		children[0] = preAux;
		children[1] = postAux;
	}

	private void swap(Object[] arr, int i, int j) {
		Object aux = arr[i];
		arr[i] = arr[j];
		arr[j] = aux;
	}
	
	public void addValue(T value) {
		if (isFull()) {
			split();
		}
		if (values[0] == null || values[0].compareTo(value) > 0) {
			swap(values, 1, 2);
			swap(values, 0, 1);
			values[0] = value;
			swap(children, 3, 4);
			swap(children, 2, 3);
			swap(children, 1, 2);
			swap(children, 0, 1);
		}
	}

	private boolean isFull() {
		return values[2] != null;
	}
}
