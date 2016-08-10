package adt.hashtable.closed;

import java.util.ArrayList;
import java.util.List;

import adt.hashtable.Util;
import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	/**
	 * A hash table with closed address works with a hash function with closed address.
	 * Such a function can follow one of these methods: DIVISION or MULTIPLICATION.
	 * In the DIVISION method, it is useful to change the size of the table to an integer 
	 * that is prime. This can be achieved by producing such a prime number that is bigger and 
	 * close to the desired size. 
	 * 
	 * For doing that, you have auxiliary methods: Util.isPrime and getPrimeAbove as documented 
	 * bellow. 
	 * 
	 * The length of the internal table must be the immediate prime number greater than the 
	 * given size. For example, if size=10 then the length must be 11. If size=20, 
	 * the length must be 23. You must implement this idea in the auxiliary method 
	 * getPrimeAbove(int size) and use it.
	 * 
	 * @param desiredSize
	 * @param method
	 */
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize  = desiredSize;
		
		if(method == HashFunctionClosedAddressMethod.DIVISION){
			realSize = this.getPrimeAbove(desiredSize); //real size must the the immediate prime above
		} 
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}
	
	//AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given number.  
	 * You can use the method Util.isPrime to check if a number is prime.
	 */
	int getPrimeAbove(int number){
		int prime = number;
		if (prime % 2 == 0) {
			prime++;
		}
		while (!Util.isPrime(prime)) {
			prime += 2;
		}
		return prime;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void insert(T element) {
		if (element == null)
			return ;
		int index = this.getHashIndex(element);
		if (this.table[index] == null) {
			List<T> array = new ArrayList<T>();
			array.add(element);
			this.table[index] = array;
		} else {
			List<T> array = (List<T>) this.table[index];
			array.add(element);
			this.COLLISIONS++;
		}
		this.elements++;
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int getHashIndex(T element) {
		int index = 0;
		if(this.hashFunction instanceof HashFunctionClosedAddress) {
			index = ((HashFunctionClosedAddress) this.hashFunction).hash(element);
		}
		return index;
	}

	@SuppressWarnings("unchecked")
	@Override
	public void remove(T element) {
		int index = this.indexOf(element);
		if (index != -1) {
			List<T> array = (List<T>) this.table[index];
			array.remove(element);
			this.elements--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		int index = this.getHashIndex(element);
		T result = null;
		if (this.table[index] != null) { // if list exists
			List<T> array = (List<T>) this.table[index];
			int indexElement = array.indexOf(element);
			if (indexElement != -1) { // if element exists
				result = array.get(indexElement);
			}
		}
		return result;
	}

	@SuppressWarnings("unchecked")
	@Override
	public int indexOf(T element) {
		int index = this.getHashIndex(element);
		if (this.table[index] != null) { // if list exists
			List<T> array = (List<T>) this.table[index];
			if (array.contains(element)) {
				return index;
			}
		}
		return -1;
	}

}
