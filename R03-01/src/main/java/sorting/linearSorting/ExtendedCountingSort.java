package sorting.linearSorting;

import sorting.AbstractSorting;

import javax.swing.undo.CannotUndoException;

/**
 * Classe que implementa do Counting Sort vista em sala. Desta vez este
 * algoritmo deve satisfazer os seguitnes requisitos:
 * - Alocar o tamanho minimo possivel para o array de contadores (C)
 * - Ser capaz de ordenar arrays contendo numeros negativos
 */
public class ExtendedCountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0) {
			int tamanhoArray = getMaiorNum(array, leftIndex, rightIndex);
			int menorValor = getMenorNum(array, leftIndex, rightIndex);
			if (menorValor == 0){
				tamanhoArray++;
			}
			int[] frequencia = contadorFrequencia(array,leftIndex,rightIndex,tamanhoArray,menorValor);
			array = countSort(array,leftIndex,rightIndex,frequencia,menorValor);
		}
	}


	private static int getMaiorNum(Integer[] array, int inicioArray, int fimArray) {

		int maiorNum = array[0];

		for (int i = inicioArray; i <= fimArray; i++) {
			if (maiorNum < array[i]) {
				maiorNum = array[i];
			}
		}

		return maiorNum;
	}

	private int getMenorNum(Integer[] array, int inicioArray, int fimArray){
		int menorNum = array[0];

		for (int i = inicioArray; i <= fimArray; i++) {
			if (menorNum > array[i]) {
				menorNum = array[i];
			}
		}

		return menorNum;
	}

	private int[] contadorFrequencia(Integer[] array, int inicioArray, int fimArray, int tamanhoArray, int menorValor){

		int[] C = new int[tamanhoArray];

		for (int i =0; i <= fimArray; i++) {
			int num = array[i];
			int posicao = num - menorValor;
			C[posicao] += 1;
		}

		for (int i = 1; i < C.length; i++) {
			C[i] += C[i - 1];
		}

		return C;
	}

	private Integer[] countSort(Integer[] array, int inicioArray, int fimArray,int[] frequenciaArray,int menorValor){
		Integer[] B = new Integer[array.length];
		for (int i = fimArray; i >= inicioArray; i--) {
			int num = array[i];
			int pos = num - menorValor;

			B[frequenciaArray[pos] - 1] = array[i];
			frequenciaArray[pos] -= 1;
		}

		for (int i = inicioArray; i <= fimArray; i++) {
			array[i] = B[i];
		}

		return array;
	}

}
