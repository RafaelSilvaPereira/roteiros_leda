package adt.splaytree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

public class SplayTreeImpl<T extends Comparable<T>> extends BSTImpl<T>
        implements SplayTree<T> {

    private void splay(BSTNode<T> node) {
        if (node != null && node.getParent() != null) {
            BSTNode<T> parent = (BSTNode<T>) node.getParent();
            if (parent.getParent() != null) {
                BSTNode<T> grandparent = (BSTNode<T>) parent.getParent();
                this.doubleRotation(node, parent, grandparent);
            } else {
                this.singleRotation(node, parent);
            }
            this.splay(node);
        }
    }

    private void doubleRotation(BSTNode<T> node, BSTNode<T> parent, BSTNode<T> grandparent) {
        if (isLeftChild(node)) {
            if (isLeftChild(parent)) {
                this.zigZigRotation(node, parent, grandparent);
            } else {
                this.zigZagRotation(node, parent, grandparent);
            }
        } else {
            if (isLeftChild(parent)) {
                this.zagZigRotation(node, parent, grandparent);
            } else {
                this.zagZagRotation(node, parent, grandparent);
            }
        }
    }

    private void zagZagRotation(BSTNode<T> node, BSTNode<T> parent, BSTNode<T> grandparent) {
        Util.leftRotation(grandparent);
        Util.leftRotation(parent);
        if (node.getParent() == null) this.root = node;
    }

    private void zagZigRotation(BSTNode<T> node, BSTNode<T> parent, BSTNode<T> grandparent) {
        Util.leftRotation(parent);
        Util.rightRotation(grandparent);
        if (node.getParent() == null) this.root = node;
    }

    private void zigZagRotation(BSTNode<T> node, BSTNode<T> parent, BSTNode<T> grandparent) {
        Util.rightRotation(parent);
        Util.leftRotation(grandparent);
        if (node.getParent() == null) this.root = node;
    }

    private void zigZigRotation(BSTNode<T> node, BSTNode<T> parent, BSTNode<T> grandparent) {
        Util.rightRotation(grandparent);
        Util.rightRotation(parent);
        if (node.getParent() == null) this.root = node;
    }

    private void singleRotation(BSTNode<T> node, BSTNode<T> parent) {
        if (isLeftChild(node)) {
            Util.rightRotation(parent);
            if (node.getParent() == null) this.root = node;
        } else {
            Util.leftRotation(parent);
            if (node.getParent() == null) this.root = node;
        }
    }

    @Override
    public void insert(T element) {
        BSTNode<T> node = super.insert(null, this.getRoot(), element);
        this.splay(node);
    }

    @Override
    public void remove(T element) {
        BSTNode<T> node = super.remove(super.search(element));
        this.splay((BSTNode<T>) node.getParent());
    }

    @Override
    public BSTNode<T> search(T element) {
        BSTNode<T> node = super.search(element);
        this.splay((node.isEmpty()) ? (BSTNode<T>) node.getParent() : node);
        return this.getRoot();
    }
}
