package adt.bst;

import adt.bt.BTNode;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class BSTVerifierImpl<T extends Comparable<T>> implements BSTVerifier<T> {

	private BSTImpl<T> bst;

	protected static final int LESSER = -1;

	protected static final int GREATER = 1;

	public BSTVerifierImpl(BST<T> bst) {
		this.bst = (BSTImpl<T>) bst;
	}

	private BSTImpl<T> getBSt() {
		return bst;
	}

	@Override
	public boolean isBST() {
		return getBSt() == null || isBST(getBSt().getRoot());
	}

	/**
	 * Metodo recursivo que verifica, a partir do node passado, para cada filho
	 * seguinte se este eh um bst node valido.
	 * 
	 * @return True se este node e todos seus nodes filhos sao validos.
	 */
	private boolean isBST(BTNode<T> node) {
		boolean shouldCallAgain = node != null && !node.isEmpty();
		boolean isOk = !shouldCallAgain || isValidNode(node);
		if (isOk && shouldCallAgain) {
			isOk = isBST(node.getLeft()) && isBST(node.getRight());
		}
		return isOk;
	}

	/**
	 * Verifica se o node eh um bst node valido, checando se seu filho da
	 * esquerda tem um valor menor que o seu e seu filho da direita um valor
	 * maior.
	 * 
	 * @return True se o node for valido.
	 */
	protected boolean isValidNode(BTNode<T> node) {
		T value = node.getData();
		return node.isEmpty()
				|| ((node.getLeft().isEmpty() || value.compareTo(node.getLeft().getData()) == GREATER) 
				&& (node.getRight().isEmpty() || value.compareTo(node.getRight().getData()) == LESSER));
	}
}
