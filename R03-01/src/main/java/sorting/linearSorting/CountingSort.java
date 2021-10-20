package sorting.linearSorting;

import sorting.AbstractSorting;

/**
 * Classe que implementa a estratégia de Counting Sort vista em sala. Procure
 * evitar desperdicio de memoria alocando o array de contadores com o tamanho
 * sendo o máximo inteiro presente no array a ser ordenado.
 *
 * Voce pode assumir que o maior inteiro armazenado não chega a 100.
 *
 */
public class CountingSort extends AbstractSorting<Integer> {

	@Override
	public void sort(Integer[] array, int leftIndex, int rightIndex) {
		if (array.length > 0) {

			int tamanhoArray = getMaiorNum(array,leftIndex,rightIndex);
			int[] frequencia = contadorFrequencia(array,leftIndex,rightIndex,tamanhoArray);
			array = countSort(array,leftIndex,rightIndex,frequencia);

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

	private int[] contadorFrequencia(Integer[] array, int inicioArray, int fimArray, int tamanhoArray){

		int[] C = new int[tamanhoArray];

		for (int i =0; i <= fimArray; i++) {
			if (array[i] == 0) {
				C[0] += 1;
			} else {
				C[array[i] - 1] += 1;
			}
		}

		for (int i = 1; i < C.length; i++) {
			C[i] += C[i - 1];
		}

		return C;
	}

	private Integer[] countSort(Integer[] array, int inicioArray, int fimArray,int[] frequenciaArray){
		Integer[] B = new Integer[array.length];
		for (int i = fimArray; i >= inicioArray; i--) {
			if (array[i] == 0) {
				B[0] = 0;
			} else {
				B[frequenciaArray[array[i] - 1] - 1] = array[i];
				frequenciaArray[array[i] - 1] -= 1;
			}

		}

		for (int i = inicioArray; i <= fimArray; i++) {
			array[i] = B[i];
		}

		return array;
	}
}
