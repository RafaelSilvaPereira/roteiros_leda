package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
        AbstractHashtableOpenAddress<T> {

    private static final int ZERO = 0;
    private static final int INVALID_INDEX = -1;

    public HashtableOpenAddressLinearProbingImpl(int size,
                                                 HashFunctionClosedAddressMethod method) {
        super((size < ZERO) ? ZERO : size);
        this.hashFunction = new HashFunctionLinearProbing<T>(size, method);
        this.initiateInternalTable(size);
    }

    @Override
    public void insert(T element) {
        if (element != null && !isFull()) {
            int probe = ZERO;
            int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
            while (!canPutElement(hash)) {
                hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, ++probe);
                this.COLLISIONS++;
            }

            this.table[hash] = element;
            this.elements++;
        } else if (isFull()) {
            throw new HashtableOverflowException();
        }
    }

    @Override
    public void remove(T element) {
        if (element != null && !isEmpty()) {
            int index = this.indexOf(element);
            if (this.containsElement(index)) {

                int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, ZERO);
                this.COLLISIONS -= Math.abs(index - hash);
                this.table[index] = deletedElement;
                this.elements--;
            }
        }
    }

    @Override
    public T search(T element) {
        return (this.containsElement(element)) ? element : null;
    }

    @Override
    public int indexOf(T element) {
        if (element != null && !isEmpty()) {
            int probe = ZERO;
            int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);

            while (!resetedProbe(element, probe)
                    && this.table[hash] != null
                    && !this.table[hash].equals(element)) {
                hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, ++probe);
            }

            return (!resetedProbe(element, probe) && this.table[hash] != null
                    && this.table[hash].equals(element)) ? hash : INVALID_INDEX;
        } else {
            return INVALID_INDEX;
        }
    }

    /**
     * Verifies if the element can be put in a position of the table.
     *
     * @param hash Hash of the element.
     * @return {@code true} if the element can be put, {@code false} otherwise.
     */
    private boolean canPutElement(int hash) {
        return this.table[hash] == null
                || this.table[hash].equals(deletedElement);
    }

    /**
     * Verifies if the hash function looped.
     *
     * @param element Element to be searched in the table.
     * @param probe   Number of the probe.
     * @return {@code true} if the hash function looped, {@code false} otherwise.
     */
    private boolean resetedProbe(T element, int probe) {
        int initialHash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, ZERO);
        int hash = ((HashFunctionLinearProbing) this.hashFunction).hash(element, probe);
        return (probe != ZERO && hash == initialHash);
    }


    /**
     * Verifies if an element is in the table.
     *
     * @param element Element to be verified.
     * @return {@code true} if the element is in the table, {@code false} otherwise.
     */
    private boolean containsElement(T element) {
        return this.containsElement(this.indexOf(element));
    }

    /**
     * Verifies if an element is in the table given it's index.
     *
     * @param index Index of the element.
     * @return {@code true} if the element is in the table, {@code false} otherwise.
     */
    private boolean containsElement(int index) {
        return index != INVALID_INDEX;
    }

}
