package adt.stack;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.DoubleLinkedListNode;

public class StackDoubleLinkedListImpl<T> implements Stack<T> {

	protected DoubleLinkedList<T> top;
	protected int size;

	public StackDoubleLinkedListImpl(int size) {
		this.size = size;
		this.top = new DoubleLinkedListImpl<T>();
	}

	@Override
	public void push(T element) throws StackOverflowException {
		if (isFull()){
			throw new StackOverflowException();
		}
		this.top.insert(element);

	}

	@Override
	public T pop() throws StackUnderflowException {
		T elemento = null;
		if (isEmpty()){
			throw new StackUnderflowException();
		}
		else {
			elemento = ((DoubleLinkedListImpl<T>)this.top).getLast().getData();
			this.top.removeLast();
		}
		return elemento;
	}

	@Override
	public T top() {
		T elemento = null;
		if (!isEmpty()){
			elemento = ((DoubleLinkedListImpl<T>)this.top).getLast().getData();
		}
		return elemento;
	}

	@Override
	public boolean isEmpty() {
		return top.size() == 0;
	}

	@Override
	public boolean isFull() {
		return this.top.size() == size;
	}

}
