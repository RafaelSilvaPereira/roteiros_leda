package adt.avltree;

/**
 * 
 * Performs consistency validations within a AVL Tree instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public interface AVLTreeVerifier<T extends Comparable<T>> {
	
	/**
	 * 
	 * Checks whether a AVL Tree instance violates any AVL property
	 * 
	 * @return
	 */
	public boolean isAVLTree();

}
