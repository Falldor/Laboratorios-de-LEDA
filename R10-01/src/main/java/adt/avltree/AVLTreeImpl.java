package adt.avltree;

import adt.bst.BSTImpl;
import adt.bst.BSTNode;
import adt.bt.Util;

/**
 * 
 * Implementacao de uma arvore AVL
 * A CLASSE AVLTree herda de BSTImpl. VOCE PRECISA SOBRESCREVER A IMPLEMENTACAO
 * DE BSTIMPL RECEBIDA COM SUA IMPLEMENTACAO "OU ENTAO" IMPLEMENTAR OS SEGUITNES
 * METODOS QUE SERAO TESTADOS NA CLASSE AVLTREE:
 *  - insert
 *  - preOrder
 *  - postOrder
 *  - remove
 *  - height
 *  - size
 *
 * @author Claudio Campelo
 *
 * @param <T>
 */
public class AVLTreeImpl<T extends Comparable<T>> extends BSTImpl<T> implements
		AVLTree<T> {

	// TODO Do not forget: you must override the methods insert and remove
	// conveniently.

	// AUXILIARY
	protected int calculateBalance(BSTNode<T> node) {
		int balanco = 0;
		if (!node.isEmpty()) {
			balanco = this.recusiveHeight(node.getLeft()) - recusiveHeight(node.getRight());
		}

		return balanco;
	}

	// AUXILIARY
	protected void rebalance(BSTNode<T> node) {
		int balanco = calculateBalance(node);

		BSTNode<T> auxiliar ;
		if (balanco > 1) {
			BSTNode<T> noEsquerda = (BSTNode<T>) node.getLeft();
			int novoBalanco = calculateBalance(noEsquerda);

			// LL
			if (novoBalanco > 0) {
				auxiliar = Util.rightRotation(node);
			}
			// LR
			else {
				Util.leftRotation(noEsquerda);
				auxiliar  = Util.rightRotation(node);
			}

			if (auxiliar.getParent() == null) {
				super.root = auxiliar ;
			}

		} else if (balanco < -1) {
			BSTNode<T> rightNode = (BSTNode<T>) node.getRight();
			int novoBalanco = calculateBalance(rightNode);

			// RR
			if (novoBalanco < 0) {
				auxiliar  = Util.leftRotation(node);
			}
			// RL
			else {
				Util.rightRotation(rightNode);
				auxiliar  = Util.leftRotation(node);
			}

			if (auxiliar.getParent() == null) {
				super.root = auxiliar ;
			}

		}
	}

	// AUXILIARY
	protected void rebalanceUp(BSTNode<T> node) {
		BSTNode<T> noPai = (BSTNode<T>) node.getParent();
		while (noPai != null) {
			rebalance(noPai);
			noPai = (BSTNode<T>) noPai.getParent();
		}
	}

	@Override
	public void insert(T element) {
		if (element != null) {
			recusiveInsert(this.root, element);
		}
	}

	private void recusiveInsert(BSTNode<T> node, T elemento) {
		if (node.isEmpty()) {
			node.setData(elemento);
			node.setLeft(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
			node.setRight(new BSTNode.Builder<T>().data(null).left(null).right(null).parent(node).build());
		} else {
			if (node.getData().compareTo(elemento) < 0) {
				recusiveInsert((BSTNode<T>) node.getRight(), elemento);
			} else if (node.getData().compareTo(elemento) > 0) {
				recusiveInsert((BSTNode<T>) node.getLeft(), elemento);
			}

			rebalance(node);
		}

	}

	@Override
	public void remove(T element) {
		BSTNode<T> no = super.search(element);
		super.remove(element);
		rebalanceUp(no);
	}
}
