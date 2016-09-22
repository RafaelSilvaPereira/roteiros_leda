package adt.bt;

import adt.bst.BSTNode;

public class Util {


    /**
     * A rotacao a esquerda em node deve subir o seu filho a direita e retorna-lo em seguida
     *
     * @param node
     * @return
     */
    public static <T extends Comparable<T>> BSTNode<T> leftRotation(BSTNode<T> node) {
        BSTNode<T> rightChild = (BSTNode<T>) node.getRight();

        node.setRight(rightChild.getLeft());
        rightChild.getLeft().setParent(node);
        rightChild.setLeft(node);
        if (node.getParent() != null) {
            if (isLeftChild(node)) {
                node.getParent().setLeft(rightChild);
            } else {
                node.getParent().setRight(rightChild);
            }
        }
        rightChild.setParent(node.getParent());
        node.setParent(rightChild);

        return rightChild;
    }

    private static <T extends Comparable<T>> boolean isLeftChild(BSTNode<T> node) {
        return node.getParent() != null && !node.getParent().isEmpty()
                && !node.getParent().getLeft().isEmpty() &&
                node.getParent().getLeft().getData().equals(node.getData());
    }

    /**
     * A rotacao a direita em node deve subir seu filho a esquerda s retorna-lo em seguida
     *
     * @param node
     * @return
     */
    public static <T extends Comparable<T>> BSTNode<T> rightRotation(BSTNode<T> node) {
        BSTNode<T> leftChild = (BSTNode<T>) node.getLeft();

        node.setLeft(leftChild.getRight());
        leftChild.getRight().setParent(node);
        leftChild.setRight(node);
        if (node.getParent() != null) {
            if (isLeftChild(node)) {
                node.getParent().setLeft(leftChild);
            } else {
                node.getParent().setRight(leftChild);
            }
        }
        leftChild.setParent(node.getParent());
        node.setParent(leftChild);

        return leftChild;
    }

}
