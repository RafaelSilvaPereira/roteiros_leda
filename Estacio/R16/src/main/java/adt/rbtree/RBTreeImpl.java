package adt.rbtree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;
import adt.rbtree.RBNode.Colour;

public class RBTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements RBTree<T> {

    public RBTreeImpl() {
        this.root = new RBNode<T>();
    }

    protected int blackHeight() {
        return this.blackHeight((RBNode<T>) this.getRoot());
    }

    private int blackHeight(RBNode<T> node) {
        if (node == null || node.isEmpty())
            return ZERO;

        int leftHeight = this.blackHeight((RBNode<T>) node.getLeft());
        int rightHeight = this.blackHeight((RBNode<T>) node.getRight());
        if (leftHeight != rightHeight) throw new RuntimeException();
        return leftHeight + ((isBlackNode(node)) ? ONE : ZERO);

    }

    protected boolean verifyProperties() {
        boolean resp = verifyNodesColour() && verifyNILNodeColour() && verifyRootColour() && verifyChildrenOfRedNodes()
                && verifyBlackHeight();

        return resp;
    }

    /**
     * The colour of each node of a RB tree is black or red. This is guaranteed
     * by the type Colour.
     */
    private boolean verifyNodesColour() {
        return true; // already implemented
    }

    /**
     * The colour of the root must be black.
     */
    private boolean verifyRootColour() {
        return ((RBNode<T>) root).getColour() == Colour.BLACK; // already
        // implemented
    }

    /**
     * This is guaranteed by the constructor.
     */
    private boolean verifyNILNodeColour() {
        return true; // already implemented
    }

    /**
     * Verifies the property for all RED nodes: the children of a red node must
     * be BLACK.
     */
    private boolean verifyChildrenOfRedNodes() {
        return this.verifyChildrenOfRedNodes((RBNode<T>) this.getRoot());
    }

    private boolean verifyChildrenOfRedNodes(RBNode<T> node) {
        if (node.isEmpty()) {
            return true;
        }
        boolean invalid = !isBlackNode(node)
                && (!isBlackNode((RBNode<T>) node.getLeft()) || !isBlackNode((RBNode<T>) node.getRight()));
        return !invalid && this.verifyChildrenOfRedNodes((RBNode<T>) node.getLeft())
                && this.verifyChildrenOfRedNodes((RBNode<T>) node.getRight());
    }

    /**
     * Verifies the black-height property from the root. The method blackHeight
     * returns an exception if the black heights are different.
     */
    private boolean verifyBlackHeight() {
        return this.blackHeight((RBNode<T>) this.getRoot().getLeft()) == this.blackHeight((RBNode<T>) this.getRoot()
                .getRight());
    }

    @Override
    public void insert(T value) {
        if (value != null) {
            this.insert((RBNode<T>) this.getRoot().getParent(), (RBNode<T>) this.getRoot(), value);
        }
    }

    private void insert(RBNode<T> parent, RBNode<T> node, T value) {
        if (node.isEmpty()) {
            node.setParent(parent);
            node.setData(value);
            node.setLeft(new RBNode<T>());
            node.setRight(new RBNode<T>());
            node.getRight().setParent(node);
            node.getLeft().setParent(node);
            node.setColour(Colour.RED);
            this.fixUpCase1(node);
        } else if (isBiggerThan(node, value)) {
            this.insert(node, (RBNode<T>) node.getLeft(), value);
        } else if (isSmallerThan(node, value)) {
            this.insert(node, (RBNode<T>) node.getRight(), value);
        }
    }

    @Override
    public RBNode<T>[] rbPreOrder() {
        RBNode<T>[] array = new RBNode[this.size()];
        this.rbPreOrder(array, (RBNode<T>) this.getRoot(), ZERO);
        return array;
    }

    private int rbPreOrder(RBNode<T>[] array, RBNode<T> node, int index) {
        if (!node.isEmpty()) {
            array[index++] = node;
            index = this.rbPreOrder(array, (RBNode<T>) node.getLeft(), index);
            index = this.rbPreOrder(array, (RBNode<T>) node.getRight(), index);
        }
        return index;
    }

    // FIXUP methods
    protected void fixUpCase1(RBNode<T> node) {
        if (this.getRoot().getData().equals(node.getData())) {
            node.setColour(Colour.BLACK);
        } else {
            this.fixUpCase2(node);
        }
    }

    protected void fixUpCase2(RBNode<T> node) {
        if (node.getParent() != null) {
            if (!isBlackNode((RBNode<T>) node.getParent()))
                this.fixUpCase3(node);
        }
    }

    protected void fixUpCase3(RBNode<T> node) {
        RBNode<T> father = (RBNode<T>) node.getParent();
        RBNode<T> uncle = (RBNode<T>) ((this.isLeftChild(father)) ? father.getParent().getRight() : father.getParent()
                .getLeft());
        if (uncle.getColour() == Colour.RED) {
            uncle.setColour(Colour.BLACK);
            ((RBNode<T>) node.getParent()).setColour(Colour.BLACK);
            ((RBNode<T>) node.getParent().getParent()).setColour(Colour.RED);
            this.fixUpCase1((RBNode<T>) node.getParent().getParent());
        } else {
            this.fixUpCase4(node);
        }
    }

    protected void fixUpCase4(RBNode<T> node) {
        RBNode<T> nextNode = node;
        if (!this.isLeftChild(node) && this.isLeftChild((BSTNode<T>) node.getParent())) {
            Util.leftRotation((BSTNode<T>) node.getParent());
            nextNode = (RBNode<T>) node.getLeft();
        } else if (this.isLeftChild(node) && !this.isLeftChild((BSTNode<T>) node.getParent())) {
            Util.rightRotation((BSTNode<T>) node.getParent());
            nextNode = (RBNode<T>) node.getRight();
        }
        this.fixUpCase5(nextNode);
    }

    protected void fixUpCase5(RBNode<T> node) {
        RBNode<T> father = (RBNode<T>) node.getParent();
        RBNode<T> grandfather = (RBNode<T>) father.getParent();
        father.setColour(Colour.BLACK);
        grandfather.setColour(Colour.RED);

        if (this.isLeftChild(node)) {
            if (grandfather.getParent() == null)
                this.root = father;
            Util.rightRotation((BSTNode<T>) node.getParent().getParent());
        } else {
            if (grandfather.getParent() == null)
                this.root = father;
            Util.leftRotation((BSTNode<T>) node.getParent().getParent());
        }
    }

    private boolean isBlackNode(RBNode<T> node) {
        return node.getColour() == Colour.BLACK;
    }
}
