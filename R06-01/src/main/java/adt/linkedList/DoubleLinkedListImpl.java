package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last = new DoubleLinkedListNode<>();


	@Override
	public void insert(T element){
		if (element != null) {
			DoubleLinkedListNode novoNo = new DoubleLinkedListNode();
			novoNo.setData(element);
			novoNo.setPrevious(this.getLast());
			novoNo.setNext(new DoubleLinkedListNode());
			this.getLast().setNext(novoNo);
			if (this.getLast().isNIL()) {
				this.setHead(novoNo);
			}
			this.setLast(novoNo);
		}
	}

	@Override
	public void insertFirst(T element) {
		if (element != null) {
			DoubleLinkedListNode novoNo = new DoubleLinkedListNode();
			novoNo.setData(element);
			novoNo.setPrevious(new DoubleLinkedListNode());
			novoNo.setNext(this.getHead());
			if(this.getLast().isNIL()){
				this.setLast(novoNo);
			}
			this.setHead(novoNo);
		}
	}

	@Override
	public void removeFirst() {
		if (!getHead().isNIL()){
			this.setHead(this.getHead().getNext());

			if (this.getHead().isNIL()){
				this.setLast((DoubleLinkedListNode<T>) this.getHead());
			}

			((DoubleLinkedListNode) this.getHead()).setPrevious(new DoubleLinkedListNode());

		}
	}

	@Override
	public void removeLast() {
		this.getLast().getPrevious().setNext(new DoubleLinkedListNode<>());
		setLast(getLast().getPrevious());
	}

	@Override
	public T search(T element) {

		DoubleLinkedListNode<T> auxHead = (DoubleLinkedListNode<T>) this.getHead();
		DoubleLinkedListNode<T> auxLast = this.getLast();
		T elemento = null;

		while (!auxHead.getNext().equals(last) && !auxHead.equals(last) && !auxHead.getData().equals(element) && !auxLast.getData().equals(element)){
			auxHead = (DoubleLinkedListNode<T>) auxHead.getNext();
			auxLast = auxLast.getPrevious();
		}

		if (auxHead.getData().equals(element)){
			elemento = auxHead.getData();
		}
		if (auxLast.getData().equals(element)){
			elemento = auxLast.getData();

		}
		return elemento;
	}
	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
