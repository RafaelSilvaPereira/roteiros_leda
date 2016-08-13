package adt.hashtable.closed;

import static util.Util.isPrime;

import java.util.LinkedList;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;

public class HashtableClosedAddressImpl<T> extends AbstractHashtableClosedAddress<T> {

	private static final int ONE = 1;

	private static final int ZERO = 0;

	private static final int IS_EVEN = ZERO;

	private static final int NOT_FOUND = -1;

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
	public HashtableClosedAddressImpl(int desiredSize, HashFunctionClosedAddressMethod method) {
		int realSize = desiredSize;

		if (method == HashFunctionClosedAddressMethod.DIVISION) {
			// real size must the the immediate prime above
			realSize = this.getPrimeAbove(desiredSize);
		}
		initiateInternalTable(realSize);
		HashFunction function = HashFunctionFactory.createHashFunction(method, realSize);
		this.hashFunction = function;
	}

	// AUXILIARY
	/**
	 * It returns the prime number that is closest (and greater) to the given
	 * number. You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		// as long as we want a greater prime number
		number++;
		// we don't need to check the even ones
		if (number % 2 == IS_EVEN)
			number++;
		while (!isPrime(number))
			number += 2;
		return number;
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			int indexCell = getHashFunction().hash(element);
			boolean containsElement = searchElementCellPosition(element) != NOT_FOUND;
			boolean positionHasElements = !getCell(indexCell).isEmpty();
			if (!containsElement) {
				if (positionHasElements)
					COLLISIONS++;
				getCell(indexCell).add(element);
				elements++;
			}
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			int indexCell = getHashFunction().hash(element);
			int elementCellPosition = searchElementCellPosition(element);
			int cellSize = getCell(indexCell).size();
			if (elementCellPosition != NOT_FOUND) {
				if (cellSize > ONE)
					COLLISIONS--;
				getCell(indexCell).remove(elementCellPosition);
				elements--;
			}
		}
	}

	@Override
	public T search(T element) {
		T result = null;
		if (element != null && indexOf(element) != NOT_FOUND) {
			int indexCell = getHashFunction().hash(element);
			int elementPosInCell = searchElementCellPosition(element);
			result = getCell(indexCell).get(elementPosInCell);
		}
		return result;
	}

	@Override
	public int indexOf(T element) {
		int index = NOT_FOUND;
		if (element != null && !this.isEmpty()) {
			int elementPosition = getHashFunction().hash(element);
			if (searchElementCellPosition(element) != NOT_FOUND) {
				index = elementPosition;
			}
		}
		return index;
	}

	@Override
	public HashFunctionClosedAddress<T> getHashFunction() {
		return (HashFunctionClosedAddress<T>) super.getHashFunction();
	}

	@SuppressWarnings("unchecked")
	private LinkedList<T> getCell(int index) {
		if (super.table[index] == null)
			super.table[index] = new LinkedList<T>();
		return (LinkedList<T>) super.table[index];
	}

	/**
	 * Searches for the index of the element in it's relative cell at the table
	 * 
	 * @return Index of the element in the cell
	 */
	private int searchElementCellPosition(T element) {
		int indexCell = getHashFunction().hash(element);
		LinkedList<T> cell = getCell(indexCell);
		int elementIndexInCell = ZERO;
		int elementHash = element.hashCode();
		for (T walker : cell) {
			int walkerHash = walker.hashCode();
			// assuming that elements are equals if their hashcode are equals
			if (walkerHash == elementHash) {
				break;
			}
			elementIndexInCell++;
		}
		if (elementIndexInCell == cell.size())
			elementIndexInCell = NOT_FOUND;
		return elementIndexInCell;
	}
}
