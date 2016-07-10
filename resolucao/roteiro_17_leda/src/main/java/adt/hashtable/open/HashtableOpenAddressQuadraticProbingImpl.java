package adt.hashtable.open;

import java.util.List;

import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull()) {
			throw new HashtableOverflowException();
		}
		if (element != null) {
			boolean insert = false;
			int valueProb = 0;
			if (search(element) == null) {
				while (!insert) {
					int valueHash = ((HashFunctionOpenAddress<T>) hashFunction)
							.hash(element, valueProb);
					if (table[valueHash] == null
							|| table[valueHash] instanceof DELETED) {
						table[valueHash] = element;
						elements++;
						insert = true;
					} else {
						valueProb++;
						COLLISIONS++;
					}

				}
			}
		}

	}

	@Override
	public void remove(T element) {
		int index = indexOf(element);
		if (index != -1) {
			table[index] = deletedElement;
			elements--;
		}
	}

	@Override
	public T search(T element) {
		int valueProb = 1;
		do {
			int valueHash = ((HashFunctionOpenAddress<T>) hashFunction).hash(
					element, valueProb);
			if (table[valueHash] == null) {
				return null;
			} else if (element.equals(table[valueHash])) {
				return (T) table[valueHash];

			}
			valueProb++;

		} while (((HashFunctionOpenAddress<T>) hashFunction).hash(element,
				valueProb) != ((HashFunctionOpenAddress<T>) hashFunction).hash(
				element, 1));
		return null;
	}

	@Override
	public int indexOf(T element) {
		for (int i = 0; i < table.length; i++) {
			if (table[i] != null && element.equals(table[i])) {
				return i;
			}

		}
		return -1;
	}
}
