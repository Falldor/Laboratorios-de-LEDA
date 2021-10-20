package sorting.divideAndConquer;

import sorting.AbstractSorting;
import util.Util;

/**
 * Quicksort is based on the divide-and-conquer paradigm. The algorithm chooses
 * a pivot element and rearranges the elements of the interval in such a way
 * that all elements lesser than the pivot go to the left part of the array and
 * all elements greater than the pivot, go to the right part of the array. Then
 * it recursively sorts the left and the right parts. Notice that if the list
 * has length == 1, it is already sorted.
 */
public class QuickSort<T extends Comparable<T>> extends AbstractSorting<T> {

	@Override
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
}
