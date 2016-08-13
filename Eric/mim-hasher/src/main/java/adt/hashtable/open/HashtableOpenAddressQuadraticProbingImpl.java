package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable> 
	extends AbstractHashtableOpenAddress<T> {
	
	public HashtableOpenAddressQuadraticProbingImpl(int size, 
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		initiateInternalTable(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	private int getHashIndex(T element, int probe) {
		int index = 0;
		if(this.hashFunction instanceof HashFunctionOpenAddress) {
			index = ((HashFunctionOpenAddress) this.hashFunction).hash(element, probe);
		}
		return index;
	}
	
	@Override
	public void insert(T element) {
		if (element == null)
			return ;
		if (this.isFull()) {
			throw new HashtableOverflowException();
		}
		
		int probe = -1;
		int index = 0;
		
		do {
			index = getHashIndex(element, ++probe);
		} while (super.table[index] != null && 
				super.table[index] != super.deletedElement && 
				probe < this.table.length);
		
		if (probe < this.table.length) {
			super.table[index] = element;
			this.elements++;
			this.COLLISIONS += probe;
		}
	}

	@Override
	public void remove(T element) {
		if (element == null)
			return ;
		
		int probe = -1;
		int index = 0;
		
		do {
			index = getHashIndex(element, ++probe);
		} while (super.table[index] != null && !super.table[index].equals(element) && 
				probe < this.table.length);
		
		if (probe < this.table.length && super.table[index] != null && super.table[index].equals(element)) {
			super.table[index] = super.deletedElement;
			this.elements--;
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T search(T element) {
		if (element == null)
			return null;
		
		int probe = -1;
		int index = 0;
		
		do {
			index = getHashIndex(element, ++probe);
		} while (super.table[index] != null && !super.table[index].equals(element) && 
				probe < this.table.length);
		
		if (probe < this.table.length && super.table[index] != null && super.table[index].equals(element)) {
			return (T) super.table[index];
		}
		
		return null;
	}

	@Override
	public int indexOf(T element) {
		if (element == null)
			return -1;
		
		int probe = -1;
		int index = 0;
		
		do {
			index = getHashIndex(element, ++probe);
		} while (super.table[index] != null && !super.table[index].equals(element) && 
				probe < this.table.length);
		
		if (probe < this.table.length && super.table[index] != null && super.table[index].equals(element)) {
			return index;
		}
		
		return -1;
	}
}
