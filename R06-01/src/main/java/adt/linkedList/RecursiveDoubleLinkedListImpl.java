package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {

	}

	@Override
	public void insert(T element) {
		if(this.size() > 0){
			if (this.isEmpty()){
				this.setData(element);
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
			}
			else{
				((RecursiveDoubleLinkedListImpl)this.next).setPrevious(this);
				this.getNext().insert(element);
			}
		}
		else{
			this.setData(element);
			this.setNext(new RecursiveDoubleLinkedListImpl<>());
			this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
		}
	}


	@Override
	public void insertFirst(T element) {
		if(!this.isEmpty()){
			RecursiveDoubleLinkedListImpl novoNo = new RecursiveDoubleLinkedListImpl();
			novoNo.setData(this.getData());
			novoNo.setNext(this.getNext());
			novoNo.setPrevious(this);
			this.setData(element);
			this.setNext(novoNo);
			this.setPrevious(new RecursiveDoubleLinkedListImpl<>());
		}
		else{this.insert(element);}
	}

	@Override
	public void removeFirst() {
		this.setData(this.getNext().getData());
		this.setNext(this.getNext().getNext());
		((RecursiveDoubleLinkedListImpl) this.getNext()).setPrevious(new RecursiveDoubleLinkedListImpl());
	}

	@Override
	public void removeLast() {
		if (this.getNext().isEmpty()) {
			this.setData(null);
		} else{
			((RecursiveDoubleLinkedListImpl) this.getNext()).removeLast();
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
