package sorting.divideAndConquer.quicksort3;

import sorting.AbstractSorting;
import util.Util;

/**
 * A classe QuickSortMedianOfThree representa uma variação do QuickSort que
 * funciona de forma ligeiramente diferente. Relembre que quando o pivô
 * escolhido divide o array aproximadamente na metade, o QuickSort tem um
 * desempenho perto do ótimo. Para aproximar a entrada do caso ótimo, diversas
 * abordagens podem ser utilizadas. Uma delas é usar a mediana de 3 para achar o
 * pivô. Essa técnica consiste no seguinte:
 * 1. Comparar o elemento mais a esquerda, o central e o mais a direita do intervalo.
 * 2. Ordenar os elementos, tal que: A[left] < A[center] < A[right].
 * 3. Adotar o A[center] como pivô.
 * 4. Colocar o pivô na penúltima posição A[right-1].
 * 5. Aplicar o particionamento considerando o vetor menor, de A[left+1] até A[right-1].
 * 6. Aplicar o algoritmo na particao a esquerda e na particao a direita do pivô.
 */
public class QuickSortMedianOfThree<T extends Comparable<T>> extends AbstractSorting<T> {

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 1) {
			if (leftIndex < rightIndex){
				int pivot = buscaPivot(array, leftIndex, rightIndex);

				sort(array, leftIndex, pivot -1);
				sort(array, pivot + 1, rightIndex);
			}
		}
	}

	private int buscaPivot(T[] array, int inicioArray, int fimArray) {
		int mediana = buscaMediana(array, inicioArray, fimArray);
		Util.swap(array,inicioArray, mediana);
		int i = inicioArray;
		int ancora = inicioArray;

		for(int j = i + 1; j < array.length; j++){
			if (array[ancora].compareTo(array[j]) > 0){
				i++;
				Util.swap(array, i, j);
			}
		}
		Util.swap(array, ancora, i);

		return i;
	}

	private int buscaMediana(T[] array, int i, int j){
		int meio = (i + j)/2;
		int[] elementos = {i,meio,j};

		for(int a = 0; a < elementos.length; a++){
			for (int b = 0; b < elementos.length - 1; b++){
				if(array[elementos[b]].compareTo(array[elementos[b+1]]) > 0){
					int aux = elementos[b];
					elementos[b] = elementos[b+1];
					elementos[b + 1] = aux;
				}
			}
		}
		return elementos[1];
	}
}
