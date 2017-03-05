package adt.avltree;

import adt.bst.BSTVerifierImpl;
import adt.bt.BTNode;

/**
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeVerifierImpl<T extends Comparable<T>> extends
		BSTVerifierImpl<T> implements AVLTreeVerifier<T> {

	private static final int MAX_HEIGHT_DIFF = 2;

	private AVLTreeImpl<T> avlTree;

	public AVLTreeVerifierImpl(AVLTree<T> avlTree) {
		super(avlTree);
		this.avlTree = (AVLTreeImpl<T>) avlTree;
	}

	private AVLTreeImpl<T> getAVLTree() {
		return avlTree;
	}

	@Override
	public boolean isAVLTree() {
		return getAVLTree() == null || (isBST() && isAVLTree(getAVLTree().getRoot()));
	}

	/**
	 * Metodo recursivo que verifica, a partir do node passado, para cada filho
	 * seguinte se este eh um avl node valido.
	 * 
	 * @return True se este node e todos seus filhos sao nodes avl validos.
	 */
	private boolean isAVLTree(BTNode<T> node) {
		boolean shouldCallAgain = node != null && !node.isEmpty();
		boolean isOk = !shouldCallAgain || isAVLNodeOk(node);
		if (isOk && shouldCallAgain) {
			isOk = isAVLTree(node.getLeft()) && isAVLTree(node.getRight());
		}
		return isOk;
	}

	/**
	 * Verifica se o node passado eh um avl node valido, checando se sua altura
	 * do lado esquerdo e direito tem no maximo 1 unidade de diferenca.
	 * 
	 * @return True se o node for um avl node valido.
	 */
	private boolean isAVLNodeOk(BTNode<T> node) {
		int leftHeight = height(node.getLeft());
		int rightHeight = height(node.getRight());
		return Math.abs(leftHeight - rightHeight) < MAX_HEIGHT_DIFF;
	}

	/**
	 * Calcula a altura para o node passado.
	 * 
	 * @return Altura do node.
	 */
	private int height(BTNode<T> nodeAt) {
		int height = 0;
		if (nodeAt != null && !nodeAt.isEmpty()) {
			int leftHeight = height(nodeAt.getLeft());
			int rightHeight = height(nodeAt.getRight());
			height = 1 + Math.max(leftHeight, rightHeight);
		}
		return height;
	}
}
