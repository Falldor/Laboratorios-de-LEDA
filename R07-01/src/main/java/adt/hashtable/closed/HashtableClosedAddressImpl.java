package adt.hashtable.closed;

import adt.hashtable.hashfunction.HashFunction;
import adt.hashtable.hashfunction.HashFunctionClosedAddress;
import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionFactory;
import util.Util;
import java.util.LinkedList;

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
	 * greater than the given size (or the given size, if it is already prime). 
	 * For example, if size=10 then the length must
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
	 * number.
	 * If the given number is prime, it is returned. 
	 * You can use the method Util.isPrime to check if a number is
	 * prime.
	 */
	int getPrimeAbove(int number) {
		while (!Util.isPrime(number)){
			number++;
		}
		return number;
	}

	@Override
	public void insert(T element) {

		int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
		LinkedList<T> tabela = (LinkedList<T>) this.table[index];

		if (tabela == null) {
			tabela = new LinkedList<T>();
			tabela.add(element);
			this.table[index] = tabela;
			this.elements++;
		} else {
			if (!tabela.contains(element)) {
				tabela.add(element);
				this.COLLISIONS++;
				this.elements++;
			}
		}

	}

	@Override
	public void remove(T element) {
		int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));

		LinkedList<T> tabela = ((LinkedList<T>) this.table[index]);
		if (tabela != null){
			if (tabela.size() > 1){
				this.COLLISIONS--;
			}

			tabela.remove(element);
			this.elements--;

			if (tabela.isEmpty()){
				this.table[index] = null;
			}
		}
	}

	@Override
	public T search(T element) {
		T resultadoBusca = null;

		int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));
		LinkedList<T> tabela = ((LinkedList<T>) this.table[index]);

		if (tabela != null) {
			if (tabela.contains(element)) {
				resultadoBusca = tabela.get(tabela.indexOf(element));
			}
		}
		return resultadoBusca;
	}

	@Override
	public int indexOf(T element) {
		int resultado = -1;
		int index = Math.abs(((HashFunctionClosedAddress<T>) this.hashFunction).hash(element));

		LinkedList<T> tabela = ((LinkedList<T>) this.table[index]);

		if (tabela != null) {
			if (tabela.contains(element))
				resultado = index;
		}

		return resultado;
	}

}
