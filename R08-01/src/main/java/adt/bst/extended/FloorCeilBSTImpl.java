package adt.bst.extended;

import adt.bst.BSTImpl;

/**
 * Note que esta classe estende sua implementacao de BST (BSTImpl).
 * Dependendo do design que voce use, sua BSTImpl precisa ter apenas funcionando
 * corretamente o metodo insert para que voce consiga testar esta classe.
 */
public class FloorCeilBSTImpl extends BSTImpl<Integer> implements FloorCeilBST {

	@Override
	public Integer floor(Integer[] array, double numero) {
		return recursiveFloor(array,(Integer) (int)numero, 0, 0);
	}

	private Integer recursiveFloor(Integer[] array, Integer numero, int pos, int posicaoFloor){
		if (array.length < pos){
			return array[posicaoFloor];
		}
		else{
			if (numero - array[pos] <= numero - array[posicaoFloor] ){
				posicaoFloor = pos;
				recursiveFloor(array,numero,pos + 1,posicaoFloor);
			}
		}
		return null;
	}

	@Override
	public Integer ceil(Integer[] array, double numero) {
		return recursiveCeil(array,(Integer) (int)numero, 0, 0);
	}

	private Integer recursiveCeil(Integer[] array, Integer numero, int pos, int posicaoFloor){
		if (array.length < pos){
			return array[posicaoFloor];
		}
		else{
			if (array[pos] - numero  >= array[posicaoFloor] - numero){
				posicaoFloor = pos;
				recursiveFloor(array,numero,pos + 1,posicaoFloor);
			}
		}
		return null;
	}

}
