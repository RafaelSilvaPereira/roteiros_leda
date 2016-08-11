package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import util.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

import java.util.LinkedList;

public class HashtableClosedAddressImpl<T> extends
        AbstractHashtableClosedAddress<T> {

    private static final int INVALID_INDEX = -1;
    private static final int FIRST_INDEX = 0;
    private static final int ZERO = 0;

    /**
     * A hash table with closed address works with a hash function with closed
     * address. Such a function can follow one of these methods: DIVISION or
     * MULTIPLICATION. In the DIVISION method, it is useful to change the size
     * of the table to an integer that is prime. This can be achieved by
     * producing such a prime number that is bigger and close to the desired
     * size.
     * <p>
     * For doing that, you have auxiliary methods: Util.isPrime and
     * getPrimeAbove as documented bellow.
     * <p>
     * The length of the internal table must be the immediate prime number
     * greater than the given size. For example, if size=10 then the length must
     * be 11. If size=20, the length must be 23. You must implement this idea in
     * the auxiliary method getPrimeAbove(int size) and use it.
     *
     * @param desiredSize
     * @param method
     */

    @SuppressWarnings({"rawtypes", "unchecked"})
    public HashtableClosedAddressImpl(int desiredSize,
                                      HashFunctionClosedAddressMethod method) {
        int realSize = (desiredSize >= ZERO) ? desiredSize : ZERO; // I don't think you'd do that, but... Trust no one.

        if (method == HashFunctionClosedAddressMethod.DIVISION) {
            realSize = this.getPrimeAbove(desiredSize); // real size must the
            // the immediate prime
            // above
        }
        initiateInternalTable(realSize);
        HashFunction function = HashFunctionFactory.createHashFunction(method,
                realSize);
        this.hashFunction = function;
    }

    // AUXILIARY

    /**
     * It returns the prime number that is closest (and greater) to the given
     * number. You can use the method Util.isPrime to check if a number is
     * prime.
     */
    int getPrimeAbove(int number) {
        while (!Util.isPrime(++number)) ; // An empty while doesn't need to have braces
        return number;
    }

    @Override
    public void insert(T element) {
        if (element != null) {

            int hash = ((HashFunctionClosedAddress) this.hashFunction)
                    .hash(element);

            int elementIndex = this.chainIndexOf(element, hash);

            if (!this.containsElement(elementIndex)) {
                this.insertNewElement(element, hash);
            } else {
                ((LinkedList<T>) this.table[hash]).set(elementIndex, element);
            }
        }
    }

    /**
     * Inserts a new element at the table.
     *
     * @param element Element to be added.
     * @param hash    Hash of the element.
     */
    private void insertNewElement(T element, int hash) {

        if (this.table[hash] == null) {
            this.table[hash] = new LinkedList<>();
        }
        if (((LinkedList<T>) this.table[hash]).size() > ZERO) {
            this.COLLISIONS++;
        }

        ((LinkedList<T>) this.table[hash]).add(element);
        this.elements++;
    }

    @Override
    public void remove(T element) {
        int hash = ((HashFunctionClosedAddress) this.hashFunction)
                .hash(element);

        int elementIndex = this.chainIndexOf(element, hash);

        if (this.containsElement(elementIndex)) {
            ((LinkedList<T>) this.table[hash]).remove(elementIndex);
            this.elements--;
        }
    }

    @Override
    public T search(T element) {
        return (this.containsElement(element)) ? element : null;
    }

    @Override
    public int indexOf(T element) {
        int hash = ((HashFunctionClosedAddress) this.hashFunction)
                .hash(element);

        return (this.table[hash] == null || // Just to be explicit, because this verification happens at the beginning of the chainIndexOf method
                !this.containsElement(this.chainIndexOf(element, hash))) ? INVALID_INDEX : hash;
    }

    /**
     * A method that returns the index of the element in the chain (the list of elements with the same hash)
     *
     * @param element Element to be indexed.
     * @param hash    Hash of the element to be indexed.
     * @return Index of the element in the chain.
     */
    private int chainIndexOf(T element, int hash) {

        if (this.table[hash] == null) {
            return INVALID_INDEX;
        }
        for (int index = FIRST_INDEX; index < ((LinkedList<T>) this.table[hash]).size(); index++) {
            T tableElement = ((LinkedList<T>) this.table[hash]).get(index);
            if (tableElement.hashCode() == element.hashCode()) {
                return index;
            }
        }

        return INVALID_INDEX;
    }

    /**
     * Verifies if the element is in the table.
     *
     * @param element Element to be verified.
     * @return {@Code true} if the element is in the table.
     */
    private boolean containsElement(T element) {
        return this.containsElement(this.indexOf(element));
    }

    /**
     * Verifies if the element is in the table / chain.
     * @param chainIndex index of the element in the chain (list of elements with the same hash).
     * @return {@Code true} if the element is in the table / chain.
     */
    private boolean containsElement(int chainIndex) {
        return (chainIndex != INVALID_INDEX);
    }

}
