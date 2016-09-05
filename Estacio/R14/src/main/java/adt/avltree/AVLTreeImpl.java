package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;

/**
 * Performs consistency validations within a AVL Tree instance
 *
 * @param <T>
 * @author Claudio Campelo
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
        AVLTree<T> {

    public static final int ZERO = 0;
    public static final int UNBALANCED = 2;
    public static final int UNBALANCED_LEFT = 1;
    public static final int UNBALANCED_RIGHT = -1;

    // AUXILIARY
    protected int calculateBalance(BSTNode<T> node) {
        return (node.isEmpty()) ? ZERO :
                this.height((BSTNode<T>) node.getLeft()) - this.height((BSTNode<T>) node.getRight());
    }

    // AUXILIARY
    protected void rebalance(BSTNode<T> node) {
        int balance = this.calculateBalance(node);
        if (balance > UNBALANCED_LEFT) {
            BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();
            if (this.calculateBalance(leftChild) <= UNBALANCED_RIGHT) {
                this.leftRotation(leftChild);
            }
            this.rightRotation(node);
        } else if (balance < UNBALANCED_RIGHT) {
            BSTNode<T> rightChild = (BSTNode<T>) node.getRight();
            if (this.calculateBalance(rightChild) >= UNBALANCED_LEFT) {
                this.rightRotation(rightChild);
            }
            this.leftRotation(node);
        }
    }

    // AUXILIARY
    protected void rebalanceUp(BSTNode<T> node) {
        int balance = this.calculateBalance(node);
        if (Math.abs(balance) >= UNBALANCED) {
            this.rebalance(node);
        }
        if (node.getParent() != null) {
            this.rebalanceUp((BSTNode<T>) node.getParent());
        }
    }

    // AUXILIARY
    protected void leftRotation(BSTNode<T> node) {
        BSTNode<T> rightChild = (BSTNode<T>) node.getRight();

        node.setRight(rightChild.getLeft());
        rightChild.setLeft(node);
        if (node.getParent() == null) {
            this.root = rightChild;
        } else if (this.isLeftChild(node)) {
            node.getParent().setLeft(rightChild);
        } else {
            node.getParent().setRight(rightChild);
        }
        rightChild.setParent(node.getParent());
        node.setParent(rightChild);
    }

    // AUXILIARY
    protected void rightRotation(BSTNode<T> node) {
        BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();

        node.setLeft(leftChild.getRight());
        leftChild.setRight(node);
        if (node.getParent() == null) {
            this.root = leftChild;
        } else if (this.isLeftChild(node)) {
            node.getParent().setLeft(leftChild);
        } else {
            node.getParent().setRight(leftChild);
        }
        leftChild.setParent(node.getParent());
        node.setParent(leftChild);
    }

    @Override
    public void insert(T element) {
        super.insert(element);
        BSTNode<T> node = this.search(element);
        this.rebalanceUp(node);
    }

    @Override
    public void remove(T element) {
        BSTNode<T> node = this.search(element);
        if (!node.isEmpty()) {
            super.remove(element);
            this.rebalanceUp(node);
        }
    }
}
