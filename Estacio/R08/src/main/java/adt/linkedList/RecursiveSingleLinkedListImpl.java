package adt.linkedList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

    private static final int UM = 1;
    private static final int ZERO = 0;
    protected T data;
    protected RecursiveSingleLinkedListImpl<T> next;

    public RecursiveSingleLinkedListImpl() {

    }

    public RecursiveSingleLinkedListImpl(T data,
                                         RecursiveSingleLinkedListImpl<T> next) {
        this.data = data;
        this.next = next;
    }

    @Override
    public boolean isEmpty() {
        return isNull();
    }

    protected boolean isNull() {
        return this.getData() == null;
    }

    @Override
    public int size() {
        if (isNull()) {
            return 0;
        } else {
            return UM + this.getNext().size();
        }
    }

    @Override
    public T search(T element) {
        if (this.isNull() || this.getData().equals(element)) {
            return this.getData();
        } else {
            return this.getNext().search(element);
        }
    }

    @Override
    public void insert(T element) {
        if (element != null) {
            if (isNull()) {
                this.setData(element);
                this.setNext(new RecursiveSingleLinkedListImpl<T>());
            } else {
                this.getNext().insert(element);
            }
        }
    }

    @Override
    public void remove(T element) {
        if (element != null && !isNull()) {
            if (this.getData().equals(element)) {
                this.setData(this.getNext().getData());
                this.setNext(this.getNext().getNext());
            } else {
                this.getNext().remove(element);
            }
        }
    }

    @Override
    public T[] toArray() {
        if(!isEmpty() && !isNull()) {
            T[] array = (T[]) new Object[this.size()];
            int arrayIndex = ZERO;
            array[arrayIndex++] = this.getData();

            T[] nextArray = this.getNext().toArray();

            for (int index = ZERO; index < nextArray.length; index++) {
                array[arrayIndex++] = nextArray[index];
            }

            return array;
        }else{
            return (T[]) new Object[ZERO];
        }
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public RecursiveSingleLinkedListImpl<T> getNext() {
        return next;
    }

    public void setNext(RecursiveSingleLinkedListImpl<T> next) {
        this.next = next;
    }

}
