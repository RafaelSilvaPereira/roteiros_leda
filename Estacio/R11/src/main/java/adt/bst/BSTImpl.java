package adt.bst;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

    private static final int ZERO = 0;
    private static final int ONE = 1;
    private static final int MINUS_ONE = -1;
    private final int EQUALS = 0;

    protected BSTNode<T> root;

    public BSTImpl() {
        root = new BSTNode<T>();
    }

    public BSTNode<T> getRoot() {
        return this.root;
    }

    @Override
    public boolean isEmpty() {
        return root.isEmpty();
    }

    @Override
    public int height() {
        return height(this.root);
    }

    /**
     * Recursively finds the height of the tree with the given node as root.
     *
     * @param node Root of the tree that will have the height returned.
     * @return The height of the tree.
     */
    private int height(BSTNode<T> node) {
        return (node.isEmpty()) ? MINUS_ONE
                : ONE + Math.max(this.height((BSTNode) node.getLeft()), this.height((BSTNode) node.getRight()));
    }

    @Override
    public BSTNode<T> search(T element) {
        return this.search(this.root, element);
    }

    /**
     * Recursively finds an element and if the given element isn't in the tree, returns
     * an empty node.
     *
     * @param node    Node to be compared with the element.
     * @param element Element to be found.
     * @return The node with data equal to the element, or an empty node if the element isn't in the tree.
     */
    private BSTNode<T> search(BSTNode<T> node, T element) {
        if (node.isEmpty() || node.getData().equals(element)) {
            return node;
        } else if (isBiggerThan(node, element)) {
            return this.search((BSTNode) node.getLeft(), element);
        } else {
            return this.search((BSTNode) node.getRight(), element);
        }
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            this.insert(null, this.root, element);
        }
    }

    /**
     * Recursively inserts an element in the tree.
     *
     * @param parent  Parent of the analysed node.
     * @param node    Analysed node.
     * @param element Element to be inserted.
     */
    private void insert(BSTNode<T> parent, BSTNode<T> node, T element) {
        if (node.isEmpty()) {
            node.setParent(parent);
            node.setData(element);
            node.setLeft(new BSTNode<T>());
            node.setRight(new BSTNode<T>());
        } else if (isBiggerThan(node, element)) {
            this.insert(node, (BSTNode) node.getLeft(), element);
        } else if (isSmallerThan(node, element)) {
            this.insert(node, (BSTNode) node.getRight(), element);
        }
    }

    @Override
    public BSTNode<T> maximum() {
        return this.maximum(this.root);
    }

    /**
     * Recursively finds the node with the maximum data in the tree.
     *
     * @param node Node to be analysed.
     * @return The node with the maximum data in the tree or null if the tree is empty.
     */
    private BSTNode<T> maximum(BSTNode<T> node) {
        if (node.isEmpty()) {
            return null;
        } else if (node.getRight().isEmpty()) {
            return node;
        } else {
            return this.maximum((BSTNode) node.getRight());
        }
    }

    @Override
    public BSTNode<T> minimum() {
        return this.minimum(this.root);
    }

    /**
     * Recursively finds the node with the minimum data in the tree.
     *
     * @param node Node to be analysed.
     * @return The node with the minimum data in the tree or null if the tree is empty.
     */
    private BSTNode<T> minimum(BSTNode<T> node) {
        if (node.isEmpty()) {
            return null;
        } else if (node.getLeft().isEmpty()) {
            return node;
        } else {
            return this.minimum((BSTNode) node.getLeft());
        }
    }

    @Override
    public BSTNode<T> sucessor(T element) {
        BSTNode<T> node = this.search(element);
        return (node.isEmpty()) ? null : successor(node);
    }

    /**
     * Recursively finds the successor of a node in the tree.
     *
     * @param node Node to be analysed.
     * @return The node with the data imediately greater than the analysed node's data or null if the
     * analysed node has the maximum data in the tree.
     */
    private BSTNode<T> successor(BSTNode<T> node) {
        BSTNode<T> successor = minimum((BSTNode) node.getRight());
        if (successor != null) {
            return successor;
        } else {
            successor = (BSTNode) node.getParent();
            while (successor != null && isSmallerThan(successor, node.getData())) {
                successor = (BSTNode) successor.getParent();
            }
            return successor;
        }
    }

    @Override
    public BSTNode<T> predecessor(T element) {
        BSTNode<T> node = this.search(element);
        return (node.isEmpty()) ? null : predecessor(node);
    }

    /**
     * Recursively finds the predecessor of a node in the tree.
     *
     * @param node Node to be analysed.
     * @return The node with the data immediately smaller than the analysed node's data or null if the
     * analysed node has the minimum data in the tree.
     */
    private BSTNode<T> predecessor(BSTNode<T> node) {
        BSTNode<T> predecessor = maximum((BSTNode) node.getLeft());
        if (predecessor != null) {
            return predecessor;
        } else {
            predecessor = (BSTNode) node.getParent();
            while (predecessor != null && isBiggerThan(predecessor, node.getData())) {
                predecessor = (BSTNode) predecessor.getParent();
            }
            return predecessor;
        }
    }

    @Override
    public void remove(T element) {
        if (element != null) {
            BSTNode<T> node = this.search(element);
            if (!node.isEmpty()) {
                this.remove(node);
            }
        }
    }

    /**
     * Removes a node of the tree.
     *
     * @param node Node to be removed.
     */
    private void remove(BSTNode<T> node) {
        if (node.isLeaf()) {
            node.setData(null);
        } else if (isOneDegree(node)) {
            this.removeOneDegree(node);
        } else {
            this.removeTwoDegree(node);
        }
    }

    /**
     * Removes a node that has two children.
     *
     * @param node Node to be removed.
     */
    private void removeTwoDegree(BSTNode<T> node) {
        BSTNode<T> auxNode = minimum((BSTNode) node.getRight());
        if (auxNode.isEmpty())
            maximum((BSTNode) node.getLeft());

        T aux = node.getData();
        node.setData(auxNode.getData());
        auxNode.setData(aux);
        remove(auxNode);
    }

    /**
     * Removes a node that has only one child.
     *
     * @param node Node to be removed.
     */
    private void removeOneDegree(BSTNode<T> node) {
        /* No need to verify if has only the right child */
        BSTNode<T> child = (hasOnlyLeftChild(node)) ? (BSTNode) node.getLeft() : (BSTNode) node.getRight();

        if (node.getParent() != null) {
            if (isLeftChild(node)) {
                node.getParent().setLeft(child);
            } else { /* is right child */
                node.getParent().setRight(child);
            }
            child.setParent(node.getParent());
        } else { /* is root */
            child.setParent(null);
            this.root = child;
        }
    }

    @Override
    public T[] preOrder() {
        T[] array = (T[]) new Comparable[this.size()];
        this.preOrder(array, this.root, ZERO);
        return array;
    }

    /**
     * Recursively fills an array with the elements of the BST according to the
     * pre-order traversing.
     *
     * @param array Array to be filled.
     * @param node  Node to traverse.
     * @param index Index of the available position in the array.
     * @return Index of the available position in the array after the pre-order traverse.
     */
    private int preOrder(T[] array, BSTNode<T> node, int index) {
        if (!node.isEmpty()) {
            array[index++] = node.getData();
            index = this.preOrder(array, (BSTNode) node.getLeft(), index);
            index = this.preOrder(array, (BSTNode) node.getRight(), index);
        }
        return index;
    }

    @Override
    public T[] order() {
        T[] array = (T[]) new Comparable[this.size()];
        this.order(array, this.root, ZERO);
        return array;
    }

    /**
     * Recursively fills an array with the elements of the BST according to the
     * symmetric order traversing.
     *
     * @param array Array to be filled.
     * @param node  Node to traverse.
     * @param index Index of the available position in the array.
     * @return Index of the available position in the array after the symmetric order traverse.
     */
    private int order(T[] array, BSTNode<T> node, int index) {
        if (!node.isEmpty()) {
            index = this.order(array, (BSTNode) node.getLeft(), index);
            array[index++] = node.getData();
            index = this.order(array, (BSTNode) node.getRight(), index);
        }
        return index;
    }

    @Override
    public T[] postOrder() {
        T[] array = (T[]) new Comparable[this.size()];
        this.postOrder(array, this.root, ZERO);
        return array;
    }

    /**
     * Recursively fills an array with the elements of the BST according to the
     * port-order traversing.
     *
     * @param array Array to be filled.
     * @param node  Node to traverse.
     * @param index Index of the available position in the array.
     * @return Index of the available position in the array after the post-order traverse.
     */
    private int postOrder(T[] array, BSTNode<T> node, int index) {
        if (!node.isEmpty()) {
            index = this.postOrder(array, (BSTNode) node.getLeft(), index);
            index = this.postOrder(array, (BSTNode) node.getRight(), index);
            array[index++] = node.getData();
        }
        return index;
    }

    /**
     * This method is already implemented using recursion. You must understand
     * how it work and use similar idea with the other methods.
     */
    @Override
    public int size() {
        return size(root);
    }

    private int size(BSTNode<T> node) {
        int result = 0;
        // base case means doing nothing (return 0)
        if (!node.isEmpty()) { // indusctive case
            result = 1 + size((BSTNode<T>) node.getLeft())
                    + size((BSTNode<T>) node.getRight());
        }
        return result;
    }

    // --- NODE METHODS ---

    /**
     * Verifies if the node has a bigger data than the element.
     *
     * @param node    Analysed node.
     * @param element Element to be analysed.
     * @return {@code true} if the node data is bigger than the element, {@code false} otherwise.
     */
    public boolean isBiggerThan(BSTNode<T> node, T element) {
        return !node.isEmpty() && element != null && node.getData().compareTo(element) > EQUALS;
    }

    /**
     * Verifies if the node has a smaller data than the element.
     *
     * @param node    Analysed node.
     * @param element Element to be analysed.
     * @return {@code true} if the node data is smaller than the element, {@code false} otherwise.
     */
    public boolean isSmallerThan(BSTNode<T> node, T element) {
        return !node.isEmpty() && element != null && node.getData().compareTo(element) < EQUALS;
    }

    /**
     * Verifies if the node has only one child.
     *
     * @param node Analysed node.
     * @return {@code true} if the node has only one child, {@code false} otherwise.
     */
    public boolean isOneDegree(BSTNode<T> node) {
        return this.hasOnlyLeftChild(node) || this.hasOnlyRightChild(node);
    }

    /**
     * Verifies if the node has only the left child.
     *
     * @param node Analysed node.
     * @return {@code true} if the node has only the left child, {@code false} otherwise.
     */
    public boolean hasOnlyLeftChild(BSTNode<T> node) {
        return (node.getRight().isEmpty() && !node.getLeft().isEmpty());
    }

    /**
     * Verifies if the node has only the right child.
     *
     * @param node Analysed node.
     * @return {@code true} if the node has only the right child, {@code false} otherwise.
     */
    public boolean hasOnlyRightChild(BSTNode<T> node) {
        return (!node.getRight().isEmpty() && node.getLeft().isEmpty());
    }

    /**
     * Verifies if the node is the left child of it's parent.
     *
     * @param node Analysed node.
     * @return {@code true} if the node is the left child of it's parent, {@code false} otherwise.
     */
    public boolean isLeftChild(BSTNode<T> node) {
        return !node.getParent().isEmpty()
                && !node.getParent().getLeft().isEmpty() &&
                node.getParent().getLeft().getData().equals(node.getData());
    }
}
