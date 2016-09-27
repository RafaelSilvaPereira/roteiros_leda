package adt.btree;

import java.util.Collections;
import java.util.LinkedList;

public class BNode<T extends Comparable<T>> {
    private static final int FIRST_INDEX = 0;
    protected LinkedList<T> elements; //PODERIA TRABALHAR COM ARRAY TAMBEM
    protected LinkedList<BNode<T>> children; //PODERIA TRABALHAR COM ARRAY TAMBEM
    protected BNode<T> parent;
    protected int maxKeys;
    protected int maxChildren;

    public BNode(int order) {
        this.maxChildren = order;
        this.maxKeys = order - 1;
        this.elements = new LinkedList<T>();
        this.children = new LinkedList<BNode<T>>();
    }

    @Override
    public String toString() {
        return this.elements.toString();
    }

    @Override
    public boolean equals(Object obj) {
        boolean resp = false;
        if (obj != null) {
            if (obj instanceof BNode) {
                if (this.size() == ((BNode<T>) obj).size()) {
                    resp = true;
                    int i = 0;
                    while (i < this.size() && resp) {
                        resp = resp && this.getElementAt(i).equals(((BNode<T>) obj).getElementAt(i));
                        i++;
                    }
                }
            }
        }
        return resp;
    }

    public boolean isEmpty() {
        return this.size() == 0;
    }

    public int size() {
        return this.elements.size();
    }

    public boolean isLeaf() {
        return this.children.size() == 0;
    }

    public boolean isFull() {
        return this.size() == maxKeys;
    }

    public void addElement(T element) {
        this.elements.add(element);
        Collections.sort(elements);
    }

    public void removeElement(T element) {
        this.elements.remove(element);
    }

    public void removeElement(int position) {
        this.elements.remove(position);
    }

    public void addChild(int position, BNode<T> child) {
        this.children.add(position, child);
        child.parent = this;
    }

    public void removeChild(BNode<T> child) {
        this.children.remove(child);
    }

    public int indexOfChild(BNode<T> child) {
        return this.children.indexOf(child);
    }

    public T getElementAt(int index) {
        return this.elements.get(index);
    }

    protected void split() {
        BNode<T> aux = new BNode<T>(this.maxChildren);
        int middleElement = (this.getElements().size() / 2);
        aux.addElement(this.getElements().get(middleElement));

        BNode<T> leftChild = new BNode<T>(this.maxChildren);
        BNode<T> rightChild = new BNode<T>(this.maxChildren);
        aux.addChild(FIRST_INDEX, leftChild);
        aux.addChild(FIRST_INDEX + 1, rightChild);

        // Copy elements
        for (int index = FIRST_INDEX; index < middleElement; index++) {
            leftChild.addElement(this.getElements().get(index));
        }
        for (int index = middleElement + 1; index < this.getElements().size(); index++) {
            rightChild.addElement(this.getElements().get(index));
        }

        // Children
        for (int index = FIRST_INDEX; index <= middleElement && index < this.getChildren().size(); index++) {
            leftChild.addChild(index, this.getChildren().get(index));
        }
        for (int childIndex = FIRST_INDEX, thisIndex = middleElement + 1; thisIndex < this.getChildren().size();
             thisIndex++, childIndex++) {
            rightChild.addChild(childIndex, this.getChildren().get(thisIndex));
        }

        this.setElements(aux.getElements());
        this.setChildren(aux.getChildren());
    }

    protected void promote() {
        this.split();
        T element = this.getElements().get(FIRST_INDEX);
        this.parent.addElement(element);
        int elementIndex = this.parent.getElements().indexOf(element);

        if(this.parent.getChildren().size() > elementIndex)
            this.parent.removeChild(this.parent.getChildren().get(elementIndex));
        this.parent.addChild(elementIndex, this.getChildren().get(FIRST_INDEX));
        this.parent.addChild(elementIndex + 1, this.getChildren().get(FIRST_INDEX + 1));

        this.getChildren().get(FIRST_INDEX).setParent(this.parent);
        this.getChildren().get(FIRST_INDEX + 1).setParent(this.parent);
    }

    public LinkedList<T> getElements() {
        return elements;
    }

    public void setElements(LinkedList<T> elements) {
        this.elements = elements;
    }

    public LinkedList<BNode<T>> getChildren() {
        return children;
    }

    public void setChildren(LinkedList<BNode<T>> children) {
        this.children = children;
    }

    public BNode<T> copy() {
        BNode<T> result = new BNode<T>(maxChildren);
        result.parent = parent;
        for (int i = 0; i < this.elements.size(); i++) {
            result.addElement(this.elements.get(i));
        }
        for (int i = 0; i < this.children.size(); i++) {
            result.addChild(i, ((BNode<T>) this.children.get(i)).copy());
        }

        return result;
    }

    public BNode<T> getParent() {
        return parent;
    }

    public void setParent(BNode<T> parent) {
        this.parent = parent;
    }

    public int getMaxKeys() {
        return maxKeys;
    }

    public void setMaxKeys(int maxKeys) {
        this.maxKeys = maxKeys;
    }

    public int getMaxChildren() {
        return maxChildren;
    }

    public void setMaxChildren(int maxChildren) {
        this.maxChildren = maxChildren;
    }


//    public String toString() {
//        String saida = this.elements.toString();
//        if (!this.children.isEmpty())
//            saida += "\n";
//        for (BNode<T> n : this.children) {
//            saida += "=" + n.toString();
//        }
//        return saida;
//    }
}
