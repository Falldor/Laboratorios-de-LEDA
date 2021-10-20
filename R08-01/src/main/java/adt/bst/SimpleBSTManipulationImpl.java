package adt.bst;

/**
 * - Esta eh a unica classe que pode ser modificada 
 * @author adalbertocajueiro
 *
 * @param <T>
 */
public class SimpleBSTManipulationImpl<T extends Comparable<T>> implements SimpleBSTManipulation<T> {

	@Override
	public boolean equals(BST<T> tree1, BST<T> tree2) {
		return recursiveEquals(tree1, tree2, true);
	}

	private boolean recursiveEquals(BST<T> t1,BST<T> t2, boolean verificador ){
		verificador = true;
		if (verificador == false){
			return false;
		}
		else{
			if (t1.getRoot().getData().compareTo(t2.getRoot().getData()) == 0) {
				boolean direita = recursiveEquals((BST<T>) t1.getRoot().getRight(),(BST<T>) t2.getRoot().getRight(), true);
				boolean esquerda = recursiveEquals((BST<T>) t1.getRoot().getLeft(),(BST<T>) t2.getRoot().getLeft(), true);
			}
			else{
				verificador = false;
			}
		}
		return verificador;
	}

	@Override
	public boolean isSimilar(BST<T> tree1, BST<T> tree2) {
		if (!tree1.isEmpty() && !tree2.isEmpty()){
			recursiveSimiliar((BST<T>)tree1.getRoot(), (BST<T>)tree2.getRoot());
		}
		return false;
	}
	private boolean recursiveSimiliar(BST<T> t1, BST<T> t2){
		boolean verificador = true;
		if (!t1.getRoot().getRight().isEmpty() && !t2.getRoot().getRight().isEmpty()){
			recursiveSimiliar((BST<T>)t1.getRoot().getRight(), (BST<T>)t2.getRoot().getRight());
		}
		else{
			verificador = false;
		}
		if (!t1.getRoot().getLeft().isEmpty() && !t2.getRoot().getLeft().isEmpty()){
			recursiveSimiliar((BST<T>)t1.getRoot().getLeft(), (BST<T>)t2.getRoot().getLeft());
		}
		else{
			verificador = false;
		}
		return verificador;
	}

	@Override
	public T orderStatistic(BST<T> tree, int k) {
		return recusiveOrder(tree, k, 0);
	}

	private T recusiveOrder(BST<T> tree, int k, int nivel){
		T elemento = null;

		if(k == nivel){
			 elemento = tree.getRoot().getData();
		}
		else{
			recusiveOrder((BST<T>)tree.getRoot().getLeft(), k, nivel + 1);
		}
		return elemento;
	}
}
