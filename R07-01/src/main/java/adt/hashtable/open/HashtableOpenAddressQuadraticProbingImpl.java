package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionOpenAddress;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	@Override
	public void insert(T element) {
		if (isFull())
			throw new HashtableOverflowException();



		int contador = 0;
		boolean guarda = true;
		int index = -1;

		while (guarda && contador < capacity()) {
			index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, contador++);
			if (this.table[index] == null || this.table[index].equals(new DELETED())) {
				this.table[index] = element;
				this.elements++;
				guarda = false;
			} else {
				this.COLLISIONS++;
			}
		}
	}

	@Override
	public void remove(T element) {
		int contador = 0;
		boolean guarda = true;
		int index;

		while (guarda && contador < capacity()) {
			index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, contador++);
			if (this.table[index] != null && this.table[index].equals(element)) {
				this.table[index] = new DELETED();
				this.elements--;
				guarda = false;
			}
		}
	}

	@Override
	public T search(T element) {
		int contador = 0;
		boolean guarda = true;
		T resultado = null;
		int index;


		while (guarda && contador < capacity()) {

			index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, contador++);

			if (this.table[index] != null && this.table[index].equals(element)) {
				resultado = (T) this.table[index];
				guarda = false;
			}
		}
		return resultado;
	}

	@Override
	public int indexOf(T element) {
		int contador = 0;
		boolean guarda = true;
		int index;
		int resultado = -1;

		while (guarda && contador < capacity()) {
			index = ((HashFunctionOpenAddress<T>) this.hashFunction).hash(element, contador++);
			if (this.table[index] != null && this.table[index].equals(element)) {
				resultado = index;
				guarda = false;
			}
		}
		return resultado;
	}
}
