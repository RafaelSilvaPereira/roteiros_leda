package adt.bst;

import adt.bt.BTNode;

public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements
		SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return (equalNulls(tree1, tree2)) || (!cantBeSimilar(tree1, tree2))
				&& (equals(tree1.getRoot(), tree2.getRoot()));
	}

	private boolean equals(BTNode<T> node1, BTNode<T> node2) {
		boolean canBeEquals = !onlyOneEmpty(node1, node2);
		boolean allEmpty = node1.isEmpty() && node2.isEmpty();
		if (canBeEquals && !allEmpty && (node1.equals(node2))) {
			canBeEquals &= equals(node1.getLeft(), node2.getLeft());
			canBeEquals &= equals(node1.getRight(), node2.getRight());
		} else if (!allEmpty) {
			canBeEquals = false;
		}
		return canBeEquals;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		return (equalNulls(tree1, tree2)) || (!cantBeSimilar(tree1, tree2))
				&& (isSimilar(tree1.getRoot(), tree2.getRoot()));
	}

	private boolean isSimilar(BTNode<T> node1, BTNode<T> node2) {
		boolean canBeSimilar = !onlyOneEmpty(node1, node2);
		if (canBeSimilar && !node1.isEmpty() && !node2.isEmpty()) {
			canBeSimilar &= isSimilar(node1.getLeft(), node2.getLeft());
			canBeSimilar &= isSimilar(node1.getRight(), node2.getRight());
		}
		return canBeSimilar;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		return (tree == null) ? null : orderStatistic(tree.minimum(), tree, k);
	}

	private T orderStatistic(BTNode<T> nodeAt, BST<T> tree, int k) {
		T result = (k == 1 && nodeAt != null) ? nodeAt.getData() : null;
		if (result == null && nodeAt != null && !nodeAt.isEmpty() && k > 1) {
			result = orderStatistic(tree.sucessor(nodeAt.getData()), tree, k - 1);
		}
		return result;
	}

	private boolean equalNulls(Object o1, Object o2) {
		return o1 == null && o2 == null;
	}

	private boolean cantBeSimilar(BST<T> tree1, BST<T> tree2) {
		return (tree1 == null && tree2 != null)
				|| (tree1 != null && tree2 == null)
				|| (tree1.isEmpty() && !tree2.isEmpty())
				|| (!tree1.isEmpty() && tree2.isEmpty())
				|| (tree1.size() != tree2.size());
	}

	private boolean onlyOneEmpty(BTNode<T> node1, BTNode<T> node2) {
		return (!equalNulls(node1, node2)) && 
				(
					(node1 == null && node2 != null)
				 || (node1 != null && node2 == null)
				 || (node1.isEmpty() && !node2.isEmpty()) 
				 || (!node1.isEmpty() && node2.isEmpty())
				);
	}
}
