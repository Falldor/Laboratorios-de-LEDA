package adt.linkedList;

import java.util.ArrayList;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {

	}


	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (isEmpty()){
			return 0;
		}
		else {
			return 1 + this.next.size();
		}
	}

	@Override
	public T search(T element) {
		if (isEmpty()){
			return null;
		}
		if (this.data.equals(element)){
			return this.data;
		}
		else {
			return this.next.search(element);
		}
	}

	@Override
	public void insert(T element) {
		if (isEmpty()){
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<>());
		}
		else{
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if (isEmpty()){

		}
		else {
			if (this.data.equals(element)){
				setData(next.getData());
				setNext(next.getNext());
			}
			else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		T[] listaAuxliar = (T[]) new Object[size()];
		arrayRecursivo(listaAuxliar, this, 0);
		return listaAuxliar;
	}

	private void arrayRecursivo(T[] listaObjetos, RecursiveSingleLinkedListImpl no, int i){
		if (!no.isEmpty()){
			listaObjetos[i] = (T) no.getData();
			arrayRecursivo(listaObjetos, no.next, i + 1);
		}
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
