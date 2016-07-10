package adt.hashtable.closed;

import java.util.ArrayList;
import java.util.List;

import adt.hashtable.AbstractHashtable;
import adt.hashtable.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends
		AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed
	 * address. Such a function can follow one of these methods: DIVISION or
	 * MULTIPLICATION. In the DIVISION method, it is useful to change the size
	 * of the table to an integer that is prime. This can be achieved by
	 * producing such a prime number that is bigger and close to the desired
	 * size.
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and
	 * getPrimeAbove as documented bellow.
	 * 
	 * The length of the internal table must be the immediate prime number
	 * greater than the given size. For example, if size=10 then the length must
	 * be 11. If size=20, the length must be 23. You must implement this idea in
	 * the auxiliary method getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */

	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize,
			HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

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
		for (int i = number; i < Integer.MAX_VALUE; i++) {
			if (Util.isPrime(i)) {
				return i;
			}
		}
		return Integer.MIN_VALUE;
	}

	@Override
	public void insert(T element) {
		int valueHash = ((HashFunctionClosedAddress<T>) hashFunction)
				.hash(element);
		if(!(element==null)){
		if (table[valueHash] == null) {
			table[valueHash] = new ArrayList<T>();
		}else{
			COLLISIONS++;
		}
		if (!((List) table[valueHash]).contains(element)) {
			((List) table[valueHash]).add(element);
			elements++;
		}
		}
	
	}

	@Override
	public void remove(T element) {
		int valueHash = ((HashFunctionClosedAddress<T>) hashFunction)
				.hash(element);
		if((table[valueHash] != null)){
		boolean remove = ((List) table[valueHash]).remove(element);
		if (remove) {
			elements--;
		}
		}
	}

	@Override
	public T search(T element) {
		int valueHash = ((HashFunctionClosedAddress<T>) hashFunction)
				.hash(element);
		if (table[valueHash]!= null && ((List) table[(valueHash)]).contains(element)) {
			return element;
		} else {
			return null;
		}
	}

	@Override
	public int indexOf(T element) {
		int valueHash = ((HashFunctionClosedAddress<T>) hashFunction)
				.hash(element);
		for (int i = 0; i < table.length; i++) {
			if (table[i]!= null  && ((List) table[i]).contains(element)) {
				return ((HashFunctionClosedAddress<T>) hashFunction)
						.hash(element);
			}

		}
		return -1;
	}

}
