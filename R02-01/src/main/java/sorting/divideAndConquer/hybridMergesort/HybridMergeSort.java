package sorting.divideAndConquer.hybridMergesort;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * A classe HybridMergeSort representa a implementação de uma variação do
 * MergeSort que pode fazer uso do InsertionSort (um algoritmo híbrido) da
 * seguinte forma: o MergeSort é aplicado a entradas maiores a um determinado
 * limite. Caso a entrada tenha tamanho menor ou igual ao limite o algoritmo usa
 * o InsertionSort.
 * 
 * A implementação híbrida deve considerar os seguintes detalhes:
 * - Ter contadores das quantidades de MergeSorts e InsertionSorts aplicados, de forma
 *   que essa informação possa ser capturada pelo teste.
 * - A cada chamado do método de sort(T[] array) esses contadores são resetados. E a cada chamada
 *   interna de um merge ou insertion, os contadores MERGESORT_APPLICATIONS e
 *   INSERTIONSORT_APPLICATIONS são incrementados.
 * - O InsertionSort utilizado no algoritmo híbrido deve ser in-place.
 */
public class HybridMergeSort<T extends Comparable<T>> extends
		AbstractSorting<T> {

	/**
	 * For inputs with size less or equal to this value, the insertionsort
	 * algorithm will be used instead of the mergesort.
	 */
	public static final int SIZE_LIMIT = 4;

	protected static int MERGESORT_APPLICATIONS = 0;
	protected static int INSERTIONSORT_APPLICATIONS = 0;

	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 1){
			if (leftIndex < rightIndex){
				if (array.length > 4){
					int meio = (leftIndex + rightIndex) / 2;

					sort(array, leftIndex, meio);
					sort(array, meio + 1, rightIndex);

					merge(array, leftIndex, meio, rightIndex);
				}
				else {
					for(int i = leftIndex; i < rightIndex; i++){
						if (array[i].compareTo(array[i + 1]) > 0) {
							int j = i;
							Util.swap(array, i , i + 1);
							while (j > 0 && array[j - 1].compareTo(array[j]) > 0){
								Util.swap(array, j - 1, j);
								j--;
							}
						}
					}
				}

			}



		}
	}
	private void merge(T[] array, int inicioArray, int meioArray, int fimArray){
		MERGESORT_APPLICATIONS++;
		T[] arraySuporte = Arrays.copyOf(array, array.length);
		int segundoInicio = meioArray + 1;
		int contador = inicioArray;

		while(inicioArray <= meioArray && segundoInicio <= fimArray) {

			if (arraySuporte[inicioArray].compareTo(arraySuporte[segundoInicio]) <= 0){
				array[contador] = arraySuporte[inicioArray];
				inicioArray++;
			}
			else{
				array[contador] = arraySuporte[segundoInicio];
				segundoInicio++;
			}
			contador++;
		}
		while(inicioArray <= meioArray){
			array[contador] = arraySuporte[inicioArray];
			inicioArray++;
			contador++;
		}

		while (segundoInicio <= fimArray){
			array[contador] = arraySuporte[segundoInicio];
			segundoInicio++;
			contador++;
		}
	}
}
