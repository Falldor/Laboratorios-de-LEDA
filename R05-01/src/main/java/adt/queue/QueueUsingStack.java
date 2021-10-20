package adt.queue;

import adt.stack.Stack;
import adt.stack.StackImpl;
import adt.stack.StackOverflowException;
import adt.stack.StackUnderflowException;

public class QueueUsingStack<T> implements Queue<T> {

	private Stack<T> stack1;
	private Stack<T> stack2;

	public QueueUsingStack(int size) {
		stack1 = new StackImpl<T>(size);
		stack2 = new StackImpl<T>(size);
	}

	@Override
	public void enqueue(T element) throws QueueOverflowException {
		if (isFull()){
			throw new QueueOverflowException();
		}
		try {
			this.stack1.push(element);
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}


	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		T elemento = null;

		if(this.isEmpty()){
			throw new QueueUnderflowException();
		}
		try {
			while (!stack1.isEmpty()){
				elemento = stack1.pop();
				stack2.push(elemento);
			}
			elemento = stack2.top();
			stack2.pop();
			while (!stack2.isEmpty()){
				stack1.push(stack2.pop());
			}
		} catch (StackUnderflowException e) {
			e.printStackTrace();
		} catch (StackOverflowException e) {
			e.printStackTrace();
		}
		return elemento;
	}

	@Override
	public T head() {
		T elemento = null;

		if(!this.isEmpty()) {
			try {
				while (!stack1.isEmpty()) {
					elemento = stack1.pop();
					stack2.push(elemento);
				}
				elemento = stack2.top();
				while (!stack2.isEmpty()) {
					stack1.push(stack2.pop());
				}
			} catch (StackUnderflowException e) {
				e.printStackTrace();
			} catch (StackOverflowException e) {
				e.printStackTrace();
			}
		}
		return elemento;
	}

	@Override
	public boolean isEmpty() {
		return stack1.isEmpty();
	}

	@Override
	public boolean isFull() {
		return stack1.isFull();
	}

}
