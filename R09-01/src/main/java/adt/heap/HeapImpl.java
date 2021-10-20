package adt.heap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;

import util.Util;

/**
 * O comportamento de qualquer heap é definido pelo heapify. Neste caso o
 * heapify dessa heap deve comparar os elementos e colocar o menor sempre no
 * topo. Ou seja, admitindo um comparador normal (responde corretamente 2 < 3),
 * essa heap deixa os elementos menores no topo. Essa comparação não é feita
 * diretamente com os elementos armazenados, mas sim usando um comparator. 
 * Dessa forma, dependendo do comparator, a heap pode funcionar como uma max-heap 
 * ou min-heap.
 */
public class HeapImpl<T extends Comparable<T>> implements Heap<T> {

	protected T[] heap;
	protected int index = -1;
	/**
	 * O comparador é utilizado para fazer as comparações da heap. O ideal é
	 * mudar apenas o comparator e mandar reordenar a heap usando esse
	 * comparator. Assim os metodos da heap não precisam saber se vai funcionar
	 * como max-heap ou min-heap.
	 */
	protected Comparator<T> comparator;

	private static final int INITIAL_SIZE = 20;
	private static final int INCREASING_FACTOR = 10;

	/**
	 * Construtor da classe. Note que de inicio a heap funciona como uma
	 * min-heap. OU seja, voce deve considerar que a heap usa o comparator
	 * interno e se o comparator responde compare(x,y) < 0 entao o x eh menor
	 * e sobe na heap.
	 */
	@SuppressWarnings("unchecked")
	public HeapImpl(Comparator<T> comparator) {
		this.heap = (T[]) (new Comparable[INITIAL_SIZE]);
		this.comparator = comparator;
	}

	// /////////////////// METODOS IMPLEMENTADOS
	private int parent(int i) {
		return (i - 1) / 2;
	}

	/**
	 * Deve retornar o indice que representa o filho a esquerda do elemento
	 * indexado pela posicao i no vetor
	 */
	private int left(int i) {
		return (i * 2 + 1);
	}

	/**
	 * Deve retornar o indice que representa o filho a direita do elemento
	 * indexado pela posicao i no vetor
	 */
	private int right(int i) {
		return (i * 2 + 1) + 1;
	}

	@Override
	public boolean isEmpty() {
		return (index == -1);
	}

	@Override
	public T[] toArray() {
		ArrayList<T> resp = new ArrayList<T>();
		for (int i = 0; i <= this.index; i++) {
			resp.add(this.heap[i]);
		}
		return (T[])resp.toArray(new Comparable[0]);
	}

	// ///////////// METODOS A IMPLEMENTAR
	/**
	 * Valida o invariante de uma heap a partir de determinada posicao, que pode
	 * ser a raiz da heap ou de uma sub-heap. O heapify deve usar o comparator
	 * para subir os elementos na heap.
	 */
	private void heapify(int position) {
		int posMaior = position;
		int direita = right(position);
		int esquerda = left(position);
		if (esquerda <= index && comparator.compare(heap[position], heap[esquerda]) < 0){
			posMaior = esquerda;
		}
		if (direita <= index && comparator.compare(heap[position], heap[direita]) < 0){
			if (comparator.compare(heap[esquerda], heap[direita]) < 0) {posMaior = direita;}
		}
		if (position != posMaior) {
			Util.swap(heap,position, posMaior);
			heapify(posMaior);
		}

	}

	@Override
	public void insert(T element) {
		// ESSE CODIGO E PARA A HEAP CRESCER SE FOR PRECISO. NAO MODIFIQUE
		if (index == heap.length - 1) {
			heap = Arrays.copyOf(heap, heap.length + INCREASING_FACTOR);
		}
		// /////////////////////////////////////////////////////////////////
		if (index == -1){
			heap[index + 1] = element;
		}
		else {
			heap[index + 1] = element;
			if (comparator.compare(heap[parent(index + 1)], heap[index + 1]) < 0) {
				recusiveInsert(parent(index + 1), index + 1);
			}
		}
		index++;
	}

	private void recusiveInsert(int pai, int posiccaoAtual){
		if (comparator.compare(heap[pai], heap[posiccaoAtual]) < 0){
				Util.swap(heap, pai, posiccaoAtual);
		}
		if (comparator.compare(heap[parent(pai)], heap[pai]) < 0){
			recusiveInsert(parent(pai), pai);
		}
	}

	@Override
	public void buildHeap(T[] array) {
		for (int i = 0; i < array.length; i++){
			insert(array[i]);
		}
	}

	@Override
	public T extractRootElement() {
		T elemento = heap[0];
		Util.swap(heap,0, index);
		heap[index] = null;
		index--;
		if (index > 0){
			heapify(0);
		}
		return elemento;
	}

	@Override
	public T rootElement() {
		return heap[0];
	}

	@Override
	public T[] heapsort(T[] array) {
		setComparator(new ComparatorMinHeap<>());
		buildHeap(array);
		for (int i = 0; i < array.length; i++){
			array[i] = extractRootElement();
		}
		return array;
	}

	@Override
	public int size() {
		int contador = 0;
		for (T elemento: heap) {
			if (elemento != null){
				contador++;
			}
		}
		return contador;
	}

	public Comparator<T> getComparator() {
		return comparator;
	}

	public void setComparator(Comparator<T> comparator) {
		this.comparator = comparator;
	}

	public T[] getHeap() {
		return heap;
	}

}
