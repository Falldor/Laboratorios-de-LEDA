package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
			throw new QueueOverflowException();
		}
		else {
			list.insert(element);
		}
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T elemento = null;
		if (isEmpty()){
			throw new QueueUnderflowException();
		}
		else{
			elemento = list.toArray()[0];
			list.removeFirst();
		}
		return elemento;
	}

	@Override
	public T head() {
		if (!this.isEmpty()) {
			return list.toArray()[0];
		}
		return null;
	}

	@Override
	public boolean isEmpty() {
		return this.list.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}
