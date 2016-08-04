package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
        DoubleLinkedList<T> {

    protected final int ONE = 1;

    protected DoubleLinkedListNode<T> last;

    @Override
    public void insertFirst(T element) {
        if (element != null) {
            SingleLinkedListNode<T> node = new SingleLinkedListNode<>(element, this.getHead());
            this.setHead(node);
        }
    }

    @Override
    public void removeFirst() {
        if (!this.getHead().isNIL()) {
            if (this.getHead().getNext() != null) {
                this.setHead(this.getHead().getNext());
            } else {
                this.setHead(new SingleLinkedListNode<T>());
            }
        }
    }

    @Override
    public void removeLast() {

        if (size() > ONE) {
            if (this.getLast() != null
                    && this.getLast().getPrevious() != null) {
                this.getLast().getPrevious().setNext(null);
                this.setLast(this.getLast().getPrevious());
            }
        } else if (size() == ONE) {
            this.getHead().setData(null);
            this.getLast().setData(null);
        }
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            DoubleLinkedListNode<T> node = new DoubleLinkedListNode<>(element, null, this.getLast());
            if (isEmpty()) {
                this.setHead(node);
                this.setLast(node);
            } else {
                this.getLast().setNext(node);
                this.setLast(node);
            }
        }
    }

    @Override
    public void remove(T element) {
        if (!isEmpty()) {
            if (this.getHead().getData().equals(element)) {
                this.removeFirst();
            } else if (this.getLast().getData().equals(element)) {
                this.removeLast();
            } else {
                DoubleLinkedListNode<T> node = this.getLast();
                DoubleLinkedListNode<T> remove = null;

                while(!node.isNIL()
                        && node.getPrevious() != null){
                    if(node.getData().equals(element)){
                        remove = node;
                    }
                    node = node.getPrevious();
                }

                if(remove != null){
                    remove.getPrevious().setNext(remove.getNext());
                    ((DoubleLinkedListNode<T>) remove.getNext())
                            .setPrevious(remove.getPrevious());
                }
            }
        }
    }

    public DoubleLinkedListNode<T> getLast() {
        return last;
    }

    public void setLast(DoubleLinkedListNode<T> last) {
        this.last = last;
    }

}
