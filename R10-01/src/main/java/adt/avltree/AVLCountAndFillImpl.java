package adt.avltree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import adt.bst.BSTNode;
import adt.bt.Util;



public class AVLCountAndFillImpl<T extends Comparable<T>> extends
		AVLTreeImpl<T> implements AVLCountAndFill<T> {

	private int LLcounter;
	private int LRcounter;
	private int RRcounter;
	private int RLcounter;

	public AVLCountAndFillImpl() {

	}

	@Override
	public int LLcount() {
		return LLcounter;
	}

	@Override
	public int LRcount() {
		return LRcounter;
	}

	@Override
	public int RRcount() {
		return RRcounter;
	}

	@Override
	public int RLcount() {
		return RLcounter;
	}

	@Override
	public void fillWithoutRebalance(T[] array) {
		List<T[]> lista = new ArrayList<T[]>();
		Arrays.sort(array);
		lista.add(array);
		int index = 0;
		while (index < lista.size()) {
			T[] arrayAuxiliar = lista.get(index);
			int middle = arrayAuxiliar.length / 2;
			T[] Auxiliar1 = Arrays.copyOfRange(arrayAuxiliar, 0, middle);
			T[] Auxiliar2 = Arrays.copyOfRange(arrayAuxiliar, middle + 1, arrayAuxiliar.length);
			if (arrayAuxiliar.length > 1) {
				lista.add(Auxiliar1);
				lista.add(Auxiliar2);
			}
			insert(arrayAuxiliar[middle]);
			index++;
		}
	}

	protected void rebalance(BSTNode<T> node) {
		int balanco = calculateBalance(node);

		BSTNode<T> auxiliar;
		if (balanco > 1) {
			BSTNode<T> leftNode = (BSTNode<T>) node.getLeft();
			int novoBalanco = calculateBalance(leftNode);


			if (novoBalanco  > 0) {
				auxiliar = Util.rightRotation(node);
				LLcounter++;
			}

			else {
				Util.leftRotation(leftNode);
				auxiliar = Util.rightRotation(node);
				LRcounter++;
			}

			if (auxiliar.getParent() == null) {
				super.root = auxiliar;
			}

		} else if (balanco < -1) {
			BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
			int novoBalanco  = calculateBalance(rightNode);


			if (novoBalanco  < 0) {
				auxiliar = Util.leftRotation(node);
				RRcounter++;
			}

			else {
				Util.rightRotation(rightNode);
				auxiliar = Util.leftRotation(node);
				RLcounter++;
			}

			if (auxiliar.getParent() == null) {
				super.root = auxiliar;
			}

		}
	}

}
