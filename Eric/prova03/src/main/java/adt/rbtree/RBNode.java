package adt.rbtree;

import adt.bst.BSTNode;

public class RBNode<T extends Comparable<T>> extends BSTNode<T> {
	enum Colour {
		BLACK, RED
	};

	private Colour colour;

	public RBNode() {
		this.colour = Colour.BLACK;
	}

	public Colour getColour() {
		return colour;
	}

	public void setColour(Colour colour) {
		if (isEmpty() && colour == Colour.RED) {
			throw new UnsupportedOperationException(
					"NIL node colour cannot be RED");
		}
		this.colour = colour;
	}

	@SuppressWarnings("unchecked")
	@Override
	public boolean equals(Object obj) {
		return super.equals(obj)
				&& this.colour == ((RBNode<T>) obj).getColour();
	}
	
	public void printTree() {
		if (right != null) {
			getRight().printTree(true, "");
		}
		String cor = "R";
		if (this.colour == Colour.BLACK) {
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
		if (this.colour == Colour.BLACK) {
			cor = "B";
		}
		String data = this.data != null ? this.data.toString() : "";
		System.out.println(data + cor);
		if (left != null) {
			getLeft().printTree(false, indent + (isRight ? " |      " : "        "));
		}
	}

	@Override
	public RBNode<T> getLeft() {
		return (RBNode<T>) super.getLeft();
	}

	@Override
	public RBNode<T> getRight() {
		return (RBNode<T>) super.getRight();
	}
	

	@Override
	public String toString() {
		String resp = "NIL";
		if (!isEmpty()) {
			resp = "(" + data.toString();
			if (colour == Colour.RED) {
				resp = resp + ",R)";
			} else {
				resp = resp + ",B)";
			}
		}
		return resp;
	}

}
