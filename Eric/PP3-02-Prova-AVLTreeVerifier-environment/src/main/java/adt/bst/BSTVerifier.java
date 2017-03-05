package adt.bst;

/**
 * 
 * Performs consistency validations within a BST instance
 * 
 * @author Claudio Campelo
 *
 * @param <T>
 */
public interface BSTVerifier<T extends Comparable<T>> {
	
	/**
	 * 
	 * Checks whether a BST instance violates any BST property
	 * 
	 * @return
	 */
	public boolean isBST();

}
