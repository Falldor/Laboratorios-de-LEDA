package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

import java.util.Arrays;

/**
 * Merge sort is based on the divide-and-conquer paradigm. The algorithm
 * consists of recursively dividing the unsorted list in the middle, sorting
 * each sublist, and then merging them into one single sorted list. Notice that
 * if the list has length == 1, it is already sorted.
 */
public class MergeSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
	public void sort(T[] array, int leftIndex, int rightIndex) {
		if (array != null && array.length > 1){
			if (leftIndex < rightIndex) {
				int meio = (leftIndex + rightIndex) / 2;

				sort(array, leftIndex, meio);
				sort(array, meio + 1, rightIndex);

				merge(array, leftIndex, meio, rightIndex);
			}
		}

	}

	private void merge(T[] array, int inicioArray, int meioArray, int fimArray){
		T[] arraySuporte =Arrays.copyOf(array, array.length);
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
