package adt.linkedList;

public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;
	public SingleLinkedListImpl() { this.head = new SingleLinkedListNode<T>();	}


	@Override
	public boolean isEmpty() {
		return head.isNIL();
	}

	@Override
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxiliar = this.head;

		while(!auxiliar.isNIL()){
			auxiliar = auxiliar.getNext();
			size++;
		}

		return size;

	}

	@Override
	public T search(T element) {

		T elemento = null;

		if (!isEmpty()) {

			SingleLinkedListNode<T> auxiliar = this.head;

			while ( !auxiliar.isNIL() && !element.equals(auxiliar.getData())) {
				auxiliar = auxiliar.getNext();
			}

			if (element.equals(auxiliar.getData())) {
				elemento = element;
			}
		}
		return elemento;

	}

	@Override
	public void insert(T element) {
		if (isEmpty()){
			this.head.setData(element);
			this.head.setNext(new SingleLinkedListNode<>());
		}
		else{
			SingleLinkedListNode<T> auxiliar = this.head;

			while (!auxiliar.isNIL()){
				auxiliar = auxiliar.getNext();
			}
			auxiliar.setData(element);
			auxiliar.setNext(new SingleLinkedListNode());
		}
	}

	@Override
	public void remove(T element) {

		SingleLinkedListNode<T> auxiliar = this.head;

		if (element.equals(this.head.getData())){
			setHead(this.head.getNext());
		}
		else {
			while (!auxiliar.getNext().isNIL() && !element.equals(auxiliar.getNext().getData()))  {
				auxiliar = auxiliar.getNext();
			}
			if(element.equals(auxiliar.getNext().getData())){
				auxiliar.setNext(auxiliar.getNext().getNext());
			}
		}
	}

	@Override
	public T[] toArray() {
		SingleLinkedListNode<T> auxiliar = this.head;
		T[] listaElementos =(T[]) new Object[this.size()];
		int i = 0;
		if (!isEmpty()){
			while (!auxiliar.isNIL()){
				listaElementos[i] = auxiliar.getData();
				auxiliar = auxiliar.getNext();
				i++;
			}
		}
		return listaElementos;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
