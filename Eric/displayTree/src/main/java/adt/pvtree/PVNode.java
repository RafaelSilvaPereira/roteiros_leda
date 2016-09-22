package adt.pvtree;

import adt.bst.BSTNode;

public class PVNode<T extends Comparable<T>> extends BSTNode<T> {

	private Color cor;

	public PVNode(T data) {
		this.data = data;
		this.cor = Color.RED;
	}

	public PVNode() {
		this.cor = Color.BLACK;
	}

	@Override
	public void setData(T data) {
		super.setData(data);
		this.cor = Color.RED;
	}

	public Color getCor() {
		return cor;
	}

	public void setCor(Color cor) {
		this.cor = cor;
	}

	public void printTree() {
		if (right != null) {
			getRight().printTree(true, "");
		}
		String cor = "R";
		if (this.cor == Color.BLACK) {
			cor = "B";
		}
		String data = this.data != null ? this.data.toString() : "";
		System.out.println(data + cor);
		if (left != null) {
			getLeft().printTree(false, "");
		}
	}

	public void printTree(boolean isRight, String indent) {
		if (right != null) {
			getRight().printTree(true, indent + (isRight ? "       " : " |      "));
		}
		System.out.print(indent);
		if (isRight) {
			System.out.print(" /");
		} else {
			System.out.print(" \\");
		}
		System.out.print("----- ");
		String cor = "R";
		if (this.cor == Color.BLACK) {
			cor = "B";
		}
		String data = this.data != null ? this.data.toString() : "";
		System.out.println(data + cor);
		if (left != null) {
			getLeft().printTree(false, indent + (isRight ? " |      " : "        "));
		}
	}

	@Override
	public PVNode<T> getLeft() {
		return (PVNode<T>) super.getLeft();
	}

	@Override
	public PVNode<T> getRight() {
		return (PVNode<T>) super.getRight();
	}
}
