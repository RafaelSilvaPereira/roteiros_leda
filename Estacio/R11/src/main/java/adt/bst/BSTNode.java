package adt.bst;

import adt.bt.BTNode;

public class BSTNode<T extends Comparable<T>> extends BTNode<T> {

    private final int EQUALS = 0;

    /**
     * Verifies if the node has a bigger data than the element.
     *
     * @param element Element to be analysed.
     * @return {@code true} if the node data is bigger than the element, {@code false} otherwise.
     */
    public boolean isBiggerThan(T element) {
        return !this.isEmpty() && element != null && this.data.compareTo(element) > EQUALS;
    }

    /**
     * Verifies if the node has a smaller data than the element.
     *
     * @param element Element to be analysed.
     * @return {@code true} if the node data is smaller than the element, {@code false} otherwise.
     */
    public boolean isSmallerThan(T element) {
        return !this.isEmpty() && element != null && this.data.compareTo(element) < EQUALS;
    }

    /**
     * Verifies if the node has only one child.
     *
     * @return {@code true} if the node has only one child, {@code false} otherwise.
     */
    public boolean isOneDegree() {
        return this.hasOnlyLeftChild() || this.hasOnlyRightChild();
    }

    /**
     * Verifies if the node has only the left child.
     *
     * @return {@code true} if the node has only the left child, {@code false} otherwise.
     */
    public boolean hasOnlyLeftChild() {
        return (this.getRight().isEmpty() && !this.getLeft().isEmpty());
    }

    /**
     * Verifies if the node has only the right child.
     *
     * @return {@code true} if the node has only the right child, {@code false} otherwise.
     */
    public boolean hasOnlyRightChild() {
        return (!this.getRight().isEmpty() && this.getLeft().isEmpty());
    }

    /**
     * Verifies if the node is the left child of it's parent.
     *
     * @return {@code true} if the node is the left child of it's parent, {@code false} otherwise.
     */
    public boolean isLeftChild() {
        return !this.getParent().isEmpty()
                && !this.getParent().getLeft().isEmpty() &&
                this.getParent().getLeft().getData().equals(this.data);
    }
}