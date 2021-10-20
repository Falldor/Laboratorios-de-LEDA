package adt.bst;
import java.util.ArrayList;
import java.util.List;

public class BSTImpl<T extends Comparable<T>> implements BST<T> {

	protected BSTNode<T> root;

	public BSTImpl() {
		root = new BSTNode<T>();
	}

	public BSTNode<T> getRoot() {
		return this.root;
	}

	@Override
	public boolean isEmpty() {
		return root.isEmpty();
	}

	@Override
	public int height() {
		return recusiveHeight(this.getRoot(), -1);
	}

	private int recusiveHeight(BSTNode<T> no, int valorAtual){
		if (!no.isEmpty()){
			int esquerda = recusiveHeight((BSTNode<T>) no.getLeft(), valorAtual+ 1);
			int  direita = recusiveHeight((BSTNode<T>) no.getRight(), valorAtual+ 1);

			valorAtual = Math.max(esquerda, direita);
		}
		return valorAtual;
	}

	@Override
	public BSTNode<T> search(T element) {
		if (!this.isEmpty()){
			return recusiveSearch(this.getRoot(), element);
		}
		else {
			return new BSTNode.Builder().build();
		}
	}

	private BSTNode<T> recusiveSearch(BSTNode<T> no, T element){

		BSTNode<T> noResultante;
		if (no.isEmpty()){
			noResultante = new BSTNode.Builder().build();
		}
		else if (element.compareTo(no.getData()) > 0){
			noResultante = recusiveSearch((BSTNode<T>) no.getRight(), element);
		}
		else if (element.compareTo(no.getData()) < 0) {
			noResultante =  recusiveSearch((BSTNode<T>) no.getLeft(), element);
		}
		else {
			noResultante =  no;
		}

		return noResultante;
	}

	@Override
	public void insert(T element) {
		recusiveInsert(this.getRoot(), element);
	}

	private void recusiveInsert(BSTNode<T> no, T element){
		if (no.isEmpty()){
			no.setData(element);
			no.setLeft(new BSTNode.Builder().parent(no).build());
			no.setRight(new BSTNode.Builder().parent(no).build());
		}else{
			if (element.compareTo(no.getData()) > 0) {
				recusiveInsert((BSTNode) no.getRight(), element);
			}
			else if (element.compareTo(no.getData()) < 0) {
				recusiveInsert((BSTNode) no.getLeft(), element);
			}
		}
	}

	@Override
	public BSTNode<T> maximum() {
		if (isEmpty())
			return null;
		else
			return recursiveMaximum(this.getRoot());
	}

	private BSTNode<T> recursiveMaximum(BSTNode<T> no) {
		if (!no.getRight().isEmpty()) {
			return recursiveMaximum((BSTNode<T>) no.getRight());
		} else
			return no;
	}

	@Override
	public BSTNode<T> minimum() {
		if (isEmpty())
			return null;
		else
			return recursiveMinimum(this.getRoot());
	}

	protected BSTNode<T> recursiveMinimum(BSTNode<T> no) {
		if (!no.getLeft().isEmpty()) {
			return recursiveMinimum((BSTNode<T>) no.getLeft());
		} else
			return no;
	}


	@Override
	public BSTNode<T> sucessor(T element) {
		BSTNode<T> no = this.search(element);
		if (!no.isEmpty()) {
			if (!no.getRight().isEmpty())
				return recursiveMinimum((BSTNode<T>) no.getRight());
			else {
				BSTNode<T> parentNode = (BSTNode<T>) no.getParent();

				while (parentNode != null && parentNode.getData().compareTo(no.getData()) < 0) {
					no = parentNode;
					parentNode = (BSTNode<T>) no.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public BSTNode<T> predecessor(T element) {
		BSTNode<T> no = this.search(element);
		if (!no.isEmpty()) {
			if (!no.getLeft().isEmpty())
				return recursiveMaximum((BSTNode<T>) no.getLeft());
			else {
				BSTNode<T> parentNode = (BSTNode<T>) no.getParent();

				while (parentNode != null && parentNode.getData().compareTo(no.getData()) > 0) {
					no = parentNode;
					parentNode = (BSTNode<T>) no.getParent();
				}
				return parentNode;
			}
		}
		return null;
	}

	@Override
	public void remove(T element) {
		BSTNode<T> no = search(element);

		if (!no.isEmpty()) {
			if (no.isLeaf()) {
				no.setData(null);

			} else if (umFilho(no)) {
				if (no.getParent() != null) {
					if (!no.getParent().getLeft().equals(no)) {
						if (!no.getLeft().isEmpty()) {
							no.getParent().setRight(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setRight(no.getRight());
							no.getRight().setParent(no.getParent());
						}

					} else {
						if (!no.getLeft().isEmpty()) {
							no.getParent().setLeft(no.getLeft());
							no.getLeft().setParent(no.getParent());
						} else {
							no.getParent().setLeft(no.getRight());
							no.getRight().setParent(no.getParent());
						}
					}
				} else {
					if (no.getLeft().isEmpty()) {
						this.root = (BSTNode<T>) no.getRight();
					} else {
						this.root = (BSTNode<T>) no.getLeft();
					}
					this.root.setParent(null);
				}
			} else {
				T sucessorNode = sucessor(no.getData()).getData();
				remove(sucessorNode);
				no.setData(sucessorNode);
			}
		}
	}
	private boolean umFilho(BSTNode<T> no) {

		return ((no.getRight().isEmpty() && !no.getLeft().isEmpty()
				|| no.getLeft().isEmpty() && !no.getRight().isEmpty()));
	}

	@Override
	public T[] preOrder() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			recusivePreOrder(this.getRoot(), aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}
	private void recusivePreOrder(BSTNode<T> no, List<T> array) {

		if (!no.isEmpty()) {
			array.add(no.getData());
			recusivePreOrder((BSTNode<T>) no.getLeft(), array);
			recusivePreOrder((BSTNode<T>) no.getRight(), array);
		}
	}

	@Override
	public T[] order() {

		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			recursiveOrderOrder(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}

	private void recursiveOrderOrder(BSTNode<T> no, List<T> array) {

		if (!no.isEmpty()) {
			recursiveOrderOrder((BSTNode<T>) no.getLeft(), array);
			array.add(no.getData());
			recursiveOrderOrder((BSTNode<T>) no.getRight(), array);
		}
	}


	@Override
	public T[] postOrder() {
		@SuppressWarnings("unchecked")
		T[] arrayResult = (T[]) new Comparable[this.size()];
		List<T> aux = new ArrayList<T>();

		if (!this.isEmpty()) {
			recursivePostOrder(this.root, aux);

			aux.toArray(arrayResult);
		}
		return arrayResult;
	}

	private void recursivePostOrder(BSTNode<T> no, List<T> array) {

		if (!no.isEmpty()) {
			recursivePostOrder((BSTNode<T>) no.getLeft(), array);
			recursivePostOrder((BSTNode<T>) no.getRight(), array);
			array.add(no.getData());
		}
	}
	@Override
	public int size() {
		return size(root);
	}

	private int size(BSTNode<T> node) {
		int result = 0;
		// base case means doing nothing (return 0)
		if (!node.isEmpty()) { // indusctive case
			result = 1 + size((BSTNode<T>) node.getLeft())
					+ size((BSTNode<T>) node.getRight());
		}
		return result;
	}

}
